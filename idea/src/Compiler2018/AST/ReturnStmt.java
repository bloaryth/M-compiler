package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ReturnStmt extends AbstractJumpStmt{
    private final AbstractExpr expr;
    private final SourcePosition posReturn;

    public ReturnStmt(AbstractExpr expr, SourcePosition posReturn){
        this.expr = expr;
        this.posReturn = posReturn;
    }

    public AbstractExpr getExpr(){
        return expr;
    }

    public SourcePosition getPosReturn(){
        return posReturn;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
