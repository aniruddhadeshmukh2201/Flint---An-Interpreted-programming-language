package dev.flint.ast.expressions;

// Node for numeric literals
public class NumberNode extends ExpressionNode {
    private final double value;

    public NumberNode(double value) {
        this.value = value;
    }

    @Override
    public Object execute(ExecutionContext context) {
        return value; // Return the numeric value
    }
}