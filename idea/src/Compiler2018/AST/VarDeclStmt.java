package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class VarDeclStmt extends AbstractStmt{
    private final VarDecl decl;

    public VarDeclStmt (VarDecl decl) {
        this.decl = decl;
    }

    public VarDecl getDecl () {
        return decl;
    }

    @Override
    public void accept (IASTVistor visitor) {
        visitor.visit(this);
    }
}
