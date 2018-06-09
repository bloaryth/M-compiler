package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

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
            throw new RuntimeException();
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
        if (lhs == null | rhs == null) {
            throw new RuntimeException("hhh");
        }
        String str = "\tMOV " +
                lhs.toIRString(lhsStar) + " " +
                rhs.toIRString(rhsStar) + "\n";
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

    @Override
    public Register getDefinedRegister() {
        if (lhsStar) {
            return null;
        } else {
            return lhs;
        }
    }

    private List<Register> usedRegisterList = null;

    @Override
    public List<Register> getUsedRegisterList() {
        if (usedRegisterList == null) {
            usedRegisterList = new LinkedList<>();
            usedRegisterList.add(rhs);
            if (lhsStar) {
                usedRegisterList.add(lhs);
            }
        }
        return usedRegisterList;
    }
}
