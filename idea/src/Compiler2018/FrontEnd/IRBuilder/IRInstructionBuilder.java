package Compiler2018.FrontEnd.IRBuilder;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.IR.IRInstruction.*;
import Compiler2018.IR.IRStructure.*;
import Compiler2018.IR.IRValue.Immediate;
import Compiler2018.IR.IRValue.Label;
import Compiler2018.IR.IRValue.Register;
import Compiler2018.Symbol.TopTable;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;

public class IRInstructionBuilder implements IASTVistor {
    private final IRProgram irProgram;
    private BasicBlock InitAfter;
    private IRFunction currentFunction;
    private BasicBlock currentBB;
    private boolean inBranch;
    private Compare currentCond;   // valid only inBranch is true
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
        InitAfter = currentBB;
        currentBB = null;
        // Function implementation
        node.getSections().stream().filter(x -> !(x instanceof VarDecl)).forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {
        node.getItems().stream().filter(x -> !(x instanceof ClassVarDecl)).forEach(x -> x.accept(this));
    }

    @Override
    public void visit(FuncDecl node) {
        currentFunction = irProgram.getIRFunction(node.getFuncSymbol().getProcessedName());
        if (node.getName().equals("main")) {
            currentBB = InitAfter;
        } else {
            currentBB = currentFunction.getStartBlock();
        }
        currentFunction.putBasicBlock(currentBB);
        node.getBlock().accept(this);
        currentFunction.setEndBlock(currentBB);
        currentBB = null;
        currentFunction = null;
    }

    @Override
    public void visit(VarDecl node) {
        if (node.getInit() != null && isLogicalExpr(node.getInit())) {
            BasicBlock block = new BasicBlock(currentFunction, null);
            node.getInit().setIfTrue(block);
            node.getInit().setIfFalse(block);
        }   // shortcut

        if (node.getVarSymbol().getBelongTable() instanceof TopTable) { // Global Init
            Label label = new Label(node.getName());
            irProgram.putGlobalVar(node.getName(), new StaticData(label));

            if (node.getInit() != null) {
                node.getInit().accept(this);
                Register init = new Register();
                currentBB.addTail(new MoveU(currentBB, init, label));
                currentBB.addTail(new Move(currentBB, init, true, node.getInit().getRegister(), node.getInit().isDataInMem()));
            } else {
                // redundant TODO class default init
            }

        } else { // BlockTabel Variable
            if (node.getInit() != null) {
                node.getInit().accept(this);
                currentBB.addTail(new Move(currentBB, node.getVarSymbol().getRegister(), false, node.getInit().getRegister(), node.getInit().isDataInMem()));
            } else {
                // redundant TODO class default init
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
//        inBranch = true;
        node.getCond().accept(this);
//        inBranch = false;

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
        if (node.getExpr() != null) {
            node.getExpr().accept(this);
            Register ret;
            if (node.getExpr().isDataInMem()) {
                ret = new Register();
                currentBB.addTail(new Move(currentBB, ret, false, node.getExpr().getRegister(), true));
            } else {
                ret = node.getExpr().getRegister();
            }
            currentBB.endWith(new Ret(currentBB, ret));
        } else {
            currentBB.endWith(new Ret(currentBB, null));
        }
        // FIXME shortcut
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

        currentBB.endWith(new Jump(currentBB, BBCond));

        currentBB = BBCond;
        currentFunction.putBasicBlock(currentBB);
        node.getCond().setIfTrue(BBLoop);
        node.getCond().setIfFalse(BBAfter);
        node.getCond().accept(this);

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
    public void visit(FunctionCall node) {
        node.getName().accept(this);
        node.getParameters().forEach(x -> x.accept(this));
        List<Register> parameterList = new LinkedList<>();
        if (node.getName() instanceof MemberAcess && node.getName().getFunc() != null) {
            parameterList.add(node.getName().getRegister());
        } else {
//            throw new RuntimeException("function call")
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

        Register ret;
        if (node.getType().getBaseType().equals("void")) {
            ret = null;
        } else {
            ret = new Register();
        }
        Call.Builder builder = new Call.Builder();
        builder.setBasicBlock(currentBB);
        builder.setProcessedName(node.getProcessedName());  // FIXME function call
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
        node.getExpr().setIfTrue(node.getIfFalse());
        node.getExpr().setIfFalse(node.getIfTrue());
        node.getExpr().accept(this);
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
        if (isLogicalExpr(node.getRhs())) {
            BasicBlock block = new BasicBlock(currentFunction, null);
            node.getRhs().setIfTrue(block);
            node.getRhs().setIfFalse(block);
        }   // shortcut

        node.getLhs().accept(this);
        node.getRhs().accept(this);

        currentBB.addTail(new Move(currentBB, node.getLhs().getRegister(), node.getLhs().isDataInMem(), node.getRhs().getRegister(), node.getRhs().isDataInMem()));
        node.setRegister(node.getLhs().getRegister());
        node.setDataInMem(node.getLhs().isDataInMem());
    }

    private void processCompare(BinaryExpr node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);

        Compare.CompareOp cond = null;
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
        Compare compare = new Compare(currentBB, cond, dest, node.getLhs().getRegister(), node.getLhs().isDataInMem(), node.getRhs().getRegister(), node.getLhs().isDataInMem());
        node.setRegister(dest);

        currentBB.addTail(new Branch(currentBB, compare, node.getIfTrue(), node.getIfFalse()));
        // TODO
//        if (inBranch) {
//            currentCond = compare; // prepare for Branch
//        } else { // in calc
//            currentBB.addTail(compare);
//        } // FIXME
    }

    private void processLogicalBinary(BinaryExpr node) {
        // FIXME shortcut
        if (node.getOp() == BinaryExpr.BinaryOp.LOGICAL_AND) { // LOGICAL_AND
            AbstractExpr lhs = node.getLhs();
            lhs.setIfTrue(new BasicBlock(currentFunction, "lhs_true"));
            lhs.setIfFalse(node.getIfFalse());
            lhs.accept(this);
//            currentBB.endWith(new Branch(currentBB, currentCond, lhs.getIfTrue(), lhs.getIfFalse()));
            currentBB = lhs.getIfTrue();
            currentFunction.putBasicBlock(currentBB);
        } else { // LOGICAL_OR
            AbstractExpr lhs = node.getLhs();
            lhs.setIfTrue(node.getIfTrue());
            lhs.setIfFalse(new BasicBlock(currentFunction, "lhs_false"));
            lhs.accept(this);
//            currentBB.endWith(new Branch(currentBB, currentCond, lhs.getIfTrue(), lhs.getIfFalse()));
            currentBB = lhs.getIfTrue();
            currentFunction.putBasicBlock(currentBB);
        }

        AbstractExpr rhs = node.getRhs();
        rhs.setIfTrue(node.getIfTrue());
        rhs.setIfFalse(node.getIfFalse());
        rhs.accept(this);
    }

    private void processStringBinary(BinaryExpr node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);

        Register ret = new Register();

        Call.Builder builder = new Call.Builder();
        builder.setBasicBlock(currentBB);
        builder.setRet(ret);

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
                builder.setProcessedName("_strBT");
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

        node.setRegister(ret);
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
                    processCompare(node);
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
        if (node.getVarSymbol().getBelongTable() instanceof TopTable) {
            Register register = new Register();
            StaticData globalVar = irProgram.getGlobalVar(node.getName());
            currentBB.addTail(new MoveU(currentBB, register, globalVar.getLabel()));
            node.setRegister(register);
            node.setDataInMem(true);
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

        Register lenRegister = new Register();
        Integer len = parameterList.size();
        currentBB.addTail(new MoveU(currentBB, lenRegister, new Immediate(len + 1)));
        preMalloc.addArgs(lenRegister);
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
    }

    @Override
    public void visit(BoolConst node) {
        Register boolVar = new Register();
        currentBB.addTail(new MoveU(currentBB, boolVar,  new Immediate(node.getValue() ? 1 : 0)));
        Register eqVar = new Register();
        currentBB.addTail(new MoveU(currentBB, eqVar, new Immediate(1)));
        // short cut
        Compare.CompareOp op = Compare.CompareOp.EQ;
        Register dest = new Register();
        Compare compare = new Compare(currentBB, op, dest, eqVar, false, boolVar, false);
        currentBB.addTail(new Branch(currentBB, compare, node.getIfTrue(), node.getIfFalse()));

        node.setRegister(dest);
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
            stringData = new StaticData(new Label(node.getStr()));
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
}
