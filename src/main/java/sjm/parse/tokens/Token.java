package sjm.parse.tokens;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A token represents a logical chunk of a string. For 
 * example, a typical tokenizer would break the string 
 * <code>"1.23 <= 12.3"</code> into three tokens: the number 
 * 1.23, a less-than-or-equal symbol, and the number 12.3. A 
 * token is a receptacle, and relies on a tokenizer to decide 
 * precisely how to divide a string into tokens. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */

public class Token {
	protected TokenType ttype;
	protected String sval;
	protected double nval;

	/** 
	 * A constant indicating that the end of the stream has 
	 * been read. 
	 */
	public static final TokenType TT_EOF = new TokenType("eof");
	/**
	 * A constant indicating that there are no more tokens
	 */
	public static final Token EOF = new Token(TT_EOF, "", 0);

	/** 
	 * A constant indicating that a token is a number, 
	 * like 3.14
	 */
	public static final TokenType TT_NUMBER = 
		new TokenType("number");

	/** 
	 * A constant indicating a token is a word, like "cat"
	 */
	public static final TokenType TT_WORD = 
		new TokenType("word");

	/**
	 * A constant indicating that a token is a symbol 
	 * like "<=".
	 */
	public static final TokenType TT_SYMBOL = 
		new TokenType("symbol");

	/**
	 * A constant indicating that a token is a quoted string, 
	 * like "Launch Mi".
	 */
	public static final TokenType TT_QUOTED = 
		new TokenType("quoted");
/**
 * Constructs a token from the given char.
 *
 * @param   char   the char
 *
 * @return   a token constructed from the given char
 */
public Token(char c) {
	this(TT_SYMBOL, new String(new char[] {c}), 0);
}
/**
 * Constructs a token from the given number.
 *
 * @param   double   the number
 *
 * @return   a token constructed from the given number
 */
public Token(double nval) {
	this(TT_NUMBER, "", nval);
}
/**
 * Constructs a token from the given string.
 *
 * @param   String   the string
 *
 * @return   a token constructed from the given string
 */
public Token (String sval) {
	this(TT_WORD, sval, 0);
}
/**
 * Constructs a token of the indicated type and associated 
 * string or numeric values.
 *
 * @param   TokenType   the type of the token, typically one 
 *                      of the constants this class defines
 *
 * @param   string  the string value of the token, typically 
 *                  null except for WORD and QUOTED tokens
 *
 * @param   double   the numeric value of the token, typically
 *                   0 except for NUMBER tokens
 *
 * @return   a token
 */
public Token (TokenType ttype, String sval, double nval) {
	this.ttype = ttype;
	this.sval = sval;
	this.nval = nval;
}
/**
 * Returns true if the supplied object is an equivalent token.
 *
 * @param   object   the object to compare
 *
 * @return   true, if the supplied object is of the same type 
 *           and value
 */
public boolean equals(Object o) {
	if (!(o instanceof Token))
		return false;
	Token t = (Token) o;
	
	if (ttype != t.ttype) {
		return false;
	}
	if (ttype == TT_NUMBER) {
		return nval == t.nval;
	}
	if (sval == null || t.sval == null) {
		return false;
	}
	return sval.equals(t.sval);
}
/**
 * Returns true if the supplied object is an equivalent token,
 * given mellowness about case in strings and characters.
 *
 * @param   object   the object to compare
 *
 * @return   true, if the supplied object is of the same type 
 *           and value. This method disregards case when 
 *           comparing the string value of tokens.
 */
public boolean equalsIgnoreCase(Object o) {
	if (!(o instanceof Token))
		return false;
	Token t = (Token) o;
	
	if (ttype != t.ttype) {
		return false;
	}
	if (ttype == TT_NUMBER) {
		return nval == t.nval;
	}
	if (sval == null || t.sval == null) {
		return false;
	}
	return sval.equalsIgnoreCase(t.sval);
}
/**
 * Returns true if this token is a number.
 *
 * @return   true, if this token is a number
 */
public boolean isNumber() {
	return ttype == TT_NUMBER;
}
/**
 * Returns true if this token is a quoted string.
 *
 * @return   true, if this token is a quoted string
 */
public boolean isQuotedString() {
	return ttype == TT_QUOTED;
}
/**
 * Returns true if this token is a symbol.
 *
 * @return   true, if this token is a symbol
 */
public boolean isSymbol() {
	return ttype == TT_SYMBOL;
}
/**
 * Returns true if this token is a word.
 *
 * @return   true, if this token is a word.
 */
public boolean isWord() {
	return ttype == TT_WORD;
}
/**
 * Returns the numeric value of this token.
 *
 * @return    the numeric value of this token
 */
public double nval() {
	return nval;
}
/**
 * Returns the string value of this token.
 *
 * @return    the string value of this token
 */
public String sval() {
	return sval;
}
/**
 * Return a textual description of this object.
 * 
 * @return a textual description of this object
 */
public String toString () {
	if (ttype == TT_EOF) {
		return "EOF";
	}
	return value().toString();
}
/**
 * Returns the type of this token.
 *
 * @return   the type of this token, typically one of the
 *           constants this class defines
 */
public TokenType ttype() {
	return ttype;
}
/**
 * Returns an object that represents the value of this token.
 *
 * @return  an object that represents the value of this token
 */
public Object value() {
	if (ttype == TT_NUMBER) {
		return new Double(nval);
	}
	if (ttype == TT_EOF) {
		return EOF;
	}
	if (sval != null) {
		return sval;
	}
	return ttype;
}
}
