package dev.flint.parser;

import dev.flint.lexer.Token;
import dev.flint.lexer.TokenType;

public class ParserUtils {
    public static boolean isLiteral(Token token) {
        return token.getType() == TokenType.NUMBER || 
               token.getType() == TokenType.BOOLEAN || 
               token.getType() == TokenType.CHAR;
    }

    // Additional utility methods if needed
}
