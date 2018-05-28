package Compiler2018.Symbol;

public class ClassTable extends AbstractSymbolTable {
    private int offset = 0;

    public ClassTable(AbstractSymbolTable outerSymbolTable, String namespace) {
        super(outerSymbolTable, namespace);
    }

    public int getOffset() {
        return offset;
    }

    public void addOffset(int length) {
        offset += length;
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
