package Compiler2018.Symbol;

public class ClassTable extends AbstractSymbolTable {

    public ClassTable(AbstractSymbolTable outerSymbolTable, String namespace) {
        super(outerSymbolTable, namespace);
    }

    @Deprecated
    @Override
    public ClassSymbol getMyClass(String name) {
        assert false;
        return super.getMyClass(name);
    }

    @Deprecated
    @Override
    public void addMyClass(String name, ClassSymbol myClass) {
        assert false;
        super.addMyClass(name, myClass);
    }
}
