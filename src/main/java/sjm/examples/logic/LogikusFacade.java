package sjm.examples.logic;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.engine.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class provides utility methods that simplify the use
 * of the Logikus parser.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class LogikusFacade {
/*
 * Translate one axiom string into an Axiom object.
 */
public static Axiom axiom(String s) { 
	return axiom(new TokenString(s));
}
/*
 * Translate the tokens for one axiom into an Axiom
 * object (either a Fact or a Rule);
 */
protected static Axiom axiom(TokenString ts) {
	Parser p = new LogikusParser().axiom();
	Object o = parse(ts, p, "axiom");
	return (Axiom) o;
}
/*
 * Throws an informative runtime exception if the provided
 * string begins with an uppercase letter.
 */
protected static void checkForUppercase(
	TokenString ts, String type) {
		
	if (ts.length() > 0) {
		Token t = ts.tokenAt(0);
		String s = t.sval();
		if (s.length() > 0 && 
			Character.isUpperCase(s.charAt(0))) {
				
			throw new LogikusException(
				"> Uppercase " + s + 
				" indicates a variable and cannot begin a " + 
				type + ".\n");
		}
	}
}
/*
 * Parse the given token string with the given parser,
 * throwing runtime exceptions if parsing fails
 * or is incomplete.
 */
protected static Object parse(
	TokenString ts, Parser p, String type) {
		
	TokenAssembly ta = new TokenAssembly(ts);
	Assembly out = p.bestMatch(ta);
	if (out == null) {
		reportNoMatch(ts, type);
	}
	if (out.hasMoreElements()) {
		// allow an extra semicolon
		if (!out.remainder("").equals(";")) {
			reportLeftovers(out, type);
		}
	}
	return out.pop();
}
/**
 * Parse the text of a Logikus program and return a
 * <code>Program</code> object.
 *
 * @param   String   the text of the program
 *
 * @return a <code>Program</code> object
 *
 * @exception   RuntimeException   if parsing fails
 */
public static Program program(String s) {
	Program p = new Program();
	TokenStringSource tss = new TokenStringSource(
		new Tokenizer(s), ";");
	while (true) {
		TokenString ts = tss.nextTokenString();
		if (ts == null) { // no more token strings
			break;
		}
		p.addAxiom(axiom(ts));
	}
	return p;
}
/**
 * Parse the text of a Logikus query and return a
 * <code>Query</code> object.
 *
 * @param   String   the text of the query
 *
 * @return a <code>Query</code> object
 *
 * @exception   RuntimeException   if parsing fails
 */
public static Query query(String s, AxiomSource as) {
	Object o = parse(
		new TokenString(s), LogikusParser.query(), "query");
	if (o instanceof Fact) {
		Fact f = (Fact) o;
		return new Query(as, f);
	}
	return new Query(as, (Rule) o);
}
/*
 * Throws a runtime exception reporting an incomplete
 * parse.
 */
protected static Object reportLeftovers(
	Assembly out, String type) {
		
	throw new LogikusException(
		"> Input for " + type + 
		" appears complete after : \n> " + out.consumed(" ") + 
		"\n");
}
/*
 * Throws a runtime exception reporting failed parse.
 */
protected static void reportNoMatch(
	TokenString ts, String type) {
		
	checkForUppercase(ts, type);
	throw new LogikusException(
		"> Cannot parse " + type + " : " + ts + "\n");
}
}
