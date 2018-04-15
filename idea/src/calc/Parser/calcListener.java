// Generated from D:/Coding/M-compiler/idea/src/calc\calc.g4 by ANTLR 4.7
package Parser;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link calcParser}.
 */
public interface calcListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link calcParser#calc}.
	 * @param ctx the parse tree
	 */
	void enterCalc(calcParser.CalcContext ctx);
	/**
	 * Exit a parse tree produced by {@link calcParser#calc}.
	 * @param ctx the parse tree
	 */
	void exitCalc(calcParser.CalcContext ctx);
	/**
	 * Enter a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link calcParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterPrintExpr(calcParser.PrintExprContext ctx);
	/**
	 * Exit a parse tree produced by the {@code printExpr}
	 * labeled alternative in {@link calcParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitPrintExpr(calcParser.PrintExprContext ctx);
	/**
	 * Enter a parse tree produced by the {@code assign}
	 * labeled alternative in {@link calcParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterAssign(calcParser.AssignContext ctx);
	/**
	 * Exit a parse tree produced by the {@code assign}
	 * labeled alternative in {@link calcParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitAssign(calcParser.AssignContext ctx);
	/**
	 * Enter a parse tree produced by the {@code blank}
	 * labeled alternative in {@link calcParser#stmt}.
	 * @param ctx the parse tree
	 */
	void enterBlank(calcParser.BlankContext ctx);
	/**
	 * Exit a parse tree produced by the {@code blank}
	 * labeled alternative in {@link calcParser#stmt}.
	 * @param ctx the parse tree
	 */
	void exitBlank(calcParser.BlankContext ctx);
	/**
	 * Enter a parse tree produced by the {@code paren}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterParen(calcParser.ParenContext ctx);
	/**
	 * Exit a parse tree produced by the {@code paren}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitParen(calcParser.ParenContext ctx);
	/**
	 * Enter a parse tree produced by the {@code addSub}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterAddSub(calcParser.AddSubContext ctx);
	/**
	 * Exit a parse tree produced by the {@code addSub}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitAddSub(calcParser.AddSubContext ctx);
	/**
	 * Enter a parse tree produced by the {@code id}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterId(calcParser.IdContext ctx);
	/**
	 * Exit a parse tree produced by the {@code id}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitId(calcParser.IdContext ctx);
	/**
	 * Enter a parse tree produced by the {@code mulDiv}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterMulDiv(calcParser.MulDivContext ctx);
	/**
	 * Exit a parse tree produced by the {@code mulDiv}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitMulDiv(calcParser.MulDivContext ctx);
	/**
	 * Enter a parse tree produced by the {@code literal}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(calcParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by the {@code literal}
	 * labeled alternative in {@link calcParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(calcParser.LiteralContext ctx);
}