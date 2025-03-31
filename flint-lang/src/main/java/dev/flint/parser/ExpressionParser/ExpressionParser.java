
package dev.flint.parser.ExpressionParser;

import dev.flint.ast.expressions.*;
import dev.flint.lexer.Token;
import dev.flint.lexer.TokenType;
import dev.flint.parser.ParseException;
import dev.flint.parser.Parser;

public class ExpressionParser {
    private final Parser parser;

    public ExpressionParser(Parser parser) {
        this.parser = parser;
    }

    public ExpressionNode parseExpression() {
        return parseLogicalOr(); // Start with logical OR
    }

    private ExpressionNode parseLogicalOr() {
        ExpressionNode node = parseLogicalAnd();

        while (parser.match(TokenType.OR)) {
            Token operator = parser.previous();
            ExpressionNode right = parseLogicalAnd();
            node = new BinaryOperationNode(node, operator.getType(), right);
        }

        return node;
    }

    private ExpressionNode parseLogicalAnd() {
        ExpressionNode node = parseEquality();

        while (parser.match(TokenType.AND)) {
            Token operator = parser.previous();
            ExpressionNode right = parseEquality();
            node = new BinaryOperationNode(node, operator.getType(), right);
        }

        return node;
    }

    private ExpressionNode parseEquality() {
        ExpressionNode node = parseComparison();

        while (parser.match(TokenType.EQUAL, TokenType.NOT_EQUAL)) {
            Token operator = parser.previous();
            ExpressionNode right = parseComparison();
            node = new BinaryOperationNode(node, operator.getType(), right);
        }

        return node;
    }

    private ExpressionNode parseComparison() {
        ExpressionNode node = parseAddition();

        while (parser.match(TokenType.LESS_THAN, TokenType.LESS_EQUAL, TokenType.GREATER_THAN, TokenType.GREATER_EQUAL)) {
            Token operator = parser.previous();
            ExpressionNode right = parseAddition();
            node = new BinaryOperationNode(node, operator.getType(), right);
        }

        return node;
    }

    private ExpressionNode parseAddition() {
        ExpressionNode node = parseMultiplication();

        while (parser.match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = parser.previous();
            ExpressionNode right = parseMultiplication();
            node = new BinaryOperationNode(node, operator.getType(), right);
        }

        return node;
    }

    private ExpressionNode parseMultiplication() {
        ExpressionNode node = parseUnary();

        while (parser.match(TokenType.MULTIPLY, TokenType.DIVIDE)) {
            Token operator = parser.previous();
            ExpressionNode right = parseUnary();
            node = new BinaryOperationNode(node, operator.getType(), right);
        }

        return node;
    }

    private ExpressionNode parseUnary() {
        if (parser.match(TokenType.NOT, TokenType.MINUS)) {
            Token operator = parser.previous();
            ExpressionNode right = parseUnary();
            return new UnaryOperationNode(operator, right);
        }

        return parsePrimary();
    }

    private ExpressionNode parsePrimary() {
        if (parser.match(TokenType.NUMBER)) {
            return new NumberNode(Double.parseDouble(parser.previous().getValue()));
        }

        if (parser.match(TokenType.BOOLEAN)) {
            return new BooleanNode(Boolean.parseBoolean(parser.previous().getValue()));
        }

        if (parser.match(TokenType.CHAR)) {
            return new CharNode(parser.previous().getValue().charAt(0));
        }

        if (parser.match(TokenType.IDENTIFIER)) {
            return new VariableNode(parser.previous().getValue());
        }

        if (parser.match(TokenType.LPAREN)) {
            ExpressionNode expression = parseExpression();
            parser.consume(TokenType.RPAREN, "Expected ')' after expression.");
            return expression;
        }

        throw new ParseException("Expected expression.");
    }
}
