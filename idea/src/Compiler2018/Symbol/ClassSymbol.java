package Compiler2018.Symbol;

public class ClassSymbol extends AbstractSymbol{
    private final String name;
    private final ClassTable inClassTable;

    public ClassSymbol(String name, ClassTable inClassTable){
        this.name = name;
        this.inClassTable = inClassTable;
    }

    public String getName(){
        return name;
    }

    public ClassTable getInClassTable(){
        return inClassTable;
    }

}
