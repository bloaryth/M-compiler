package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ExprStmt extends AbstractStmt {
    private final AbstractExpr expr;
    private final SourcePosition posExpr;

    public ExprStmt(AbstractExpr expr, SourcePosition posExpr) {
        this.expr = expr;
        this.posExpr = posExpr;
    }

    public AbstractExpr getExpr() {
        return expr;
    }

    public SourcePosition getPosExpr() {
        return posExpr;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
