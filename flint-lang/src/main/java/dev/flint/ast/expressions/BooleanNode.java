package dev.flint.ast.expressions;

import dev.flint.interpreter.ExecutionContext;

// Node for boolean literals
public class BooleanNode extends ExpressionNode {
    private final boolean value;

    public BooleanNode(boolean value) {
        this.value = value;
    }

    @Override
    public Object execute(ExecutionContext context) {
        return value; // Return the boolean value
    }
}
