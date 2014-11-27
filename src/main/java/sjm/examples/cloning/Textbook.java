package sjm.examples.cloning;

import sjm.utensil.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/** 
 * This class just supports the <code>ThisClass</code>
 * example of a typical clone.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Textbook implements PubliclyCloneable {
/**
 * Return a copy of this object.
 *
 * @return a copy of this object
 */
public Object clone() {
	try {
		return super.clone();
	} catch (CloneNotSupportedException e) {
		// this shouldn't happen, since we are Cloneable
		throw new InternalError();
	}
}
}
