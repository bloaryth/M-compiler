package Compiler2018.FrontEnd;

import Compiler2018.AST.*;

public interface IASTVistor {
    void visit(Program node);

    // AbstractDecl
    void visit(ClassDecl node);
    void visit(FuncDecl node);
    void visit(VarDecl node);

    // AbstractClassItem
    void visit(ClassVarDecl node);
    void visit(ClassCstrDecl node);
    void visit(ClassFuncDecl node);

    // AbstractStmt
    void visit(BlockStmt node);
    void visit(VarDeclStmt node);
    void visit(BranchStmt node);
    void visit(ExprStmt node);
    void visit(EmptyStmt node);

    // AbstractJumpStmt
    void visit(ReturnStmt node);
    void visit(BreakStmt node);
    void visit(ContinueStmt node);

    // AbstractLoopStmt
    void visit(ForStmt node);
    void visit(WhileStmt node);

    // ClassType
    void visit(ClassType node);

    // AbstractExpr
    void visit(FunctionCall node);
    void visit(ArrayAcess node);
    void visit(MemberAcess node);
    void visit(NewExpr node);
    void visit(UnaryExpr node);
    void visit(BinaryExpr node);
    void visit(Identifier node);

    // AbstractNewObject
    void visit(NewArray node);
    void visit(NewNonArray node);

    // AbstractConst
    void visit(BoolConst node);
    void visit(NumConst node);
    void visit(StrConst node);
    void visit(NullConst node);

}
