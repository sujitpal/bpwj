package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * The EmptyList is a list with no terms.
 * <p>
 * All lists except this one contain a head, which may be any
 * term, and a tail, which is another list. This recursive 
 * defintion terminates with this singleton, the empty list.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class EmptyList extends Fact {
/**
 * Constructs the empty list singleton.
 */
protected EmptyList() {
	super(".");
}
/**
 * Return true, since an empty list is a list.
 *
 * @return true 
 */
public boolean isList() {
	return true;
}
/**
 * Returns a string representation of this list as a part of 
 * another list. When the empty list represents itself as part
 * of another list, it just returns "".
 *
 * @return an empty string
 */
public String listTailString() {
	return "";
}
/**
 * Returns a string representation of the empty list.
 *
 * @return   a string representation of the empty list
 */
public String toString() {
	return "[]";
}
}
