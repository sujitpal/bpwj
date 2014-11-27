package sjm.examples.sling;

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
 * This class augments the stack with default scale arguments, 
 * and then uses a <code>FunctionAssembler</code> with a 
 * <code>Scale</code> object to work on the assembly.
 * <p>
 * A scale requires five source functions, namely: aFrom, a, 
 * aTo, bFrom, and bTo. This allows, for example, a scale to 
 * map coordinates from an "a" coordinate system to a "b" 
 * coordinate system. The Sling language only accepts two 
 * arguments in a <code>scale</code> function: bFrom and bTo, 
 * and defaults the "a" system to be time. For example, the 
 * Sling language function <code>scale(-pi, pi)</code> implies 
 * the internal scale function <code>scale(0, t, 1, -pi, 
 * pi)</code>. This class slips the arguments (0, t, 1) into 
 * the stack, and then uses a normal <code>Scale</code> object 
 * to work on the assembly.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ScaleAssembler extends Assembler {
/**
 * Slip the arguments (0, t, 1) into the stack, and use a normal
 * <code>Scale</code> object to work on the assembly.
 * 
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	SlingFunction last = (SlingFunction) a.pop();
	SlingFunction nextToLast = (SlingFunction)a.pop();
	a.push(new Point(0, 0));
	a.push(new T());
	a.push(new Point(1, 1));
	a.push(nextToLast);
	a.push(last);

	new FunctionAssembler(new Scale()).workOn(a); 
}
}
