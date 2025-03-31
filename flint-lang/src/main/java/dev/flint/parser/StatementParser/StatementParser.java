package dev.flint.parser.StatementParser;

import dev.flint.ast.statements.*;
import dev.flint.ast.ASTNode;
import dev.flint.ast.expressions.ExpressionNode;
import dev.flint.lexer.Token;
import dev.flint.lexer.TokenType;
import dev.flint.parser.Parser;

import java.util.ArrayList;
import java.util.List;

public class StatementParser {
    private final Parser parser;

    public StatementParser(Parser parser) {
        this.parser = parser;
    }

    public BlockNode parseStatements() {
        List<StatementNode> statements = new ArrayList<>();
        while (!parser.isAtEnd()) {
            statements.add( (StatementNode) parseStatement());
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
            return parser.getExpressionParser().parseExpression();
        }
    }

    private PrintNode parsePrintStatement() {
        parser.consume(TokenType.PRINT, "Expected 'print' keyword.");
        ExpressionNode value =  (ExpressionNode) parser.getExpressionParser().parseExpression();
        parser.consume(TokenType.SEMICOLON, "Expected ';' after print statement.");
        return new PrintNode(value);
    }

    private IfNode parseIfStatement() {
        parser.consume(TokenType.IF, "Expected 'if' keyword.");
        parser.consume(TokenType.LPAREN, "Expected '(' after 'if'.");
        ExpressionNode condition = (ExpressionNode) parser.getExpressionParser().parseExpression();
        parser.consume(TokenType.RPAREN, "Expected ')' after if condition.");

        StatementNode thenBranch = (StatementNode) parseStatement();
        StatementNode elseBranch = null;
        if (parser.match(TokenType.ELSE)) {
            elseBranch = (StatementNode) parseStatement();
        }

        return new IfNode(condition, thenBranch, elseBranch);
    }

    private WhileNode parseWhileStatement() {
        parser.consume(TokenType.WHILE, "Expected 'while' keyword.");
        parser.consume(TokenType.LPAREN, "Expected '(' after 'while'.");
        ExpressionNode condition = parser.getExpressionParser().parseExpression();
        parser.consume(TokenType.RPAREN, "Expected ')' after while condition.");

        StatementNode body = (StatementNode) parseStatement();
        return new WhileNode(condition, body);
    }

    private VarDeclarationNode parseVarDeclaration() {
        parser.consume(TokenType.VAR, "Expected 'var' keyword.");
        Token identifier = parser.consume(TokenType.IDENTIFIER, "Expected variable name.");
        parser.consume(TokenType.EQUAL, "Expected '=' after variable name.");
        ExpressionNode initializer = (ExpressionNode) parser.getExpressionParser().parseExpression();
        parser.consume(TokenType.SEMICOLON, "Expected ';' after variable declaration.");
        return new VarDeclarationNode(identifier.getValue(), initializer);
    }

    private VarAssignmentNode parseAssignment() {
        Token identifier = parser.previous();
        parser.consume(TokenType.EQUAL, "Expected '=' after variable name.");
        ExpressionNode value = (ExpressionNode) parser.getExpressionParser().parseExpression();
        parser.consume(TokenType.SEMICOLON, "Expected ';' after assignment.");
        return new VarAssignmentNode(identifier, value);
    }
}
