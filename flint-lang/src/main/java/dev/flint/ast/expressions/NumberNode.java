package dev.flint.ast.expressions;

import dev.flint.interpreter.ExecutionContext;

// Node for numeric literals
public class NumberNode extends ExpressionNode {
    private final Double value;

    public NumberNode(double number) {
        this.value = number;
    }

    @Override
    public Object execute(ExecutionContext context) {
        return value; // Return the numeric value
    }
}