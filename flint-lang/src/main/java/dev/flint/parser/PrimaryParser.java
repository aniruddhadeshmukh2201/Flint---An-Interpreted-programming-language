package dev.flint.parser;

package parser;

public class PrimaryParser {
    private final Parser parser;

    public PrimaryParser(Parser parser) {
        this.parser = parser;
    }

    public ASTNode parsePrimary() {
        // Parse literals: numbers, booleans, characters, and strings
        if (parser.match(TokenType.NUMBER, TokenType.BOOLEAN, TokenType.CHAR, TokenType.STRING)) {
            return new LiteralNode(parser.previous().getLiteralValue());
        }
        
        // Parse identifiers (variables)
        if (parser.match(TokenType.IDENTIFIER)) {
            return new VariableNode(parser.previous());
        }

        // Parse grouped expressions: (expression)
        if (parser.match(TokenType.LEFT_PAREN)) {
            ASTNode expression = parser.expressionParser.parseExpression();
            parser.consume(TokenType.RIGHT_PAREN, "Expected ')' after expression.");
            return new GroupingNode(expression);
        }

        throw new ParseError("Expected expression, but found: " + parser.currentToken());
    }
}