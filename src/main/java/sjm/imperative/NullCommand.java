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
 * This command does nothing, which can simplify coding
 * in some cases. For example, an "if" command with no 
 * given "else" uses a <code>NullCommand</code> for its else 
 * command.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class NullCommand extends Command {
/**
 * Does nothing.
 */
public void execute() {
}
/**
 * Returns a string description of this null command.
 *
 * @return   a string description of this null command
 */
public String toString() {
	return "NullCommand";
}
}
