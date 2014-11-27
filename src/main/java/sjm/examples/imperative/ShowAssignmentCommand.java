package sjm.examples.imperative;

import sjm.engine.*;
import sjm.imperative.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class provides an example of the assignment command. 
 *
 * The <code>main</code> method of this class creates
 * a variable "x" and pre-assigns it the value 0. Then the
 * method creates a "for" command that encapsulates:
 *
 * <blockquote><pre>
 *
 *     for (int i = 1; i <= 4; i++) {
 *         x = x * 10 + 1;
 *     }
 *
 * </pre></blockquote>
 *
 * The method executes the "for" command, leaving x with the 
 * value 1111.0.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowAssignmentCommand {
/**
 * Provide an example of the assignment command. 
 */
public static void main(String[] args) {
	Variable x = new Variable("x");
	x.unify(new NumberFact(0));

	// *(x, 10.0)
	ArithmeticOperator op1 = 
		new ArithmeticOperator('*', x, new NumberFact(10));

	// +(*(x, 10.0), 1.0)
	ArithmeticOperator op2 = 
		new ArithmeticOperator('+', op1, new NumberFact(1));

	// #(x, +(*(x, 10.0), 1.0))
	AssignmentCommand ac = 
		new AssignmentCommand(new Evaluation(x, op2));

	ForCommand f = 
		new ForCommand(new Variable("i"), 1, 4, ac);

	f.execute();	
	System.out.println(x);
}
}
