package Compiler2018.FrontEnd.IRBuilder;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.IR.IRStructure.IRClass;
import Compiler2018.IR.IRStructure.IRProgram;

// process VarDecl in ClassDecl
// Class is array-like data set
public class IRClassBuilder implements IASTVistor {
    private final IRProgram irProgram;
    private IRClass irClass = null;

    public IRClassBuilder(IRProgram irProgram) {
        this.irProgram = irProgram;
    }

    @Override
    public void visit(Program node) {
        node.getSections().stream().filter(x -> x instanceof ClassDecl).forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {
        irClass = new IRClass();
        node.getItems().forEach(x -> x.accept(this));
        irProgram.putIRClass(node.getName(), irClass);
        irClass = null;
    }

    @Override
    public void visit(FuncDecl node) {

    }

    @Override
    public void visit(VarDecl node) {
        irClass.addHeapOffset(node.getName());
    }

    @Override
    public void visit(ClassVarDecl node) {
        node.getDecl().accept(this);
    }

    @Override
    public void visit(ClassCstrDecl node) {

    }

    @Override
    public void visit(ClassFuncDecl node) {

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

    }

    @Override
    public void visit(NewArray node) {

    }

    @Override
    public void visit(NewNonArray node) {

    }

    @Override
    public void visit(BoolConst node) {

    }

    @Override
    public void visit(NumConst node) {

    }

    @Override
    public void visit(StrConst node) {

    }

    @Override
    public void visit(NullConst node) {

    }
}
