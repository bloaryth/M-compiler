package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.Symbol.FuncSymbol;
import Compiler2018.Symbol.VarSymbol;

public class Identifier extends AbstractExpr {
    private final String name;

    public Identifier(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    // prepare for IR Generation
    private VarSymbol varSymbol;
    private FuncSymbol funcSymbol;    // variable belong to which function

    public VarSymbol getVarSymbol() {
        return varSymbol;
    }

    public void setVarSymbol(VarSymbol varSymbol) {
        this.varSymbol = varSymbol;
    }

    public FuncSymbol getFuncSymbol() {
        return funcSymbol;
    }

    public void setFuncSymbol(FuncSymbol funcSymbol) {
        this.funcSymbol = funcSymbol;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
