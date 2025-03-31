package dev.flint.ast.expressions;

import dev.flint.ast.ASTNode;
import dev.flint.interpreter.ExecutionContext;

public abstract class ExpressionNode extends ASTNode {

    @Override
    public abstract Object execute(ExecutionContext context);
}
