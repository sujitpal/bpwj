package sjm.examples.logic;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.engine.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pops two comparison terms and an operator, builds 
 * the comparison, and pushes it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ComparisonAssembler extends Assembler {
/**
 * Pops two comparison terms and an operator, builds 
 * the comparison, and pushes it.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	ComparisonTerm second = (ComparisonTerm) a.pop();
	ComparisonTerm first = (ComparisonTerm) a.pop();
	Token t = (Token) a.pop();
	a.push(new Comparison(t.sval(), first, second));
}
}
