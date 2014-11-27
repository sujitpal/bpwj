package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * In practice, an Axiom is either a fact or a rule, the two 
 * types of objects that can appear in a program. More 
 * precisely, an Axiom has a head structure with which a 
 * consulting structure can unify, and an Axiom can produce 
 * a ProvableAxiom. 
 * <p>
 * Facts are simply true, and return themselves as 
 * DynamicAxioms. To prove itself, a Rule needs to 
 * create a DynamicAxiom that can attempt to prove 
 * itself against an axiom source.  
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public interface Axiom {
/**
 * Return an axiom that a consulting structure can use
 * to prove itself.
 *
 * @return an axiom that a consulting structure can use
 *         to prove itself.
 */
DynamicAxiom dynamicAxiom(AxiomSource as);
/**
 * Return the first structure of this axiom.
 *
 * @return the first structure of this axiom
 */
Structure head();
}
