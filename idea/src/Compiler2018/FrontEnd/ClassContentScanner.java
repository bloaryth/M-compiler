package Compiler2018.FrontEnd;

import Compiler2018.AST.*;
import Compiler2018.Symbol.*;

import java.util.Stack;

public class ClassContentScanner implements IASTVistor{
    private final TopTable topTable;
    private final Stack<AbstractSymbolTable> currentTable = new Stack<> ();

    public ClassContentScanner (TopTable topTable) {
        this.topTable = topTable;
    }

    @Override
    public void visit (Program node) {
        currentTable.push (topTable);
        node.getSections ().forEach (x -> x.accept (this));
        currentTable.pop ();
    }

    @Override
    public void visit (ClassDecl node) {
        ClassSymbol myClass = currentTable.peek ().getMyClass (node.getName ());
        currentTable.push (myClass.getInClassTable ());
        node.getItems ().forEach (x -> x.accept (this));
        currentTable.pop ();
    }

    @Override
    public void visit (FuncDecl node) {

    }

    @Override
    public void visit (VarDecl node) {

    }

    @Override
    public void visit (ClassVarDecl node) {
        if (topTable.getMyClass (node.getDecl ().getType ().getBaseType ()) == null){
            throw new RuntimeException ("Undefined Class in Variable Declaration.");
        }
        ClassTable classTable = (ClassTable) currentTable.peek ();
        if (classTable.getVar (node.getDecl ().getName ()) != null){
            throw new RuntimeException ("classVar previously declared.");
        }
        classTable.addVar (node.getDecl ().getName (), new VarSymbol (node.getDecl ()));
    }

    @Override
    public void visit (ClassCstrDecl node) {
        node.getParameters ().forEach (x -> {
            if(topTable.getMyClass (x.getType ().getBaseType ()) == null){
                throw new RuntimeException ("Undefined Class in Constructor Declaration.");
            }
        });
        ClassTable classTable = (ClassTable) currentTable.peek ();
        classTable.addCstr (node.getName (), new CstrSymbol (node, new BlockTable (classTable)));
    }

    @Override
    public void visit (ClassFuncDecl node) {
        node.getDecl ().getParameters ().forEach (x -> {
            if(topTable.getMyClass (x.getType ().getBaseType ()) == null){
                throw new RuntimeException ("Undefined Class in Function Declaration.");
            }
        });
        if (topTable.getMyClass (node.getDecl ().getReturnType ().getBaseType ()) == null){
            throw new RuntimeException ("Undefined Class in Function Declaration.");
        }
        if(node.getDecl ().getName ().equals ("this")){
            throw new RuntimeException ("Reserved keywords");
        }
        // TODO
        ClassTable classTable = (ClassTable) currentTable.peek ();
        classTable.addFunc (node.getDecl ().getName (), new FuncSymbol (node.getDecl (), new BlockTable (classTable)));
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
