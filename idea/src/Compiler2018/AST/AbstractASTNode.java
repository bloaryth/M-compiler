package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public abstract class AbstractASTNode{
    public abstract void accept(IASTVistor visitor);
}
