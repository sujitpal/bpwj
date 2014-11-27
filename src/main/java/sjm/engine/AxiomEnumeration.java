package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

 /**
 * An object that implements the AxiomEnumeration interface
 * generates a series of axioms, one at a time.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public interface AxiomEnumeration {
/**
 * Tests if this enumeration contains more axioms.
 *
 * @return  <code>true</code> if this enumeration contains more
 *          axioms, and <code>false</code> otherwise.
 */
boolean hasMoreAxioms();
/**
 * Returns the next axiom of this enumeration.
 *
 * @return the next axiom of this enumeration.
 */
Axiom nextAxiom();
}
