package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ReturnStmt extends AbstractJumpStmt {
    private final AbstractExpr expr;
    private final SourcePosition posReturn;

    public ReturnStmt(AbstractExpr expr, SourcePosition posReturn) {
        this.expr = expr;
        this.posReturn = posReturn;
    }

    public AbstractExpr getExpr() {
        return expr;
    }

    public SourcePosition getPosReturn() {
        return posReturn;
    }

    // prepare for IR Generation
    private ClassType classType;

    public ClassType getClassType() {
        return classType;
    }

    public void setClassType(ClassType classType) {
        this.classType = classType;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
