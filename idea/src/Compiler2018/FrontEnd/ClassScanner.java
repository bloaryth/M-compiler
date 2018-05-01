package Compiler2018.FrontEnd;

import Compiler2018.AST.*;
import Compiler2018.Symbol.ClassSymbol;
import Compiler2018.Symbol.ClassTable;
import Compiler2018.Symbol.TopTable;

public class ClassScanner implements IASTVistor {
    private TopTable topTable;

    public ClassScanner (TopTable topTable) {
        this.topTable = topTable;
    }

    @Override
    public void visit (Program node) {
        topTable.addMyClass ("int", new ClassSymbol ("int", new ClassTable (topTable)));
        topTable.addMyClass ("bool", new ClassSymbol ("bool", new ClassTable (topTable)));
        topTable.addMyClass ("void", new ClassSymbol ("void", new ClassTable (topTable)));
        topTable.addMyClass ("string", new ClassSymbol ("string", new ClassTable (topTable)));

        node.getSections ().forEach (x -> x.accept (this));
    }

    @Override
    public void visit (ClassDecl node) {
        topTable.addMyClass (node.getName (), new ClassSymbol (node.getName (), new ClassTable (topTable)));
    }

    @Override
    public void visit (FuncDecl node) {

    }

    @Override
    public void visit (VarDecl node) {

    }

    @Override
    public void visit (ClassVarDecl node) {

    }

    @Override
    public void visit (ClassCstrDecl node) {

    }

    @Override
    public void visit (ClassFuncDecl node) {

    }

    @Override
    public void visit (BlockStmt node) {

    }

    @Override
    public void visit (VarDeclStmt node) {

    }

    @Override
    public void visit (BranchStmt node) {

    }

    @Override
    public void visit (ExprStmt node) {

    }

    @Override
    public void visit (EmptyStmt node) {

    }

    @Override
    public void visit (ReturnStmt node) {

    }

    @Override
    public void visit (BreakStmt node) {

    }

    @Override
    public void visit (ContinueStmt node) {

    }

    @Override
    public void visit (ForStmt node) {

    }

    @Override
    public void visit (WhileStmt node) {

    }

    @Override
    public void visit (ClassType node) {

    }

    @Override
    public void visit (FunctionCall node) {

    }

    @Override
    public void visit (ArrayAcess node) {

    }

    @Override
    public void visit (MemberAcess node) {

    }

    @Override
    public void visit (NewExpr node) {

    }

    @Override
    public void visit (UnaryExpr node) {

    }

    @Override
    public void visit (BinaryExpr node) {

    }

    @Override
    public void visit (Identifier node) {

    }

    @Override
    public void visit (NewArray node) {

    }

    @Override
    public void visit (NewNonArray node) {

    }

    @Override
    public void visit (BoolConst node) {

    }

    @Override
    public void visit (NumConst node) {

    }

    @Override
    public void visit (StrConst node) {

    }

    @Override
    public void visit (NullConst node) {

    }
}
