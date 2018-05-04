package Compiler2018.Symbol;

import Compiler2018.AST.ClassType;
import Compiler2018.AST.VarDecl;

public class VarSymbol extends AbstractSymbol{
    private final ClassType type;
    private final String name;

    public VarSymbol(String baseType, Integer dim, String name){
        this.type = new ClassType(baseType, dim);
        this.name = name;
    }

    public VarSymbol(VarDecl decl){
        type = decl.getType();
        name = decl.getName();
    }

    public ClassType getType(){
        return type;
    }

    public String getName(){
        return name;
    }
}
