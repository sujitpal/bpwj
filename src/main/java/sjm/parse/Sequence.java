package sjm.parse;

import java.util.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A <code>Sequence</code> object is a collection of 
 * parsers, all of which must in turn match against an 
 * assembly for this parser to successfully match.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */

public class Sequence extends CollectionParser {

/**
 * Constructs a nameless sequence.
 */
public Sequence () {
}
/**
 * Constructs a sequence with the given name.
 *
 * @param    name    a name to be known by
 */
public Sequence(String name) {
	super(name);
}
/**
 * Accept a "visitor" and a collection of previously visited
 * parsers.
 *
 * @param   ParserVisitor   the visitor to accept
 *
 * @param   Vector   a collection of previously visited parsers
 */
public void accept(ParserVisitor pv, Vector visited) {
	pv.visitSequence(this, visited);
}
/**
 * Given a set of assemblies, this method matches this
 * sequence against all of them, and returns a new set 
 * of the assemblies that result from the matches.
 *
 * @return   a Vector of assemblies that result from 
 *           matching against a beginning set of assemblies
 *
 * @param   Vector   a vector of assemblies to match against
 *
 */
public Vector match(Vector in) {
	Vector out = in;
	Enumeration e = subparsers.elements();
	while (e.hasMoreElements()) {
		Parser p = (Parser) e.nextElement();
		out = p.matchAndAssemble(out);
		if (out.isEmpty()) {
			return out;
		}
	}
	return out;
}
/*
 * Create a random expansion for each parser in this 
 * sequence and return a collection of all these expansions.
 */
protected Vector randomExpansion(int maxDepth, int depth) {
	Vector v = new Vector();
	Enumeration e = subparsers.elements();
	while (e.hasMoreElements()) {
		Parser p = (Parser) e.nextElement();
		Vector w = p.randomExpansion(maxDepth, depth++);
		Enumeration f = w.elements();
		while (f.hasMoreElements()) {
			v.addElement(f.nextElement());
		}
	}
	return v;
}
/*
 * Returns the string to show between the parsers this
 * parser is a sequence of. This is an empty string,
 * since convention indicates sequence quietly. For
 * example, note that in the regular expression 
 * <code>(a|b)c</code>, the lack of a delimiter between
 * the expression in parentheses and the 'c' indicates a 
 * sequence of these expressions.
 */
protected String toStringSeparator() {
	return "";
}
}
