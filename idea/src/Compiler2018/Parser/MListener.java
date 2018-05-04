// Generated from D:/Coding/M-compiler/idea/src/Compiler2018\M.g4 by ANTLR 4.7
package Compiler2018.Parser;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link MParser}.
 */
public interface MListener extends ParseTreeListener{
    /**
     * Enter a parse tree produced by {@link MParser#program}.
     *
     * @param ctx the parse tree
     */
    void enterProgram(MParser.ProgramContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#program}.
     *
     * @param ctx the parse tree
     */
    void exitProgram(MParser.ProgramContext ctx);

    /**
     * Enter a parse tree produced by the {@code ClassDecl}
     * labeled alternative in {@link MParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void enterClassDecl(MParser.ClassDeclContext ctx);

    /**
     * Exit a parse tree produced by the {@code ClassDecl}
     * labeled alternative in {@link MParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void exitClassDecl(MParser.ClassDeclContext ctx);

    /**
     * Enter a parse tree produced by the {@code FuncDecl}
     * labeled alternative in {@link MParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void enterFuncDecl(MParser.FuncDeclContext ctx);

    /**
     * Exit a parse tree produced by the {@code FuncDecl}
     * labeled alternative in {@link MParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void exitFuncDecl(MParser.FuncDeclContext ctx);

    /**
     * Enter a parse tree produced by the {@code VarDecl}
     * labeled alternative in {@link MParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void enterVarDecl(MParser.VarDeclContext ctx);

    /**
     * Exit a parse tree produced by the {@code VarDecl}
     * labeled alternative in {@link MParser#programSection}.
     *
     * @param ctx the parse tree
     */
    void exitVarDecl(MParser.VarDeclContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#classDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterClassDeclaration(MParser.ClassDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#classDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitClassDeclaration(MParser.ClassDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#functionDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionDeclaration(MParser.FunctionDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#functionDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionDeclaration(MParser.FunctionDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#variableDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterVariableDeclaration(MParser.VariableDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#variableDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitVariableDeclaration(MParser.VariableDeclarationContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#variableDeclarationStatement}.
     *
     * @param ctx the parse tree
     */
    void enterVariableDeclarationStatement(MParser.VariableDeclarationStatementContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#variableDeclarationStatement}.
     *
     * @param ctx the parse tree
     */
    void exitVariableDeclarationStatement(MParser.VariableDeclarationStatementContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#functionParameters}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionParameters(MParser.FunctionParametersContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#functionParameters}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionParameters(MParser.FunctionParametersContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#classBlock}.
     *
     * @param ctx the parse tree
     */
    void enterClassBlock(MParser.ClassBlockContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#classBlock}.
     *
     * @param ctx the parse tree
     */
    void exitClassBlock(MParser.ClassBlockContext ctx);

    /**
     * Enter a parse tree produced by the {@code ClassVarDecl}
     * labeled alternative in {@link MParser#classBlockItem}.
     *
     * @param ctx the parse tree
     */
    void enterClassVarDecl(MParser.ClassVarDeclContext ctx);

    /**
     * Exit a parse tree produced by the {@code ClassVarDecl}
     * labeled alternative in {@link MParser#classBlockItem}.
     *
     * @param ctx the parse tree
     */
    void exitClassVarDecl(MParser.ClassVarDeclContext ctx);

    /**
     * Enter a parse tree produced by the {@code ClassCstrDecl}
     * labeled alternative in {@link MParser#classBlockItem}.
     *
     * @param ctx the parse tree
     */
    void enterClassCstrDecl(MParser.ClassCstrDeclContext ctx);

    /**
     * Exit a parse tree produced by the {@code ClassCstrDecl}
     * labeled alternative in {@link MParser#classBlockItem}.
     *
     * @param ctx the parse tree
     */
    void exitClassCstrDecl(MParser.ClassCstrDeclContext ctx);

    /**
     * Enter a parse tree produced by the {@code ClassFuncDecl}
     * labeled alternative in {@link MParser#classBlockItem}.
     *
     * @param ctx the parse tree
     */
    void enterClassFuncDecl(MParser.ClassFuncDeclContext ctx);

    /**
     * Exit a parse tree produced by the {@code ClassFuncDecl}
     * labeled alternative in {@link MParser#classBlockItem}.
     *
     * @param ctx the parse tree
     */
    void exitClassFuncDecl(MParser.ClassFuncDeclContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#constructorDeclaration}.
     *
     * @param ctx the parse tree
     */
    void enterConstructorDeclaration(MParser.ConstructorDeclarationContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#constructorDeclaration}.
     *
     * @param ctx the parse tree
     */
    void exitConstructorDeclaration(MParser.ConstructorDeclarationContext ctx);

    /**
     * Enter a parse tree produced by the {@code BlockStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterBlockStmt(MParser.BlockStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code BlockStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitBlockStmt(MParser.BlockStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code VarDeclStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterVarDeclStmt(MParser.VarDeclStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code VarDeclStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitVarDeclStmt(MParser.VarDeclStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code BranchStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterBranchStmt(MParser.BranchStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code BranchStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitBranchStmt(MParser.BranchStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code LoopStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterLoopStmt(MParser.LoopStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code LoopStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitLoopStmt(MParser.LoopStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code ExprStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterExprStmt(MParser.ExprStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code ExprStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitExprStmt(MParser.ExprStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code JumpStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterJumpStmt(MParser.JumpStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code JumpStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitJumpStmt(MParser.JumpStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code EmptyStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void enterEmptyStmt(MParser.EmptyStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code EmptyStmt}
     * labeled alternative in {@link MParser#statement}.
     *
     * @param ctx the parse tree
     */
    void exitEmptyStmt(MParser.EmptyStmtContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#blockStatement}.
     *
     * @param ctx the parse tree
     */
    void enterBlockStatement(MParser.BlockStatementContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#blockStatement}.
     *
     * @param ctx the parse tree
     */
    void exitBlockStatement(MParser.BlockStatementContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#branchStatement}.
     *
     * @param ctx the parse tree
     */
    void enterBranchStatement(MParser.BranchStatementContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#branchStatement}.
     *
     * @param ctx the parse tree
     */
    void exitBranchStatement(MParser.BranchStatementContext ctx);

    /**
     * Enter a parse tree produced by the {@code ForStmt}
     * labeled alternative in {@link MParser#loopStatement}.
     *
     * @param ctx the parse tree
     */
    void enterForStmt(MParser.ForStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code ForStmt}
     * labeled alternative in {@link MParser#loopStatement}.
     *
     * @param ctx the parse tree
     */
    void exitForStmt(MParser.ForStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code WhileStmt}
     * labeled alternative in {@link MParser#loopStatement}.
     *
     * @param ctx the parse tree
     */
    void enterWhileStmt(MParser.WhileStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code WhileStmt}
     * labeled alternative in {@link MParser#loopStatement}.
     *
     * @param ctx the parse tree
     */
    void exitWhileStmt(MParser.WhileStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code ReturnStmt}
     * labeled alternative in {@link MParser#jumpStatement}.
     *
     * @param ctx the parse tree
     */
    void enterReturnStmt(MParser.ReturnStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code ReturnStmt}
     * labeled alternative in {@link MParser#jumpStatement}.
     *
     * @param ctx the parse tree
     */
    void exitReturnStmt(MParser.ReturnStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code BreakStmt}
     * labeled alternative in {@link MParser#jumpStatement}.
     *
     * @param ctx the parse tree
     */
    void enterBreakStmt(MParser.BreakStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code BreakStmt}
     * labeled alternative in {@link MParser#jumpStatement}.
     *
     * @param ctx the parse tree
     */
    void exitBreakStmt(MParser.BreakStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code ContinueStmt}
     * labeled alternative in {@link MParser#jumpStatement}.
     *
     * @param ctx the parse tree
     */
    void enterContinueStmt(MParser.ContinueStmtContext ctx);

    /**
     * Exit a parse tree produced by the {@code ContinueStmt}
     * labeled alternative in {@link MParser#jumpStatement}.
     *
     * @param ctx the parse tree
     */
    void exitContinueStmt(MParser.ContinueStmtContext ctx);

    /**
     * Enter a parse tree produced by the {@code ArrayType}
     * labeled alternative in {@link MParser#classType}.
     *
     * @param ctx the parse tree
     */
    void enterArrayType(MParser.ArrayTypeContext ctx);

    /**
     * Exit a parse tree produced by the {@code ArrayType}
     * labeled alternative in {@link MParser#classType}.
     *
     * @param ctx the parse tree
     */
    void exitArrayType(MParser.ArrayTypeContext ctx);

    /**
     * Enter a parse tree produced by the {@code NonArrayType}
     * labeled alternative in {@link MParser#classType}.
     *
     * @param ctx the parse tree
     */
    void enterNonArrayType(MParser.NonArrayTypeContext ctx);

    /**
     * Exit a parse tree produced by the {@code NonArrayType}
     * labeled alternative in {@link MParser#classType}.
     *
     * @param ctx the parse tree
     */
    void exitNonArrayType(MParser.NonArrayTypeContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#arrayClass}.
     *
     * @param ctx the parse tree
     */
    void enterArrayClass(MParser.ArrayClassContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#arrayClass}.
     *
     * @param ctx the parse tree
     */
    void exitArrayClass(MParser.ArrayClassContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#nonArrayClass}.
     *
     * @param ctx the parse tree
     */
    void enterNonArrayClass(MParser.NonArrayClassContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#nonArrayClass}.
     *
     * @param ctx the parse tree
     */
    void exitNonArrayClass(MParser.NonArrayClassContext ctx);

    /**
     * Enter a parse tree produced by the {@code Identifier}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterIdentifier(MParser.IdentifierContext ctx);

    /**
     * Exit a parse tree produced by the {@code Identifier}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitIdentifier(MParser.IdentifierContext ctx);

    /**
     * Enter a parse tree produced by the {@code MemberAcess}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterMemberAcess(MParser.MemberAcessContext ctx);

    /**
     * Exit a parse tree produced by the {@code MemberAcess}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitMemberAcess(MParser.MemberAcessContext ctx);

    /**
     * Enter a parse tree produced by the {@code ArrayAcess}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterArrayAcess(MParser.ArrayAcessContext ctx);

    /**
     * Exit a parse tree produced by the {@code ArrayAcess}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitArrayAcess(MParser.ArrayAcessContext ctx);

    /**
     * Enter a parse tree produced by the {@code Const}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterConst(MParser.ConstContext ctx);

    /**
     * Exit a parse tree produced by the {@code Const}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitConst(MParser.ConstContext ctx);

    /**
     * Enter a parse tree produced by the {@code SubExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterSubExpr(MParser.SubExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code SubExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitSubExpr(MParser.SubExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code BinaryExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterBinaryExpr(MParser.BinaryExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code BinaryExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitBinaryExpr(MParser.BinaryExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code NewExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterNewExpr(MParser.NewExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code NewExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitNewExpr(MParser.NewExprContext ctx);

    /**
     * Enter a parse tree produced by the {@code FunctionCall}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterFunctionCall(MParser.FunctionCallContext ctx);

    /**
     * Exit a parse tree produced by the {@code FunctionCall}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitFunctionCall(MParser.FunctionCallContext ctx);

    /**
     * Enter a parse tree produced by the {@code PostfixIncDec}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterPostfixIncDec(MParser.PostfixIncDecContext ctx);

    /**
     * Exit a parse tree produced by the {@code PostfixIncDec}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitPostfixIncDec(MParser.PostfixIncDecContext ctx);

    /**
     * Enter a parse tree produced by the {@code UnaryExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void enterUnaryExpr(MParser.UnaryExprContext ctx);

    /**
     * Exit a parse tree produced by the {@code UnaryExpr}
     * labeled alternative in {@link MParser#expression}.
     *
     * @param ctx the parse tree
     */
    void exitUnaryExpr(MParser.UnaryExprContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#callParameter}.
     *
     * @param ctx the parse tree
     */
    void enterCallParameter(MParser.CallParameterContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#callParameter}.
     *
     * @param ctx the parse tree
     */
    void exitCallParameter(MParser.CallParameterContext ctx);

    /**
     * Enter a parse tree produced by the {@code NewError}
     * labeled alternative in {@link MParser#newObject}.
     *
     * @param ctx the parse tree
     */
    void enterNewError(MParser.NewErrorContext ctx);

    /**
     * Exit a parse tree produced by the {@code NewError}
     * labeled alternative in {@link MParser#newObject}.
     *
     * @param ctx the parse tree
     */
    void exitNewError(MParser.NewErrorContext ctx);

    /**
     * Enter a parse tree produced by the {@code NewArray}
     * labeled alternative in {@link MParser#newObject}.
     *
     * @param ctx the parse tree
     */
    void enterNewArray(MParser.NewArrayContext ctx);

    /**
     * Exit a parse tree produced by the {@code NewArray}
     * labeled alternative in {@link MParser#newObject}.
     *
     * @param ctx the parse tree
     */
    void exitNewArray(MParser.NewArrayContext ctx);

    /**
     * Enter a parse tree produced by the {@code NewNonArray}
     * labeled alternative in {@link MParser#newObject}.
     *
     * @param ctx the parse tree
     */
    void enterNewNonArray(MParser.NewNonArrayContext ctx);

    /**
     * Exit a parse tree produced by the {@code NewNonArray}
     * labeled alternative in {@link MParser#newObject}.
     *
     * @param ctx the parse tree
     */
    void exitNewNonArray(MParser.NewNonArrayContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#brackets}.
     *
     * @param ctx the parse tree
     */
    void enterBrackets(MParser.BracketsContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#brackets}.
     *
     * @param ctx the parse tree
     */
    void exitBrackets(MParser.BracketsContext ctx);

    /**
     * Enter a parse tree produced by {@link MParser#constant}.
     *
     * @param ctx the parse tree
     */
    void enterConstant(MParser.ConstantContext ctx);

    /**
     * Exit a parse tree produced by {@link MParser#constant}.
     *
     * @param ctx the parse tree
     */
    void exitConstant(MParser.ConstantContext ctx);
}