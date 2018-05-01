package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ClassType extends AbstractASTNode {
    private final String baseType;
    private final Integer dim;

    public ClassType (String baseType, Integer dim) {
        this.baseType = baseType;
        this.dim = dim;
    }

    public String getBaseType () {
        return baseType;
    }

    public Integer getDim () {
        return dim;
    }

    @Override
    public boolean equals (Object obj) {
        return obj instanceof ClassType && baseType.equals (((ClassType) obj).getBaseType ()) && dim.equals (((ClassType) obj).getDim ());
    }

    @Override
    public String toString () {
        return "baseType: " + baseType + "\t" + "dim: " + dim;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
