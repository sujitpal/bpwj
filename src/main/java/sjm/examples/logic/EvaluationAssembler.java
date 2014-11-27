package sjm.examples.logic;

import sjm.engine.*;
import sjm.parse.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pops two terms, constructs an Evaluation from these terms,
 * and pushes it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class EvaluationAssembler extends Assembler {
/**
 * Pops two terms, constructs an Evaluation from these terms,
 * and pushes it.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	Term second = (Term) a.pop();
	Term first = (Term) a.pop();
	a.push(new Evaluation(first, second));
}
}
