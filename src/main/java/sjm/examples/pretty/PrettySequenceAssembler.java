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
 * Replace a given number of nodes on the stack with a new 
 * composite that holds the popped nodes as its children.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PrettySequenceAssembler extends Assembler {
	protected String name;
	protected int numberNodes;
/**
 * Construct an assembler that will replace a given number of 
 * nodes on the stack with a new composite that holds the 
 * popped nodes as its children.
 */
public PrettySequenceAssembler(String name, int numberNodes) {
	this.name = name;
	this.numberNodes = numberNodes;
}
/**
 * Replace a given number of nodes on the stack with a new 
 * composite that holds the popped nodes as its children.
 *
 * @param   Assembly   the assembly to work on
 */
public void workOn(Assembly a) {
	CompositeNode newNode = new CompositeNode(name);
	for (int i = 0; i < numberNodes; i++) {
		ComponentNode node = (ComponentNode) a.pop();
		newNode.insert(node);
	}
	a.push(newNode);
}
}
