package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

public class Move extends AbstractIRInstruction {
    private final Register lhs;
    private final boolean lhsStar; // ** * can be 2
    private final Register rhs;
    private final boolean rhsStar;    // rhs or *rhs or **rhs
    private final Register intermediate;

    public Move(BasicBlock basicBlock, Register lhs, boolean lhsStar, Register rhs, boolean rhsStar) {
        super(basicBlock);
        this.lhs = lhs;
        this.lhsStar = lhsStar;
        this.rhs = rhs;
        this.rhsStar = rhsStar;

        if (lhsStar && rhsStar) {
            intermediate = new Register();
        } else {
            intermediate = null;
        }
    }

    public Register getLhs() {
        return lhs;
    }

    public boolean isLhsStar() {
        return lhsStar;
    }

    public Register getRhs() {
        return rhs;
    }

    public boolean isRhsStar() {
        return rhsStar;
    }

    public Register getIntermediate() {
        return intermediate;
    }

    @Override
    public String toIRString() {
        String str = "\tMOV " +
                lhs.toIRString(lhsStar) + " " +
                rhs.toIRString(rhsStar) + "\n";
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
