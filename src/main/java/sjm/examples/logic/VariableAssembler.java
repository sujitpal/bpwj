package sjm.examples.logic;

import sjm.engine.*;
import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pops a string like "X" or "Person" from an assembly's stack
 * and pushes a variable with that name.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class VariableAssembler extends Assembler {
/**
 * Pops a string like "X" or "Person" from an assembly's stack
 * and pushes a variable with that name.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	Token t = (Token) a.pop();
	String name = t.sval();
	a.push(new Variable(name));
}
}
