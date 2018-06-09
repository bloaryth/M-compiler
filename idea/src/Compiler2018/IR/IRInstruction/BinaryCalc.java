package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

public class BinaryCalc extends AbstractIRInstruction {
    public enum BinaryOp{
        ADD, SUB, MUL, DIV, MOD,
        LSH, RSH, BAND, BOR, XOR
    }

    private final BinaryOp oprator;
    private final Register destination;
    private final Register leftOperand;
    private final boolean leftStar;
    private final Register rightOperand;
    private final boolean rightStar;
    private final Register intermediate;

    public BinaryCalc(BasicBlock basicBlock, BinaryOp oprator, Register destination, Register leftOperand, boolean leftStar, Register rightOperand, boolean rightStar) {
        super(basicBlock);
        this.oprator = oprator;
        this.destination = destination;
        this.leftOperand = leftOperand;
        this.leftStar = leftStar;
        this.rightOperand = rightOperand;
        this.rightStar = rightStar;
        intermediate = new Register();  // avoid dest rsp share the same register
    }

    public BinaryOp getOprator() {
        return oprator;
    }

    public Register getDestination() {
        return destination;
    }

    public Register getLeftOperand() {
        return leftOperand;
    }

    public boolean isLeftStar() {
        return leftStar;
    }

    public Register getRightOperand() {
        return rightOperand;
    }

    public boolean isRightStar() {
        return rightStar;
    }

    public Register getIntermediate() {
        return intermediate;
    }

    @Override
    public String toIRString() {
        String str = "\t" +
                oprator.toString() + " " +
                destination.toIRString() + " " +
                leftOperand.toIRString(leftStar) + " " +
                rightOperand.toIRString(rightStar) + "\n";
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
            usedRegisterList.add(leftOperand);
            usedRegisterList.add(rightOperand);
        }
        return usedRegisterList;
    }
}
