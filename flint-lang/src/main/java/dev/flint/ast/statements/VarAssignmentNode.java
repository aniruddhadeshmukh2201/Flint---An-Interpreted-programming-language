package dev.flint.ast.statements;

import dev.flint.ast.ASTNode;
import dev.flint.interpreter.ExecutionContext;
import dev.flint.lexer.Token;

// Node for variable assignments
public class VarAssignmentNode extends StatementNode {
    private String name;
    private ASTNode value;

    public VarAssignmentNode(Token identifier, ASTNode value) {
        this.name = identifier.getValue();
        this.value = value;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object evaluatedValue = value.execute(context);
        context.assignVariable(name, evaluatedValue); // Update the variable in the context
        return null; // Assignment does not return a value
    }
}
