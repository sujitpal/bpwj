package sjm.imperative;

import sjm.engine.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This command mimics a normal "if" statement, such as:
 *
 * <blockquote><pre>
 *
 *     if (x > 7) {
 *         // body to execute if condition is true
 *     } else {
 *         // body to execute if condition is false
 *     }
 * </pre></blockquote>
 *
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class IfCommand extends Command {
	protected BooleanTerm condition;
	protected Command ifCommand;
	protected Command elseCommand;
/**
 * Construct an "if" command from the given condition and
 * command.
 *
 * @param condition   the condition to check
 *
 * @param ifCommand   the command to execute if the 
 *                    condition evaluates to true
 */
public IfCommand(BooleanTerm condition, Command ifCommand) {
	this.condition = condition;
	this.ifCommand = ifCommand;
	this.elseCommand = new NullCommand();
}
/**
 * Construct an "if" command from the given condition and
 * command.
 *
 * @param condition   the condition to check
 *
 * @param ifCommand   the command to execute if the 
 *                    condition evaluates to true
 *
 * @param elseCommand   the command to execute if the 
 *                      condition evaluates to false
 */
public IfCommand(
	BooleanTerm condition, Command ifCommand, 
	Command elseCommand) {
		
	this.condition = condition;
	this.ifCommand = ifCommand;
	this.elseCommand = elseCommand;
}
/**
 * Execute this "if" command. Evaluate the condition. If it
 * is true, execute this object's <code>ifCommand</code>.
 * Otherwise, execute the <code>elseCommand</code>, which 
 * may be a <code>NullCommand</code> object.
 */
public void execute() {
	Boolean b = (Boolean) condition.eval();
	if (b.booleanValue()) {
		ifCommand.execute();
	} else {
		elseCommand.execute();
	}
}
/**
 * Returns a string description of this if command.
 *
 * @return   a string description of this if command
 */
public String toString() {
	return 
		"if"   + "(" + condition + ")" 
		       + "{" + ifCommand + "}" +
		"else" + "{" + elseCommand + "}";
}
}
