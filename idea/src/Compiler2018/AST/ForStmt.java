package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ForStmt extends AbstractLoopStmt{
    private final AbstractExpr init;
    private final AbstractExpr cond;  // can be null
    private final AbstractExpr step;
    private final AbstractStmt stmt;
    private final SourcePosition posInit;
    private final SourcePosition posCond;   // can be null
    private final SourcePosition posStep;

    public ForStmt(AbstractExpr init, AbstractExpr cond, AbstractExpr step, AbstractStmt stmt, SourcePosition posInit, SourcePosition posCond, SourcePosition posStep){
        this.init = init;
        this.cond = cond;
        this.step = step;
        this.stmt = stmt;
        this.posInit = posInit;
        this.posCond = posCond;
        this.posStep = posStep;
    }

    public AbstractExpr getInit(){
        return init;
    }

    public AbstractExpr getCond(){
        return cond;
    }

    public AbstractExpr getStep(){
        return step;
    }

    public AbstractStmt getStmt(){
        return stmt;
    }

    public SourcePosition getPosInit(){
        return posInit;
    }

    public SourcePosition getPosCond(){
        return posCond;
    }

    public SourcePosition getPosStep(){
        return posStep;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
