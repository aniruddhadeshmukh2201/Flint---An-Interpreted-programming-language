package dev.flint.ast;

import java.util.List;

import dev.flint.ast.expressions.BinaryOperationNode;
import dev.flint.ast.expressions.BooleanNode;
import dev.flint.ast.expressions.CharNode;
import dev.flint.ast.expressions.ExpressionNode;
import dev.flint.ast.expressions.NumberNode;
import dev.flint.ast.statements.BlockNode;
import dev.flint.ast.statements.IfNode;
import dev.flint.ast.statements.PrintNode;
import dev.flint.ast.statements.StatementNode;
import dev.flint.ast.statements.VarAssignmentNode;
import dev.flint.ast.statements.VarDeclarationNode;
import dev.flint.ast.statements.WhileNode;
import dev.flint.lexer.Token;
import dev.flint.lexer.TokenType;

public class ASTBuilder {
    public static VarAssignmentNode createAssignmentNode(Token identifier, ASTNode value) {
        return new VarAssignmentNode(identifier, value);
    }

    public static BinaryOperationNode createBinaryOperationNode(ExpressionNode left, TokenType operator, ExpressionNode right) {
        return new BinaryOperationNode(left, operator, right);
    }

    public static IfNode createIfNode(ExpressionNode condition, StatementNode thenBlock, StatementNode elseBlock) {
        return new IfNode(condition, thenBlock, elseBlock);
    }

    public static WhileNode createWhileNode(ExpressionNode condition, StatementNode block) {
        return new WhileNode(condition, block);
    }

    public static CharNode createCharNode(Token character) {
        return new CharNode(character.getValue().charAt(0));
    }

    public static BooleanNode createBooleanNode(Token bool) {
        return new BooleanNode(Boolean.parseBoolean(bool.getValue()));
    }

    public static NumberNode createNumberNode(Token number) {
        return new NumberNode(Double.parseDouble(number.getValue()));
    }

    public static PrintNode createPrintNode(ASTNode value) {
        return new PrintNode(value);
    }

    public static VarDeclarationNode createVarDeclarationNode(Token identifier, ExpressionNode value) {
        return new VarDeclarationNode(identifier.getValue(), value);
    }

    public static BlockNode createBlockNode(List<StatementNode> statements) {
        return new BlockNode(statements);
    }
}
