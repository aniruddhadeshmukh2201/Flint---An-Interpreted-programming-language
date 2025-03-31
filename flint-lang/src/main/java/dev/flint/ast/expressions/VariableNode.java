package dev.flint.ast.expressions;

import dev.flint.interpreter.ExecutionContext;

public class VariableNode extends ExpressionNode {
    private final String name; // The name of the variable

    public VariableNode(String name) {
        this.name = name; // Assuming token has a method to get the variable name
    }

    @Override
    public Object execute(ExecutionContext context) {
        // Retrieve the value of the variable from the execution context
        return context.getVariable(name);
    }
}
