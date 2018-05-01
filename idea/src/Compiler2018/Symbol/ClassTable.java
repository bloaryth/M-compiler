package Compiler2018.Symbol;

public class ClassTable extends AbstractSymbolTable {
    public ClassTable (AbstractSymbolTable outerSymbolTable) {
        super (outerSymbolTable);
    }

    @Deprecated
    @Override
    public ClassSymbol getMyClass (String name) {
        return super.getMyClass (name);
    }

    @Deprecated
    @Override
    public void addMyClass (String name, ClassSymbol myClass) {
        super.addMyClass (name, myClass);
    }
}
