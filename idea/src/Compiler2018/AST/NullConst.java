package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class NullConst extends AbstractConst {
    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
