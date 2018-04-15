package Parser;

import org.antlr.v4.runtime.tree.ParseTreeProperty;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by abcdabcd987 on 2016-03-23.
 */
public class Evaluator extends calcBaseListener {
    public Map<String, Double> vars = new HashMap<>();
    public ParseTreeProperty<Double> values = new ParseTreeProperty<>();

    // stmt : ID '=' expr NEWLINE ;
    @Override
    public void exitAssign(calcParser.AssignContext ctx) {
        String id = ctx.ID().getText();
        Double val = values.get(ctx.expr());
        vars.put(id, val);
    }

    // stmt : expr NEWLINE ;
    @Override
    public void exitPrintExpr(calcParser.PrintExprContext ctx) {
        System.out.println(values.get(ctx.expr()));
    }

    // expr : NUMBER ;
    @Override
    public void exitLiteral(calcParser.LiteralContext ctx) {
        values.put(ctx, Double.valueOf(ctx.NUMBER().getText()));
    }

    // expr : ID ;
    @Override
    public void exitId(calcParser.IdContext ctx) {
        values.put(ctx, vars.containsKey(ctx.ID().getText()) ? vars.get(ctx.ID().getText()) : .0);
    }

    // expr : expr op=('*'|'/') expr ;
    @Override
    public void exitMulDiv(calcParser.MulDivContext ctx) {
        double lhs = values.get(ctx.expr(0));
        double rhs = values.get(ctx.expr(1));
        values.put(ctx, ctx.op.getType() == calcParser.MUL ? lhs * rhs : lhs / rhs);
    }

    // expr : expr op=('+'|'-') expr ;
    @Override
    public void exitAddSub(calcParser.AddSubContext ctx) {
        double lhs = values.get(ctx.expr(0));
        double rhs = values.get(ctx.expr(1));
        values.put(ctx, ctx.op.getType() == calcParser.ADD ? lhs + rhs : lhs - rhs);
    }

    // expr : '(' expr ')' ;
    @Override
    public void exitParen(calcParser.ParenContext ctx) {
        values.put(ctx, values.get(ctx.expr()));
    }
}