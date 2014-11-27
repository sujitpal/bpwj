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
 * This command mimics a normal "while" loop, executing
 * a command in a loop, so long as some condition holds
 * true.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class WhileCommand extends Command {
	protected BooleanTerm condition;
	protected Command command;
/**
 * Construct a "while" command from the given condition
 * and command.
 *
 * @param condition   the condition to check each time
 *                    before executing the body
 *
 * @param command   the command to repeatedly execute
 */
public WhileCommand(BooleanTerm condition, Command command) {
	this.condition = condition;
	this.command = command;
}
/**
 * Execute this "while" command. This means repeatedly
 * checking the condition, and executing command from
 * this object's command, so long as the condition is true.
 */
public void execute() {
	while (((Boolean) condition.eval()).booleanValue()) {
		command.execute();
	}
}
/**
 * Returns a string description of this while command.
 *
 * @return   a string description of this while command
 */
public String toString() {
	return "while" + 
		"(" + condition + ")" + 
		"{" + command + "}";
}
}
