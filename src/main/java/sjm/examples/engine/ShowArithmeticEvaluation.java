package sjm.examples.engine;

import sjm.engine.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show the evaluation of an arithmetic operator.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowArithmeticEvaluation {
/**
 * Show the evaluation of an arithmetic operator.
 */
public static void main(String args[]) {

	NumberFact a = new NumberFact(231);
	NumberFact b = new NumberFact(3367);

	ArithmeticOperator op = 
		new ArithmeticOperator('*', a, b);

	System.out.println(op);
	System.out.println(op.eval());
}
}
