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
 * Show how to perform arithmetic within the engine.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowArithmetic {
/**
 * Show how to perform arithmetic within the engine.
 */
public static void main(String[] args) {
	NumberFact a, b;
	a = new NumberFact(1000);
	b = new NumberFact(999);
	
	ArithmeticOperator x, y;
	x = new ArithmeticOperator('*', a, b);
	y = new ArithmeticOperator('+', x, b);
	
	System.out.println(y);
	System.out.println(y.eval());
}
}
