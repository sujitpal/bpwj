package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * An Atom is a Structure that no terms.
 * <p>
 * For example, the following structure
 * <blockquote><pre>
 *     city(denver, 5280);
 * </pre></blockquote>
 *
 * is a fact that contains two atoms, "denver" and
 * 5280.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Atom extends Fact implements ComparisonTerm {
/**
 * Contructs an atom from the specified object.
 * 
 * @param Object the functor for this atom
 */
public Atom(Object functor) {
	super(functor);
}
/**
 * Returns the functor if this structure.
 *
 * @return the functor if this structure
 */
public Object eval() {
	return functor;
}
}
