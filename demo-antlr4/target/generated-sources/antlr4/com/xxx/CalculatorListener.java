// Generated from com/xxx/Calculator.g4 by ANTLR 4.7.1
package com.xxx;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CalculatorParser}.
 */
public interface CalculatorListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(CalculatorParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(CalculatorParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link CalculatorParser#line}.
	 * @param ctx the parse tree
	 */
	void enterLine(CalculatorParser.LineContext ctx);
	/**
	 * Exit a parse tree produced by {@link CalculatorParser#line}.
	 * @param ctx the parse tree
	 */
	void exitLine(CalculatorParser.LineContext ctx);
}