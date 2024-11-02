package dev.flint.parser;

package parser;

public class PrimaryParser {
    private Parser parser;

    public PrimaryParser(Parser parser) {
        this.parser = parser;
    }

    public ASTNode parsePrimary() {
        if (parser.match(TokenType.NUMBER, TokenType.BOOLEAN, TokenType.CHAR)) {
            return new LiteralNode(parser.previous());
        }
        if (parser.match(TokenType.LEFT_PAREN)) {
            ASTNode expr = parser.expressionParser.parseExpression();
            parser.consume(TokenType.RIGHT_PAREN, "Expected ')' after expression.");
            return new GroupingNode(expr);
        }
        throw new ParseError("Expected expression.");
    }
}
