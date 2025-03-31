package dev.flint.lexer;

/**
 * Enum representing the different types of tokens that the lexer can produce.
 */
public enum TokenType {
    // Literals 
    NUMBER,       // Numeric literals like 123
    BOOLEAN,      // Boolean literals like true or false
    CHAR,         // Character literals like 'a'
    IDENTIFIER,   // Variable names or identifiers

    // Keywords
    VAR,          // 'var' keyword for variable declaration
    PRINT,        // 'print' keyword for printing
    IF,           // 'if' keyword for conditionals
    ELSE,         // 'else' keyword for conditionals
    WHILE,        // 'while' keyword for loops

    // Operators
    ASSIGN,       // '=' for assignment
    PLUS,         // '+'
    MINUS,        // '-'
    MULTIPLY,     // '*'
    DIVIDE,       // '/'
    EQUAL,        // '==' for equality comparison
    NOT_EQUAL,    // '!=' for inequality comparison
    LESS_THAN,    // '<'
    LESS_EQUAL,   // '<='
    GREATER_THAN, // '>'
    GREATER_EQUAL,// '>='

    // Logical Operators (if needed in the future for expressions like AND/OR)
    AND,          // '&&'
    OR,           // '||'
    NOT,          // '!' logical NOT

    // Punctuation
    LPAREN,       // '('
    RPAREN,       // ')'
    LBRACE,       // '{'
    RBRACE,       // '}'
    SEMICOLON,    // ';' statement terminator

    // End of file
    EOF           // End of file/input
}
