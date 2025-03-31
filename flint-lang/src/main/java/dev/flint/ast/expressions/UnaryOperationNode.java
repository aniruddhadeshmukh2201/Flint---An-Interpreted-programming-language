
package dev.flint.ast.expressions;

import dev.flint.interpreter.ExecutionContext;
import dev.flint.lexer.Token;
import dev.flint.lexer.TokenType;

public class UnaryOperationNode extends ExpressionNode {
    private final Token operator;
    private final ExpressionNode operand;

    public UnaryOperationNode(Token operator, ExpressionNode operand) {
        this.operator = operator;
        this.operand = operand;
    }

    public Token getOperator() {
        return operator;
    }

    public ExpressionNode getOperand() {
        return operand;
    }

    @Override
    public Object execute(ExecutionContext context) {
        Object operandValue = operand.execute(context);

        switch (operator.getType()) {
            case MINUS:  // Negation, e.g., -x
                if (operandValue instanceof Number) {
                    return -((Number) operandValue).doubleValue();
                }
                throw new RuntimeException("Unary '-' operator requires a numeric operand.");

            case NOT:  // Logical NOT, e.g., !x
                if (operandValue instanceof Boolean) {
                    return !(Boolean) operandValue;
                }
                throw new RuntimeException("Unary '!' operator requires a boolean operand.");

            default:
                throw new RuntimeException("Unsupported unary operator: " + operator.getValue());
        }
    }

    @Override
    public String toString() {
        return "(" + operator.getValue() + operand + ")";
    }
}
