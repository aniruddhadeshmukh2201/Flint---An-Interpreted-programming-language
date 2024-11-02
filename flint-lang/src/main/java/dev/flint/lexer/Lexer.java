package dev.flint.lexer;

import java.util.ArrayList;
import java.util.List;

public class Lexer {
    private final String input;
    private int position = 0;  // Current position in input
    private char currentChar;   // Current character being processed

    public Lexer(String input) {
        this.input = input;
        this.currentChar = input.charAt(position);
    }

    private void advance() {
        position++;
        if (position >= input.length()) {
            currentChar = '\0'; // End of input
        } else {
            currentChar = input.charAt(position);
        }
    }

    public List<Token> tokenize() {
        List<Token> tokens = new ArrayList<>();

        while (currentChar != '\0') {
            if (Character.isWhitespace(currentChar)) {
                advance();
                continue;
            }

            if (Character.isDigit(currentChar)) {
                tokens.add(new Token(TokenType.NUMBER, readNumber()));
                continue;
            }

            if (currentChar == '\'') {
                tokens.add(new Token(TokenType.CHAR, readChar()));
                continue;
            }

            if (currentChar == 't' || currentChar == 'f') {
                tokens.add(new Token(TokenType.BOOLEAN, readBoolean()));
                continue;
            }

            if (Character.isLetter(currentChar)) {
                String identifier = readIdentifier();
                Token.Type type = TokenType.IDENTIFIER; // Default to identifier
                if (identifier.equals("var") || identifier.equals("print")) {
                    type = TokenType.KEYWORD; // Change to keyword if it's a keyword
                }
                tokens.add(new Token(type, identifier));
                continue;
            }

            if (isOperator(currentChar)) {
                tokens.add(new Token(TokenType.OPERATOR, readOperator()));
                continue;
            }

            if (isPunctuation(currentChar)) {
                tokens.add(new Token(TokenType.PUNCTUATION, String.valueOf(currentChar)));
                advance();
                continue;
            }

            // If we reach here, we encountered an unrecognized character
            throw new RuntimeException("Unexpected character: " + currentChar);
        }

        tokens.add(new Token(TokenType.EOF, ""));
        return tokens;
    }

    private String readNumber() {
        StringBuilder number = new StringBuilder();
        while (Character.isDigit(currentChar)) {
            number.append(currentChar);
            advance();
        }
        return number.toString();
    }

    private String readChar() {
        advance(); // Skip the opening quote
        char charValue = currentChar;
        advance(); // Move past the character
        advance(); // Skip the closing quote
        return String.valueOf(charValue);
    }

    private String readBoolean() {
        StringBuilder booleanValue = new StringBuilder();
        while (Character.isLetter(currentChar)) {
            booleanValue.append(currentChar);
            advance();
        }
        return booleanValue.toString();
    }

    private String readIdentifier() {
        StringBuilder identifier = new StringBuilder();
        while (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
            identifier.append(currentChar);
            advance();
        }
        return identifier.toString();
    }

    private boolean isOperator(char c) {
        return "+-*/==!=&&||".indexOf(c) >= 0;
    }

    private String readOperator() {
        StringBuilder operator = new StringBuilder();
        while (isOperator(currentChar)) {
            operator.append(currentChar);
            advance();
        }
        return operator.toString();
    }

    private boolean isPunctuation(char c) {
        return ";=(){}".indexOf(c) >= 0;
    }
}
