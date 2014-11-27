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
 * Show an evaluation.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowEvaluation {
/**
 * Show an evaluation.
 */
public static void main(String[] args) {
	Variable you        = new Variable("You");	
	Variable youAndBaby = new Variable("YouAndBaby");	
	Variable baby       = new Variable("Baby");
	
	ArithmeticOperator diff;
	diff = new ArithmeticOperator('-', youAndBaby, you);
	
	Evaluation e = new Evaluation(baby, diff);
	System.out.println("Before weighing:");
	System.out.println(e);
	
	you.unify(new NumberFact(185));
	youAndBaby.unify(new NumberFact(199));

	System.out.println("\nAfter weighing:");
	System.out.println(e);
	
	e.canFindNextProof();
	
	System.out.println(
		"\nThat baby weighs about " + baby + " pounds.");
}
}
