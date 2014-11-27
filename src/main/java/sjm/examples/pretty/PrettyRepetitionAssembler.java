package sjm.examples.pretty;

import java.util.*;
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
 * Replace the nodes above a given "fence" object with
 * a new composite that holds the popped nodes as its children.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PrettyRepetitionAssembler extends Assembler {
	protected String name;
	protected Object fence;
/**
 * Construct an assembler that will replace the nodes above the
 * supplied "fence" object with a new composite that will hold
 * the popped nodes as its children.
 */
public PrettyRepetitionAssembler(String name, Object fence) {
	this.name = name;
	this.fence = fence;
}
/**
 * Replace the nodes above a given "fence" object with
 * a new composite that holds the popped nodes as its children.
 *
 * @param   Assembly   the assembly to work on
 */
public void workOn(Assembly a) {
	CompositeNode newNode = new CompositeNode(name);
	Vector v = elementsAbove(a, fence);
	Enumeration e = v.elements();
	while (e.hasMoreElements()) {
		newNode.add((ComponentNode) e.nextElement());
	}
	a.push(newNode);
}
}
