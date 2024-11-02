package dev.flint.ast;

package dev.flint.ast.statements;

import java.util.List;

// BlockNode class
public class BlockNode extends StatementNode {
    private final List<ASTNode> statements;

    public BlockNode(List<ASTNode> statements) {
        this.statements = statements;
    }

    public List<ASTNode> getStatements() {
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
