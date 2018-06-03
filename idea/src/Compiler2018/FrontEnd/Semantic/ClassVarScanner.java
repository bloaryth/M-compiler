package Compiler2018.FrontEnd.Semantic;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.Symbol.*;

import java.util.Stack;

public class ClassVarScanner implements IASTVistor {
    private final TopTable topTable;
    private final Stack<AbstractSymbolTable> currentTable = new Stack<>();

    public ClassVarScanner(TopTable topTable) {
        this.topTable = topTable;
    }

    @Override
    public void visit(Program node) {
        currentTable.push(topTable);
        node.getSections().stream().filter(x -> x instanceof ClassDecl).forEach(x -> x.accept(this));
        currentTable.pop();
    }

    @Override
    public void visit(ClassDecl node) {
        currentTable.push(currentTable.peek().getMyClass(node.getName()).getInClassTable());
        node.getItems().forEach(x -> x.accept(this));
        currentTable.pop();
    }

    @Override
    public void visit(FuncDecl node) {
    }

    @Override
    public void visit(VarDecl node) { // type
        if (node.getType().getBaseType().equals("void")) {
            throw new RuntimeException("Void type should not be declared.");
        }
        if (topTable.getMyClass(node.getType().getBaseType()) == null) {
            throw new RuntimeException("Undeclared class occurred.");
        }
        // name
        if (node.getName().equals("this")) {
            throw new RuntimeException("'this' should not be a variable name.");
        }
        if (currentTable.peek().getFunc(node.getName()) != null) {
            throw new RuntimeException("Variable has the same name of function.");
        }
        if (currentTable.peek().getVar(node.getName()) != null) {
            throw new RuntimeException("Variable is previously declared.");
        }
        // init TODO
        if (node.getInit() != null) {
            throw new RuntimeException("Init is prohibited.");
        }
        currentTable.peek().addVar(node.getName(), new VarSymbol(currentTable.peek(), node));
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
