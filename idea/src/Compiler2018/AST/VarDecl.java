package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class VarDecl extends AbstractDecl {
    private final ClassType type;
    private final String name;
    private final AbstractExpr init; // can be null
    private final SourcePosition posType;
    private final SourcePosition posName;
    private final SourcePosition posInit;

    public VarDecl(
            ClassType type,
            String name,
            AbstractExpr init,
            SourcePosition posType,
            SourcePosition posName,
            SourcePosition posInit) {
        this.type = type;
        this.name = name;
        this.init = init;
        this.posType = posType;
        this.posName = posName;
        this.posInit = posInit;
    }

    public ClassType getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public AbstractExpr getInit() {
        return init;
    }

    public SourcePosition getPosType() {
        return posType;
    }

    public SourcePosition getPosName() {
        return posName;
    }

    public SourcePosition getPosInit() {
        return posInit;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
