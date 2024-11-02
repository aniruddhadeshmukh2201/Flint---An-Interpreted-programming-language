package dev.flint.ast.expressions;

// Node for character literals
public class CharNode extends ExpressionNode {
    private final char value;

    public CharNode(char value) {
        this.value = value;
    }

    @Override
    public Object execute(ExecutionContext context) {
        return value; // Return the char value
    }
}
