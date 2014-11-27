package sjm.examples.sling;

import sjm.engine.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/** 
 * This class's constructor accepts two functions and an 
 * operator, and can evaluate and compare the functions.
 * An object of this class can say whether a comparison holds 
 * for two functions. A comparison must ultimately be between 
 * two numbers rather than two functions. This class actually 
 * compares the y components of functions at time zero.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class FunctionComparison extends Gateway
	implements BooleanTerm {
		
	protected String operator;
	protected SlingFunction f0;
	protected SlingFunction f1;
/**
 * Create a comparison with the specified operator and
 * comparison functions.
 *
 * @param   String   the comparison operator
 * 
 * @param   SlingFunction   the first function
 * 
 * @param   SlingFunction   the second function
 */
public FunctionComparison(
	String operator, SlingFunction f0, SlingFunction f1) {
		
	super(operator, new Term[0]);
	this.operator = operator;
	this.f0 = f0;
	this.f1 = f1;
}
/**
 * Returns true if the comparison operator holds true between 
 * the values of this comparison's terms. 
 * <p>
 * This method evaluates the two functions received in the 
 * constructor to fix any variable references. Then this method
 * extracts the y components of the functions at time zero,
 * and compares these values.
 *
 * @return <code>true</code> if the comparison operator holds 
 * true between the values of this comparison's terms. 
 */
public boolean canProveOnce() {
	SlingFunction eval0 =  f0.eval();
	SlingFunction eval1 =  f1.eval();
		
	double d0 = eval0.f(0).y;
	double d1 = eval1.f(0).y;
	
	if (operator.equals(">")) {
		return d0 > d1;
	}	
	if (operator.equals("<")) {
		return d0 < d1;
	}	
	if (operator.equals("=")) {
		return d0 == d1;
	}
	if (operator.equals(">=")) {
		return d0 >= d1;
	}	
	if (operator.equals("<=")) {
		return d0 <= d1;
	}	
	if (operator.equals("!=")) {
		return d0 != d1;
	}
	return false; 
}
/**
 * Returns <code>Boolean.TRUE</code> if the comparison
 * operator holds true between the values of the two
 * terms.
 *
 * @return <code>Boolean.TRUE</code> if the comparison
 * operator holds true between the values of the two
 * terms. 
 */
public Object eval() {
	return new Boolean(canProveOnce());
}
/**
 * Returns a string representation of this comparison. 
 *
 * @return   a string representation of this comparison
 */
public String toString() {
	return operator + "(" + f0 + ", " + f1 + ")";
}
}
