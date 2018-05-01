package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class MemberAcess extends AbstractExpr {
    private final AbstractExpr expr;
    private final String name;
    private final SourcePosition posExpr;
    private final SourcePosition posName;

    public MemberAcess (AbstractExpr expr, String name, SourcePosition posExpr, SourcePosition posName) {
        this.expr = expr;
        this.name = name;
        this.posExpr = posExpr;
        this.posName = posName;
    }

    public AbstractExpr getExpr () {
        return expr;
    }

    public String getName () {
        return name;
    }

    public SourcePosition getPosExpr () {
        return posExpr;
    }

    public SourcePosition getPosName () {
        return posName;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
