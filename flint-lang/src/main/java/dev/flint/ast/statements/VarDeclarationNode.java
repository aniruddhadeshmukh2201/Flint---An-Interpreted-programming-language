package dev.flint.ast.statements;

import dev.flint.ast.ASTNode;
import dev.flint.ast.expressions.ExpressionNode;
import dev.flint.interpreter.ExecutionContext;

public class VarDeclarationNode extends StatementNode {
    private String name;
    private ExpressionNode value; // The value can be another expression node

    public VarDeclarationNode(String name, ExpressionNode value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object evaluatedValue = value.execute(context);
        context.defineVariable(name, evaluatedValue); // Store the variable in the context
        return null; // Declaration does not return a value
    }
}
