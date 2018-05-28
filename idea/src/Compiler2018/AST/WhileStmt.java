package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class WhileStmt extends AbstractLoopStmt {
    private final AbstractExpr cond;
    private final AbstractStmt stmt;
    private final SourcePosition posCond;

    public WhileStmt(AbstractExpr cond, AbstractStmt stmt, SourcePosition posCond) {
        this.cond = cond;
        this.stmt = stmt;
        this.posCond = posCond;
    }

    public AbstractExpr getCond() {
        return cond;
    }

    public AbstractStmt getStmt() {
        return stmt;
    }

    public SourcePosition getPosCond() {
        return posCond;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
