package dev.flint.ast.statements;

import java.util.List;

import dev.flint.ast.ASTNode;
import dev.flint.ast.expressions.ExpressionNode;
import dev.flint.interpreter.ExecutionContext;

// IfNode class
public class IfNode extends StatementNode {
    private final ExpressionNode condition;
    private final StatementNode thenBranch;
    private final StatementNode elseBranch; // Optional

    public IfNode(ExpressionNode condition, StatementNode thenBranch, StatementNode elseBranch) {
        this.condition = condition;
        this.thenBranch = thenBranch instanceof BlockNode ? thenBranch : new BlockNode(List.of(thenBranch)); // Ensure thenBranch is a block
        this.elseBranch = elseBranch != null && !(elseBranch instanceof BlockNode) ? new BlockNode(List.of(elseBranch)) : elseBranch; // Ensure elseBranch is a block if it exists
    }

    @Override
    public Object execute(ExecutionContext context) {
        Boolean conditionValue = (Boolean) condition.execute(context);
        if (conditionValue) {
            return thenBranch.execute(context); // Execute the 'then' branch
        } else if (elseBranch != null) {
            return elseBranch.execute(context); // Execute the 'else' branch, if present
        }
        return null; // No value to return
    }
}
