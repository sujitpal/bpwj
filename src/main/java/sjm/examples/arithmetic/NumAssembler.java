package sjm.examples.arithmetic;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Replace the top token in the stack with the token's
 * Double value.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NumAssembler extends Assembler {
/**
 * Replace the top token in the stack with the token's
 * Double value.
 * 
 * @param   Assembly   the assembly whose stack to use
 */
public void workOn(Assembly a) {
	Token t = (Token) a.pop();
	a.push(new Double(t.nval()));
}
}
