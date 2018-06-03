//package Compiler2018.FrontEnd.IRBuilder;
//
//import Compiler2018.AST.*;
//import Compiler2018.FrontEnd.IASTVistor;
//import Compiler2018.IR.IRStructure.BasicBlock;
//import Compiler2018.IR.IRStructure.IRFunction;
//import Compiler2018.IR.IRStructure.IRProgram;
//
//public class IRBuilder implements IASTVistor {
//    private IRProgram program = new IRProgram();
//    private IRFunction currentIRFunction;
//    private BasicBlock currentBlock;
//
//    @Override
//    public void visit(Program node) {
//        node.getSections().forEach(x -> x.accept(this));
//    }
//
//    @Override
//    public void visit(ClassDecl node) {
//        node.getItems().forEach(x -> x.accept(this));
//    }
//
//    @Override
//    public void visit(FuncDecl node) {
//
//    }
//
//    @Override
//    public void visit(VarDecl node) {
//
//    }
//
//    @Override
//    public void visit(ClassVarDecl node) {
//        // DO Nothing
//    }
//
//    @Override
//    public void visit(ClassCstrDecl node) {
//        IRFunction IRFunction = new IRFunction(node.getName());
//        currentIRFunction = IRFunction;
////        node.getParameters().forEach(x -> x.accept(this));    // no parameters !!
//        node.getBlock().accept(this);
//    }
//
//    @Override
//    public void visit(ClassFuncDecl node) {
////        IRFunction function = new IRFunction(program, )
//    }
//
//    @Override
//    public void visit(BlockStmt node) {
//
//    }
//
//    @Override
//    public void visit(VarDeclStmt node) {
//
//    }
//
//    @Override
//    public void visit(BranchStmt node) {
//
//    }
//
//    @Override
//    public void visit(ExprStmt node) {
//
//    }
//
//    @Override
//    public void visit(EmptyStmt node) {
//
//    }
//
//    @Override
//    public void visit(ReturnStmt node) {
//
//    }
//
//    @Override
//    public void visit(BreakStmt node) {
//
//    }
//
//    @Override
//    public void visit(ContinueStmt node) {
//
//    }
//
//    @Override
//    public void visit(ForStmt node) {
//
//    }
//
//    @Override
//    public void visit(WhileStmt node) {
//
//    }
//
//    @Override
//    public void visit(ClassType node) {
//
//    }
//
//    @Override
//    public void visit(FunctionCall node) {
//
//    }
//
//    @Override
//    public void visit(ArrayAcess node) {
//
//    }
//
//    @Override
//    public void visit(MemberAcess node) {
//
//    }
//
//    @Override
//    public void visit(NewExpr node) {
//
//    }
//
//    @Override
//    public void visit(UnaryExpr node) {
//
//    }
//
//    @Override
//    public void visit(BinaryExpr node) {
//
//    }
//
//    @Override
//    public void visit(Identifier node) {
//        // FIXME This
////        node.getTable().getVar(node.generateProcessedName()); // "this" should be in table
////        VarSymbol varSymbol = node.getTable().getVar(node.generateProcessedName());
////        if (varSymbol.getVirtualRegister() == null) {
////
////        }
////        // == null is additionally processed
////        node.setIRValue(node.getTable().getVar(node.generateProcessedName()).getVirtualRegister());
//    }
//
//    @Override
//    public void visit(NewArray node) {
//
//    }
//
//    @Override
//    public void visit(NewNonArray node) {
////        node.getParameters().forEach(x -> x.accept(this));
////        List<AbstractValue> args = new LinkedList<>();
////        node.getParameters().forEach(x -> args.add(x.getIRValue()));
////        StringBuilder callName = new StringBuilder();
////        callName.append("_");
////        callName.append(Integer.toString(node.getType().getBaseType().length()));
////
////        currentBlock.addTail(new Call(currentBlock, "malloc", args));
//    }
//
//    @Override
//    public void visit(BoolConst node) {
////        Immediate immediate = new Immediate(node.getValue() ? 1 : 0);
////        node.setIRValue(immediate);
//    }
//
//    @Override
//    public void visit(NumConst node) {
////        Immediate immediate = new Immediate(node.getNum());
////        node.setIRValue(immediate);
//    }
//
//    @Override
//    public void visit(StrConst node) {
////        StaticData staticData = program.getStaticData(node.getStr());
////        if (staticData == null) {
////            staticData = new StaticData(new Label(node.getStr()), node.getStr());
////            program.putStaticData(node.getStr(), staticData);
////        }
////        node.setIRValue(staticData.name);
//    }
//
//    @Override
//    public void visit(NullConst node) {
////        Immediate immediate = new Immediate(0);
////        node.setIRValue(immediate);
//    }
//}
