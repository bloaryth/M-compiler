package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ClassType extends AbstractASTNode {
    private final String baseType;
    private final Integer dim;

    public ClassType(String baseType, Integer dim) {
        this.baseType = baseType;
        this.dim = dim;
    }

    public String getBaseType() {
        return baseType;
    }

    public Integer getDim() {
        return dim;
    }

    // prepare for IR Generation
    public Integer isPtrSaved() {
        if (dim > 0) {
            return 0;
        }
        switch (baseType) {
            case "int":
            case "bool":
                return 0;
            default:
                return 1;
        }
    }

//    public boolean isPtrCopy(){
//        return dim > 0 | baseType.equals("string");
//    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof ClassType
                && baseType.equals(((ClassType) obj).getBaseType())
                && dim.equals(((ClassType) obj).getDim());
    }

    @Override
    public String toString() {
        return "baseType: " + baseType + "\t" + "dim: " + dim;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
