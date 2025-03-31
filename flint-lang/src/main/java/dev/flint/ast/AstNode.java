package dev.flint.ast;

import dev.flint.interpreter.ExecutionContext;

public abstract class ASTNode {
    public abstract Object execute(ExecutionContext context);
}

