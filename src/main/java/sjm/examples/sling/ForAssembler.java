package sjm.examples.sling;

import java.util.*;
import sjm.imperative.*;
import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.engine.BooleanTerm;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Builds a "for" command from elements on the stack, which 
 * should be: a variable, a "from" function, a "to" function, 
 * a '{' token, and a series of commands.
 * <p>
 * This class uses the curly brace as a fence, popping 
 * commands above it and creating a composite command from 
 * these commands. This composite command is the body of the 
 * "for" loop. This assembler pops the "from" and "to" 
 * functions and the variable, constructs a 
 * <code>ForCommand</code> object, and pushes it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ForAssembler extends Assembler {
/*
 * Pop the elements on the stack above a '{' token, and
 * build a composite command from them.
 */
protected static CommandSequence popCommandSequence(
	Assembly a) {
		
	Token fence = new Token('{');
	Vector statementVector = elementsAbove(a, fence);
	CommandSequence cs = new CommandSequence();
	int n = statementVector.size();
	for (int i = n - 1; i >= 0; i--) {
		Command c = (Command) statementVector.elementAt(i);
		cs.addCommand(c);
	}
	return cs;
}
/**
 * Pop the elements of a "for" loop, construct a <code>
 * ForCommand</code>, and push it.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	// pop the elements of a "for" loop
	CommandSequence cs = popCommandSequence(a);
	SlingFunction to = (SlingFunction) a.pop();
	SlingFunction from = (SlingFunction) a.pop();
	Variable v = (Variable) a.pop();

	// create and push a ForCommand
	Command setup = new AssignFunctionCommand(v, from);
	BooleanTerm condition = new FunctionComparison("<=", v, to);
	SlingFunction step = new Point(1, 1);
	Arithmetic plus1 = new Arithmetic('+', v, step); 
	Command endCommand = new AssignFunctionCommand(v, plus1);
	a.push(new ForCommand(setup, condition, endCommand, cs));
}
}
