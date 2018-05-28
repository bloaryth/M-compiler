package Compiler2018.IR.IRValue;

import Compiler2018.AST.ClassType;

public class Register extends AbstractValue {
    private final ClassType type;
    private final String name;  // != null if is a varSymbol

    public Register(ClassType type, String name) {
        this.type = type;
        this.name = name;
    }

    public ClassType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    enum PysicalReg {
        RAX, RBX, RCX, RDX, RDI, RSI, RBP, RSP,
        R8, R9, R10, R11, R12, R13, R14, R15
    }

    // allocation
//    private Function function;
    private int offset; // offset in mem
    private PysicalReg allocReg;  // Pysical Reg Allocated

    public boolean isPtr(){
        return type.isPtr();
    }

}
