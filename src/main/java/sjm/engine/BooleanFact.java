package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A BooleanFact is a fact with either <code>Boolean.TRUE
 * </code> or <code>Boolean.FALSE</code> as its functor.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class BooleanFact extends Atom {

/**
 * Contructs a boolean fact from the provide Boolean.
 * 
 * @param Boolean <code>Boolan.TRUE</code> or 
 *                <code>Boolean.FALSE</code>
 */
public BooleanFact(Boolean b) {
	super(b);
}
/**
 * Contructs a boolean fact from the boolean.
 * 
 * @param boolean <code>true</code> or <code>false</code>
 */
public BooleanFact(boolean b) {
	this(b ? Boolean.TRUE : Boolean.FALSE);
}
}
