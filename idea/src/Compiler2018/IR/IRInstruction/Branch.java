package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;

public class Branch extends AbstractIRInstruction {
    private final Compare cond;
    private final BasicBlock ifTrue;
    private final BasicBlock ifFalse;

    public Branch(BasicBlock basicBlock, Compare cond, BasicBlock ifTrue, BasicBlock ifFalse) {
        super(basicBlock);
        this.cond = cond;
        this.ifTrue = ifTrue;
        this.ifFalse = ifFalse;
    }

    public Compare getCond() {
        return cond;
    }

    public BasicBlock getIfTrue() {
        return ifTrue;
    }

    public BasicBlock getIfFalse() {
        return ifFalse;
    }

    @Override
    public String toIRString() {
        String str = cond.toIRString() +
                "\tBR " +
                cond.getDestination().toIRString() + " " +
                ifTrue.getProcessedName() + " " +
                ifFalse.getProcessedName() + "\n";
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }
}
