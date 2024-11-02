package dev.flint.parser;

import dev.flint.ast.*;
import dev.flint.lexer.Token;
import dev.flint.lexer.TokenType;
import java.util.ArrayList;
import java.util.List;

public class StatementParser {
    private final Parser parser;

    public StatementParser(Parser parser) {
        this.parser = parser;
    }

    public ASTNode parseStatements() {
        List<ASTNode> statements = new ArrayList<>();
        while (!parser.isAtEnd()) {
            statements.add(parseStatement());
        }
        return new BlockNode(statements);
    }

    private ASTNode parseStatement() {
        if (parser.match(TokenType.PRINT)) {
            return parsePrintStatement();
        } else if (parser.match(TokenType.IF)) {
            return parseIfStatement();
        } else if (parser.match(TokenType.WHILE)) {
            return parseWhileStatement();
        } else if (parser.match(TokenType.VAR)) {
            return parseVarDeclaration();
        } else if (parser.match(TokenType.IDENTIFIER)) {
            return parseAssignment();
        } else {
            return parser.expressionParser.parseExpression();
        }
    }

    private ASTNode parsePrintStatement() {
        parser.consume(TokenType.PRINT, "Expected 'print' keyword.");
        ASTNode value = parser.expressionParser.parseExpression();
        parser.consume(TokenType.SEMICOLON, "Expected ';' after print statement.");
        return new PrintNode(value);
    }

    private ASTNode parseIfStatement() {
        parser.consume(TokenType.IF, "Expected 'if' keyword.");
        parser.consume(TokenType.LEFT_PAREN, "Expected '(' after 'if'.");
        ASTNode condition = parser.expressionParser.parseExpression();
        parser.consume(TokenType.RIGHT_PAREN, "Expected ')' after if condition.");

        ASTNode thenBranch = parseStatement();

        ASTNode elseBranch = null;
        if (parser.match(TokenType.ELSE)) {
            elseBranch = parseStatement();
        }

        return new IfNode(condition, thenBranch, elseBranch);
    }

    private ASTNode parseWhileStatement() {
        parser.consume(TokenType.WHILE, "Expected 'while' keyword.");
        parser.consume(TokenType.LEFT_PAREN, "Expected '(' after 'while'.");
        ASTNode condition = parser.expressionParser.parseExpression();
        parser.consume(TokenType.RIGHT_PAREN, "Expected ')' after while condition.");

        ASTNode body = parseStatement();
        return new WhileNode(condition, body);
    }

    private ASTNode parseVarDeclaration() {
        parser.consume(TokenType.VAR, "Expected 'var' keyword.");
        Token identifier = parser.consume(TokenType.IDENTIFIER, "Expected variable name.");
        parser.consume(TokenType.EQUAL, "Expected '=' after variable name.");
        ASTNode initializer = parser.expressionParser.parseExpression();
        parser.consume(TokenType.SEMICOLON, "Expected ';' after variable declaration.");
        return new VarDeclarationNode(identifier, initializer);
    }

    private ASTNode parseAssignment() {
        Token identifier = parser.previous();
        parser.consume(TokenType.EQUAL, "Expected '=' after variable name.");
        ASTNode value = parser.expressionParser.parseExpression();
        parser.consume(TokenType.SEMICOLON, "Expected ';' after assignment.");
        return new AssignmentNode(identifier, value);
    }
}
