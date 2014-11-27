package sjm.utensil;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Defines a type of object which anybody can clone.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public interface PubliclyCloneable extends Cloneable {

/**
 * A PubliclyCloneable object is one to which any object can send
 * <code>clone()</code>.
 * 
 * @return   a copy of the receiving object
 */
public Object clone();
}
