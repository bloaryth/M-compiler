package Compiler2018.Symbol;

public class ClassSymbol extends AbstractSymbol {
    private final String name;
    private final ClassTable inClassTable;

    public ClassSymbol(AbstractSymbolTable belongTable, String name, ClassTable inClassTable) {
        super(belongTable);
        this.name = name;
        this.inClassTable = inClassTable;
    }

    public String getName() {
        return name;
    }

    public ClassTable getInClassTable() {
        return inClassTable;
    }
}
