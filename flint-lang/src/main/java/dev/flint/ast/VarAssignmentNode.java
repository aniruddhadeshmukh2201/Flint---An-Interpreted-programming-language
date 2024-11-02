package dev.flint.ast;

// Node for variable assignments
public class VarAssignmentNode extends StatementNode {
    private String name;
    private AstNode value;

    public VarAssignmentNode(String name, AstNode value) {
        this.name = name;
        this.value = value;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object evaluatedValue = value.execute(context);
        context.assignVariable(name, evaluatedValue); // Update the variable in the context
        return null; // Assignment does not return a value
    }
}
