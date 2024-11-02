package dev.flint.ast;

public abstract class StatementNode extends ASTNode {
    // Statements perform actions and may or may not return a value
    @Override
    public abstract Object execute(ExecutionContext context);
}
