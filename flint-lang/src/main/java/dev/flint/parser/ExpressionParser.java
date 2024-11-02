package dev.flint.parser;

import dev.flint.ast.*;
import dev.flint.lexer.Token;
import dev.flint.lexer.TokenType;

public class ExpressionParser {
    private final Parser parser;

    public ExpressionParser(Parser parser) {
        this.parser = parser;
    }

    public ASTNode parseExpression() {
        return parseEquality();
    }

    // Parses equality expressions: == and !=
    private ASTNode parseEquality() {
        ASTNode node = parseComparison();

        while (parser.match(TokenType.EQUAL_EQUAL, TokenType.BANG_EQUAL)) {
            Token operator = parser.previous();
            ASTNode right = parseComparison();
            node = new BinaryOperationNode(node, operator, right);
        }

        return node;
    }

    // Parses comparison expressions: <, <=, >, >=
    private ASTNode parseComparison() {
        ASTNode node = parseAddition();

        while (parser.match(TokenType.LESS, TokenType.LESS_EQUAL, TokenType.GREATER, TokenType.GREATER_EQUAL)) {
            Token operator = parser.previous();
            ASTNode right = parseAddition();
            node = new BinaryOperationNode(node, operator, right);
        }

        return node;
    }

    // Parses addition and subtraction: + and -
    private ASTNode parseAddition() {
        ASTNode node = parseMultiplication();

        while (parser.match(TokenType.PLUS, TokenType.MINUS)) {
            Token operator = parser.previous();
            ASTNode right = parseMultiplication();
            node = new BinaryOperationNode(node, operator, right);
        }

        return node;
    }

    // Parses multiplication and division: * and /
    private ASTNode parseMultiplication() {
        ASTNode node = parseUnary();

        while (parser.match(TokenType.STAR, TokenType.SLASH)) {
            Token operator = parser.previous();
            ASTNode right = parseUnary();
            node = new BinaryOperationNode(node, operator, right);
        }

        return node;
    }

    // Parses unary operators: ! and -
    private ASTNode parseUnary() {
        if (parser.match(TokenType.BANG, TokenType.MINUS)) {
            Token operator = parser.previous();
            ASTNode right = parseUnary();
            return new UnaryOperationNode(operator, right);
        }

        return parsePrimary();
    }

    // Parses primary expressions: numbers, booleans, identifiers, and grouping
    private ASTNode parsePrimary() {
        if (parser.match(TokenType.NUMBER)) {
            return new LiteralNode(parser.previous().getLiteralValue());
        }

        if (parser.match(TokenType.BOOLEAN)) {
            return new LiteralNode(parser.previous().getLiteralValue());
        }

        if (parser.match(TokenType.IDENTIFIER)) {
            return new VariableNode(parser.previous());
        }

        if (parser.match(TokenType.LEFT_PAREN)) {
            ASTNode expression = parseExpression();
            parser.consume(TokenType.RIGHT_PAREN, "Expected ')' after expression.");
            return new GroupingNode(expression);
        }

        throw new ParseException("Expected expression.");
    }
}
