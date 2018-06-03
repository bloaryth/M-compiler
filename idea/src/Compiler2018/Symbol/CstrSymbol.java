package Compiler2018.Symbol;

import Compiler2018.AST.ClassCstrDecl;
import Compiler2018.AST.VarDecl;

import java.util.LinkedHashMap;
import java.util.Map;

public class CstrSymbol extends AbstractSymbol {
    private final String name;
    private final Map<String, VarSymbol> stringParameters;
    private final Map<Integer, VarSymbol> intParameters;
    private final BlockTable blockTable;

    public CstrSymbol(AbstractSymbolTable belongTable, String name, Map<String, VarSymbol> stringParameters, Map<Integer, VarSymbol> intParameters, BlockTable blockTable) {
        super(belongTable);
        this.name = name;
        this.stringParameters = stringParameters;
        this.intParameters = intParameters;
        this.blockTable = blockTable;
    }

    public CstrSymbol(AbstractSymbolTable belongTable, ClassCstrDecl decl, BlockTable blockTable) {
        super(belongTable);
        name = decl.getName();
        stringParameters = new LinkedHashMap<>();
        intParameters = new LinkedHashMap<>();
        for (VarDecl varDecl : decl.getParameters()) {
            VarSymbol varSymbol = new VarSymbol(blockTable, varDecl);
            stringParameters.put(varDecl.getName(), varSymbol);
            intParameters.put(intParameters.size(), varSymbol);
        }
        this.blockTable = blockTable;
        stringParameters.forEach(this.blockTable::addVar);
    }

//    public static class Builder {
//        private String name;
//        private Map<String, VarSymbol> stringParameters = new LinkedHashMap<>();
//        private Map<Integer, VarSymbol> intParameters = new LinkedHashMap<>();
//
//        private BlockTable blockTable;
//
//        public void setProcessedName(String name) {
//            this.name = name;
//        }
//
//        public void addParameter(String name, VarSymbol parameter) {
//            intParameters.put(intParameters.size(), parameter);
//            stringParameters.put(name, parameter);
//        }
//
//        public void setBlockTable(BlockTable blockTable) {
//            this.blockTable = blockTable;
//        }
//
//        public CstrSymbol build() {
//            stringParameters.forEach((x, y) -> blockTable.addVar(x, y));
//            return new CstrSymbol(name, stringParameters, intParameters, blockTable);
//        }
//
//    }

    public String getName() {
        return name;
    }

    public Map<String, VarSymbol> getStringParameters() {
        return stringParameters;
    }

    public Map<Integer, VarSymbol> getIntParameters() {
        return intParameters;
    }

    public BlockTable getBlockTable() {
        return blockTable;
    }

    // only for IR, M*
    private String processedName = null;
    public String getProcessedName() {
        if (processedName == null) {
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append("_");
            if (!(belongTable instanceof TopTable)) {
                stringBuilder.append("N");
            }
            stringBuilder.append(belongTable.getNamespace());
            stringBuilder.append(name);
            processedName = stringBuilder.toString();
        }
        return processedName;
    }
}
