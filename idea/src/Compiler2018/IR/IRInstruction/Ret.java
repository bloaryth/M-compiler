package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

public class Ret extends AbstractIRInstruction {
    private final Register ret; // ret addr

    public Ret(BasicBlock basicBlock, Register ret) {
        super(basicBlock);
        this.ret = ret;
    }

    public Register getRet() {
        return ret;
    }

    @Override
    public String toIRString(){
        String ret = this.ret == null ? "void" : this.ret.toIRString();
        String str = "\tRET " + ret + "\n";
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

    @Override
    public Register getDefinedRegister() {
        return null;
    }

    private List<Register> usedRegisterList = null;

    @Override
    public List<Register> getUsedRegisterList() {
        if (usedRegisterList == null) {
            usedRegisterList = new LinkedList<>();
            usedRegisterList.add(ret);
        }
        return usedRegisterList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Ret(super.getBasicBlock(), ret);
    }
}
