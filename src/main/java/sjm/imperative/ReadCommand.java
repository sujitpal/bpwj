package sjm.imperative;

import java.io.*;
import sjm.engine.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This command, when executed, reads in a string and
 * assigns it to a supplied variable.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ReadCommand extends Command {
	protected Variable variable;
	protected BufferedReader reader;
/**
 * Construct a "read" command to read a value, assigning
 * it to the supplied variable. 
 *
 * This constructor sets the command to read from <code>
 * System.in</code>.
 *
 * @param Variable   the variable to assign to
 */
public ReadCommand(Variable variable) {
	this(variable, System.in);
}
/**
 * Construct a "read" command to read a value from the
 * supplied reader, assigning the value to the supplied 
 * variable.
 *
 * @param Variable   the variable to assign to
 *
 * @param BufferedReader   where to read from
 */
public ReadCommand(Variable variable, BufferedReader reader) {
	this.variable = variable;
	this.reader = reader;
}
/**
 * Construct a "read" command to read a value from the
 * supplied input stream, assigning the value to the supplied 
 * variable.
 *
 * @param Variable   the variable to assign to
 *
 * @param InputStream   where to read from
 */
public ReadCommand(Variable variable, InputStream in) {
	this(
		variable, 
		new BufferedReader(new InputStreamReader(in)));
}
/**
 * Read in a string from this object's input reader, and
 * assign the string to this object's variable.
 */
public void execute() {
	String s;
	try {
		s = reader.readLine();
	} catch (IOException e) {
		s = "";
	}
	Evaluation e = new Evaluation(variable, new Atom(s));
	AssignmentCommand ac = new AssignmentCommand(e);
	ac.execute();
}
/**
 * Returns a string description of this read command.
 *
 * @return   a string description of this read command
 */
public String toString() {
	return "read(" + variable.name + ")";
}
}
