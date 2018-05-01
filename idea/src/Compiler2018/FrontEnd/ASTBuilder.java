package Compiler2018.FrontEnd;

import Compiler2018.AST.*;
import Compiler2018.Parser.MListener;
import Compiler2018.Parser.MParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import org.antlr.v4.runtime.tree.TerminalNode;

public class ASTBuilder implements MListener {
    private ParseTreeProperty<Object> map = new ParseTreeProperty<>();
    private Program program = null;

    public Program getProgram() {
        return program;
    }

    @Override
    public void enterProgram (MParser.ProgramContext ctx) {

    }

    @Override
    public void exitProgram (MParser.ProgramContext ctx) {
        Program.Builder builder = new Program.Builder ();
        ctx.programSection ().stream ().map (map::get).map (AbstractDecl.class::cast).forEachOrdered (builder::addSection);
        map.put (ctx, builder.build ());
        if (program == null){
            program = (Program) map.get (ctx);
        }
    }

    @Override
    public void enterClassDecl (MParser.ClassDeclContext ctx) {

    }

    @Override
    public void exitClassDecl (MParser.ClassDeclContext ctx) {
        map.put (ctx, map.get (ctx.classDeclaration ()));
    }

    @Override
    public void enterFuncDecl (MParser.FuncDeclContext ctx) {

    }

    @Override
    public void exitFuncDecl (MParser.FuncDeclContext ctx) {
        map.put (ctx, map.get (ctx.functionDeclaration ()));
    }

    @Override
    public void enterVarDecl (MParser.VarDeclContext ctx) {

    }

    @Override
    public void exitVarDecl (MParser.VarDeclContext ctx) {
        map.put (ctx, map.get (ctx.variableDeclarationStatement ()));
    }

    @Override
    public void enterClassDeclaration (MParser.ClassDeclarationContext ctx) {

    }

    @Override
    public void exitClassDeclaration (MParser.ClassDeclarationContext ctx) {
        ClassDecl.Builder builder = new ClassDecl.Builder ();
        builder.setName (ctx.Identifier ().getText ());
        ctx.classBlock ().classBlockItem ().stream ().map (map::get).map (AbstractClassItem.class::cast).forEachOrdered (builder::addItem);
        map.put (ctx, builder.build ());
    }

    @Override
    public void enterFunctionDeclaration (MParser.FunctionDeclarationContext ctx) {

    }

    @Override
    public void exitFunctionDeclaration (MParser.FunctionDeclarationContext ctx) {
        FuncDecl.Builder builder = new FuncDecl.Builder ();
        builder.setType ((ClassType) map.get (ctx.classType ()));
        builder.setName (ctx.Identifier ().getText ());
        if (ctx.functionParameters () != null){
            ctx.functionParameters ().variableDeclaration ().stream ().map (map::get).map (VarDecl.class::cast).forEachOrdered (builder::addParameter);
        }
        builder.setBlock ((BlockStmt) map.get (ctx.blockStatement ()));
        builder.setPosName (new SourcePosition (ctx.Identifier ()));
        builder.setPosType (new SourcePosition (ctx.classType ()));
        map.put(ctx, builder.build ());
    }

    @Override
    public void enterVariableDeclaration (MParser.VariableDeclarationContext ctx) {

    }

    @Override
    public void exitVariableDeclaration (MParser.VariableDeclarationContext ctx) {
        ClassType type = (ClassType) map.get (ctx.classType ());
        String name = ctx.Identifier ().getText ();
        AbstractExpr init = (AbstractExpr) map.get (ctx.expression ());
        map.put (ctx, new VarDecl (type, name, init,
                new SourcePosition (ctx.classType ()),
                new SourcePosition (ctx.Identifier ()),
                init == null ? null : new SourcePosition (ctx.expression ())
        ));
    }

    @Override
    public void enterFunctionParameters (MParser.FunctionParametersContext ctx) {

    }

    @Override
    public void exitFunctionParameters (MParser.FunctionParametersContext ctx) {

    }

    @Override
    public void enterVariableDeclarationStatement (MParser.VariableDeclarationStatementContext ctx) {

    }

    @Override
    public void exitVariableDeclarationStatement (MParser.VariableDeclarationStatementContext ctx) {
        map.put (ctx, map.get (ctx.variableDeclaration ()));
    }

    @Override
    public void enterClassBlock (MParser.ClassBlockContext ctx) {

    }

    @Override
    public void exitClassBlock (MParser.ClassBlockContext ctx) {

    }

    @Override
    public void enterClassVarDecl (MParser.ClassVarDeclContext ctx) {

    }

    @Override
    public void exitClassVarDecl (MParser.ClassVarDeclContext ctx) {
        map.put (ctx, new ClassVarDecl ((VarDecl) map.get (ctx.variableDeclarationStatement ())));
    }

    @Override
    public void enterClassCstrDecl (MParser.ClassCstrDeclContext ctx) {

    }

    @Override
    public void exitClassCstrDecl (MParser.ClassCstrDeclContext ctx) {
        map.put (ctx, map.get (ctx.constructorDeclaration ()));
    }

    @Override
    public void enterClassFuncDecl (MParser.ClassFuncDeclContext ctx) {

    }

    @Override
    public void exitClassFuncDecl (MParser.ClassFuncDeclContext ctx) {
        map.put (ctx, new ClassFuncDecl ((FuncDecl) map.get (ctx.functionDeclaration ())));
    }

    @Override
    public void enterConstructorDeclaration (MParser.ConstructorDeclarationContext ctx) {

    }

    @Override
    public void exitConstructorDeclaration (MParser.ConstructorDeclarationContext ctx) {
        ClassCstrDecl.Builder builder = new ClassCstrDecl.Builder ();
        builder.setName (ctx.Identifier ().getText ());
        builder.setBlock ((BlockStmt) map.get (ctx.blockStatement ()));
        if (ctx.functionParameters () != null){
            ctx.functionParameters ().variableDeclaration ().stream ().map (map::get).map (VarDecl.class::cast).forEachOrdered (builder::addParameter);
        }
        map.put (ctx, builder.build ());
    }

    @Override
    public void enterBlockStmt (MParser.BlockStmtContext ctx) {

    }

    @Override
    public void exitBlockStmt (MParser.BlockStmtContext ctx) {
        map.put (ctx, map.get (ctx.blockStatement ()));
    }

    @Override
    public void enterVarDeclStmt (MParser.VarDeclStmtContext ctx) {

    }

    @Override
    public void exitVarDeclStmt (MParser.VarDeclStmtContext ctx) {
        map.put (ctx, new VarDeclStmt ( (VarDecl) map.get (ctx.variableDeclarationStatement ())));
    }

    @Override
    public void enterBranchStmt (MParser.BranchStmtContext ctx) {

    }

    @Override
    public void exitBranchStmt (MParser.BranchStmtContext ctx) {
        map.put (ctx, map.get (ctx.branchStatement ()));
    }

    @Override
    public void enterLoopStmt (MParser.LoopStmtContext ctx) {

    }

    @Override
    public void exitLoopStmt (MParser.LoopStmtContext ctx) {
        map.put (ctx, map.get (ctx.loopStatement ()));
    }

    @Override
    public void enterExprStmt (MParser.ExprStmtContext ctx) {

    }

    @Override
    public void exitExprStmt (MParser.ExprStmtContext ctx) {
        map.put (ctx, new ExprStmt ( (AbstractExpr) map.get (ctx.expression ()), new SourcePosition (ctx.expression ())));
    }

    @Override
    public void enterJumpStmt (MParser.JumpStmtContext ctx) {

    }

    @Override
    public void exitJumpStmt (MParser.JumpStmtContext ctx) {
        map.put (ctx, map.get (ctx.jumpStatement ()));
    }

    @Override
    public void enterBlockStatement (MParser.BlockStatementContext ctx) {

    }

    @Override
    public void exitBlockStatement (MParser.BlockStatementContext ctx) {
        BlockStmt.Builder builder = new BlockStmt.Builder ();
        ctx.statement ().stream ().map(map::get).map (AbstractStmt.class::cast).forEachOrdered (builder::addStmt);
        map.put (ctx, builder.build ());
    }

    @Override
    public void enterBranchStatement (MParser.BranchStatementContext ctx) {

    }

    @Override
    public void exitBranchStatement (MParser.BranchStatementContext ctx) {
        AbstractExpr cond = (AbstractExpr) map.get (ctx.expression ());
        AbstractStmt ifStmt = (AbstractStmt) map.get (ctx.statement (0));
        AbstractStmt elseStmt = (AbstractStmt) map.get (ctx.statement (1));
        map.put (ctx, new BranchStmt (cond, ifStmt, elseStmt, new SourcePosition (ctx.start)));
    }

    @Override
    public void enterForStmt (MParser.ForStmtContext ctx) {

    }

    @Override
    public void exitForStmt (MParser.ForStmtContext ctx) {
        AbstractExpr init = (AbstractExpr) map.get (ctx.init);
        AbstractExpr cond = (AbstractExpr) map.get (ctx.cond);
        AbstractExpr step = (AbstractExpr) map.get (ctx.step);
        AbstractStmt stmt = (AbstractStmt) map.get (ctx.statement ());
        map.put (ctx, new ForStmt (init, cond, step, stmt,
                new SourcePosition (ctx.init),
                cond == null ? null : new SourcePosition (ctx.cond),
                new SourcePosition (ctx.step)
        ));
    }

    @Override
    public void enterWhileStmt (MParser.WhileStmtContext ctx) {

    }

    @Override
    public void exitWhileStmt (MParser.WhileStmtContext ctx) {
        map.put (ctx, new WhileStmt ((AbstractExpr) map.get (ctx.expression ()), (AbstractStmt) map.get (ctx.statement ()), new SourcePosition (ctx.expression ())));
    }

    @Override
    public void enterReturnStmt (MParser.ReturnStmtContext ctx) {

    }

    @Override
    public void exitReturnStmt (MParser.ReturnStmtContext ctx) {
        map.put (ctx, new ReturnStmt ((AbstractExpr) map.get (ctx.expression ()), new SourcePosition (ctx.start)));
    }

    @Override
    public void enterBreakStmt (MParser.BreakStmtContext ctx) {

    }

    @Override
    public void exitBreakStmt (MParser.BreakStmtContext ctx) {
        map.put (ctx, new BreakStmt (new SourcePosition (ctx.start)));
    }

    @Override
    public void enterContinueStmt (MParser.ContinueStmtContext ctx) {

    }

    @Override
    public void exitContinueStmt (MParser.ContinueStmtContext ctx) {
        map.put (ctx, new ContinueStmt (new SourcePosition (ctx.start)));
    }

    @Override
    public void enterEmptyStmt (MParser.EmptyStmtContext ctx) {

    }

    @Override
    public void exitEmptyStmt (MParser.EmptyStmtContext ctx) {
        map.put (ctx, new EmptyStmt ());
    }

    //    classType
    //    :   arrayClass  # ArrayType
    //    |   nonArrayClass   # NonArrayType
    //    ;
    @Override
    public void enterArrayType (MParser.ArrayTypeContext ctx) {

    }

    @Override
    public void exitArrayType (MParser.ArrayTypeContext ctx) {
        map.put(ctx, map.get (ctx.arrayClass ()));
    }

    @Override
    public void enterNonArrayType (MParser.NonArrayTypeContext ctx) {

    }

    @Override
    public void exitNonArrayType (MParser.NonArrayTypeContext ctx) {
        map.put (ctx, map.get (ctx.nonArrayClass ()));
    }

    //    arrayClass:   nonArrayClass (brackets)+;
    @Override
    public void enterArrayClass (MParser.ArrayClassContext ctx) {

    }

    @Override
    public void exitArrayClass (MParser.ArrayClassContext ctx) {
        String baseType = ((ClassType) map.get (ctx.nonArrayClass ())).getBaseType ();
        Integer dim = ctx.brackets ().size ();
        map.put(ctx, new ClassType (baseType, dim));
    }

    //    nonArrayClass
    //        :   type='bool'
    //        |   type='int'
    //        |   type='void'
    //        |   type='string'
    //        |   type=Identifier
    //    ;
    @Override
    public void enterNonArrayClass (MParser.NonArrayClassContext ctx) {

    }

    @Override
    public void exitNonArrayClass (MParser.NonArrayClassContext ctx) {
        String baseType = ctx.type.getText ();
        map.put(ctx, new ClassType (baseType, 0));
    }

    //    expression
    //    :   expression op=('++'|'--')   # PostfixIncDec
    //    |   expression '(' callParameter? ')'   # FunctionCall
    //    |   expression '[' expression ']'   # ArrayAcess
    //    |   expression '.' Identifier     # MemberAcess
    //
    //    |   <assoc=right> op=('++'|'--') expression # UnaryExpr
    //    |   <assoc=right> op=('+'|'-') expression   # UnaryExpr
    //    |   <assoc=right> op=('!'|'`') expression   # UnaryExpr
    //    |   <assoc=right> 'new' newObject   # NewExpr
    //
    //    |   expression op=('*'|'/'|'%') expression # BinaryExpr
    //    |   expression op=('-'|'+') expression  # BinaryExpr
    //    |   expression op=('<<'|'>>') expression    # BinaryExpr
    //    |   expression op=('<'|'<='|'>'|'>=') expression    # BinaryExpr
    //    |   expression op=('=='|'!=') expression    # BinaryExpr
    //    |   expression op='&' expression    # BinaryExpr
    //    |   expression op='^' expression    # BinaryExpr
    //    |   expression op='|' expression    # BinaryExpr
    //    |   expression op='&&' expression   # BinaryExpr
    //    |   expression op='||' expression   # BinaryExpr
    //    |   <assoc=right> expression op='=' expression  # BinaryExpr
    //
    //    |   Identifier  # Identifier
    //    |   constant    # Const
    //    |   '(' expression ')'  # SubExpr
    //    ;
    @Override
    public void enterIdentifier (MParser.IdentifierContext ctx) {

    }

    @Override
    public void exitIdentifier (MParser.IdentifierContext ctx) {
        String name = ctx.getText ();
        map.put (ctx, new Identifier (name));
    }

    @Override
    public void enterMemberAcess (MParser.MemberAcessContext ctx) {

    }

    @Override
    public void exitMemberAcess (MParser.MemberAcessContext ctx) {
        map.put (ctx, new MemberAcess (
                (AbstractExpr) map.get (ctx.expression ()), ctx.Identifier ().getText (),
                new SourcePosition (ctx.expression ()), new SourcePosition (ctx.Identifier ())
        ));
    }

    @Override
    public void enterArrayAcess (MParser.ArrayAcessContext ctx) {

    }

    @Override
    public void exitArrayAcess (MParser.ArrayAcessContext ctx) {
        map.put (ctx, new ArrayAcess (
                (AbstractExpr) map.get (ctx.expression (0)),
                (AbstractExpr) map.get (ctx.expression (1)),
                new SourcePosition (ctx.expression (0)),
                new SourcePosition (ctx.expression (1))
        ));
    }

    @Override
    public void enterConst (MParser.ConstContext ctx) {

    }

    @Override
    public void exitConst (MParser.ConstContext ctx) {
        map.put (ctx, map.get (ctx.constant ()));
    }

    @Override
    public void enterSubExpr (MParser.SubExprContext ctx) {

    }

    @Override
    public void exitSubExpr (MParser.SubExprContext ctx) {
        map.put (ctx, map.get (ctx.expression ()));
    }

    @Override
    public void enterBinaryExpr (MParser.BinaryExprContext ctx) {

    }

    @Override
    public void exitBinaryExpr (MParser.BinaryExprContext ctx) {
        BinaryExpr.BinaryOp op;
        switch (ctx.op.getType ()){
            case MParser.Mul: op = BinaryExpr.BinaryOp.MUL; break;
            case MParser.Div: op = BinaryExpr.BinaryOp.DIV; break;
            case MParser.Mod: op = BinaryExpr.BinaryOp.MOD; break;
            case MParser.Add: op = BinaryExpr.BinaryOp.ADD; break;
            case MParser.Sub: op = BinaryExpr.BinaryOp.SUB; break;
            case MParser.LShift: op = BinaryExpr.BinaryOp.LEFT_SHIFT; break;
            case MParser.RShift: op = BinaryExpr.BinaryOp.RIGHT_SHIFT; break;
            case MParser.EQ: op = BinaryExpr.BinaryOp.EQ; break;
            case MParser.NE: op = BinaryExpr.BinaryOp.NE; break;
            case MParser.LT: op = BinaryExpr.BinaryOp.LT; break;
            case MParser.GT: op = BinaryExpr.BinaryOp.GT; break;
            case MParser.LE: op = BinaryExpr.BinaryOp.LE; break;
            case MParser.GE: op = BinaryExpr.BinaryOp.GE; break;
            case MParser.BOr: op = BinaryExpr.BinaryOp.BITWISE_OR; break;
            case MParser.BAnd: op = BinaryExpr.BinaryOp.BITWISE_AND; break;
            case MParser.BXor: op = BinaryExpr.BinaryOp.XOR; break;
            case MParser.Or: op = BinaryExpr.BinaryOp.LOGICAL_OR; break;
            case MParser.And: op = BinaryExpr.BinaryOp.LOGICAL_AND; break;
            case MParser.Assign: op = BinaryExpr.BinaryOp.ASSIGN; break;
            default: throw new RuntimeException ("Unknown Operation");
        }
        map.put (ctx, new BinaryExpr (
                op, (AbstractExpr) map.get(ctx.expression (0)), (AbstractExpr) map.get(ctx.expression (1)),
                new SourcePosition (ctx.op), new SourcePosition (ctx.expression (0)), new SourcePosition (ctx.expression (1))
                ));
    }

    @Override
    public void enterNewExpr (MParser.NewExprContext ctx) {

    }

    @Override
    public void exitNewExpr (MParser.NewExprContext ctx) {
        map.put (ctx, new NewExpr ((AbstractNewObject) map.get (ctx.newObject ()), new SourcePosition (ctx.newObject ())));
    }

    @Override
    public void enterFunctionCall (MParser.FunctionCallContext ctx) {

    }

    @Override
    public void exitFunctionCall (MParser.FunctionCallContext ctx) {
        FunctionCall.Builder builder = new FunctionCall.Builder ();
        builder.setName ((AbstractExpr) map.get(ctx.expression ()));
        builder.setPosName (new SourcePosition (ctx.expression ()));
        if(ctx.callParameter () != null){
            ctx.callParameter ().expression ().stream ().map (map::get).map (AbstractExpr.class::cast).forEachOrdered (builder::addParameter);
            ctx.callParameter ().expression ().forEach (x -> builder.addPosParameter (new SourcePosition (x)));
        }
        map.put (ctx, builder.build ());
    }

    @Override
    public void enterPostfixIncDec (MParser.PostfixIncDecContext ctx) {

    }

    @Override
    public void exitPostfixIncDec (MParser.PostfixIncDecContext ctx) {
        UnaryExpr.UnaryOp op;
        switch (ctx.op.getType ()){
            case MParser.AddAdd: op = UnaryExpr.UnaryOp.POSTFIX_INC; break;
            case MParser.SubSub: op = UnaryExpr.UnaryOp.POSTFIX_DEC; break;
            default: throw new RuntimeException ("Unknown UnaryOp");
        }
        map.put (ctx, new UnaryExpr (op, (AbstractExpr) map.get(ctx.expression ()), new SourcePosition (ctx.expression ())));
    }

    @Override
    public void enterUnaryExpr (MParser.UnaryExprContext ctx) {

    }

    @Override
    public void exitUnaryExpr (MParser.UnaryExprContext ctx) {
        UnaryExpr.UnaryOp op;
        switch (ctx.op.getType ()){
            case MParser.AddAdd: op = UnaryExpr.UnaryOp.PREFIX_INC; break;
            case MParser.SubSub: op = UnaryExpr.UnaryOp.PREFIX_DEC; break;
            case MParser.Add: op = UnaryExpr.UnaryOp.POS; break;
            case MParser.Sub: op = UnaryExpr.UnaryOp.NEG; break;
            case MParser.Not: op = UnaryExpr.UnaryOp.LOGICAL_NOT; break;
            case MParser.BNot: op = UnaryExpr.UnaryOp.BITWISE_NOT; break;
            default: throw new RuntimeException ("Unknown UnaryOp");
        }
        map.put (ctx, new UnaryExpr (op, (AbstractExpr) map.get (ctx.expression ()), new SourcePosition (ctx.expression ())));
    }

    @Override
    public void enterCallParameter (MParser.CallParameterContext ctx) {

    }

    @Override
    public void exitCallParameter (MParser.CallParameterContext ctx) {

    }

    //    newObject
    //    :   nonArrayClass ('[' expression ']')+ ('[' ']')+ ('[' expression ']')+    # NewError
    //    |   nonArrayClass ('[' expression ']')+ (Brackets)*   # NewArray
    //    |   nonArrayClass ('(' callParameter? ')')? # NewNonArray
    //    ;
    @Override
    public void enterNewError (MParser.NewErrorContext ctx) {
        throw new RuntimeException ("New Expr Error");
    }

    @Override
    public void exitNewError (MParser.NewErrorContext ctx) {

    }

    @Override
    public void enterNewArray (MParser.NewArrayContext ctx) {

    }

    @Override
    public void exitNewArray (MParser.NewArrayContext ctx) {
        NewArray.Builder builder = new NewArray.Builder ();
        ClassType type = (ClassType) map.get (ctx.nonArrayClass ());
        ClassType newType = new ClassType (type.getBaseType (), ctx.brackets ().size () + ctx.expression ().size ());
        builder.setType (newType);
        ctx.expression ().stream ().map(map::get).map(AbstractExpr.class::cast).forEachOrdered (builder::addLens);
        map.put (ctx, builder.build ());
    }

    @Override
    public void enterNewNonArray (MParser.NewNonArrayContext ctx) {

    }


    @Override
    public void exitNewNonArray (MParser.NewNonArrayContext ctx) {
        NewNonArray.Builder builder = new NewNonArray.Builder ();
        builder.setType ((ClassType) map.get (ctx.nonArrayClass ()));
        if(ctx.callParameter () != null){
            ctx.callParameter ().expression ().stream ().map (map::get).map (AbstractExpr.class::cast).forEachOrdered (builder::addParameter);
        }
        map.put (ctx, builder.build ());
    }

    //    constant
    //    :   type=BoolConst
    //    |   type=NumConst
    //    |   type=StrConst
    //    |   type=NullConst
    //    ;
    @Override
    public void enterConstant (MParser.ConstantContext ctx) {

    }

    @Override
    public void exitConstant (MParser.ConstantContext ctx) {
        String s = ctx.type.getText ();
        Integer type = ctx.type.getType ();
        switch (type){
            case MParser.NumConst: map.put (ctx, new NumConst (Integer.valueOf (s))); break;
            case MParser.NullConst: map.put (ctx, new NullConst ()); break;
            case MParser.StrConst: map.put (ctx, new StrConst (s)); break;
            case MParser.BoolConst: map.put (ctx, new BoolConst (s.equals ("true"))); break;
            default: throw new RuntimeException ("Unknown Constant type");
        }
    }

    @Override
    public void enterBrackets (MParser.BracketsContext ctx) {

    }

    @Override
    public void exitBrackets (MParser.BracketsContext ctx) {

    }

    @Override
    public void visitTerminal (TerminalNode terminalNode) {

    }

    @Override
    public void visitErrorNode (ErrorNode errorNode) {

    }

    @Override
    public void enterEveryRule (ParserRuleContext parserRuleContext) {

    }

    @Override
    public void exitEveryRule (ParserRuleContext parserRuleContext) {

    }
}
