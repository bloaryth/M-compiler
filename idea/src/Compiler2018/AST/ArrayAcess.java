package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ArrayAcess extends AbstractExpr {
    private final AbstractExpr array;
    private final AbstractExpr subscript;
    private final SourcePosition posArray;
    private final SourcePosition posSubscript;

    public ArrayAcess (AbstractExpr array, AbstractExpr subscript, SourcePosition posArray, SourcePosition posSubscript) {
        this.array = array;
        this.subscript = subscript;
        this.posArray = posArray;
        this.posSubscript = posSubscript;
    }

    public AbstractExpr getArray () {
        return array;
    }

    public AbstractExpr getSubscript () {
        return subscript;
    }

    public SourcePosition getPosArray () {
        return posArray;
    }

    public SourcePosition getPosSubscript () {
        return posSubscript;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
