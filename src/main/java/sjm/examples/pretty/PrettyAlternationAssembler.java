package sjm.examples.pretty;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Replace a <code>ComponentNode</code> object on the stack
 * with a new composite that holds the popped node as its
 * only child.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PrettyAlternationAssembler extends Assembler {
	protected String name; 
/**
 * Create an assembler that will replace a <code>ComponentNode
 * </code> object on the stack with a new composite that holds 
 * the popped node as its only child and whose name is as
 * supplied here.
 */
public PrettyAlternationAssembler(String name) {
	this.name = name;
}
/**
 * Replace a <code>ComponentNode</code> object on the stack
 * with a new composite that holds the popped node as its
 * only child.
 *
 * @param   Assembly   the assembly to work on
 */
public void workOn(Assembly a) {
	CompositeNode newNode = new CompositeNode(name);
	ComponentNode node = (ComponentNode) a.pop();
	newNode.insert(node);
	a.push(newNode);
}
}
