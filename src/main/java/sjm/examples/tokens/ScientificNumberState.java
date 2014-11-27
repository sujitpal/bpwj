package sjm.examples.tokens;

import java.io.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A <code>ScientificNumberState</code> object returns a 
 * number from a reader. This state's idea of a number 
 * expands on its superclass, allowing an 'e' followed by 
 * an integer to represent 10 to the indicated power. For 
 * example, this state will recognize 1e2 as equaling 100.
 *
 * <p>
 * This class exists primarily to show how to introduce a
 * new tokenizing state.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ScientificNumberState extends NumberState {
	protected boolean absorbedE;
/**
 * Just a demo.
 */
public static void main(String[] args) throws IOException {
	Tokenizer t = new Tokenizer();
	ScientificNumberState sns = new ScientificNumberState();
	t.setCharacterState('0', '9', sns);
	t.setCharacterState('.', '.', sns);
	t.setCharacterState('-', '-', sns);
	
	t.setString("1e2");
	System.out.println(t.nextToken());
}
/*
 * Parse from a decimal point to the end of a number, 
 * including exponential or "scientific" notation.
 */
protected void parseRight(PushbackReader r)
	throws IOException {
		
	super.parseRight(r);
	int sign = 1;
	if (c == 'e') {
		absorbedE = true;
		c = r.read();
		if (c == '-') {
			c = r.read();
			sign = -1;
		}
		value *= Math.pow(10, sign * absorbDigits(r, false));
	}
}
/*
 * Prepare to assemble a new number.
 */
protected void reset(int cin) {
	super.reset(cin);
	absorbedE = false;
}
/*
 * Put together the pieces of a number.
 */
protected Token value(PushbackReader r, Tokenizer t) 
	throws IOException {
		
	if (!gotAdigit && absorbedE) {
		String s = "";
		if (absorbedLeadingMinus) {
			s += "-";
		}
		if (absorbedDot) {
			s += ".";
		}
		s += "e";
		return new Token(Token.TT_WORD, s, 0);
	}
	return super.value(r, t);
}
}
