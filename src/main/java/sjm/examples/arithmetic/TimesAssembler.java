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
 * multiplying the top number by the one below it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class TimesAssembler extends Assembler {
/**
 * Pop two numbers from the stack and push the result of
 * multiplying the top number by the one below it.
 * 
 * @param   Assembly   the assembly whose stack to use
 */
public void workOn(Assembly a) {
	Double d1 = (Double) a.pop();
	Double d2 = (Double) a.pop();
	Double d3 = 
		new Double(d2.doubleValue() * d1.doubleValue());
	a.push(d3);
}
}
