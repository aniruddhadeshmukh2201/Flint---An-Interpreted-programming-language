package dev.flint.ast.statements;

import dev.flint.ast.AstNode;

// Node for print statements
public class PrintNode extends StatementNode {
    private final AstNode expression;

    public PrintNode(AstNode expression) {
        this.expression = expression;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object valueToPrint = expression.execute(context);
        System.out.println(valueToPrint); // Print to standard output
        return null; // Print does not return a value
    }
}