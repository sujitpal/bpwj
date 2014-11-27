package sjm.examples.arithmetic;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show how to use the <code>ArithmeticParser</code> class.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 * 
 */

public class ShowArithmeticParser {
/*
 * Help out the main() method.
 */
private static void eval(String s, double d) 
	throws ArithmeticExpressionException {
		
	System.out.println(
		"Given: "    + s +
		" Expected: " + d + 
		"\tFound: "  + ArithmeticParser.value(s));
}
/**
 * Show a few examples of arithmetic.
 */
public static void main(String args[])
	throws ArithmeticExpressionException {
		
	eval("9^2 - 81       ",   0); // exponentiation
	eval("7 - 3 - 1      ",   3); // minus associativity
	eval("2 ^ 1 ^ 4      ",   2); // exp associativity
	eval("100 - 25*3     ",  25); // precedence
	eval("100 - 5^2*3    ",  25); // precedence
	eval("(100 - 5^2) * 3", 225); // parentheses
}
}
