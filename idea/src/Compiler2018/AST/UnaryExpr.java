package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class UnaryExpr extends AbstractExpr{
    public enum UnaryOp {
        PREFIX_INC, PREFIX_DEC, POSTFIX_INC, POSTFIX_DEC, POS, NEG, LOGICAL_NOT, BITWISE_NOT
    }

    private final UnaryOp op;
    private final AbstractExpr expr;
    private final SourcePosition posExpr;

    public UnaryExpr (UnaryOp op, AbstractExpr expr, SourcePosition posExpr) {
        this.op = op;
        this.expr = expr;
        this.posExpr = posExpr;
    }

    public UnaryOp getOp () {
        return op;
    }

    public AbstractExpr getExpr () {
        return expr;
    }

    public SourcePosition getPosExpr () {
        return posExpr;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
