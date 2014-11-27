package sjm.examples.logic;

import sjm.parse.*;
import sjm.engine.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This assembler pops two arithmetic operands, builds an
 * ArithmeticOperator from them, and pushes it. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ArithmeticAssembler extends Assembler {
	/**
	 * the character which represents an arithmetic operator
	 */
	protected char operator;
/**
 * Constructs an assembler that will stack an 
 * ArithmeticOperator with the specified operator.
 */
public ArithmeticAssembler(char operator) {
	this.operator = operator;
}
/**
 * Pop two arithmetic operands, build an ArithmeticOperator 
 * from them, and push it. 
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	ArithmeticTerm operand1 = (ArithmeticTerm) a.pop();
	ArithmeticTerm operand0 = (ArithmeticTerm) a.pop();
	a.push(new ArithmeticOperator(
		operator, operand0, operand1));
}
}
