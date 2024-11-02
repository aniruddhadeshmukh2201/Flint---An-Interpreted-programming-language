package dev.flint.ast.expressions;

public abstract class ExpressionNode extends ASTNode {
    // Expressions typically evaluate to a value
    public abstract Object evaluate(ExecutionContext context);
}
