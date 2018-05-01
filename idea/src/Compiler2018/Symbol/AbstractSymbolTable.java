package Compiler2018.Symbol;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class AbstractSymbolTable {
    private final AbstractSymbolTable outerSymbolTable;
    private final Map<String, ClassSymbol> myClasses = new LinkedHashMap<> ();
    private final Map<String, CstrSymbol> cstrs = new LinkedHashMap<> ();
    private final Map<String, FuncSymbol> funcs = new LinkedHashMap<> ();
    private final Map<String, VarSymbol> vars = new LinkedHashMap<> ();

    public AbstractSymbolTable (AbstractSymbolTable outerSymbolTable) {
        this.outerSymbolTable = outerSymbolTable;
    }

    public final AbstractSymbolTable getOuterSymbolTable () {
        return outerSymbolTable;
    }

    private Map<String, ClassSymbol> getMyClasses () {
        return myClasses;
    }

    private Map<String, CstrSymbol> getCstrs () {
        return cstrs;
    }

    private Map<String, FuncSymbol> getFuncs () {
        return funcs;
    }

    private Map<String, VarSymbol> getVars () {
        return vars;
    }

    public ClassSymbol getMyClass (String name){
        return getMyClasses ().get (name);
    }

    public CstrSymbol getCstr (String name){
        return getCstrs ().get (name);
    }

    public FuncSymbol getFunc (String name){
        return getFuncs ().get (name);
    }

    public VarSymbol getVar (String name){
        return getVars ().get (name);
    }

    public final VarSymbol findVar(String name){
        AbstractSymbolTable symbolTable = this;
        while(symbolTable != null){
            VarSymbol var = symbolTable.getVar (name);
            if (var != null){
                return var;
            }
            else {
                symbolTable = symbolTable.getOuterSymbolTable ();
            }
        }
        return null;
    }

    public void addMyClass (String name, ClassSymbol myClass){
        getMyClasses ().put (name, myClass);
    }

    public void addCstr (String name, CstrSymbol cstr){
        getCstrs ().put (name, cstr);
    }

    public void addFunc (String name, FuncSymbol func){
        getFuncs ().put (name, func);
    }

    public void addVar (String name, VarSymbol var){
        getVars ().put (name, var);
    }

}
