package sjm.examples.sling;

import sjm.engine.*;
import sjm.imperative.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This command, when executed, evaluates a renderable 
 * function and adds it to a renderable collection.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class AddFunctionCommand extends Command {
	protected RenderableCollection renderables;
	protected SlingFunction f;
	protected Variable nLine;
/**
 * Construct a command to add the supplied function to the
 * supplied function collection.
 *
 * @param   RenderableCollection   the collection
 *
 * @param   SlingFunction   the function to evaluate and add
 *                          at execution time
 *
 * @param   nLine   a varialbe representing the number of
 *                  lines to plot when rendering the
 *                  function
 */
public AddFunctionCommand(
	RenderableCollection renderables, 
	SlingFunction f, 
	Variable nLine) {
		
	this.renderables = renderables;
	this.f = f;
	this.nLine = nLine;
}
/**
 * Evaluate the function and add it to the collection.
 */
public void execute() {
	renderables.add(new Renderable(f.eval(), nLine.eval()));
}
/**
 * Returns a string description of this command.
 *
 * @return   a string description of this command
 */
public String toString() {
	return "add(" + f + ", " + renderables + ")";
}
}
