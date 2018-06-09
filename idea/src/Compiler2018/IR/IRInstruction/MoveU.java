package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.AbstractValue;
import Compiler2018.IR.IRValue.Register;

import java.util.List;
import java.util.Map;

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
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }


    @Override
    public Register getDefinedRegister() {
        return lhs;
    }

    @Override
    public List<Register> getUsedRegisterList() {
        return null;
    }

    @Override
    public AbstractIRInstruction partClone(Map<Register, Register> renameMap) {
        return new MoveU(super.getBasicBlock(), rename(renameMap, lhs), rhs);
    }
}
