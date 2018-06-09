package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

public class SelfInc extends AbstractIRInstruction{
    private final Register dest;
    private final boolean star;
    private final Integer inc; // 1 or -1

    public SelfInc(BasicBlock basicBlock, Register dest, boolean star, Integer inc) {
        super(basicBlock);
        this.dest = dest;
        this.star = star;
        this.inc = inc;
    }

    public Register getDest() {
        return dest;
    }

    public boolean isStar() {
        return star;
    }

    public Integer getInc() {
        return inc;
    }

    @Override
    public String toIRString(){
        String str = "\tADD " +
                dest.toIRString(star) + " " +
                inc.toString() + '\n';
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

    @Override
    public Register getDefinedRegister() {
        if (star) {
            return null;
        } else {
            return dest;
        }
    }

    private List<Register> usedRegisterList = null;

    @Override
    public List<Register> getUsedRegisterList() {
        if (usedRegisterList == null) {
            usedRegisterList = new LinkedList<>();
            usedRegisterList.add(dest);
        }
        return usedRegisterList;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new SelfInc(super.getBasicBlock(), dest, star, inc);
    }
}
