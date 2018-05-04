package Compiler2018.Symbol;

public class BlockTable extends AbstractSymbolTable {

    public BlockTable (AbstractSymbolTable outerSymbolTable) {
        super (outerSymbolTable);
    }

    @Deprecated
    @Override
    public ClassSymbol getMyClass (String name) {
        assert false;
        return super.getMyClass (name);
    }

    @Deprecated
    @Override
    public CstrSymbol getCstr (String name) {
        assert false;
        return super.getCstr (name);
    }

    @Deprecated
    @Override
    public FuncSymbol getFunc (String name) {
        assert false;
        return super.getFunc (name);
    }

    @Deprecated
    @Override
    public void addMyClass (String name, ClassSymbol myClass) {
        assert false;
        super.addMyClass (name, myClass);
    }

    @Deprecated
    @Override
    public void addCstr (String name, CstrSymbol cstr) {
        assert false;
        super.addCstr (name, cstr);
    }

    @Deprecated
    @Override
    public void addFunc (String name, FuncSymbol func) {
        assert false;
        super.addFunc (name, func);
    }
}
