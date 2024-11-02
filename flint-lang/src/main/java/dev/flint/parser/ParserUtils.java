package dev.flint.parser;

package parser;

public class ParseUtils {
    public static boolean isLiteral(Token token) {
        return token.getType() == TokenType.NUMBER || 
               token.getType() == TokenType.BOOLEAN || 
               token.getType() == TokenType.CHAR;
    }

    // Additional utility methods if needed
}
