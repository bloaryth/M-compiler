package Compiler2018.IR.IRInstruction;

import Compiler2018.IR.IRValue.AbstractValue;

public class UnaryInst {
    public enum UnaryOp {
        PREFIX_INC, PREFIX_DEC,
        POSTFIX_INC, POSTFIX_DEC,
        POS, NEG,
        LOGICAL_NOT, BITWISE_NOT
    }

    private final UnaryOp op;
    private final AbstractValue dest;
    private final AbstractValue lhs;

    public UnaryInst(UnaryOp op, AbstractValue dest, AbstractValue lhs) {
        this.op = op;
        this.dest = dest;
        this.lhs = lhs;
    }
}
