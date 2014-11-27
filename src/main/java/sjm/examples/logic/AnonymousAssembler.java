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
 * Pushes an anonymous variable onto an assembly's stack.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AnonymousAssembler extends Assembler {
/**
 * Pushes an anonymous variable onto an assembly's stack.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	a.push(new Anonymous());	
}
}
