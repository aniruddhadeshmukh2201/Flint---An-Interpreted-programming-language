package dev.flint.ast;

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
