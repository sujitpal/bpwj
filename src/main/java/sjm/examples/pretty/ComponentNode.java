package sjm.examples.pretty;

/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class tops a hierarchy that implements the Composite
 * pattern. The two subclasses are <code>CompositeNode</code>
 * and <code>TerminalNode</code>. A composite node contains
 * other nodes, which can be other composites or terminal
 * nodes. A terminal node does not contain other nodes.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public abstract class ComponentNode {
	protected Object value;
/**
 * Returns a string of blanks.
 *
 * @return  a string of blanks
 */
public static String indent(int n) {
	StringBuffer buf = new StringBuffer();
	for (int i = 0; i < n; i++) {
		buf.append("    ");
	}
	return buf.toString();
}
/**
 * Return a textual description of this node.
 * 
 * @return a textual description of this node
 */
public String toString() {
	return toString(0, true, new java.util.Vector());
}
/*
 * Return a textual description of this node, noting whether
 * composite objects should display their objects, and keeping
 * track of previously visited nodes.
 * 
 * @return a textual description of this node
 */
protected abstract String toString(
	int n, boolean label, java.util.Vector visited);
/**
 * Return a textual description of this node without showing
 * composite values. This is useful if the presence of a
 * composite is clear just from the indenting in the display
 * string. The class <code>ShowDangle</code> gives an example.
 * 
 * @return a textual description of this node, omitting
 *         composite values
 */
public String toStringWithoutLabels() {
	return toString(0, false, new java.util.Vector());
}
}
