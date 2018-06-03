package Compiler2018.FrontEnd.IRBuilder;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.IR.IRStructure.IRFunction;
import Compiler2018.IR.IRStructure.IRProgram;
import Compiler2018.Symbol.CstrSymbol;
import Compiler2018.Symbol.FuncSymbol;

public class IRFuncParamBuilder implements IASTVistor {
    private final IRProgram irProgram;
    private String className;

    public IRFuncParamBuilder(IRProgram irProgram) {
        this.irProgram = irProgram;
    }

    @Override
    public void visit(Program node) {
        node.getSections().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {
        className = node.getName();
        node.getItems().forEach(x -> x.accept(this));
        className = null;
    }

    @Override
    public void visit(FuncDecl node) {
        FuncSymbol funcSymbol = node.getFuncSymbol();
        IRFunction irFunction = new IRFunction(funcSymbol.getProcessedName(), className);
        if (className != null) {
            irFunction.addStackOffset(irFunction.getThisRegister());
        }
        // add parameter register
        node.getFuncSymbol().getParameterRegisterList().forEach(irFunction::addStackOffset);
        irProgram.putIRFunction(funcSymbol.getProcessedName(), irFunction);
    }

    @Override
    public void visit(VarDecl node) {

    }

    @Override
    public void visit(ClassVarDecl node) {

    }

    @Override
    public void visit(ClassCstrDecl node) {
        irProgram.getIRClass(className).setCstr(true);

        CstrSymbol cstrSymbol = node.getCstrSymbol();
        IRFunction irFunction = new IRFunction(cstrSymbol.getProcessedName(), className);
        irFunction.addStackOffset(irFunction.getThisRegister());
        // ignoring all parameters FIXME
        irProgram.putIRFunction(cstrSymbol.getProcessedName(), irFunction);
    }

    @Override
    public void visit(ClassFuncDecl node) {
        node.getDecl().accept(this);
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
