package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.List;

public class Jump extends AbstractIRInstruction {
    private final BasicBlock jumpBlock;

    public Jump(BasicBlock basicBlock, BasicBlock jumpBlock) {
        super(basicBlock);
        this.jumpBlock = jumpBlock;
    }

    public BasicBlock getJumpBlock() {
        return jumpBlock;
    }

    @Override
    public String toIRString() {
        return "\tJUMP " + jumpBlock.getProcessedName() + "\n";
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }

    @Override
    public Register getDefinedRegister() {
        return null;
    }

    @Override
    public List<Register> getUsedRegisterList() {
        return null;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        return new Jump(super.getBasicBlock(), jumpBlock);
    }
}
