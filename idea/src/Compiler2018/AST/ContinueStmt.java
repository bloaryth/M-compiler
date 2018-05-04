package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ContinueStmt extends AbstractJumpStmt{
    private final SourcePosition posContinue;

    public ContinueStmt(SourcePosition posContinue){
        this.posContinue = posContinue;
    }

    public SourcePosition getPosContinue(){
        return posContinue;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
