package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class NumConst extends AbstractConst{
    private final Integer num;

    public NumConst(Integer num){
        this.num = num;
    }

    public Integer getNum(){
        return num;
    }

    @Override
    public void accept(IASTVistor visitor){
        visitor.visit(this);
    }
}
