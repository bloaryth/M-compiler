package Compiler2018.FrontEnd.Semantic;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.Symbol.*;

import java.util.Stack;

public class FuncScanner implements IASTVistor {
    private final TopTable topTable;
    private final Stack<AbstractSymbolTable> currentTable = new Stack<>();
    private ClassSymbol classSymbol = null;

    public FuncScanner(TopTable topTable) {
        this.topTable = topTable;
    }

    // FIXME built-in function varSymbol setIRInfo | write asm
    // built-in function
    private void addFuncPrint() {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(topTable);
        builder.setReturnType(new ClassType("void", 0));
        builder.setName("print");
        BlockTable table = new BlockTable(topTable, "");
        builder.addParameter("str", new VarSymbol(table, new ClassType("string", 0), "str")); // setIRInfo ? FIXME
        builder.setBlockTable(table);
        topTable.addFunc("print", builder.build());
    }

    private void addFuncPrintln() {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(topTable);
        builder.setReturnType(new ClassType("void", 0));
        builder.setName("println");
        BlockTable table = new BlockTable(topTable, "");
        builder.addParameter("str", new VarSymbol(table, new ClassType("string", 0), "str")); // setIRInfo ? FIXME
        builder.setBlockTable(table);
        topTable.addFunc("println", builder.build());
    }

    private void addFuncGetString() {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(topTable);
        builder.setReturnType(new ClassType("string", 0));
        builder.setName("getString");
        builder.setBlockTable(new BlockTable(topTable, ""));
        topTable.addFunc("getString", builder.build());
    }

    private void addFuncGetInt() {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(topTable);
        builder.setReturnType(new ClassType("int", 0));
        builder.setName("getInt");
        builder.setBlockTable(new BlockTable(topTable, ""));
        topTable.addFunc("getInt", builder.build());
    }

    private void addFuncToString() {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(topTable);
        builder.setReturnType(new ClassType("string", 0));
        builder.setName("toString");
        BlockTable table = new BlockTable(topTable, "");
        builder.addParameter("i", new VarSymbol(table, new ClassType("int", 0), "i")); // setIRInfo ? FIXME
        builder.setBlockTable(table);
        topTable.addFunc("toString", builder.build());
    }

    // string built-in function
    private void addFuncLength(AbstractSymbolTable stringInClassTable) {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(stringInClassTable);
        builder.setReturnType(new ClassType("int", 0));
        builder.setName("length");
        BlockTable table = new BlockTable(stringInClassTable, "");
//        builder.addParameter("this", new VarSymbol(table, new ClassType("string", 0), "this"));
        builder.setBlockTable(table);
        stringInClassTable.addFunc("length", builder.build());
    }

    private void addFuncSubstring(AbstractSymbolTable stringInClassTable) {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(stringInClassTable);
        builder.setReturnType(new ClassType("string", 0));
        builder.setName("substring");
        BlockTable table = new BlockTable(stringInClassTable, "");
//        builder.addParameter("this", new VarSymbol(table, new ClassType("string", 0), "this"));
        builder.addParameter("left", new VarSymbol(table, new ClassType("int", 0), "left")); // setIRInfo ? FIXME
        builder.addParameter("right", new VarSymbol(table, new ClassType("int", 0), "right")); // setIRInfo ? FIXME
        builder.setBlockTable(table);
        stringInClassTable.addFunc("substring", builder.build());
    }

    private void addFuncParseInt(AbstractSymbolTable stringInClassTable) {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(stringInClassTable);
        builder.setReturnType(new ClassType("int", 0));
        builder.setName("parseInt");
        builder.setBlockTable(new BlockTable(stringInClassTable, ""));
        stringInClassTable.addFunc("parseInt", builder.build());
    }

    private void addFuncOrd(AbstractSymbolTable stringInClassTable) {
        FuncSymbol.Builder builder = new FuncSymbol.Builder();
        builder.setBelongTable(stringInClassTable);
        builder.setReturnType(new ClassType("int", 0));
        builder.setName("ord");
        BlockTable table = new BlockTable(stringInClassTable, "");
        builder.addParameter("pos", new VarSymbol(table, new ClassType("int", 0), "pos")); // setIRInfo ? FIXME
        builder.setBlockTable(table);
        stringInClassTable.addFunc("ord", builder.build());
    }

    @Override
    public void visit(Program node) {
        // built-in function
        addFuncPrint();
        addFuncPrintln();
        addFuncGetString();
        addFuncGetInt();
        addFuncToString();

        // string built-in function
        AbstractSymbolTable stringInClassTable = topTable.getMyClass("string").getInClassTable();
        addFuncLength(stringInClassTable);
        addFuncSubstring(stringInClassTable);
        addFuncParseInt(stringInClassTable);
        addFuncOrd(stringInClassTable);

        currentTable.push(topTable);
        node.getSections().forEach(x -> x.accept(this));
        currentTable.pop();

        if (topTable.getFunc("main") == null) {
            throw new RuntimeException("main() required.");
        }
        if (!topTable.getFunc("main").getReturnType().equals(new ClassType("int", 0))) {
            throw new RuntimeException("main() should return int.");
        }
        if (topTable.getFunc("main").getIntParameters().size() != 0) {
            throw new RuntimeException("main() should contain zero parameter.");
        }
    }

    @Override
    public void visit(ClassDecl node) {
        classSymbol = topTable.getMyClass(node.getName());
        currentTable.push(classSymbol.getInClassTable());
        node.getItems().forEach(x -> x.accept(this));
        currentTable.pop();
        classSymbol = null;
    }

    @Override
    public void visit(FuncDecl node) {
        // TODO overload
        // name
        if (node.getName().equals("this")) {
            throw new RuntimeException("'this' should not be a function name.");
        }
        if (classSymbol == null && topTable.getMyClass(node.getName()) != null) {
            throw new RuntimeException("Global function name collides with class.");
        }
        if (currentTable.peek().getFunc(node.getName()) != null) {
            throw new RuntimeException("IRFunction is previously declared.");
        }
        // parameter
        node.getParameters()
                .forEach(
                        x -> {
                            if (topTable.getMyClass(x.getType().getBaseType()) == null) {
                                throw new RuntimeException("Undeclared class occurred.");
                            }
                        });
        // returnType
        if (topTable.getMyClass(node.getReturnType().getBaseType()) == null) {
            throw new RuntimeException("Undeclared class occurred.");
        }

        FuncSymbol funcSymbol = new FuncSymbol(currentTable.peek(), node, new BlockTable(currentTable.peek(), node.getName()));
        node.setFuncSymbol(funcSymbol); // prepare for IR Generation
        currentTable.peek().addFunc(node.getName(), funcSymbol);
    }

    @Override
    public void visit(VarDecl node) {
    }

    @Override
    public void visit(ClassVarDecl node) {
    }

    @Override
    public void visit(ClassCstrDecl node) {
        // TODO overload
        // name
        if (node.getName().equals("this")) {
            throw new RuntimeException("'this' should not be a function name.");
        }
        if (currentTable.peek().getFunc(node.getName()) != null) {
            throw new RuntimeException("IRClass constructor previously declared.");
        }
        if (!classSymbol.getName().equals(node.getName())) {
            throw new RuntimeException("IRClass constructor should be the same name with class.");
        }
        // parameter
        node.getParameters()
                .forEach(
                        x -> {
                            if (topTable.getMyClass(x.getType().getBaseType()) == null) {
                                throw new RuntimeException("Undeclared class occurred.");
                            }
                        });
        CstrSymbol cstrSymbol = new CstrSymbol(currentTable.peek(), node, new BlockTable(currentTable.peek(), node.getName()));
        node.setCstrSymbol(cstrSymbol);
        currentTable.peek().addCstr(node.getName(), cstrSymbol);
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
