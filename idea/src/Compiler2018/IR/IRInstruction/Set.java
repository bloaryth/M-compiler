package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

public class Set extends AbstractIRInstruction {
    private final Compare.CompareOp op;
    private final Register dest;
    private final boolean star;

    public Set(BasicBlock basicBlock, Compare.CompareOp op, Register dest, boolean star) {
        super(basicBlock);
        this.op = op;
        this.dest = dest;
        this.star = star;
    }

    public Compare.CompareOp getOp() {
        return op;
    }

    public Register getDest() {
        return dest;
    }

    public boolean isStar() {
        return star;
    }

    @Override
    public String toIRString() {
        String str = "\tSET=" + op.toString() + " " +
                dest.toIRString(star) + "\n";
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
