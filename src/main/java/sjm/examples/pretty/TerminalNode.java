package sjm.examples.pretty;

public class TerminalNode extends ComponentNode {
/**
 * Create a node that holds the given value.
 */
public TerminalNode(Object value) {
	this.value = value;
}
/*
 * Return a textual description of this node, indenting the
 * string based on the depth of the node.
 */
protected String toString(
	int depth, boolean label, java.util.Vector ignored) {
		
	return indent(depth) + value + "\n";
}
}
