package dev.flint.ast.expressions;

import dev.flint.interpreter.ExecutionContext;
import dev.flint.lexer.TokenType;

public class ComparisonOperationNode extends ExpressionNode {
    private final ExpressionNode left;
    private final TokenType operator;
    private final ExpressionNode right;

    public ComparisonOperationNode(ExpressionNode left, TokenType operator, ExpressionNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object leftValue = left.execute(context);
        Object rightValue = right.execute(context);

        switch (operator) {
            case EQUAL:
                return leftValue.equals(rightValue);
            case NOT_EQUAL:
                return !leftValue.equals(rightValue);
            case LESS_THAN:
                return (double) leftValue < (double) rightValue;
            case GREATER_THAN:
                return (double) leftValue > (double) rightValue;
            case LESS_EQUAL:
                return (double) leftValue <= (double) rightValue;
            case GREATER_EQUAL:
                return (double) leftValue >= (double) rightValue;
            default:
                throw new UnsupportedOperationException("Unknown comparison operator: " + operator);
        }
    }

    @Override
    public String toString() {
        return "(" + left + " " + operator + " " + right + ")";
    }
}
