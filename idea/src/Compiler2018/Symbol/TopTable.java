package Compiler2018.Symbol;

public class TopTable extends AbstractSymbolTable {
    public TopTable(AbstractSymbolTable outerSymbolTable, String namespace) {
        super(outerSymbolTable, namespace);
    }

    @Deprecated
    @Override
    public CstrSymbol getCstr(String name) {
        assert false;
        return super.getCstr(name);
    }

    @Deprecated
    @Override
    public void addCstr(String name, CstrSymbol cstr) {
        assert false;
        super.addCstr(name, cstr);
    }
}
