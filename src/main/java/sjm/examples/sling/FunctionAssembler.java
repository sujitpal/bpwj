package sjm.examples.sling;

import sjm.parse.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This assembler uses information from a prototypical 
 * function object received in a constructor to guide the 
 * construction of a new function.
 * <p>
 * The constructor for this class requires a <code>
 * SlingFunction</code> object that serves as a prototype. The 
 * prototype tells the type of function to assemble, and tells 
 * how many source functions this type needs. When this 
 * assembler works on an assembly, it will pop as many 
 * functions to use as sources as the number of sources in the 
 * prototype. This assembler will then create a copy of the 
 * prototype, will use the popped sources as the copy's source 
 * functions, and will push the copy.
 * <p>
 * For example, the constructor may receive a <code>Cartesian
 * </code> object that has two source functions. When this 
 * assembler's <code>workOn</code> method executes, it will 
 * pop two functions from the stack, since the prototype 
 * function has two functions. This assembler will then create 
 * a fresh copy of the <code>Cartesian</code> prototype 
 * object, will use the popped functions as the copy's 
 * function sources, and will push the new copy.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class FunctionAssembler extends Assembler {
	protected SlingFunction function;
/**
 * Construct a new function assembler, using the provided
 * function as a prototype.
 */
public FunctionAssembler(SlingFunction function) {
	this.function = function;
}
/**
 * Pop as many source functions as the prototype function has,
 * create a fresh copy of the prototype using these sources,
 * and push the copy.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	SlingFunction s = function.fresh();
	int len = function.source.length; 
	for (int i = 0; i < len; i++){
		s.source[len - 1 - i] = (SlingFunction) a.pop();
	} 
	a.push(s);
}
}
