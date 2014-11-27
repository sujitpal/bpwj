package sjm.examples.logic;

import sjm.engine.*;
import sjm.parse.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pops a structure from the top of the stack and pushes a Not
 * version of it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NotAssembler extends Assembler {
/**
 * Pops a structure from the top of the stack and pushes a Not
 * version of it.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	Structure s = (Structure) a.pop();
	a.push(new Not(s));
}
}
