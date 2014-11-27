package sjm.examples.sling;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class wraps an arithmetic function around two source 
 * functions.
 * <p>
 * An <code>Arithmetic</code> object has an operator and two 
 * sources. The operator must be '+', '-', '/', '*' or '%' (or 
 * else <code>f(t)</code> of this object will always be (0, 
 * 0)). 
 * <p>
 * The value of this function at time t is the value of the 
 * operator applied to the source functions at time t. For 
 * example, evaluating the arithmetic sum <code>f1 + f2</code> 
 * at time t creates the point <code>(f1(t).x + f2(t).x, 
 * f1(t).y + f2(t).y)</code>.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Arithmetic extends SlingFunction {
	protected char operator;
/**
 * Constructs <code>operator(t, t)</code>, where operator is
 * one of the symbols +, -, /, *, %.
 */
public Arithmetic(char operator) {
	this(operator, new T(), new T());
}
/**
 * Constructs a function object that is the sum, difference,
 * quotient, product or remainder (depending on the 
 * operator) of the supplied functions.
 *
 * @param   operator   the operator to apply, one of the
 *                     characters in <code>+-/*%</code>
 *
 * @param    SlingFunction   an operand
 *
 * @param    SlingFunction   another operand
 *
 * @return a function that represents the specified 
 *         arithmetic operation on the supplied functions
 */
public Arithmetic(
	char operator, SlingFunction a, SlingFunction b) {
		
	super(a, b);
	this.operator = operator; 
}
/*
 * Do the math.
 */
protected double arithmetic(double a, double b) {
	switch (operator) {
		case '+' :
			return a + b;
		case '-' :
			return a - b;
		case '*' :
			return a * b;
		case '/' :
			return a / b;
		case '%' :
			return a % b;
	}
	return 0.0;
}
/** 
 * Returns the sum, difference, quotient, product or 
 * remainder (depending on the operator) of this object's 
 * source functions.
 *
 * @param   t   a number that represents how far along a 
 *              plot is
 *
 * @return a new point that applies this object's operator
 *         to the values of the source functions at time t
 *
 */
public Point f(double t) {
	Point a = source[0].f(t);
	Point b = source[1].f(t);
	return new Point(
		arithmetic(a.x, b.x), arithmetic(a.y, b.y));
}
/**
 * Returns a string representation of this function.
 */
public String toString() {
	return 
		operator + "(" + source[0] + ", " + source[1] + ")";
}
}
