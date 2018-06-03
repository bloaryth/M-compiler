package Compiler2018.Symbol;

public abstract class AbstractSymbol {
    protected final AbstractSymbolTable belongTable;

    public AbstractSymbol(AbstractSymbolTable belongTable) {
        this.belongTable = belongTable;
    }

    public AbstractSymbolTable getBelongTable() {
        return belongTable;
    }
}
