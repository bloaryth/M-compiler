package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

public class Ret extends AbstractIRInstruction {
    private final Register register; // ret addr

    public Ret(BasicBlock basicBlock, Register register) {
        super(basicBlock);
        this.register = register;
    }

    public Register getRegister() {
        return register;
    }

    @Override
    public String toIRString(){
        String ret = register == null ? "void" : register.toIRString();
        String str = "\tRET " + ret + "\n";
        return str;
    }

    @Override
    void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
