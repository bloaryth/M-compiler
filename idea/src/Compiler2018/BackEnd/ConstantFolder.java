package Compiler2018.BackEnd;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;

public class ConstantFolder implements IASTVistor {
    @Override
    public void visit(Program node) {
        node.getSections().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {
        node.getItems().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(FuncDecl node) {
        node.getParameters().forEach(this::visit);
    }

    @Override
    public void visit(VarDecl node) {
        node.getType().accept(this);
        if (node.getInit() != null) {
            node.getInit().accept(this);
        }
    }

    @Override
    public void visit(ClassVarDecl node) {
        node.getDecl().accept(this);
    }

    @Override
    public void visit(ClassCstrDecl node) {
        node.getParameters().forEach(x -> x.accept(this));
        node.getBlock().accept(this);
    }

    @Override
    public void visit(ClassFuncDecl node) {
        node.getDecl().accept(this);
    }

    @Override
    public void visit(BlockStmt node) {
        node.getStmts().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(VarDeclStmt node) {
        node.getDecl().accept(this);
    }

    @Override
    public void visit(BranchStmt node) {
        node.getCond().accept(this);
        node.getIfStmt().accept(this);
        if (node.getElseStmt() != null) {
            node.getElseStmt().accept(this);
        }
    }

    @Override
    public void visit(ExprStmt node) {
        node.getExpr().accept(this);
    }

    @Override
    public void visit(EmptyStmt node) {

    }

    @Override
    public void visit(ReturnStmt node) {
        if (node.getExpr() != null) {
            node.getExpr().accept(this);
        }
    }

    @Override
    public void visit(BreakStmt node) {

    }

    @Override
    public void visit(ContinueStmt node) {

    }

    @Override
    public void visit(ForStmt node) {
        if (node.getInit() != null) {
            node.getInit().accept(this);
        }
        if (node.getCond() != null) {
            node.getCond().accept(this);
        }
        if (node.getStep() != null) {
            node.getStep().accept(this);
        }
        node.getStmt().accept(this);
    }

    @Override
    public void visit(WhileStmt node) {
        node.getCond().accept(this);
        node.getStmt().accept(this);
    }

    @Override
    public void visit(ClassType node) {

    }

    @Override
    public void visit(FunctionCall node) {
        node.getName().accept(this);
        node.getParameters().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ArrayAcess node) {
        node.getArray().accept(this);
        node.getSubscript().accept(this);
    }

    @Override
    public void visit(MemberAcess node) {
        node.getExpr().accept(this);
    }

    @Override
    public void visit(NewExpr node) {
        node.getNewObject().accept(this);
    }

    @Override
    public void visit(UnaryExpr node) {
        node.getExpr().accept(this);
    }

    @Override
    public void visit(BinaryExpr node) {
        node.getLhs().accept(this);
        node.getRhs().accept(this);
        if (node.getLhs().isFolded() && node.getRhs().isFolded()) {
            switch (node.getOp()) {
                case ADD:
                    node.setAns(node.getLhs().getAns() + node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case SUB:
                    node.setAns(node.getLhs().getAns() - node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case MUL:
                    node.setAns(node.getLhs().getAns() * node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case DIV:
                    node.setAns(node.getLhs().getAns() / node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case MOD:
                    node.setAns(node.getLhs().getAns() % node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case LEFT_SHIFT:
                    node.setAns(node.getLhs().getAns() << node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case RIGHT_SHIFT:
                    node.setAns(node.getLhs().getAns() >> node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case BITWISE_OR:
                    node.setAns(node.getLhs().getAns() | node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case BITWISE_AND:
                    node.setAns(node.getLhs().getAns() & node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                case XOR:
                    node.setAns(node.getLhs().getAns() ^ node.getRhs().getAns());
                    node.setFolded(true);
                    break;
                default:
            }
        }
    }

    @Override
    public void visit(Identifier node) {

    }

    @Override
    public void visit(NewArray node) {
        node.getType().accept(this);
        node.getLens().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(NewNonArray node) {
        node.getType().accept(this);
        node.getParameters().forEach(x -> x.accept(this));
    }

    @Override
    public void visit(BoolConst node) {

    }

    @Override
    public void visit(NumConst node) {
        node.setFolded(true);
    }

    @Override
    public void visit(StrConst node) {

    }

    @Override
    public void visit(NullConst node) {

    }
}
