package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class NewExpr extends AbstractExpr{
    private final AbstractNewObject newObject;
    private final SourcePosition posNewObject;

    public NewExpr(AbstractNewObject newObject, SourcePosition posNewObject){
        this.newObject = newObject;
        this.posNewObject = posNewObject;
    }

    public AbstractNewObject getNewObject(){
        return newObject;
    }

    public SourcePosition getPosNewObject(){
        return posNewObject;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
