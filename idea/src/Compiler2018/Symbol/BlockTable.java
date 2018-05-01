package Compiler2018.Symbol;

public class BlockTable extends AbstractSymbolTable {

    public BlockTable (AbstractSymbolTable outerSymbolTable) {
        super (outerSymbolTable);
    }

    @Deprecated
    @Override
    public ClassSymbol getMyClass (String name) {
        return super.getMyClass (name);
    }

    @Deprecated
    @Override
    public CstrSymbol getCstr (String name) {
        return super.getCstr (name);
    }

    @Deprecated
    @Override
    public FuncSymbol getFunc (String name) {
        return super.getFunc (name);
    }

    @Deprecated
    @Override
    public void addMyClass (String name, ClassSymbol myClass) {
        super.addMyClass (name, myClass);
    }

    @Deprecated
    @Override
    public void addCstr (String name, CstrSymbol cstr) {
        super.addCstr (name, cstr);
    }

    @Deprecated
    @Override
    public void addFunc (String name, FuncSymbol func) {
        super.addFunc (name, func);
    }
}
