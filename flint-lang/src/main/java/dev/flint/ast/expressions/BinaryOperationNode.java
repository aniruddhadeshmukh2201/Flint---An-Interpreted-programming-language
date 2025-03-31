package dev.flint.ast.expressions;

import dev.flint.interpreter.ExecutionContext;
import dev.flint.lexer.TokenType;

public class BinaryOperationNode extends ExpressionNode {
    private final ExpressionNode left;
    private final TokenType operator;
    private final ExpressionNode right;

    public BinaryOperationNode(ExpressionNode left, TokenType operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object leftValue = left.execute(context);
        Object rightValue = right.execute(context);

        switch (operator) {
            // Arithmetic operations
            case PLUS:
                return (double) leftValue + (double) rightValue;
            case MINUS:
                return (double) leftValue - (double) rightValue;
            case MULTIPLY:
                return (double) leftValue * (double) rightValue;
            case DIVIDE:
                return (double) leftValue / (double) rightValue;

            // Logical operations
            case AND:
                if (leftValue instanceof Boolean && rightValue instanceof Boolean) {
                    return (Boolean) leftValue && (Boolean) rightValue;
                }
                throw new RuntimeException("Logical '&&' requires boolean operands.");
            case OR:
                if (leftValue instanceof Boolean && rightValue instanceof Boolean) {
                    return (Boolean) leftValue || (Boolean) rightValue;
                }
                throw new RuntimeException("Logical '||' requires boolean operands.");

            default:
                throw new UnsupportedOperationException("Unknown operator: " + operator);
        }
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }

    
}
