package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class StrConst extends AbstractConst {
    private final String str;

    public StrConst(String str) {
        this.str = str;
    }

    public String getStr() {
        return str;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
