package sjm.examples.query;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.engine.*;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This assembler pops a token from the stack, extracts
 * its string, and pushes a <code>Variable</code> of that
 * name. This assembler also looks up the name in a
 * <code>ChipSpeller</code>, and throws a runtime
 * exception if this variable name is unknown.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class VariableAssembler extends Assembler {
	Speller speller;
/**
 * Construct a VariableAssembler that will consult the
 * given speller for the proper spelling of variable names.
 */
public VariableAssembler(Speller speller) {
	this.speller = speller;
}
/**
 * Pop a token from the stack, extract its string, and push
 * a <code>Variable</code> of that name. Check the spelling
 * of the name with the speller provided in the constructor.
 */
public void workOn(Assembly a) {
	Token t = (Token) a.pop();
	String properName = speller.getVariableName(t.sval());
	if (properName == null) {
		throw new UnrecognizedVariableException(
			"No variable named " + t.sval() + 
			" in object model");
	}
	a.push(new Variable(properName));
}
}
