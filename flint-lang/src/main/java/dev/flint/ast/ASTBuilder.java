package dev.flint.parser;

package dev.flint.ast;

import dev.flint.lexer.Token;

public class ASTBuilder {
    public static VarAssignmentNode createAssignmentNode(Token identifier, ASTNode value) {
        return new VarAssignmentNode(identifier, value);
    }

    public static BinaryOperationNode createBinaryOperationNode(ASTNode left, Token operator, ASTNode right) {
        return new BinaryOperationNode(left, operator, right);
    }

    public static IfNode createIfNode(ASTNode condition, ASTNode thenBlock, ASTNode elseBlock) {
        return new IfNode(condition, thenBlock, elseBlock);
    }

    public static WhileNode createWhileNode(ASTNode condition, ASTNode block) {
        return new WhileNode(condition, block);
    }

    public static CharNode createCharNode(Token character) {
        return new CharNode(character);
    }

    public static BooleanNode createBooleanNode(Token bool) {
        return new BooleanNode(bool);
    }

    public static NumberNode createNumberNode(Token number) {
        return new NumberNode(number);
    }

    public static PrintNode createPrintNode(ASTNode value) {
        return new PrintNode(value);
    }

    public static VarDeclarationNode createVarDeclarationNode(Token identifier, ASTNode value) {
        return new VarDeclarationNode(identifier, value);
    }
}
