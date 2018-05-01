package Compiler2018.AST;

public abstract class AbstractNewObject extends AbstractASTNode{
    private final ClassType type;

    public AbstractNewObject (ClassType type) {
        this.type = type;
    }

    public ClassType getType () {
        return type;
    }
}
