package Compiler2018.IR.IRInstruction;

import Compiler2018.BackEnd.IIRVistor;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedList;
import java.util.List;

public class Compare extends AbstractIRInstruction {
    public enum CompareOp {
        EQ, NE, LT, GT, LE, GE
    }

    private final CompareOp oprator;
    private final Register leftOperand;
    private final boolean leftStar;
    private final Register rightOperand;
    private final boolean rightStar;
    private final Register intermediate;

    public Compare(BasicBlock basicBlock, CompareOp oprator, Register leftOperand, boolean leftStar, Register rightOperand, boolean rightStar) {
        super(basicBlock);
        this.oprator = oprator;
        this.leftOperand = leftOperand;
        this.leftStar = leftStar;
        this.rightOperand = rightOperand;
        this.rightStar = rightStar;

        if (leftStar && rightStar) {
            intermediate = new Register();
        } else {
            intermediate = null;
        }
    }

    public CompareOp getOprator() {
        return oprator;
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
    public String toIRString(){
        String str = "\t" +
                oprator.toString() + " " +
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
        return null;
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
