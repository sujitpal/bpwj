package sjm.examples.arithmetic;

import sjm.parse.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Pop two numbers from the stack and push the result of
 * exponentiating the lower number to the upper one.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ExpAssembler extends Assembler {
/**
 * Pop two numbers from the stack and push the result of
 * exponentiation the lower number to the upper one.
 * 
 * @param   Assembly   the assembly whose stack to use
 */
public void workOn(Assembly a) {
	Double d1 = (Double) a.pop();
	Double d2 = (Double) a.pop();
	Double d3 = new Double(
		Math.pow(d2.doubleValue(), d1.doubleValue()));
	a.push(d3);
}
}
