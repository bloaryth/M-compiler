package Compiler2018.Symbol;

import Compiler2018.AST.ClassType;
import Compiler2018.AST.FuncDecl;
import Compiler2018.AST.VarDecl;
import Compiler2018.IR.IRValue.Register;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class FuncSymbol extends AbstractSymbol {
    private final ClassType returnType;
    private final String name;
    private final Map<String, VarSymbol> stringParameters;
    private final Map<Integer, VarSymbol> intParameters;
    private final BlockTable blockTable;
    private final List<Register> parameterRegisterList;  // prepare for IR Generation

    public static class Builder {
        private AbstractSymbolTable belongTable;
        private ClassType returnType;
        private String name;
        private Map<String, VarSymbol> stringParameters = new LinkedHashMap<>();
        private Map<Integer, VarSymbol> intParameters = new LinkedHashMap<>();
        private BlockTable blockTable;
        private List<Register> parameterRegisterList = new LinkedList<>();  // prepare for IR Generation

        public void setBelongTable(AbstractSymbolTable belongTable) {
            this.belongTable = belongTable;
        }

        public void setReturnType(ClassType returnType) {
            this.returnType = returnType;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void addParameter(String name, VarSymbol parameter) {
            intParameters.put(intParameters.size(), parameter);
            stringParameters.put(name, parameter);
        }

        public void setBlockTable(BlockTable blockTable) {
            this.blockTable = blockTable;
        }

        public FuncSymbol build() {
            stringParameters.forEach((x, y) -> blockTable.addVar(x, y));
            stringParameters.forEach((x, y) -> parameterRegisterList.add(y.getRegister())); // varSymbol in BlockTable
            return new FuncSymbol(belongTable, returnType, name, stringParameters, intParameters, blockTable, parameterRegisterList);
        }
    }

    public FuncSymbol(AbstractSymbolTable belongTable, ClassType returnType, String name, Map<String, VarSymbol> stringParameters, Map<Integer, VarSymbol> intParameters, BlockTable blockTable, List<Register> parameterRegisterList) {
        super(belongTable);
        this.returnType = returnType;
        this.name = name;
        this.stringParameters = stringParameters;
        this.intParameters = intParameters;
        this.blockTable = blockTable;
        this.parameterRegisterList = parameterRegisterList;
    }

    public FuncSymbol(AbstractSymbolTable belongTable, FuncDecl decl, BlockTable blockTable) {
        super(belongTable);
        returnType = decl.getReturnType();
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
        this.parameterRegisterList = new LinkedList<>();
        stringParameters.forEach((x, y) -> parameterRegisterList.add(y.getRegister()));
    }

    public ClassType getReturnType() {
        return returnType;
    }

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

    public List<Register> getParameterRegisterList() {
        return parameterRegisterList;
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
