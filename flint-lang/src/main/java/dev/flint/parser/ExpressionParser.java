package dev.flint.parser;

package parser;

public class ExpressionParser {
    private Parser parser;

    public ExpressionParser(Parser parser) {
        this.parser = parser;
    }

    public ASTNode parseExpression() {
        return parseEquality();
    }

    private ASTNode parseEquality() { /* Implementation */ }
    private ASTNode parseComparison() { /* Implementation */ }
    private ASTNode parseTerm() { /* Implementation */ }
    private ASTNode parseFactor() { /* Implementation */ }
    private ASTNode parseUnary() { /* Implementation */ }
    private ASTNode parsePrimary() {
        return new PrimaryParser(parser).parsePrimary();
    }
}
