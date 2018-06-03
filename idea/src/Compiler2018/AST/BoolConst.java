package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class BoolConst extends AbstractConst {
    private final boolean value;

    public BoolConst(boolean value) {
        this.value = value;
    }

    public boolean getValue() {
        return value;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
