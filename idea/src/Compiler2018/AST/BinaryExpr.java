package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class BinaryExpr extends AbstractExpr{
    public enum BinaryOp {
        ASSIGN,
        LOGICAL_OR, LOGICAL_AND,
        BITWISE_OR, BITWISE_AND, XOR,
        EQ, NE, LT, GT, LE, GE,
        LEFT_SHIFT, RIGHT_SHIFT,
        ADD, SUB,
        MUL, DIV, MOD
    }

    private final BinaryOp op;
    private final AbstractExpr lhs;
    private final AbstractExpr rhs;
    private final SourcePosition posOp;
    private final SourcePosition posLhs;
    private final SourcePosition posRhs;

    public BinaryExpr (BinaryOp op, AbstractExpr lhs, AbstractExpr rhs, SourcePosition posOp, SourcePosition posLhs, SourcePosition posRhs) {
        this.op = op;
        this.lhs = lhs;
        this.rhs = rhs;
        this.posOp = posOp;
        this.posLhs = posLhs;
        this.posRhs = posRhs;
    }

    public BinaryOp getOp () {
        return op;
    }

    public AbstractExpr getLhs () {
        return lhs;
    }

    public AbstractExpr getRhs () {
        return rhs;
    }

    public SourcePosition getPosOp () {
        return posOp;
    }

    public SourcePosition getPosLhs () {
        return posLhs;
    }

    public SourcePosition getPosRhs () {
        return posRhs;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
