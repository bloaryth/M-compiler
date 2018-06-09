package Compiler2018.AST;

import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRValue.Register;
import Compiler2018.Symbol.FuncSymbol;

public abstract class AbstractExpr extends AbstractASTNode {
    // semantic check
    private ClassType type = null;
    private boolean isLValue = true;
    private FuncSymbol func = null;

    public ClassType getType() {
        return type;
    }

    public void setType(ClassType type) {
        this.type = type;
    }

    public boolean getLValue() {
        return isLValue;
    }

    public void setLValue(boolean LValue) {
        isLValue = LValue;
    }

    public FuncSymbol getFunc() {
        return func;
    }

    public void setFunc(FuncSymbol func) {
        this.func = func;
    }

    // short-cut
    private BasicBlock ifTrue = null;
    private BasicBlock ifFalse = null;

    public BasicBlock getIfTrue() {
        return ifTrue;
    }

    public void setIfTrue(BasicBlock ifTrue) {
        this.ifTrue = ifTrue;
    }

    public BasicBlock getIfFalse() {
        return ifFalse;
    }

    public void setIfFalse(BasicBlock ifFalse) {
        this.ifFalse = ifFalse;
    }

    // IR generation Info
    private Register register = null;
    private boolean dataInMem = false; // where the real data in

    public Register getRegister() {
        return register;
    }

    public void setRegister(Register register) {
        this.register = register;
    }

    public boolean isDataInMem() {
        return dataInMem;
    }

    public void setDataInMem(boolean dataInMem) {
        this.dataInMem = dataInMem;
    }

    // constant folder
    boolean folded = false;
    Integer ans = null;

    public boolean isFolded() {
        return folded;
    }

    public void setFolded(boolean folded) {
        this.folded = folded;
    }

    public Integer getAns() {
        return ans;
    }

    public void setAns(Integer ans) {
        this.ans = ans;
    }
}
