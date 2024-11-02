package dev.flint.parser;

package parser;

import java.util.ArrayList;
import java.util.List;

import dev.flint.ast.BlockNode;
import dev.flint.ast.PrintNode;
import dev.flint.lexer.Token;

public class StatementParser {
    private Parser parser;

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
        } else if (parser.match(TokenType.IDENTIFIER)) {
            return parseAssignment();
        } else {
            return parser.expressionParser.parseExpression();
        }
    }

    private ASTNode parsePrintStatement() {
        Token printToken = parser.previous();  // Assuming we’ve just matched PRINT
        ASTNode value = parser.expressionParser.parseExpression();  // Parse the expression to be printed
        parser.consume(TokenType.SEMICOLON, "Expected ';' after print statement.");
        return new PrintNode(printToken, value);  // Create PrintNode with the print token and value
    }

    private ASTNode parseAssignment() {
        Token identifier = parser.previous();  // The identifier token
        parser.consume(TokenType.EQUAL, "Expected '=' after variable name.");
        ASTNode value = parser.expressionParser.parseExpression();  // Parse the expression for assignment
        parser.consume(TokenType.SEMICOLON, "Expected ';' after assignment.");
        return new AssignmentNode(identifier, value);  // Create AssignmentNode with identifier and value
    }

}
