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
 * Pops a function and a variable, constructs an <code>
 * AssignFunctionCommand</code> from these terms, and
 * pushes the command. For example, after parsing:
 *
 * <blockquote><pre>
 *     y = sin(x);
 * </pre></blockquote>
 *
 * this assembler expects the variable <code>y</code> and
 * the function <code>sin(x)</code> to be on the stack, and
 * constructs a command from these objects.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AssignmentAssembler extends Assembler {
/**
 * Pops a function and a variable, constructs an <code>
 * AssignFunctionCommand</code> from these terms, and
 * pushes the command.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	SlingFunction f = (SlingFunction) a.pop();
	Variable v = (Variable) a.pop();
	a.push(new AssignFunctionCommand(v, f));
}
}
