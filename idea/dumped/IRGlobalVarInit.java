package Compiler2018.FrontEnd.IRBuilder;

import Compiler2018.AST.*;
import Compiler2018.FrontEnd.IASTVistor;
import Compiler2018.IR.IRInstruction.Move;
import Compiler2018.IR.IRInstruction.MoveU;
import Compiler2018.IR.IRStructure.BasicBlock;
import Compiler2018.IR.IRStructure.IRFunction;
import Compiler2018.IR.IRStructure.IRProgram;
import Compiler2018.IR.IRStructure.StaticData;
import Compiler2018.IR.IRValue.Label;
import Compiler2018.IR.IRValue.Register;

public class IRGlobalVarInit implements IASTVistor {
    private final IRProgram irProgram;
    private IRFunction currentFunction;
    private BasicBlock currentBB;

    public IRGlobalVarInit(IRProgram irProgram) {
        this.irProgram = irProgram;
    }

    @Override
    public void visit(Program node) {
        node.getSections().stream().filter(x -> x instanceof VarDecl).forEach(x -> x.accept(this));
    }

    @Override
    public void visit(ClassDecl node) {

    }

    @Override
    public void visit(FuncDecl node) {

    }

    @Override
    public void visit(VarDecl node) {
        Label label = new Label(node.getName());
        irProgram.putGlobalVar(node.getName(), new StaticData(label));
        currentBB.addTail(new MoveU(currentBB, node.getVarSymbol().getRegister(), null, label));    // addr of variabel

        if (node.getInit() != null) {
            node.getInit().accept(this);    // FIXME
            Register lhs = node.getVarSymbol().getRegister();
            Register rhs = node.getInit().getRegister();
            assert lhs.getAddrFlag();
            if (rhs.getAddrFlag()) {
                currentBB.addTail(new Move(currentBB, lhs, new Register(null, false), rhs, true));
            } else {
                currentBB.addTail(new Move(currentBB, lhs, new Register(null, false), rhs, false));
            }
        } else {
            // FIXME default constructor
        }
    }

    @Override
    public void visit(ClassVarDecl node) {

    }

    @Override
    public void visit(ClassCstrDecl node) {

    }

    @Override
    public void visit(ClassFuncDecl node) {

    }

    @Override
    public void visit(BlockStmt node) {

    }

    @Override
    public void visit(VarDeclStmt node) {

    }

    @Override
    public void visit(BranchStmt node) {

    }

    @Override
    public void visit(ExprStmt node) {

    }

    @Override
    public void visit(EmptyStmt node) {

    }

    @Override
    public void visit(ReturnStmt node) {

    }

    @Override
    public void visit(BreakStmt node) {

    }

    @Override
    public void visit(ContinueStmt node) {

    }

    @Override
    public void visit(ForStmt node) {

    }

    @Override
    public void visit(WhileStmt node) {

    }

    @Override
    public void visit(ClassType node) {

    }

    @Override
    public void visit(FunctionCall node) {

    }

    @Override
    public void visit(ArrayAcess node) {

    }

    @Override
    public void visit(MemberAcess node) {

    }

    @Override
    public void visit(NewExpr node) {

    }

    @Override
    public void visit(UnaryExpr node) {

    }

    @Override
    public void visit(BinaryExpr node) {

    }

    @Override
    public void visit(Identifier node) {

    }

    @Override
    public void visit(NewArray node) {

    }

    @Override
    public void visit(NewNonArray node) {

    }

    @Override
    public void visit(BoolConst node) {

    }

    @Override
    public void visit(NumConst node) {

    }

    @Override
    public void visit(StrConst node) {

    }

    @Override
    public void visit(NullConst node) {

    }
}
