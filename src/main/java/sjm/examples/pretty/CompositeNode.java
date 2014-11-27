package sjm.examples.pretty;

import java.util.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class provides a composite node that can contain
 * other nodes.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */

public class CompositeNode extends ComponentNode {
	protected Vector children = new Vector();
/**
 * Create a node that can contain other nodes, and that
 * holds the given value.
 */
public CompositeNode(Object v) {
	this.value = v;
}
/**
 * Add a node after the currently held nodes.
 *
 * @param    ComponentNode   another node, either a composite
 *                           or a terminal node
 */
public void add(ComponentNode node) {
	children.addElement(node);
}
/**
 * Add a node before the currently held nodes.
 *
 * @param    ComponentNode   another node, either a composite
 *                           or a terminal node
 */
public void insert(ComponentNode n) {
	children.insertElementAt(n, 0);
}
/*
 * Return a textual description of this node. We take care
 * to print a node only once, since a composite may contain
 * cycles. We may or may not want to print the object this
 * composite contains -- the identation indicates the presence
 * of the composite and can obviate the need for printing the
 * composite's value. ShowDangle gives an example of not
 * needing to see the composite's value.
 */
protected String toString(
	int depth, boolean label, Vector visited) {
		
	if (visited.contains(this)) {
		return "...";
	}
	visited.addElement(this);
	StringBuffer buf = new StringBuffer();
	if (label) {
		buf.append(indent(depth));
		buf.append(value);
		buf.append("\n");
	}
	Enumeration e = children.elements();
	while (e.hasMoreElements()) {
		ComponentNode child = (ComponentNode) e.nextElement();
		buf.append(child.toString(depth + 1, label, visited));
	}
	return buf.toString();
}
}
