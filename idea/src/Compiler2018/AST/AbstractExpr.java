package Compiler2018.AST;

import Compiler2018.Symbol.FuncSymbol;

public abstract class AbstractExpr extends AbstractASTNode {
    // midterm check
    private ClassType type = null;
    private Boolean isLValue = true;
    private FuncSymbol func = null;

    public ClassType getType () {
        return type;
    }

    public Boolean getLValue () {
        return isLValue;
    }

    public FuncSymbol getFunc () {
        return func;
    }

    public void setType (ClassType type) {
        this.type = type;
    }

    public void setLValue (Boolean LValue) {
        isLValue = LValue;
    }

    public void setFunc (FuncSymbol func) {
        this.func = func;
    }
}
