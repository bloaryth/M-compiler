package Compiler2018.FrontEnd.Semantic;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.Symbol.ClassSymbol;
import Compiler2018.Symbol.ClassTable;
import Compiler2018.Symbol.TopTable;

public class ClassScanner implements IASTVistor {
    private TopTable topTable;

    public ClassScanner(TopTable topTable) {
        this.topTable = topTable;
    }

    @Override
    public void visit(Program node) {
        // add primitive type
        topTable.addMyClass("int", new ClassSymbol(topTable,"int", new ClassTable(topTable, "int")));
        topTable.addMyClass("bool", new ClassSymbol(topTable,"bool", new ClassTable(topTable, "bool")));
        topTable.addMyClass("void", new ClassSymbol(topTable,"void", new ClassTable(topTable, "void")));
        topTable.addMyClass("string", new ClassSymbol(topTable,"string", new ClassTable(topTable, "string")));

        node.getSections().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {
        if (node.getName().equals("this")) {
            throw new RuntimeException("'this' should not be a class name");
        }
        if (topTable.getMyClass(node.getName()) != null) {
            throw new RuntimeException("IRClass is previously declared.");
        }
        topTable.addMyClass(node.getName(), new ClassSymbol(topTable, node.getName(), new ClassTable(topTable, node.getName())));
    }

    @Override
    public void visit(FuncDecl node) {
    }

    @Override
    public void visit(VarDecl node) {
    }

    @Override
    public void visit(ClassVarDecl node) {
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
