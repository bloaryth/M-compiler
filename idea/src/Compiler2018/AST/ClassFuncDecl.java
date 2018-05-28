package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

public class ClassFuncDecl extends AbstractClassItem {
    private final FuncDecl decl;

    public ClassFuncDecl(FuncDecl decl) {
        this.decl = decl;
    }

    public FuncDecl getDecl() {
        return decl;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
