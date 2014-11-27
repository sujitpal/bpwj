package sjm.examples.pretty;

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
 * Places a given "fence" or marker object on an assembly's
 * stack. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class FenceAssembler extends Assembler {
	protected Object fence;
/**
 * Construct an assembler that will place the given object
 * on an assembly's stack.
 */
public FenceAssembler(Object fence) {
	this.fence = fence;
}
/**
 * Place the fence object on the assembly's stack.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	a.push(fence);
}
}
