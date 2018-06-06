package Compiler2018.FrontEnd.IRBuilder;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Immediate;
import Compiler2018.IR.IRValue.Label;
import Compiler2018.IR.IRValue.Register;
import Compiler2018.Symbol.ClassTable;
import Compiler2018.Symbol.TopTable;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IRInstructionBuilder implements IASTVistor {
    private final IRProgram irProgram;
    private BasicBlock globalInitAfter;
    private IRClass currentClass;
    private IRFunction currentFunction;
    private BasicBlock currentBB;
    private Stack<BasicBlock> loopStepBBStack = new Stack<>();
    private Stack<BasicBlock> loopAfterBBStack = new Stack<>();

    public IRInstructionBuilder(IRProgram irProgram) {
        this.irProgram = irProgram;
    }

    @Override
    public void visit(Program node) {
        // Global Variable Init
        currentFunction = irProgram.getIRFunction("_main");
        currentBB = currentFunction.getStartBlock();
        currentFunction.putBasicBlock(currentBB);
        node.getSections().stream().filter(x -> x instanceof VarDecl).forEach(x -> x.accept(this));
        globalInitAfter = currentBB;
        currentBB = null;
        // Function implementation
        node.getSections().stream().filter(x -> !(x instanceof VarDecl)).forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {
        currentClass = irProgram.getIRClass(node.getName());
        node.getItems().stream().filter(x -> !(x instanceof ClassVarDecl)).forEach(x -> x.accept(this));
        currentClass = null;
    }

    @Override
    public void visit(FuncDecl node) {
        currentFunction = irProgram.getIRFunction(node.getFuncSymbol().getProcessedName());
        if (node.getName().equals("main")) {
            currentBB = globalInitAfter;
        } else {
            currentBB = currentFunction.getStartBlock();
        }
        currentFunction.putBasicBlock(currentBB);
        node.getBlock().accept(this);
        // default ret may be redundant
        Register ret = new Register();
        currentBB.addTail(new MoveU(currentBB, ret, new Immediate(0)));
        currentBB.endWith(new Ret(currentBB, ret));

        currentFunction.setEndBlock(currentBB);
        currentBB = null;
        currentFunction = null;
    }

    @Override
    public void visit(VarDecl node) {
        BasicBlock trueBlock = new BasicBlock(currentFunction, "Init_true");
        BasicBlock falseBlock = new BasicBlock(currentFunction, "Init_false");
        if (node.getInit() != null && isLogicalExpr(node.getInit())) {
            currentFunction.putBasicBlock(trueBlock);
            currentFunction.putBasicBlock(falseBlock);
            node.getInit().setIfTrue(trueBlock);
            node.getInit().setIfFalse(falseBlock);
        }   // shortcut

        if (node.getVarSymbol().getBelongTable() instanceof TopTable) { // Global Init
            Label label = new Label(node.getName());
            irProgram.putGlobalVar(node.getName(), new StaticData(label, null));

            if (node.getInit() != null) {
                node.getInit().accept(this);
                Register init = new Register();
                currentBB.addTail(new MoveU(currentBB, init, label));
                if (isLogicalExpr(node.getInit())) {
                    BasicBlock mergeBlock = new BasicBlock(currentFunction, "Init_merge");
                    trueBlock.endWith(new Jump(trueBlock, mergeBlock));
                    falseBlock.endWith(new Jump(falseBlock, mergeBlock));
                    currentFunction.putBasicBlock(mergeBlock);
                    currentBB = mergeBlock;
                    currentBB.addTail(new Move(currentBB, init, true, node.getInit().getRegister(), false)); // FIXME
                } else {
                    currentBB.addTail(new Move(currentBB, init, true, node.getInit().getRegister(), node.getInit().isDataInMem()));
                }
            }

        } else { // BlockTabel Variable
            if (node.getInit() != null) {
                node.getInit().accept(this);
                if (isLogicalExpr(node.getInit())) {
                    BasicBlock mergeBlock = new BasicBlock(currentFunction, "Init_merge");
                    trueBlock.endWith(new Jump(trueBlock, mergeBlock));
                    falseBlock.endWith(new Jump(falseBlock, mergeBlock));
                    currentFunction.putBasicBlock(mergeBlock);
                    currentBB = mergeBlock;
                    currentBB.addTail(new Move(currentBB, node.getVarSymbol().getRegister(), false, node.getInit().getRegister(), false)); // FIXME
                } else {
                    currentBB.addTail(new Move(currentBB, node.getVarSymbol().getRegister(), false, node.getInit().getRegister(), node.getInit().isDataInMem()));
                }
            }
        }
    }

    @Override
    public void visit(ClassVarDecl node) {
        // Do Nothing
    }

    @Override
    public void visit(ClassCstrDecl node) {
        // parameters already processed
        currentFunction = irProgram.getIRFunction(node.getCstrSymbol().getProcessedName());
        currentBB = currentFunction.getStartBlock();
        currentFunction.putBasicBlock(currentBB);
        node.getBlock().accept(this);
        // default ret may be redundant
        currentBB.endWith(new Ret(currentBB, null));
        currentFunction.setEndBlock(currentBB);
        currentBB = null;
        currentFunction = null;
    }

    @Override
    public void visit(ClassFuncDecl node) {
        node.getDecl().accept(this);
    }

    @Override
    public void visit(BlockStmt node) {
        node.getStmts().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(VarDeclStmt node) {
        node.getDecl().accept(this);
    }

    @Override
    public void visit(BranchStmt node) {
        BasicBlock BBTrue = new BasicBlock(currentFunction, "if_true");
        BasicBlock BBFalse = node.getElseStmt() != null ? new BasicBlock(currentFunction, "if_false") : null;
        BasicBlock BBMerge = new BasicBlock(currentFunction, "if_merge");

        node.getCond().setIfTrue(BBTrue);
        node.getCond().setIfFalse(BBFalse != null ? BBFalse : BBMerge);

        node.getCond().accept(this);

        Register trueReg = new Register();
        currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));
        Compare compare = new Compare(currentBB, Compare.CompareOp.EQ, trueReg, false, node.getCond().getRegister(), node.getCond().isDataInMem());
        currentBB.addTail(compare);
        currentBB.endWith(new Branch(currentBB, compare, node.getCond().getIfTrue(), node.getCond().getIfFalse()));

        currentBB = BBTrue;

        currentFunction.putBasicBlock(currentBB);
        node.getIfStmt().accept(this);
        currentBB.endWith(new Jump(currentBB, BBMerge));    // FIXME assert !end

        if (BBFalse != null) {
            currentBB = BBFalse;
            currentFunction.putBasicBlock(currentBB);
            node.getElseStmt().accept(this);
            currentBB.endWith(new Jump(currentBB, BBMerge));
        }

        currentBB = BBMerge;
        currentFunction.putBasicBlock(currentBB);
    }

    @Override
    public void visit(ExprStmt node) {
        node.getExpr().accept(this);
    }

    @Override
    public void visit(EmptyStmt node) {

    }

    @Override
    public void visit(ReturnStmt node) {
        Register ret = new Register();
        if (node.getExpr() != null) {
            BasicBlock trueBlock = new BasicBlock(currentFunction, "Ret_true");
            BasicBlock falseBlock = new BasicBlock(currentFunction, "Ret_false");

            if (isLogicalExpr(node.getExpr())) {
                currentFunction.putBasicBlock(trueBlock);
                currentFunction.putBasicBlock(falseBlock);
                node.getExpr().setIfTrue(trueBlock);
                node.getExpr().setIfFalse(falseBlock);
            }
            node.getExpr().accept(this);
            if (isLogicalExpr(node.getExpr())) {
                BasicBlock mergeBlock = new BasicBlock(currentFunction, "Ret_merge");
                trueBlock.endWith(new Jump(trueBlock, mergeBlock));
                falseBlock.endWith(new Jump(falseBlock, mergeBlock));
                currentFunction.putBasicBlock(mergeBlock);
                currentBB = mergeBlock;
                currentBB.addTail(new Move(currentBB, ret, false, node.getExpr().getRegister(), false)); // FIXME
                currentBB.endWith(new Ret(currentBB, ret));
            } else {
                currentBB.addTail(new Move(currentBB, ret, false, node.getExpr().getRegister(), node.getExpr().isDataInMem()));
                currentBB.endWith(new Ret(currentBB, ret));
            }
        } else {
            currentBB.endWith(new Ret(currentBB, null));
        }
    }

    @Override
    public void visit(BreakStmt node) {
        currentBB.addTail(new Jump(currentBB, loopAfterBBStack.peek()));
    }

    @Override
    public void visit(ContinueStmt node) {
        currentBB.addTail(new Jump(currentBB, loopStepBBStack.peek()));
    }

    @Override
    public void visit(ForStmt node) {
        BasicBlock BBCond = new BasicBlock(currentFunction, "for_cond");
        BasicBlock BBLoop = new BasicBlock(currentFunction, "for_loop");
        BasicBlock BBStep = new BasicBlock(currentFunction, "for_step");
        BasicBlock BBAfter = new BasicBlock(currentFunction, "for_after");

        if(node.getCond() == null) BBCond = BBLoop;
        if(node.getStep() == null) BBStep = BBCond;

        loopStepBBStack.push(BBStep);
        loopAfterBBStack.push(BBAfter);

        if (node.getInit() != null) {
            node.getInit().accept(this);
        }

        currentBB.endWith(new Jump(currentBB, BBCond));

        if (node.getCond() != null) {
            currentBB = BBCond;
            currentFunction.putBasicBlock(currentBB);
            node.getCond().setIfTrue(BBLoop);
            node.getCond().setIfFalse(BBAfter);
            node.getCond().accept(this);

            Register trueReg = new Register();
            currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));
            Compare compare = new Compare(currentBB, Compare.CompareOp.EQ, trueReg, false, node.getCond().getRegister(), node.getCond().isDataInMem());
            currentBB.addTail(compare);
            currentBB.addTail(new Branch(currentBB, compare, node.getCond().getIfTrue(), node.getCond().getIfFalse()));
        }

        currentBB = BBLoop;
        currentFunction.putBasicBlock(currentBB);
        node.getStmt().accept(this);
        currentBB.endWith(new Jump(currentBB, BBStep));

        if (node.getStep() != null) {
            currentBB = BBStep;
            currentFunction.putBasicBlock(currentBB);
            node.getStep().accept(this);
            currentBB.endWith(new Jump(currentBB, BBCond));
        }

        loopStepBBStack.pop();
        loopAfterBBStack.pop();
        currentBB = BBAfter;
        currentFunction.putBasicBlock(currentBB);
    }

    @Override
    public void visit(WhileStmt node) {
        BasicBlock BBCond = new BasicBlock(currentFunction, "while_cond");
        BasicBlock BBLoop = new BasicBlock(currentFunction, "while_loop");
        BasicBlock BBAfter = new BasicBlock(currentFunction, "while_after");

        loopStepBBStack.push(BBCond);
        loopAfterBBStack.push(BBAfter);

        currentBB = BBCond;
        currentFunction.putBasicBlock(currentBB);
        node.getCond().setIfTrue(BBLoop);
        node.getCond().setIfFalse(BBAfter);
        node.getCond().accept(this);

        Register trueReg = new Register();
        currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));
        Compare compare = new Compare(currentBB, Compare.CompareOp.EQ, trueReg, false, node.getCond().getRegister(), node.getCond().isDataInMem());
        currentBB.addTail(compare);
        currentBB.addTail(new Branch(currentBB, compare, node.getCond().getIfTrue(), node.getCond().getIfFalse()));

        currentBB = BBLoop;
        currentFunction.putBasicBlock(currentBB);
        node.getStmt().accept(this);
        currentBB.endWith(new Jump(currentBB, BBCond));

        loopStepBBStack.pop();
        loopAfterBBStack.pop();
        currentBB = BBAfter;
        currentFunction.putBasicBlock(currentBB);
    }

    @Override
    public void visit(ClassType node) {
        // Do Nothing
    }

    @Override
    public void visit(FunctionCall node) {  // FIXME size
        node.getName().accept(this);
        node.getParameters().forEach(x -> x.accept(this));
        List<Register> parameterList = new LinkedList<>();
        if (node.getName() instanceof MemberAcess && node.getName().getFunc() != null) {
            parameterList.add(node.getName().getRegister());
        }
        node.getParameters().forEach(x -> {
            if (x.isDataInMem()) {
                Register param = new Register();
                currentBB.addTail(new Move(currentBB, param, false, x.getRegister(), true));
                parameterList.add(param);
            } else {
                parameterList.add(x.getRegister());
            }
        });

        if (node.getProcessedName().equals("_size")) {
            node.setRegister(node.getName().getRegister());
            return;
        }

        Register ret;
        if (node.getType().getBaseType().equals("void")) {
            ret = null;
        } else {
            ret = new Register();
        }
        Call.Builder builder = new Call.Builder();
        builder.setBasicBlock(currentBB);
        // restore builtin name
        switch (node.getProcessedName()) {
            case "_print":
                builder.setProcessedName("print");
                break;
            case "_println":
                builder.setProcessedName("println");
                break;
            case "_getString":
                builder.setProcessedName("getString");
                break;
            case "_getInt":
                builder.setProcessedName("getInt");
                break;
            case "_toString":
                builder.setProcessedName("toString");
                break;
            case "_Nstringlength":
                builder.setProcessedName("length");
                break;
            case "_Nstringsubstring":
                builder.setProcessedName("substring");
                break;
            case "_NstringparseInt":
                builder.setProcessedName("parseInt");
                break;
            case "_Nstringord":
                builder.setProcessedName("ord");
                break;
//            case "_size":
//                builder.setProcessedName("size");
//                break;
            default:
                builder.setProcessedName(node.getProcessedName());
                break;
        }
        builder.setRet(ret);
        parameterList.forEach(builder::addArgs);

        currentBB.addTail(builder.build());
        node.setRegister(ret);
    }

    @Override
    public void visit(ArrayAcess node) {
        node.getArray().accept(this);
        node.getSubscript().accept(this);

        Register array;
        if (node.getArray().isDataInMem()) {
            array = new Register();
            currentBB.addTail(new Move(currentBB, array, false, node.getArray().getRegister(), true));
        } else {
            array = node.getArray().getRegister();
        }

        Register subscript;
        if (node.getSubscript().isDataInMem()) {
            subscript = new Register();
            currentBB.addTail(new Move(currentBB, subscript, false, node.getSubscript().getRegister(), true));
        } else {
            subscript = node.getSubscript().getRegister();
        }
        Register dest = new Register();
        currentBB.addTail(new Lea(currentBB, dest, array, subscript, 8));

        node.setRegister(dest);
        node.setDataInMem(true);
    }

    @Override
    public void visit(MemberAcess node) {
        node.getExpr().accept(this);

        Register base;
        if (node.getExpr().isDataInMem()) {
            base = new Register();
            currentBB.addTail(new Move(currentBB, base, false, node.getExpr().getRegister(), true));
        } else {
            base = node.getExpr().getRegister();
        }

        if (node.getName().equals("size")) {
            Call.Builder builder = new Call.Builder();
            Register ret = new Register();
            builder.setRet(ret);
            builder.setProcessedName("size");
            builder.addArgs(base);
            builder.setBasicBlock(currentBB);
            currentBB.addTail(builder.build());
            node.setRegister(ret);
            return;
        }

        if (node.getFunc() == null) {
            Integer heapOffset = irProgram.getIRClass(node.getExpr().getType().getBaseType()).getHeapOffset(node.getName());
            Register dest = new Register();
            currentBB.addTail(new Lea(currentBB, dest, base, null, heapOffset));
            node.setRegister(dest);
            node.setDataInMem(true);
        } else { // func
            node.setRegister(base);
        }
    }

    @Override
    public void visit(NewExpr node) {
        node.getNewObject().accept(this);
        node.setRegister(node.getNewObject().getRegister());
    }

    private void processLogicalNot(UnaryExpr node) {
        node.setRegister(new Register());


//        BasicBlock mergeBLock = new BasicBlock(currentFunction, "merge");
//        currentFunction.putBasicBlock(mergeBLock);
        // preserve short cut
        node.getExpr().setIfTrue(node.getIfFalse());
        node.getExpr().setIfFalse(node.getIfTrue());
        node.getExpr().accept(this);

//        node.getExpr().getIfTrue().endWith(new Jump(node.getExpr().getIfTrue(), mergeBLock));
//        node.getExpr().getIfFalse().endWith(new Jump(node.getExpr().getIfFalse(), mergeBLock));

//        currentBB = mergeBLock;
//        currentFunction.putBasicBlock(mergeBLock);
        Register trueReg = new Register();
        currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));


        // Pre
        currentBB.addTail(new BinaryCalc(currentBB, BinaryCalc.BinaryOp.XOR, node.getRegister(), node.getExpr().getRegister(), node.getExpr().isDataInMem(), trueReg, false));


//        Compare cmp = new Compare(currentBB, Compare.CompareOp.EQ, node.getExpr().getRegister(), node.getExpr().isDataInMem(), trueReg, false);
//        currentBB.addTail(cmp);
//        currentBB.endWith(new Branch(currentBB, cmp, node.getIfTrue(), node.getIfFalse()));
//
////        currentBB.addTail(new Set(currentBB, node.getRegister(), ));


        // Post
        node.getIfTrue().addTail(new MoveU(node.getIfTrue(), trueReg, new Immediate(1)));
        node.getIfTrue().addTail(new BinaryCalc(node.getIfTrue(), BinaryCalc.BinaryOp.XOR, node.getRegister(), node.getExpr().getRegister(), node.getExpr().isDataInMem(), trueReg, false));
        node.getIfFalse().addTail(new MoveU(node.getIfFalse(), trueReg, new Immediate(1)));
        node.getIfFalse().addTail(new BinaryCalc(node.getIfFalse(), BinaryCalc.BinaryOp.XOR, node.getRegister(), node.getExpr().getRegister(), node.getExpr().isDataInMem(), trueReg, false));

//        node.getIfFalse().endWith(new Jump(currentBB, mergeBLock));
//        node.getIfFalse().endWith(new Jump(currentBB, mergeBLock));
        //        BasicBlock block = new BasicBlock(currentFunction, "not_end");
//        currentFunction.putBasicBlock(block);
//        node.getIfTrue().endWith(new Jump(node.getIfTrue(), block));
//        node.getIfFalse().endWith(new Jump(node.getIfFalse(), block));
//        currentBB = block;

    }

    private void processPos(UnaryExpr node) {
        node.getExpr().accept(this);
        node.setRegister(node.getExpr().getRegister());
        node.setDataInMem(node.getExpr().isDataInMem());    // Lvalue
    }

    private void processIntUnary(UnaryExpr node) {
        node.getExpr().accept(this);

        UnaryCalc.UnaryOp op;
        if (node.getOp() == UnaryExpr.UnaryOp.NEG) { // NEG
            op = UnaryCalc.UnaryOp.NEG;
        } else { // BITWISE NOT
            op = UnaryCalc.UnaryOp.BITWISE_NOT;
        }
        Register dest = new Register();

        currentBB.addTail(new UnaryCalc(currentBB, op, dest, node.getExpr().getRegister(), node.getExpr().isDataInMem()));

        node.setRegister(dest);
    }

    private void processPrefix(UnaryExpr node) {
        node.getExpr().accept(this);

        if (node.getOp() == UnaryExpr.UnaryOp.PREFIX_INC) {
            currentBB.addTail(new SelfInc(currentBB, node.getExpr().getRegister(), node.getExpr().isDataInMem(), 1));
        } else {
            currentBB.addTail(new SelfInc(currentBB, node.getExpr().getRegister(), node.getExpr().isDataInMem(), -1));
        }

        node.setRegister(node.getExpr().getRegister());
        node.setDataInMem(node.getExpr().isDataInMem());
    }

    private void processPosfix(UnaryExpr node) {
        node.getExpr().accept(this);

        Register save = new Register();

        currentBB.addTail(new Move(currentBB, save, false, node.getExpr().getRegister(), node.getExpr().isDataInMem()));
        if (node.getOp() == UnaryExpr.UnaryOp.POSTFIX_INC) {
            currentBB.addTail(new SelfInc(currentBB, node.getExpr().getRegister(), node.getExpr().isDataInMem(), 1));
        } else {
            currentBB.addTail(new SelfInc(currentBB, node.getExpr().getRegister(), node.getExpr().isDataInMem(), -1));
        }

        node.setRegister(save);
    }

    @Override
    public void visit(UnaryExpr node) {
        switch (node.getOp()) {
            case LOGICAL_NOT:
                processLogicalNot(node);
                break;
            case POS:
                processPos(node);
                break;
            case NEG:
            case BITWISE_NOT:
                processIntUnary(node);
                break;
            case PREFIX_INC:
            case PREFIX_DEC:
                processPrefix(node);
                break;
            case POSTFIX_INC:
            case POSTFIX_DEC:
                processPosfix(node);
                break;
        }
    }

    private void processAssign(BinaryExpr node) { // =
        BasicBlock trueBlock = new BasicBlock(currentFunction, "Assign_true");
        BasicBlock falseBlock = new BasicBlock(currentFunction, "Assign_false");

        if (isLogicalExpr(node.getRhs())) {
            currentFunction.putBasicBlock(trueBlock);
            currentFunction.putBasicBlock(falseBlock);
            node.getRhs().setIfTrue(trueBlock);
            node.getRhs().setIfFalse(falseBlock);
        }   // shortcut

        node.getRhs().accept(this);
        node.getLhs().accept(this);

        if (isLogicalExpr(node.getRhs())) {
            BasicBlock mergeBlock = new BasicBlock(currentFunction, "Ret_merge");
            trueBlock.endWith(new Jump(trueBlock, mergeBlock));
            falseBlock.endWith(new Jump(falseBlock, mergeBlock));
            currentFunction.putBasicBlock(mergeBlock);
            currentBB = mergeBlock;
            currentBB.addTail(new Move(currentBB, node.getLhs().getRegister(), node.getLhs().isDataInMem(), node.getRhs().getRegister(), false)); // FIXME
        } else {
            currentBB.addTail(new Move(currentBB, node.getLhs().getRegister(), node.getLhs().isDataInMem(), node.getRhs().getRegister(), node.getRhs().isDataInMem()));
        }

        node.setRegister(node.getLhs().getRegister());
        node.setDataInMem(node.getLhs().isDataInMem());
    }

    private void processIntCompare(BinaryExpr node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);

        Compare.CompareOp cond;
        switch (node.getOp()) {
            case EQ:
                cond = Compare.CompareOp.EQ;
                break;
            case NE:
                cond = Compare.CompareOp.NE;
                break;
            case LT:
                cond = Compare.CompareOp.LT;
                break;
            case GT:
                cond = Compare.CompareOp.GT;
                break;
            case LE:
                cond = Compare.CompareOp.LE;
                break;
            case GE:
                cond = Compare.CompareOp.GE;
                break;
            default:
                throw new RuntimeException("cond init error.");
        }

        Register dest = new Register();  // assert false
        Compare compare = new Compare(currentBB, cond, node.getLhs().getRegister(), node.getLhs().isDataInMem(), node.getRhs().getRegister(), node.getRhs().isDataInMem());
        currentBB.addTail(compare);
        currentBB.addTail(new Set(currentBB, cond, dest, false));

        node.setRegister(dest);



//        if (node.getIfTrue() != null) {
//            currentCond = compare;
//        } else {
//            currentBB.addTail(compare);
//        }
    }

    private void processLogicalBinary(BinaryExpr node) {
        node.setRegister(new Register());
        // lhs
        if (node.getOp() == BinaryExpr.BinaryOp.LOGICAL_AND) { // LOGICAL_AND
            AbstractExpr lhs = node.getLhs();
            lhs.setIfTrue(new BasicBlock(currentFunction, "and_lhs_true"));
            lhs.setIfFalse(node.getIfFalse());
            lhs.accept(this);

            Register trueReg = new Register();
            currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));
            Compare cmp = new Compare(currentBB, Compare.CompareOp.EQ, lhs.getRegister(), lhs.isDataInMem(), trueReg, false);
            currentBB.addTail(cmp);
            currentBB.endWith(new Branch(currentBB, cmp, lhs.getIfTrue(), lhs.getIfFalse()));
            lhs.getIfFalse().addTail(new MoveU(lhs.getIfFalse(), node.getRegister(), new Immediate(0)));

            currentBB = lhs.getIfTrue();
            currentFunction.putBasicBlock(currentBB);
        } else { // LOGICAL_OR
            AbstractExpr lhs = node.getLhs();
            lhs.setIfTrue(node.getIfTrue());
            lhs.setIfFalse(new BasicBlock(currentFunction, "or_lhs_false"));
            lhs.accept(this);

            Register trueReg = new Register();
            currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));
            Compare cmp = new Compare(currentBB, Compare.CompareOp.EQ, lhs.getRegister(), lhs.isDataInMem(), trueReg, false);
            currentBB.addTail(cmp);
            currentBB.endWith(new Branch(currentBB, cmp, lhs.getIfTrue(), lhs.getIfFalse()));
            lhs.getIfTrue().addTail(new MoveU(lhs.getIfTrue(), node.getRegister(), new Immediate(1)));

            currentBB = lhs.getIfFalse();
            currentFunction.putBasicBlock(currentBB);
        }

        // rhs
        AbstractExpr rhs = node.getRhs();
        rhs.setIfTrue(node.getIfTrue());
        rhs.setIfFalse(node.getIfFalse());
        rhs.accept(this);


        if (node.getOp() == BinaryExpr.BinaryOp.LOGICAL_AND) {
            Register trueReg = new Register();
            currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));
            Compare cmp = new Compare(currentBB, Compare.CompareOp.EQ, rhs.getRegister(), rhs.isDataInMem(), trueReg, false);
            currentBB.addTail(cmp);
            currentBB.endWith(new Branch(currentBB, cmp, rhs.getIfTrue(), rhs.getIfFalse()));
            rhs.getIfTrue().addTail(new MoveU(rhs.getIfTrue(), node.getRegister(), new Immediate(1)));
        } else {
            Register trueReg = new Register();
            currentBB.addTail(new MoveU(currentBB, trueReg, new Immediate(1)));
            Compare cmp = new Compare(currentBB, Compare.CompareOp.EQ, rhs.getRegister(), rhs.isDataInMem(), trueReg, false);
            currentBB.addTail(cmp);
            currentBB.endWith(new Branch(currentBB, cmp, rhs.getIfTrue(), rhs.getIfFalse()));
            rhs.getIfFalse().addTail(new MoveU(rhs.getIfFalse(), node.getRegister(), new Immediate(0)));
        }

    }


    private void processStringBinary(BinaryExpr node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);

        Register cmpAns = new Register();

        Call.Builder builder = new Call.Builder();
        builder.setBasicBlock(currentBB);
        builder.setRet(cmpAns);

        if (node.getLhs().isDataInMem()) {
            Register lhs = new Register();
            currentBB.addTail(new Move(currentBB, lhs, false, node.getLhs().getRegister(), true));
            builder.addArgs(lhs);
        } else {
            builder.addArgs(node.getLhs().getRegister());
        }
        if (node.getRhs().isDataInMem()) {
            Register rhs = new Register();
            currentBB.addTail(new Move(currentBB, rhs, false, node.getRhs().getRegister(), true));
            builder.addArgs(rhs);
        } else {
            builder.addArgs(node.getRhs().getRegister());
        }

        switch (node.getOp()) {
            case ADD:
                builder.setProcessedName("_strADD");
                break;
            case LT:
                builder.setProcessedName("_strLT");
                break;
            case GT:
                builder.setProcessedName("_strGT");
                break;
            case LE:
                builder.setProcessedName("_strLE");
                break;
            case GE:
                builder.setProcessedName("_strGE");
                break;
            case EQ:
                builder.setProcessedName("_strEQ");
                break;
            case NE:
                builder.setProcessedName("_strNE");
                break;
        }

        currentBB.addTail(builder.build());

        node.setRegister(cmpAns);

    }

    private void processIntBinary(BinaryExpr node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);

        BinaryCalc.BinaryOp op = null;
        switch (node.getOp()) {
            case ADD:
                op = BinaryCalc.BinaryOp.ADD;
                break;
            case SUB:
                op = BinaryCalc.BinaryOp.SUB;
                break;
            case MUL:
                op = BinaryCalc.BinaryOp.MUL;
                break;
            case DIV:
                op = BinaryCalc.BinaryOp.DIV;
                break;
            case MOD:
                op = BinaryCalc.BinaryOp.MOD;
                break;
            case BITWISE_AND:
                op = BinaryCalc.BinaryOp.BAND;
                break;
            case BITWISE_OR:
                op = BinaryCalc.BinaryOp.BOR;
                break;
            case XOR:
                op = BinaryCalc.BinaryOp.XOR;
                break;
            case LEFT_SHIFT:
                op = BinaryCalc.BinaryOp.LSH;
                break;
            case RIGHT_SHIFT:
                op = BinaryCalc.BinaryOp.RSH;
                break;
            default:
                break;
        }

        Register dest = new Register();
        currentBB.addTail(new BinaryCalc(currentBB, op, dest, node.getLhs().getRegister(), node.getLhs().isDataInMem(), node.getRhs().getRegister(), node.getRhs().isDataInMem()));
        node.setRegister(dest);
    }

    @Override
    public void visit(BinaryExpr node) {
        // not visit immediately
        switch (node.getOp()) {
            case ASSIGN:
                processAssign(node);
                break;
            case LOGICAL_OR:
            case LOGICAL_AND:
                processLogicalBinary(node);
                break;
            case EQ:
            case NE:
            case LT:
            case GT:
            case LE:
            case GE:
                if (node.getLhs().getType().getBaseType().equals("string")) {
                    processStringBinary(node);
                } else {
                    processIntCompare(node);
                }
                break;
            case LEFT_SHIFT:
            case RIGHT_SHIFT:
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case MOD:
            case BITWISE_OR:
            case BITWISE_AND:
            case XOR:
                if (node.getLhs().getType().getBaseType().equals("string")) {
                    processStringBinary(node);
                } else {
                    processIntBinary(node);
                }
                break;
            default:
        }
    }

    @Override
    public void visit(Identifier node) {
        if (node.getName().equals("this")) { // this
            node.setRegister(currentFunction.getThisRegister());
            return;
        }
        if (node.getVarSymbol() == null) { // func
            return; // FIXME
        }
        // FIXME
        if (node.getName().equals("age")) {
            System.err.println("age");
        }

        if (node.getVarSymbol().getBelongTable() instanceof TopTable) {
            Register register = new Register();
            StaticData globalVar = irProgram.getGlobalVar(node.getName());
            currentBB.addTail(new MoveU(currentBB, register, globalVar.getLabel()));
            node.setRegister(register);
            node.setDataInMem(true);
        } else if (node.getVarSymbol().getBelongTable() instanceof ClassTable) { // in class use
            Register dest = new Register();
            Register base = currentFunction.getThisRegister();
            Integer offset = currentClass.getHeapOffset(node.getName());
            currentBB.addTail(new Lea(currentBB, dest, base, null, offset));
            node.setRegister(dest);
            node.setDataInMem(true);
            // FIXME
        } else { // BlockTable
            node.setRegister(node.getVarSymbol().getRegister());
        }
    }

    @Override
    public void visit(NewArray node) {
        node.getLens().forEach(x -> x.accept(this));

        List<Register> parameterList = new LinkedList<>();
        node.getLens().forEach(x -> parameterList.add(x.getRegister()));    // can be in mem

        Register preRet = new Register();

        Call.Builder preMalloc = new Call.Builder();
        preMalloc.setBasicBlock(currentBB);
        preMalloc.setProcessedName("_malloc");
        preMalloc.setRet(preRet);

        Integer len = parameterList.size();
        Register lenRegister = new Register();
        currentBB.addTail(new MoveU(currentBB, lenRegister, new Immediate(len)));
        Register byteRegister = new Register();
        currentBB.addTail(new MoveU(currentBB, byteRegister, new Immediate(len)));
        preMalloc.addArgs(byteRegister);

        currentBB.addTail(preMalloc.build());

        Register iter = new Register();
        currentBB.addTail(new Move(currentBB, iter, false, preRet, false));
        currentBB.addTail(new Move(currentBB, iter, true, lenRegister, false));
        for (int i = 0; i < len; i++) {
            currentBB.addTail(new SelfInc(currentBB, iter, false, 8));
            currentBB.addTail(new Move(currentBB, iter, true, parameterList.get(i), node.getLens().get(i).isDataInMem()));
        }

        Register ret = new Register();
        Call.Builder malloc = new Call.Builder();
        malloc.setBasicBlock(currentBB);
        malloc.setProcessedName("newArray");
        malloc.addArgs(preRet);
        malloc.setRet(ret);
        currentBB.addTail(malloc.build());

        node.setRegister(ret);
    }

    @Override
    public void visit(NewNonArray node) {
        // ignoring the paramters
        Register ret = new Register();

        Call.Builder builder = new Call.Builder();
        builder.setBasicBlock(currentBB);
        builder.setProcessedName("_malloc");
        builder.setRet(ret);

        Register lenRegister = new Register();
        Integer len;
        switch (node.getType().getBaseType()) {
            case "int":
            case "bool":
                len = 8;
                break;
            case "string":
                len = 8; // FIXME
                break;
            default:
                len = irProgram.getIRClass(node.getType().getBaseType()).getSize();
        }
        currentBB.addTail(new MoveU(currentBB, lenRegister, new Immediate(len)));
        builder.addArgs(lenRegister);

        currentBB.addTail(builder.build());

        node.setRegister(ret);

        String processedName = null;
        switch (node.getType().getBaseType()) {
            case "int":
            case "bool":
            case "string":
                break;
            default:
                processedName = "_N" + node.getType().getBaseType() + node.getType().getBaseType();
                if (irProgram.getIrFunctionMap().get(processedName) == null) {
                    processedName = null;
                }
                break;
        }
        if (processedName != null) {
            Call.Builder cstrBuilder = new Call.Builder();
            cstrBuilder.setProcessedName(processedName);
            cstrBuilder.setBasicBlock(currentBB);
            cstrBuilder.addArgs(ret);
            // void ret, no parameters
            currentBB.addTail(cstrBuilder.build());
        }
//        Call.Builder cstrBuilder = new Call.Builder();
//        cstrBuilder.setProcessedName("_"node.getType().getBaseType());
//        irProgram.getIrFunctionMap()


    }

    @Override
    public void visit(BoolConst node) {
        Register boolVar = new Register();
        currentBB.addTail(new MoveU(currentBB, boolVar,  new Immediate(node.getValue() ? 1 : 0)));
        node.setRegister(boolVar);
    }

    @Override
    public void visit(NumConst node) {
        Register constVar = new Register();
        currentBB.addTail(new MoveU(currentBB, constVar, new Immediate(node.getNum())));
        node.setRegister(constVar);
    }

    @Override
    public void visit(StrConst node) {
        StaticData stringData = irProgram.getStaticString(node.getStr());
        if (stringData == null) {
            stringData = new StaticData(new Label(node.getStr()), node.getStr());
            irProgram.putStaticString(node.getStr(), stringData);
        }
        Register constVar = new Register();
        currentBB.addTail(new MoveU(currentBB, constVar, stringData.getLabel()));
        node.setRegister(constVar);
//        node.setDataInMem(true); // FIXME
    }

    @Override
    public void visit(NullConst node) {
        Register constVar = new Register();
        currentBB.addTail(new MoveU(currentBB, constVar, new Immediate(0)));
        node.setRegister(constVar);
    }

    private boolean isLogicalExpr(AbstractExpr node){
        if(node instanceof BinaryExpr){
            BinaryExpr.BinaryOp op = ((BinaryExpr) node).getOp();
            return op == BinaryExpr.BinaryOp.LOGICAL_AND || op == BinaryExpr.BinaryOp.LOGICAL_OR;
        } else if(node instanceof UnaryExpr){
            return ((UnaryExpr) node).getOp() == UnaryExpr.UnaryOp.LOGICAL_NOT;
        } else{
            return false;
        }
    }

//    private void generateIRBranch(AbstractExpr node) {
//        // assert ifTrue ifFalse != null
//        if (currentCond != null) {
//            // strCompare intCompare
//            currentBB.addTail(currentCond);
//            if (boolRet != null) {
//                currentBB.addTail(new Move(currentBB, boolRet, false, currentCond.getDestination(), false));
//            }
//            currentBB.endWith(new Branch(currentBB, currentCond, node.getIfTrue(), node.getIfFalse()));
//
//            // node.getRegister Null ?? FIXME
////            node.getIfTrue().addTail(new MoveU(currentBB, node.getRegister(), new Immediate(1)));
////            node.getIfFalse().addTail(new MoveU(currentBB, node.getRegister(), new Immediate(0)));
//
//            currentCond = null;
//        } else {
//            // bool const | bool identifier | bool array | bool member
//            // TODO constant folding
//            Register val = node.getRegister();
//            Register trueConst = new Register();
//            currentBB.addTail(new MoveU(currentBB, trueConst, new Immediate(1)));
//            Register cond = new Register();
//            Compare compare = new Compare(currentBB, Compare.CompareOp.EQ, trueConst, false, val, false);
//            currentBB.addTail(compare);
//            if (boolRet != null) {
//                currentBB.addTail(new Move(currentBB, boolRet, false, compare.getDestination(), false));
//            }
//            currentBB.endWith(new Branch(currentBB, compare, node.getIfTrue(), node.getIfFalse()));
//        }
//
//    }
}
