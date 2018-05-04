package Compiler2018.Symbol;

import Compiler2018.AST.ClassType;
import Compiler2018.AST.FuncDecl;

import java.util.LinkedHashMap;
import java.util.Map;

public class FuncSymbol extends AbstractSymbol{
    private final ClassType returnType;
    private final String name;
    private final Map<String, VarSymbol> stringParameters;
    private final Map<Integer, VarSymbol> intParameters;
    private final BlockTable blockTable;

    public static class Builder{
        private ClassType returnType;
        private String name;
        private Map<String, VarSymbol> stringParameters = new LinkedHashMap<>();
        private Map<Integer, VarSymbol> intParameters = new LinkedHashMap<>();
        private BlockTable blockTable;

        public void setReturnType(ClassType returnType){
            this.returnType = returnType;
        }

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

        public FuncSymbol build(){
            stringParameters.forEach((x, y) -> blockTable.addVar(x, y));
            return new FuncSymbol(returnType, name, stringParameters, intParameters, blockTable);
        }
    }

    private FuncSymbol(ClassType returnType, String name, Map<String, VarSymbol> stringParameters, Map<Integer, VarSymbol> intParameters, BlockTable blockTable){
        this.returnType = returnType;
        this.name = name;
        this.stringParameters = stringParameters;
        this.intParameters = intParameters;
        this.blockTable = blockTable;
    }

    public FuncSymbol(FuncDecl decl, BlockTable blockTable){
        returnType = decl.getReturnType();
        name = decl.getName();
        stringParameters = new LinkedHashMap<>();
        decl.getParameters().forEach(x -> stringParameters.put(x.getName(), new VarSymbol(x)));
        intParameters = new LinkedHashMap<>();
        decl.getParameters().forEach(x -> intParameters.put(intParameters.size(), new VarSymbol(x)));
        this.blockTable = blockTable;
        stringParameters.forEach(this.blockTable::addVar);
    }

    public ClassType getReturnType(){
        return returnType;
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
