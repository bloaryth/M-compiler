package Compiler2018.AST;

import Compiler2018.IR.IRValue.AbstractValue;
import Compiler2018.Symbol.AbstractSymbolTable;
import Compiler2018.Symbol.FuncSymbol;

public abstract class AbstractExpr extends AbstractASTNode {
    // semantic check
    private ClassType type = null;
    private Boolean isLValue = true;
    private FuncSymbol func = null;

    public ClassType getType() {
        return type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }

    public Boolean getLValue() {
        return isLValue;
    }

    public void setLValue(Boolean LValue) {
        isLValue = LValue;
    }

    public FuncSymbol getFunc() {
        return func;
    }

    public void setFunc(FuncSymbol func) {
        this.func = func;
    }

    // IR generation
    private AbstractSymbolTable table = null;
    private AbstractValue irValue = null;

    public AbstractSymbolTable getTable() {
        return table;
    }

    public void setTable(AbstractSymbolTable table) {
        this.table = table;
    }

    public AbstractValue getIrValue() {
        return irValue;
    }

    public void setIrValue(AbstractValue irValue) {
        this.irValue = irValue;
    }
}
