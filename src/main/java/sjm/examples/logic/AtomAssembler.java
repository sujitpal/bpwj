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
 * Exchanges a token on an assembly's stack with an atom 
 * that has the token's value as its functor. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class AtomAssembler extends Assembler {
/**
 * Exchanges a token on an assembly's stack with an atom 
 * that has the token's value as its functor. In the case
 * of a quoted string, this assembler removes the quotes,
 * so that a string such as "Smith" becomes just Smith. In
 * the case of a number, this assembler pushes a NumberFact.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	Token t = (Token) a.pop();
	// remove quotes from quoted string
	if (t.isQuotedString()) {
		String s = t.sval();
		String plain = s.substring(1, s.length() - 1);
		a.push(new Atom(plain));
	} else
		if (t.isNumber()) {
			a.push(new NumberFact(t.nval()));
		} else {
			a.push(new Atom(t.value()));
		}
}
}
