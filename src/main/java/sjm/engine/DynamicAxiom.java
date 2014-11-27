package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A DynamicAxiom is an axiom (that is, either a fact
 * or a rule) that a structure can consult to prove itself. 
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public interface DynamicAxiom {
/**
 * Return the first structure of this dynamic axiom.
 *
 * @return the first structure of this dynamic axiom
 */
Structure head();
/**
 * Return the tail of this dynamic axiom.
 *
 * @return the tail of this dynamic axiom. This tail
 *         is the part of the dynamic that still needs to
 *         prove itself.
 */
DynamicRule resolvent();
}
