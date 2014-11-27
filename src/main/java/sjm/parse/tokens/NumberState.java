package sjm.parse.tokens;

import java.io.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A NumberState object returns a number from a reader. This 
 * state's idea of a number allows an optional, initial 
 * minus sign, followed by one or more digits. A decimal 
 * point and another string of digits may follow these 
 * digits. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class NumberState extends TokenizerState {
	protected int c;
	protected double value;
	protected boolean absorbedLeadingMinus;
	protected boolean absorbedDot;
	protected boolean gotAdigit;
/*
 * Convert a stream of digits into a number, making this
 * number a fraction if the boolean parameter is true.
 */
protected double absorbDigits(
	PushbackReader r, boolean fraction) throws IOException {
		
	int divideBy = 1;
	double v = 0;
	while ('0' <= c && c <= '9') {
		gotAdigit = true;
		v = v * 10 + (c - '0');
		c = r.read();
		if (fraction) {
			divideBy *= 10;
		}
	}
	if (fraction) {
		v = v / divideBy;
	}
	return v;
}
/**
 * Return a number token from a reader.
 *
 * @return a number token from a reader
 */
public Token nextToken(
	PushbackReader r, int cin, Tokenizer t)
	throws IOException {
	 
	reset(cin);	
	parseLeft(r);
	parseRight(r);
	r.unread(c);
	return value(r, t);
}
/*
 * Parse up to a decimal point.
 */
protected void parseLeft(PushbackReader r)
	throws IOException {
	 
	if (c == '-') {
		c = r.read();
		absorbedLeadingMinus = true;
	}
	value = absorbDigits(r, false);	 
}
/*
 * Parse from a decimal point to the end of the number.
 */
protected void parseRight(PushbackReader r)
	throws IOException {
	 
	if (c == '.') {
		c = r.read();
		absorbedDot = true;
		value += absorbDigits(r, true);
	}
}
/*
 * Prepare to assemble a new number.
 */
protected void reset(int cin) {
	c = cin;
	value = 0;
	absorbedLeadingMinus = false;
	absorbedDot = false;
	gotAdigit = false;
}
/*
 * Put together the pieces of a number.
 */
protected Token value(PushbackReader r, Tokenizer t) 
	throws IOException {
		
	if (!gotAdigit) {
		if (absorbedLeadingMinus && absorbedDot) {
			r.unread('.');
			return t.symbolState().nextToken(r, '-', t);
			}
		if (absorbedLeadingMinus) {
			return t.symbolState().nextToken(r, '-', t);
		}
		if (absorbedDot) {
			return t.symbolState().nextToken(r, '.', t);
		}
	}
	if (absorbedLeadingMinus) {
		value = -value;
	}
	return new Token(Token.TT_NUMBER, "", value);
}
}
