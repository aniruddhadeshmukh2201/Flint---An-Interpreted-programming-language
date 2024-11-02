package dev.flint.ast.statements;

public class WhileNode extends StatementNode {
    private final ASTNode condition;
    private final ASTNode body;

    public WhileNode(ASTNode condition, ASTNode body) {
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

