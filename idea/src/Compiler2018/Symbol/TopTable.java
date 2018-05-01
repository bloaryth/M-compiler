package Compiler2018.Symbol;

public class TopTable extends AbstractSymbolTable {
    public TopTable (AbstractSymbolTable outerSymbolTable) {
        super (outerSymbolTable);
    }

    @Deprecated
    @Override
    public CstrSymbol getCstr (String name) {
        return super.getCstr (name);
    }

    @Deprecated
    @Override
    public void addCstr (String name, CstrSymbol cstr) {
        super.addCstr (name, cstr);
    }
}
