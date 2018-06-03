package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.AbstractValue;
import Compiler2018.IR.IRValue.Register;

// into register
public class MoveU extends AbstractIRInstruction{
    private final Register lhs;
    private final AbstractValue rhs; // Immediate or Label

    public MoveU(BasicBlock basicBlock, Register lhs, AbstractValue rhs) {
        super(basicBlock);
        this.lhs = lhs;
        this.rhs = rhs;
    }

    public Register getLhs() {
        return lhs;
    }

    public AbstractValue getRhs() {
        return rhs;
    }

    @Override
    public String toIRString() {
        String str = "\tMOVU " +
                lhs.toIRString() + " " +
                rhs.toIRString() + "\n";
        return str;
    }

    @Override
    void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
