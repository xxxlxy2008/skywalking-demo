package com.xxx;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.TerminalNode;

public class PrintListener extends com.xxx.CalculatorBaseListener {
    @Override
    public void enterLine(com.xxx.CalculatorParser.LineContext ctx) {
        System.out.println("enterLine:" + ctx.getText());
    }

    @Override
    public void enterExpr(com.xxx.CalculatorParser.ExprContext ctx) {
        System.out.println("enterExpr:" + ctx.getText());
    }

    @Override
    public void exitExpr(com.xxx.CalculatorParser.ExprContext ctx) {
        System.out.println("exitExpr:" + ctx.getText());
    }

    @Override
    public void enterEveryRule(ParserRuleContext ctx) {
        System.out.println("enterEveryRule:" + ctx.getText());
    }

    @Override
    public void exitEveryRule(ParserRuleContext ctx) {
        System.out.println("exitEveryRule:" + ctx.getText());
    }

    @Override
    public void visitTerminal(TerminalNode node) {
        System.out.println("visitTerminal:" + node.getText());
    }

    @Override
    public void visitErrorNode(ErrorNode node) {
        System.out.println("visitErrorNode:" + node.getText());
    }
}
