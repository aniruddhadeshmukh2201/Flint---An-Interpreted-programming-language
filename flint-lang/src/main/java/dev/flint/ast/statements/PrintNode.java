package dev.flint.ast.statements;

import dev.flint.ast.ASTNode;
import dev.flint.interpreter.ExecutionContext;

// Node for print statements
public class PrintNode extends StatementNode {
    private final ASTNode expression;

    public PrintNode(ASTNode expression) {
        this.expression = expression;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object valueToPrint = expression.execute(context);
        System.out.println(valueToPrint); // Print to standard output
        return null; // Print does not return a value
    }
}