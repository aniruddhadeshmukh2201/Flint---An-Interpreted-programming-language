package dev.flint.ast.statements;

import dev.flint.ast.AstNode;
import dev.flint.ast.expressions.ExpressionNode;

// Node for arithmetic operations
public class BinaryOperationNode extends ExpressionNode {
    private final AstNode left;
    private final String operator; // e.g., "+", "-", "*", "/"
    private final AstNode right;

    public BinaryOperationNode(AstNode left, String operator, AstNode right) {
        this.left = left;
        this.operator = operator;
        this.right = right;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object leftValue = left.execute(context);
        Object rightValue = right.execute(context);

        // Perform the operation based on the operator
        switch (operator) {
            case "+":
                return (double) leftValue + (double) rightValue;
            case "-":
                return (double) leftValue - (double) rightValue;
            case "*":
                return (double) leftValue * (double) rightValue;
            case "/":
                return (double) leftValue / (double) rightValue;
            default:
                throw new UnsupportedOperationException("Unknown operator: " + operator);
        }
    }
}
