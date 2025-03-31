package dev.flint.ast.statements;

import dev.flint.ast.ASTNode;
import dev.flint.interpreter.ExecutionContext;

public abstract class StatementNode extends ASTNode {
    // Statements perform actions and may or may not return a value
    @Override
    public abstract Object execute(ExecutionContext context);
}
