package Compiler2018.AST;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.TerminalNode;

public class SourcePosition {
    private final int line;
    private final int column;

    public int getLine() {
        return line;
    }

    public int getColumn() {
        return column;
    }

    public SourcePosition(int line, int column) {
        this.line = line;
        this.column = column;
    }

    public SourcePosition(Token token) {
        this.line = token.getLine();
        this.column = token.getCharPositionInLine();
    }

    public SourcePosition(ParserRuleContext ctx) {
        this(ctx.start);
    }

    public SourcePosition(TerminalNode terminal) {
        this(terminal.getSymbol());
    }

    @Override
    public String toString() {
        return "Line " + line + " Column " + column;
    }
}
