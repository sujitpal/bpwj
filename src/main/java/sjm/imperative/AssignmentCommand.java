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
 * This class holds an <code>sjm.engine.Evaluation</code> 
 * object, and executes it upon receiving an <code>execute
 * </code> command.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AssignmentCommand extends Command {
	protected Evaluation evaluation;
/**
 * Construct an <code>Assignment</code> command from the
 * given <code>Evaluation</code>.
 */
public AssignmentCommand(Evaluation evaluation) {
	this.evaluation = evaluation; 
}
/**
 * Execute the assignment. Evaluate the evaluation's second
 * term, and retrieve the variable from the evaluation's 
 * first term. Unbind the variable, so that it can unify, 
 * and unify the variable with the second term.
 */
public void execute() {
	
	/* Note: we can only unbind the variable after 
	 * evaluating the first term. Not doing this
	 * would create a defect with i := i + 1 */

	Object o = evaluation.terms()[1].eval();
	Variable v = (Variable) evaluation.terms()[0];
	v.unbind();
	v.unify(new Atom(o));
}
/**
 * Returns a string representation of this command.
 *
 * @return   a string representation of this command
 */
public String toString() {
	return evaluation.toString();
}
}
