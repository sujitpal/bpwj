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
 * This class looks up a variable by name, using the word on 
 * an assembly's stack, and pushes a <code>Variable</code> of 
 * that name.
 * <p>
 * This class expects an assembly's target to be a <code>
 * SlingPlot</code> object. The target has a "scope", which is 
 * a collection of variables organized by name. When this 
 * assembler works on an assembly, it pops a name from the 
 * stack, looks up a variable in the scope using the name, and 
 * pushes the variable onto the stack. This lookup creates the 
 * variable in the scope if the scope does not already contain 
 * a variable of that name.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class VariableAssembler extends Assembler {
/**
 * Pop the name of a variable, lookup the variable in the
 * target's scope, and push the variable.
 */
public void workOn(Assembly a) {
	SlingTarget target = (SlingTarget) a.getTarget();
	Token t = (Token) a.pop();
	a.push(target.lookup(t.sval()));
}
}
