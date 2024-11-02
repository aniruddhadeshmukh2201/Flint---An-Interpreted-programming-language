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

            if (Character.isLetter(currentChar)) {
                String identifier = readIdentifier();
                TokenType type = getKeywordOrIdentifierType(identifier);
                tokens.add(new Token(type, identifier));
                continue;
            }

            if (isOperatorStart(currentChar)) {
                tokens.add(new Token(getOperatorTokenType(), readOperator()));
                continue;
            }

            if (isPunctuation(currentChar)) {
                tokens.add(new Token(getPunctuationTokenType(), String.valueOf(currentChar)));
                advance();
                continue;
            }

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

    private String readIdentifier() {
        StringBuilder identifier = new StringBuilder();
        while (Character.isLetterOrDigit(currentChar) || currentChar == '_') {
            identifier.append(currentChar);
            advance();
        }
        return identifier.toString();
    }

    private TokenType getKeywordOrIdentifierType(String identifier) {
        return switch (identifier) {
            case "var" -> TokenType.VAR;
            case "print" -> TokenType.PRINT;
            case "if" -> TokenType.IF;
            case "else" -> TokenType.ELSE;
            case "while" -> TokenType.WHILE;
            case "true", "false" -> TokenType.BOOLEAN;
            default -> TokenType.IDENTIFIER;
        };
    }

    private boolean isOperatorStart(char c) {
        return "+-*/=!<>&|".indexOf(c) >= 0;
    }

    private String readOperator() {
        StringBuilder operator = new StringBuilder();
        while (isOperatorStart(currentChar)) {
            operator.append(currentChar);
            advance();
        }
        return operator.toString();
    }

    private TokenType getOperatorTokenType() {
        String operator = readOperator();
        return switch (operator) {
            case "=" -> TokenType.ASSIGN;
            case "==" -> TokenType.EQUAL;
            case "!=" -> TokenType.NOT_EQUAL;
            case "<" -> TokenType.LESS_THAN;
            case "<=" -> TokenType.LESS_EQUAL;
            case ">" -> TokenType.GREATER_THAN;
            case ">=" -> TokenType.GREATER_EQUAL;
            case "+" -> TokenType.PLUS;
            case "-" -> TokenType.MINUS;
            case "*" -> TokenType.MULTIPLY;
            case "/" -> TokenType.DIVIDE;
            case "&&" -> TokenType.AND;
            case "||" -> TokenType.OR;
            case "!" -> TokenType.NOT;
            default -> throw new RuntimeException("Unknown operator: " + operator);
        };
    }

    private boolean isPunctuation(char c) {
        return ";=(){}".indexOf(c) >= 0;
    }

    private TokenType getPunctuationTokenType() {
        return switch (currentChar) {
            case ';' -> TokenType.SEMICOLON;
            case '(' -> TokenType.LPAREN;
            case ')' -> TokenType.RPAREN;
            case '{' -> TokenType.LBRACE;
            case '}' -> TokenType.RBRACE;
            default -> throw new RuntimeException("Unknown punctuation: " + currentChar);
        };
    }
}
