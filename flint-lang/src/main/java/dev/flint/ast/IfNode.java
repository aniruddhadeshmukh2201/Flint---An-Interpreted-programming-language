package dev.flint.ast;


// IfNode class
public class IfNode extends StatementNode {
    private final ASTNode condition;
    private final ASTNode thenBranch;
    private final ASTNode elseBranch; // Optional

    public IfNode(ASTNode condition, ASTNode thenBranch, ASTNode elseBranch) {
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
