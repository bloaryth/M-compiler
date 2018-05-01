package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class EmptyStmt extends AbstractStmt{
    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
