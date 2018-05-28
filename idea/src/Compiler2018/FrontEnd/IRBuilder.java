package Compiler2018.FrontEnd;

import Compiler2018.AST.*;
import Compiler2018.IR.IRInstruction.Call;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRStructure.Function;
import Compiler2018.IR.IRStructure.IRProgram;
import Compiler2018.IR.IRValue.AbstractValue;
import Compiler2018.IR.IRValue.Immediate;

import java.util.LinkedList;
import java.util.List;

public class IRBuilder implements IASTVistor{
    private IRProgram program = new IRProgram();
    private Function currentFunction;
    private BasicBlock currentBlock;

    @Override
    public void visit(Program node) {
        node.getSections().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {
        node.getItems().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(FuncDecl node) {

    }

    @Override
    public void visit(VarDecl node) {

    }

    @Override
    public void visit(ClassVarDecl node) {
        // DO Nothing
    }

    @Override
    public void visit(ClassCstrDecl node) {
        Function function = new Function(program, node.getName());
        currentFunction = function;
//        node.getParameters().forEach(x -> x.accept(this));    // no parameters !!
        node.getBlock().accept(this);
    }

    @Override
    public void visit(ClassFuncDecl node) {
//        Function function = new Function(program, )
    }

    @Override
    public void visit(BlockStmt node) {

    }

    @Override
    public void visit(VarDeclStmt node) {

    }

    @Override
    public void visit(BranchStmt node) {

    }

    @Override
    public void visit(ExprStmt node) {

    }

    @Override
    public void visit(EmptyStmt node) {

    }

    @Override
    public void visit(ReturnStmt node) {

    }

    @Override
    public void visit(BreakStmt node) {

    }

    @Override
    public void visit(ContinueStmt node) {

    }

    @Override
    public void visit(ForStmt node) {

    }

    @Override
    public void visit(WhileStmt node) {

    }

    @Override
    public void visit(ClassType node) {

    }

    @Override
    public void visit(FunctionCall node) {

    }

    @Override
    public void visit(ArrayAcess node) {

    }

    @Override
    public void visit(MemberAcess node) {

    }

    @Override
    public void visit(NewExpr node) {

    }

    @Override
    public void visit(UnaryExpr node) {

    }

    @Override
    public void visit(BinaryExpr node) {

    }

    @Override
    public void visit(Identifier node) {
        node.setIrValue(node.getTable().getVar(node.getName()).getVirtualReg());
    }

    @Override
    public void visit(NewArray node) {

    }

    @Override
    public void visit(NewNonArray node) {
        node.getParameters().forEach(x -> x.accept(this));
        List<AbstractValue> args = new LinkedList<>();
        node.getParameters().forEach(x -> args.add(x.getIrValue()));
        StringBuilder callName = new StringBuilder();
        callName.append("_");
        callName.append(Integer.toString(node.getType().getBaseType().length()));

        currentBlock.addTail(new Call(currentBlock, "malloc", args));
    }

    @Override
    public void visit(BoolConst node) {
        Immediate immediate = new Immediate(node.getValue() ? 1 : 0);
        node.setIrValue(immediate);
    }

    @Override
    public void visit(NumConst node) {
        Immediate immediate = new Immediate(node.getNum());
        node.setIrValue(immediate);
    }

    @Override
    public void visit(StrConst node) {
//        Label label = new Label(node)
//        Register register = new Register(node.getType(), null)
//        node.setVirtualReg(register);
//        currentFunction.addRegister(register);
        // TODO
    }

    @Override
    public void visit(NullConst node) {
        Immediate immediate = new Immediate(0);
        node.setIrValue(immediate);
    }
}
