package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class BreakStmt extends AbstractJumpStmt{
    private final SourcePosition posBreak;

    public BreakStmt(SourcePosition posBreak){
        this.posBreak = posBreak;
    }

    public SourcePosition getPosBreak(){
        return posBreak;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
