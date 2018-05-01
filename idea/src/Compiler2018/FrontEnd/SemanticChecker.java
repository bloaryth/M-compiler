package Compiler2018.FrontEnd;

import Compiler2018.AST.*;
import Compiler2018.Symbol.*;

import java.util.Stack;

public class SemanticChecker implements IASTVistor {
    private final TopTable topTable;
    private final Stack<AbstractSymbolTable> currentTable = new Stack<> ();
    private Integer loopScope = 0;
    private FuncSymbol funcScope = null;

    public SemanticChecker (TopTable topTable) {
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
        currentTable.push (topTable.getMyClass (node.getName ()).getInClassTable ());
        node.getItems ().forEach (x -> x.accept (this));
        currentTable.pop ();
    }

    // for Global
    @Override
    public void visit (FuncDecl node) {
        funcScope = currentTable.peek ().getFunc (node.getName ());
        currentTable.push (currentTable.peek ().getFunc (node.getName ()).getBlockTable ());
        node.getBlock ().accept (this);
        currentTable.pop ();
        funcScope = null;
    }

    // for both Global and Local
    @Override
    public void visit (VarDecl node) {
        if (node.getInit () != null) {
            node.getInit ().accept (this);
        }
        if (topTable.getMyClass (node.getType ().getBaseType ()) == null){
            throw new RuntimeException ("Undefined Class in Variable Declaration.");
        }
        if (node.getType ().getBaseType ().equals ("void")){
            throw new RuntimeException ("Void type should not be declared.");
        }
        if (node.getInit () != null){
            if (node.getInit ().getType ().getBaseType ().equals ("null")){
                if (node.getType ().getDim () == 0){
                    throw new RuntimeException ("null should not be assigned to NonArray.");
                }
            }
            if(!node.getType ().equals (node.getInit ().getType ())){
                throw new RuntimeException ("Init Type mismatch..");
            }
        }
        if (currentTable.peek ().getVar (node.getName ()) != null){
            throw new RuntimeException ("Variable is previously declared.");
        }
        currentTable.peek ().addVar (node.getName (), new VarSymbol (node));
    }

    @Override
    public void visit (ClassVarDecl node) {

    }

    @Override
    public void visit (ClassCstrDecl node) {
        currentTable.push (currentTable.peek ().getCstr (node.getName ()).getBlockTable ());
        node.getBlock ().accept (this);
        currentTable.pop ();
    }

    @Override
    public void visit (ClassFuncDecl node) {
        currentTable.push (currentTable.peek ().getFunc (node.getDecl ().getName ()).getBlockTable ());
        node.getDecl ().getBlock ().accept (this);
        currentTable.pop ();
    }

    @Override
    public void visit (BlockStmt node) {
        node.getStmts ().forEach (x -> x.accept (this));
    }

    // Local
    @Override
    public void visit (VarDeclStmt node) {
        node.getDecl ().accept (this);
    }

    @Override
    public void visit (BranchStmt node) {
        node.getCond ().accept (this);
        currentTable.push (new BlockTable (currentTable.peek ()));
        node.getIfStmt ().accept (this);
        currentTable.pop ();
        if (node.getElseStmt () != null){
            currentTable.push (new BlockTable (currentTable.peek ()));
            node.getIfStmt ().accept (this);
            currentTable.pop ();
        }
    }

    @Override
    public void visit (ExprStmt node) {
        node.getExpr ().accept (this);
    }

    @Override
    public void visit (EmptyStmt node) {

    }

    @Override
    public void visit (ReturnStmt node) {
        if (funcScope == null){
            throw new RuntimeException ("Return should be in FuncScope");
        }
        // TODO
    }

    @Override
    public void visit (BreakStmt node) {
        if (loopScope == 0){
            throw new RuntimeException ("Break should be in LoopScope");
        }
    }

    @Override
    public void visit (ContinueStmt node) {
        if (loopScope == 0){
            throw new RuntimeException ("Continue should be in LoopScope");
        }
    }

    @Override
    public void visit (ForStmt node) {
        node.getInit ().accept (this);
        if (node.getCond () != null){
            node.getCond ().accept (this);
        }
        node.getStep ().accept (this);
        loopScope += 1;
        currentTable.push (new BlockTable (currentTable.peek ()));
        node.getStmt ().accept (this);
        currentTable.pop ();
        loopScope -= 1;
    }

    @Override
    public void visit (WhileStmt node) {
        node.getCond ().accept (this);
        currentTable.push (new BlockTable (currentTable.peek ()));
        node.getStmt ().accept (this);
        currentTable.pop ();
    }

    @Override
    public void visit (ClassType node) {

    }

    @Override
    public void visit (FunctionCall node) {
        // for built-in size
        if (node.getFunc () != null && node.getFunc ().getName ().equals ("size")){
            node.setType (node.getFunc ().getReturnType ());
            node.setLValue (false);
            return;
        }

        // normal function
        node.getName ().accept (this);
        node.getParameters ().forEach (x -> x.accept (this));
        for (int i = 0; i < node.getName ().getFunc ().getIntParameters ().size (); i++) {
            if (!node.getName ().getFunc ().getIntParameters ().get (i).getType ().equals (node.getParameters ().get (i).getType ())){
                throw new RuntimeException ("Parameter type mismatch.");
            }
        }
        ClassType classType = node.getName ().getFunc ().getReturnType ();
        node.setType (classType);
        switch (classType.getBaseType ()){
            case "int":
            case "bool":
            case "null":
            case "void":
                if(classType.getDim () == 0){
                    node.setLValue (false);
                }
        }
    }

    @Override
    public void visit (ArrayAcess node) {
        node.getArray ().accept (this);
        node.getSubscript ().accept (this);
        ClassType lType = node.getArray ().getType ();
        ClassType rType = node.getSubscript ().getType ();
        if (!rType.equals (new ClassType ("int", 0))){
            throw new RuntimeException ("Subscript should be int.");
        }
        if (lType.getDim () == 0){
            throw new RuntimeException ("ArrayType is required in ArrayAcess.");
        }
        node.setType (new ClassType (lType.getBaseType (), lType.getDim ()-1));
    }

    @Override
    public void visit (MemberAcess node) {
        node.getExpr ().accept (this);
        ClassType lType = node.getExpr ().getType ();
        String name = node.getName ();

        // for built-in size()
        if (name.equals ("size")){
            if (lType.getDim () == 0){
                throw new RuntimeException ("size() is for Array Type.");
            }
            FuncSymbol.Builder builder = new FuncSymbol.Builder ();
            builder.setName ("size");
            builder.setReturnType (new ClassType ("int", 0));
            node.setFunc (builder.build ());
            return;
        }

        // for normal member
        if (lType.getDim () != 0){
            throw new RuntimeException ("Array is not acceptable in MemberAcess.");
        }
        ClassSymbol symbol = topTable.getMyClass (node.getExpr ().getType ().getBaseType ());   // assert symbol cannot be null
        if (symbol == null) {
            throw new RuntimeException ("Class is not declared.");
        }

        // assert Var Func share the same scope.
        if (symbol.getInClassTable ().getFunc (name) != null) {
            node.setFunc (symbol.getInClassTable ().getFunc (name));
        }
        else if (symbol.getInClassTable ().getVar (name) != null){
            node.setType (symbol.getInClassTable ().getVar (name).getType ());
        }
        else{
            throw new RuntimeException (name + " is not a member.");
        }
    }

    @Override
    public void visit (NewExpr node) {
        node.getNewObject ().accept (this);
        node.setType (node.getNewObject ().getType ());
    }

    @Override
    public void visit (UnaryExpr node) {
        node.getExpr ().accept (this);
        switch (node.getOp ()){
            case POSTFIX_INC:
            case POSTFIX_DEC:
            case PREFIX_INC:
            case PREFIX_DEC:
                if (!node.getExpr ().getLValue ()){
                    throw new RuntimeException ("LValur required.");
                }
                break;
            case POS:
            case NEG:
            case BITWISE_NOT:
                if (!node.getExpr ().getType ().getBaseType ().equals ("int")){
                    throw new RuntimeException ("int required.");
                }
                break;
            case LOGICAL_NOT:
                if (!node.getExpr ().getType ().getBaseType ().equals ("bool")) {
                    throw new RuntimeException ("bool required");
                }
                break;
        }
        switch (node.getOp ()) {
            case POSTFIX_INC:
            case POSTFIX_DEC:
            case BITWISE_NOT:
            case LOGICAL_NOT:
            case POS:
            case NEG:
                node.setLValue (false);
            case PREFIX_INC:
            case PREFIX_DEC:
        }
    }

    @Override
    public void visit (BinaryExpr node) {
        node.getLhs ().accept (this);
        node.getRhs ().accept (this);
        switch (node.getOp ()) {
            case XOR:
            case BITWISE_OR:
            case BITWISE_AND:
            case LOGICAL_OR:
            case LOGICAL_AND:
            case LEFT_SHIFT:
            case RIGHT_SHIFT:
                if (node.getLhs ().getType ().getBaseType ().equals ("int") && node.getRhs ().getType ().getBaseType ().equals ("int")) {
                    break;
                }
                else{
                    throw new RuntimeException ("int required");
                }
            case EQ:
            case NE:
            case GE:
            case GT:
            case LE:
            case LT:
                if (node.getLhs ().getType ().getBaseType ().equals (node.getRhs ().getType ().getBaseType ())) {
                    switch (node.getLhs ().getType ().getBaseType ()) {
                        case "int":
                        case "string":
                            break;
                        case "bool":
                            switch (node.getOp ()){
                                case EQ:
                                case NE:
                                    break;
                                default:
                                    throw new RuntimeException ("bool does not support this operation.");
                            }
                        case "null":
                        case "void":
                        default:
                            throw new RuntimeException ("type does not support comparison.");
                    }
                }
                else{
                    throw new RuntimeException ("same type required.");
                }
                break;
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case MOD:
                if (node.getLhs ().getType ().getBaseType ().equals (node.getRhs ().getType ().getBaseType ())) {
                    switch (node.getLhs ().getType ().getBaseType ()) {
                        case "int":
                            break;
                        case "string":
                            if (node.getOp ().equals (BinaryExpr.BinaryOp.ADD)){
                                break;
                            }
                            else {
                                throw new RuntimeException ("string does not support this operation.");
                            }
                        case "null":
                        case "bool":
                        case "void":
                        default:
                            throw new RuntimeException ("type does not support basic arithmetic.");
                    }
                }
                break;
            case ASSIGN:
                if (node.getLhs ().getType ().getBaseType ().equals (node.getRhs ().getType ().getBaseType ())) {
                    if (node.getLhs ().getLValue ()) {
                        break;
                    }
                    else{
                        throw new RuntimeException ("Assign require LValue LHS");
                    }
                }
                else if(node.getRhs ().getType ().getBaseType ().equals ("null")){
                    if (node.getLhs ().getLValue () && node.getLhs ().getType ().getDim () > 0){
                        break;
                    }
                    else {
                        throw new RuntimeException ("LValue Array is required.");
                    }
                }
                else {
                    throw new RuntimeException ("same type required.");
                }
        }
        switch (node.getOp ()) {
            case XOR:
            case BITWISE_OR:
            case BITWISE_AND:
            case LOGICAL_OR:
            case LOGICAL_AND:
            case LEFT_SHIFT:
            case RIGHT_SHIFT:
            case ADD:
            case SUB:
            case MUL:
            case DIV:
            case MOD:
                node.setType (node.getRhs ().getType ());
                break;
            case EQ:
            case NE:
            case GE:
            case GT:
            case LE:
            case LT:
                node.setType (new ClassType ("bool",0));
                break;
            case ASSIGN:
                node.setType (node.getLhs ().getType ());
                break;
        }

        node.setLValue (false);     // also for assign
    }

    @Override
    public void visit (Identifier node) {
        // func
        FuncSymbol func = topTable.getFunc (node.getName ());
        if (func != null){
            node.setFunc (func);
            return;
        }

        // var
        VarSymbol var = currentTable.peek ().findVar (node.getName ());
        if (var != null) {
            node.setType (var.getType ());
            return;
        }

        throw new RuntimeException ("Identifier " + node.getName () + " is not declared.");
    }

    @Override
    public void visit (NewArray node) {
        node.getLens ().forEach (x -> x.accept (this));
        ClassSymbol myClass = topTable.getMyClass (node.getType ().getBaseType ());
        if (myClass == null) {
            throw new RuntimeException ("Class is not declared.");
        }
        node.getLens ().forEach (x -> {
            if (!x.getType ().getBaseType ().equals ("int") ||
                    x.getType ().getDim () != 0){
                throw new RuntimeException ("New Array Dim should be int.");
            }
        });
    }

    @Override
    public void visit (NewNonArray node) {
        node.getParameters ().forEach (x -> x.accept (this));
        ClassSymbol myClass = topTable.getMyClass (node.getType ().getBaseType ());
        if (myClass == null){
            throw new RuntimeException ("Class is not declared.");
        }
        CstrSymbol cstr = myClass.getInClassTable ().getCstr (myClass.getName ());
        for (int i = 0; i < cstr.getIntParameters ().size (); i++) {
            if ( ! cstr.getIntParameters ().get (i).getType ().equals (node.getParameters ().get (i).getType ())) {
                throw new RuntimeException ("Parameter" + i + "Type mismatch.");
            }
        }
    }

    @Override
    public void visit (BoolConst node) {
        node.setType (new ClassType ("bool", 0));
        node.setLValue (false);
    }

    @Override
    public void visit (NumConst node) {
        node.setType (new ClassType ("int", 0));
        node.setLValue (false);
    }

    @Override
    public void visit (StrConst node) {
        node.setType (new ClassType ("string", 0));
        node.setLValue (false);
    }

    @Override
    public void visit (NullConst node) {
        node.setType (new ClassType ("null", 0));
        node.setLValue (false);
    }
}
