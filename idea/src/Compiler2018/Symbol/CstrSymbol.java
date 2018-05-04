package Compiler2018.Symbol;

import Compiler2018.AST.ClassCstrDecl;

import java.util.LinkedHashMap;
import java.util.Map;

public class CstrSymbol extends AbstractSymbol{
    private final String name;
    private final Map<String, VarSymbol> stringParameters;
    private final Map<Integer, VarSymbol> intParameters;
    private final BlockTable blockTable;

    public static class Builder{
        private String name;
        private Map<String, VarSymbol> stringParameters = new LinkedHashMap<>();
        private Map<Integer, VarSymbol> intParameters = new LinkedHashMap<>();
        private BlockTable blockTable;

        public void setName(String name){
            this.name = name;
        }

        public void addParameter(String name, VarSymbol parameter){
            intParameters.put(intParameters.size(), parameter);
            stringParameters.put(name, parameter);
        }

        public void setBlockTable(BlockTable blockTable){
            this.blockTable = blockTable;
        }

        public CstrSymbol build(){
            stringParameters.forEach((x, y) -> blockTable.addVar(x, y));
            return new CstrSymbol(name, stringParameters, intParameters, blockTable);
        }
    }

    public CstrSymbol(String name, Map<String, VarSymbol> stringParameters, Map<Integer, VarSymbol> intParameters, BlockTable blockTable){
        this.name = name;
        this.stringParameters = stringParameters;
        this.intParameters = intParameters;
        this.blockTable = blockTable;
    }

    public CstrSymbol(ClassCstrDecl decl, BlockTable blockTable){
        name = decl.getName();
        stringParameters = new LinkedHashMap<>();
        decl.getParameters().forEach(x -> stringParameters.put(x.getName(), new VarSymbol(x)));
        intParameters = new LinkedHashMap<>();
        decl.getParameters().forEach(x -> intParameters.put(intParameters.size(), new VarSymbol(x)));
        this.blockTable = blockTable;
        stringParameters.forEach(this.blockTable::addVar);
    }

    public String getName(){
        return name;
    }

    public Map<String, VarSymbol> getStringParameters(){
        return stringParameters;
    }

    public Map<Integer, VarSymbol> getIntParameters(){
        return intParameters;
    }

    public BlockTable getBlockTable(){
        return blockTable;
    }
}
