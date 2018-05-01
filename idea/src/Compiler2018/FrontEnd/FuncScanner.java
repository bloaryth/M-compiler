package Compiler2018.FrontEnd;

import Compiler2018.AST.*;
import Compiler2018.Symbol.*;

public class FuncScanner implements IASTVistor {
    private TopTable topTable;

    public FuncScanner (TopTable topTable) {
        this.topTable = topTable;
    }

    // built-in function
    private void addFuncPrint (){
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("void", 0));
        builder.setName ("print");
        builder.addParameter ("str", new VarSymbol ("string", 0, "str"));
        builder.setBlockTable (new BlockTable (topTable));
        topTable.addFunc ("print", builder.build ());
    }

    private void addFuncPrintln () {
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("void", 0));
        builder.setName ("println");
        builder.addParameter ("str", new VarSymbol ("string", 0, "str"));
        builder.setBlockTable (new BlockTable (topTable));
        topTable.addFunc ("println", builder.build ());
    }

    private void addFuncGetString () {
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("string", 0));
        builder.setName ("getString");
        builder.setBlockTable (new BlockTable (topTable));
        topTable.addFunc ("getString", builder.build ());
    }

    private void addFuncGetInt () {
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("int", 0));
        builder.setName ("getInt");
        builder.setBlockTable (new BlockTable (topTable));
        topTable.addFunc ("getInt", builder.build ());
    }

    private void addFuncToString () {
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("string", 0));
        builder.setName ("toString");
        builder.addParameter ("i", new VarSymbol ("int", 0, "i"));
        builder.setBlockTable (new BlockTable (topTable));
        topTable.addFunc ("toString", builder.build ());
    }

    // string built-in function
    private void addFuncLength(AbstractSymbolTable stringInClassTable){
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("int", 0));
        builder.setName ("length");
        builder.setBlockTable (new BlockTable (topTable));
        stringInClassTable.addFunc ("length", builder.build ());
    }

    private void addFuncSubstring(AbstractSymbolTable stringInClassTable){
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("string", 0));
        builder.setName ("substring");
        builder.addParameter ("left", new VarSymbol ("int", 0, "left"));
        builder.addParameter ("right", new VarSymbol ("int", 0, "right"));
        builder.setBlockTable (new BlockTable (topTable));
        stringInClassTable.addFunc ("substring", builder.build ());
    }

    private void addFuncParseInt(AbstractSymbolTable stringInClassTable){
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("int", 0));
        builder.setName ("parseInt");
        builder.setBlockTable (new BlockTable (topTable));
        stringInClassTable.addFunc ("parseInt", builder.build ());
    }

    private void addFuncOrd(AbstractSymbolTable stringInClassTable){
        FuncSymbol.Builder builder = new FuncSymbol.Builder ();
        builder.setReturnType (new ClassType ("int", 0));
        builder.setName ("ord");
        builder.addParameter ("pos", new VarSymbol ("int", 0, "pos"));
        builder.setBlockTable (new BlockTable (topTable));
        stringInClassTable.addFunc ("ord", builder.build ());
    }

    @Override
    public void visit (Program node) {
        // built-in function
        addFuncPrint ();
        addFuncPrintln ();
        addFuncGetString ();
        addFuncGetInt ();
        addFuncToString ();

        // string built-in function
        AbstractSymbolTable stringInClassTable = topTable.getMyClass ("string").getInClassTable ();
        addFuncLength (stringInClassTable);
        addFuncSubstring (stringInClassTable);
        addFuncParseInt (stringInClassTable);
        addFuncOrd (stringInClassTable);

        node.getSections ().forEach (x -> x.accept (this));
    }

    @Override
    public void visit (ClassDecl node) {

    }

    @Override
    public void visit (FuncDecl node) {
        if(topTable.getMyClass (node.getReturnType ().getBaseType ()) == null){
            throw new RuntimeException ("returnType is not declared.");
        }
        // function overload is not permitted
        if (topTable.getFunc (node.getName ()) != null){
            throw new RuntimeException ("Function is previously declared.");
        }
        if (node.getName ().equals ("this")){
            throw new RuntimeException ("Reserved keyword.");
        }
        // TODO
        topTable.addFunc (node.getName (), new FuncSymbol (node, new BlockTable (topTable)));
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
