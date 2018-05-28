package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class Identifier extends AbstractExpr {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
