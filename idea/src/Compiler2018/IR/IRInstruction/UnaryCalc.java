package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

public class UnaryCalc extends AbstractIRInstruction{
    public enum UnaryOp {
        NEG, BITWISE_NOT
    }

    private final UnaryOp oprator;
    private final Register destination;
    private final Register operand;
    private final boolean star;

    public UnaryCalc(BasicBlock basicBlock, UnaryOp oprator, Register destination, Register operand, boolean star) {
        super(basicBlock);
        this.oprator = oprator;
        this.destination = destination;
        this.operand = operand;
        this.star = star;
    }

    public UnaryOp getOprator() {
        return oprator;
    }

    public Register getDestination() {
        return destination;
    }

    public Register getOperand() {
        return operand;
    }

    public boolean isStar() {
        return star;
    }

    @Override
    public String toIRString() {
        String str = "\t" +
                oprator.toString() + " " +
                destination.toIRString() + " " +
                operand.toIRString(star) + "\n";
        return str;
    }

    @Override
    public void accept(IIRVistor vistor) {
        vistor.visit(this);
    }


    @Override
    public Register getDefinedRegister() {
        return destination;
    }

    private List<Register> usedRegisterList = null;

    @Override
    public List<Register> getUsedRegisterList() {
        if (usedRegisterList == null) {
            usedRegisterList = new LinkedList<>();
            usedRegisterList.add(operand);
        }
        return usedRegisterList;
    }
}
