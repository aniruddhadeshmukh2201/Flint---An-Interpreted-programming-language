package dev.flint.ast.statements;

import java.util.List;

import dev.flint.ast.ASTNode;
import dev.flint.interpreter.ExecutionContext;

// BlockNode class
public class BlockNode extends StatementNode {
    private final List<StatementNode> statements;

    public BlockNode(List<StatementNode> statements) {
        this.statements = statements;
    }

    public List<StatementNode> getStatements() {
        return statements;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object result = null;
        for (ASTNode statement : statements) {
            result = statement.execute(context);
        }
        return result;
    }
}
