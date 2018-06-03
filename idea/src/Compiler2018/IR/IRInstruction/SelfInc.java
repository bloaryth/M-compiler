package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

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
    void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
