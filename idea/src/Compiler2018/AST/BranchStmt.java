package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class BranchStmt extends AbstractStmt{
    private final AbstractExpr cond;
    private final AbstractStmt ifStmt;
    private final AbstractStmt elseStmt;  // can be null
    private final SourcePosition posBranch;

    public BranchStmt(AbstractExpr cond, AbstractStmt ifStmt, AbstractStmt elseStmt, SourcePosition posBranch){
        this.cond = cond;
        this.ifStmt = ifStmt;
        this.elseStmt = elseStmt;
        this.posBranch = posBranch;
    }

    public AbstractExpr getCond(){
        return cond;
    }

    public AbstractStmt getIfStmt(){
        return ifStmt;
    }

    public AbstractStmt getElseStmt(){
        return elseStmt;
    }

    public SourcePosition getPosBranch(){
        return posBranch;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
