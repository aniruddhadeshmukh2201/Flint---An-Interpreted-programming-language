package dev.flint.ast.statements;

import java.util.List;

import dev.flint.ast.expressions.ExpressionNode;
import dev.flint.interpreter.ExecutionContext;

public class WhileNode extends StatementNode {
    private final ExpressionNode condition;
    private final StatementNode body;

    public WhileNode(ExpressionNode condition, StatementNode body) {
        this.condition = condition;
        this.body = body instanceof BlockNode ? body : new BlockNode(List.of(body)); // Ensure body is a block
    }

    @Override
    public Object execute(ExecutionContext context) {
        while ((Boolean) condition.execute(context)) {
            body.execute(context); // Execute the loop body
        }
        return null; // No value to return
    }
}

