package Compiler2018.AST;

import Compiler2018.FrontEnd.IASTVistor;

import java.util.LinkedList;
import java.util.List;

public class BlockStmt extends AbstractStmt {
    private final List<AbstractStmt> stmts;

    public static class Builder {
        private List<AbstractStmt> stmts = new LinkedList<>();

        public void addStmt(AbstractStmt stmt) {
            stmts.add(stmt);
        }

        public BlockStmt build() {
            return new BlockStmt(stmts);
        }
    }

    private BlockStmt(List<AbstractStmt> stmts) {
        this.stmts = stmts;
    }

    public List<AbstractStmt> getStmts() {
        return stmts;
    }

    @Override
    public void accept(IASTVistor visitor) {
        visitor.visit(this);
    }
}
