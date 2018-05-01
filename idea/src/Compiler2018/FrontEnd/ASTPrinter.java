package Compiler2018.FrontEnd;

import Compiler2018.AST.*;

public class ASTPrinter implements IASTVistor {
    private final StringBuilder indent = new StringBuilder();

    private void addIndent(){
        indent.append ("    ");
    }

    private void subIndent(){
        indent.delete (indent.length ()-4, indent.length ());
    }

    @Override
    public void visit (Program node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getSections ().forEach (x -> x.accept (this));
        subIndent ();
    }

    @Override
    public void visit (ClassDecl node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getName ());
        node.getItems ().forEach (x -> x.accept (this));
        subIndent ();
    }

    @Override
    public void visit (FuncDecl node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getReturnType ().accept (this);
//        System.out.println (indent.toString () + node.getPosType ());
        System.out.println (indent.toString () + node.getName ());
//        System.out.println (indent.toString () + node.getPosName ());
        node.getParameters ().forEach (this::visit);
        node.getBlock ().accept (this);
        subIndent ();
    }

    @Override
    public void visit (VarDecl node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getType ().accept (this);
//        System.out.println (indent.toString () + node.getPosType ());
        System.out.println (indent.toString () + node.getName ());
//        System.out.println (indent.toString () + node.getPosName ());
        if (node.getInit () != null){
            node.getInit ().accept (this);
//        System.out.println (indent.toString () + node.getPosInit ());
        }
        else{
            System.out.println (indent.toString () + "Init is null");
        }
        subIndent ();
    }

    @Override
    public void visit (ClassVarDecl node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getDecl ().accept (this);
        subIndent ();
    }

    @Override
    public void visit (ClassCstrDecl node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getName ());
        node.getParameters ().forEach (x -> x.accept (this));
        node.getBlock ().accept (this);
        subIndent ();
    }

    @Override
    public void visit (ClassFuncDecl node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getDecl ().accept (this);
        subIndent ();
    }

    @Override
    public void visit (BlockStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getStmts ().forEach (x -> x.accept (this));
        subIndent ();
    }

    @Override
    public void visit (VarDeclStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getDecl ().accept (this);
        subIndent ();
    }

    @Override
    public void visit (BranchStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getCond ().accept (this);
//        System.out.println (indent.toString () + node.getPosBranch ());
        node.getIfStmt ().accept (this);
        if(node.getElseStmt () != null){
            node.getElseStmt ().accept (this);
        }
        subIndent ();
    }

    @Override
    public void visit (ExprStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getExpr ().accept (this);
//        System.out.println (indent.toString () + node.getPosExpr ());
        subIndent ();
    }

    @Override
    public void visit (EmptyStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        subIndent ();
    }

    @Override
    public void visit (ReturnStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        if(node.getExpr () != null){
            node.getExpr ().accept (this);
        }
        else{
            System.out.println (indent.toString () + "Expr is null");
        }
        subIndent ();
    }

    @Override
    public void visit (BreakStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        subIndent ();
    }

    @Override
    public void visit (ContinueStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        subIndent ();
    }

    @Override
    public void visit (ForStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getInit ().accept (this);
//        System.out.println (indent.toString () + node.getPosInit ());
        if(node.getCond () != null){
            node.getCond ().accept (this);
//            System.out.println (indent.toString () + node.getPosCond ());
        }
        else{
            System.out.println (indent.toString () + "Cond is null");
        }
        node.getStep ().accept (this);
//        System.out.println (indent.toString () + node.getPosStep ());
        node.getStmt ().accept (this);
        subIndent ();
    }

    @Override
    public void visit (WhileStmt node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getCond ().accept (this);
//        System.out.println (indent.toString () + node.getPosCond ());
        node.getStmt ().accept (this);
        subIndent ();
    }

    @Override
    public void visit (ClassType node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getBaseType ());
        System.out.println (indent.toString () + node.getDim ());
        subIndent ();
    }

    @Override
    public void visit (FunctionCall node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getName ().accept (this);
//        System.out.println (indent.toString () + node.getPosName ());
        node.getParameters ().forEach (x -> x.accept (this));
//        System.out.println (indent.toString () + node.getPosParameters ());
        subIndent ();
    }

    @Override
    public void visit (ArrayAcess node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getArray ().accept (this);
//        System.out.println (indent.toString () + node.getPosArray ());
        node.getSubscript ().accept (this);
//        System.out.println (indent.toString () + node.getPosSubscript ());
        subIndent ();
    }

    @Override
    public void visit (MemberAcess node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getExpr ().accept (this);
//        System.out.println (indent.toString () + node.getPosExpr ());
        System.out.println (indent.toString () + node.getName ());
//        System.out.println (indent.toString () + node.getPosName ());
        subIndent ();
    }

    @Override
    public void visit (NewExpr node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getNewObject ().accept (this);
//        System.out.println (indent.toString () + node.getPosNewObject ());
        subIndent ();
    }

    @Override
    public void visit (UnaryExpr node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getOp ());
        node.getExpr ().accept (this);
//        System.out.println (indent.toString () + node.getPosExpr ());
        subIndent ();
    }

    @Override
    public void visit (BinaryExpr node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getOp ());
//        System.out.println (indent.toString () + node.getPosOp ());
        node.getLhs ().accept (this);
//        System.out.println (indent.toString () + node.getPosLhs ());
        node.getRhs ().accept (this);
//        System.out.println (indent.toString () + node.getPosRhs ());
        subIndent ();
    }

    @Override
    public void visit (Identifier node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getName ());
        subIndent ();
    }

    @Override
    public void visit (NewArray node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getType ().accept (this);
        node.getLens ().forEach (x -> x.accept (this));
        subIndent ();
    }

    @Override
    public void visit (NewNonArray node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        node.getType ().accept (this);
        node.getParameters ().forEach (x -> x.accept (this));
        subIndent ();
    }

    @Override
    public void visit (BoolConst node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getValue ());
        subIndent ();
    }

    @Override
    public void visit (NumConst node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getNum ());
        subIndent ();
    }

    @Override
    public void visit (StrConst node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        System.out.println (indent.toString () + node.getStr ());
        subIndent ();
    }

    @Override
    public void visit (NullConst node) {
        System.out.println (indent.toString () + node.getClass ().getSimpleName ());
        addIndent ();
        subIndent ();
    }
}
