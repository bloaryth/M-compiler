package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class BoolConst extends AbstractConst {
    private final Boolean value;

    public BoolConst(Boolean value) {
        this.value = value;
    }

    public Boolean getValue() {
        return value;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
