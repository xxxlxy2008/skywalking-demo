package com.xxx;

import org.antlr.v4.runtime.ANTLRInputStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

public class Main {
    public static void main(String[] args) throws Exception {
        // 这里处理的是"1+2"这一行计算器语言，读取得到字节流
        ANTLRInputStream input = new ANTLRInputStream("1+2");
        // 创建CalculatorLexer，词法分析器(Lexer)识别字节流得到 Token流
        com.xxx.CalculatorLexer lexer = new com.xxx.CalculatorLexer(input);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        // 创建CalculatorParser，语法分析器(Parser)识别 Token流得到 AST
        com.xxx.CalculatorParser parser = new com.xxx.CalculatorParser(tokens);
        ParseTree tree = parser.line();
        // 遍历 AST中各个节点回调 PrintListener中相应的方法
        ParseTreeWalker walker = new ParseTreeWalker();
        walker.walk(new PrintListener(), tree);
        // 将整个 AST转换成字符串输出
        System.out.println(tree.toStringTree(parser));
    }
}
