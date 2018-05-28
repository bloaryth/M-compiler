package Compiler2018.Symbol;

import Compiler2018.AST.ClassType;
import Compiler2018.AST.VarDecl;
import Compiler2018.IR.IRValue.Register;

public class VarSymbol extends AbstractSymbol {
    private final ClassType type;
    private final String name;
    private final Register virtualReg;  // == null means a class var | != 0 means a function var
    private final int offset; // used in class var access

    // Function Variable
    public VarSymbol(ClassType type, String name) {
        this.type = type;
        this.name = name;
        virtualReg = new Register(type, name);
        this.offset = 0;
    }

    public VarSymbol(VarDecl decl){
        type = decl.getType();
        name = decl.getName();
        virtualReg = new Register(decl.getType(), decl.getName());
        offset = 0;
    }

    // Class Variable
    public VarSymbol(VarDecl decl, ClassTable table) {
        type = decl.getType();
        name = decl.getName();
        virtualReg = null;  // Val is in memory, so there is no virtual register    // FIXME when it is a ptr
        offset = table.getOffset();
        table.addOffset(8); // all 8 bytes long
    }

    public ClassType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Register getVirtualReg() {
        return virtualReg;
    }

    public int getOffset() {
        return offset;
    }
}
