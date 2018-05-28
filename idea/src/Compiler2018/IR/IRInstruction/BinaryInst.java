package Compiler2018.IR.IRInstruction;

import Compiler2018.IR.IRValue.AbstractValue;
import Compiler2018.IR.IRStructure.BasicBlock;

public class BinaryInst extends AbstractIRInstruction{
    public enum BinaryOp {
        ASSIGN, LOGICAL_OR, LOGICAL_AND, BITWISE_OR, BITWISE_AND, XOR,
        EQ, NE, LT, GT, LE, GE,
        LEFT_SHIFT, RIGHT_SHIFT,
        ADD, SUB, MUL, DIV, MOD
    }

    private final BinaryOp op;
    private final AbstractValue dest;
    private final AbstractValue lhs;
    private final AbstractValue rhs;

    public BinaryInst(BasicBlock basicBlock, BinaryOp op, AbstractValue dest, AbstractValue lhs, AbstractValue rhs) {
        super(basicBlock);
        this.op = op;
        this.dest = dest;
        this.lhs = lhs;
        this.rhs = rhs;
    }
}
