package dev.flint.parser;


package parser;

import java.util.List;

public class Parser {
    private final List<Token> tokens; // List of tokens from the lexer
    private int current = 0;          // Pointer to track current token
    private StatementParser statementParser;
    private ExpressionParser expressionParser;

    public Parser(List<Token> tokens) {
        this.tokens = tokens;
        this.statementParser = new StatementParser(this);
        this.expressionParser = new ExpressionParser(this);
    }

    // Starts parsing, beginning with statements (the entry point)
    public ASTNode parse() {
        return statementParser.parseStatements();
    }

    /**
     * Advances to the next token if not at the end, and returns the current token.
     */
    Token advance() {
        if (!isAtEnd()) current++;
        return previous();
    }

    /**
     * Checks if the current token matches any of the given types.
     * Advances if a match is found.
     */
    boolean match(TokenType... types) {
        for (TokenType type : types) {
            if (check(type)) {
                advance();
                return true;
            }
        }
        return false;
    }

    /**
     * Consumes a token of the specified type.
     * Throws an error with the given message if the current token is not of the specified type.
     */
    Token consume(TokenType type, String message) {
        if (check(type)) return advance();
        throw new ParseError(message);
    }

    /**
     * Checks if the current token matches a given type.
     */
    boolean check(TokenType type) {
        if (isAtEnd()) return false;
        return peek().getType() == type;
    }

    /**
     * Checks if the parser has reached the end of the token list.
     */
    boolean isAtEnd() {
        return peek().getType() == TokenType.EOF;
    }

    /**
     * Returns the current token.
     */
    Token peek() {
        return tokens.get(current);
    }

    /**
     * Returns the previous token (the last token we advanced past).
     */
    Token previous() {
        return tokens.get(current - 1);
    }
}

