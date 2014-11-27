package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * A ConsultingNot is a Not that has an axiom source to
 * consult.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */

public class ConsultingNot extends Gateway {
	ConsultingStructure consultingStructure;
/*
 * Contructs a ConsultingNot from the specified consulting
 * structure. This constructor is for use by Not.
 */
protected ConsultingNot(
	ConsultingStructure consultingStructure) {
		
	super(consultingStructure.functor, consultingStructure.terms);
	this.consultingStructure = consultingStructure;
}
/**
 * Returns <code>false</code> if there is any way to prove this
 * structure.
 *
 * @return <code>false</code> if there is any way to prove 
 *         this structure
 */
public boolean canProveOnce() {
	return !(consultingStructure.canUnify() && 
		     consultingStructure.resolvent.canEstablish());
}
/**
 * After succeeding once, unbind any variables bound during
 * the successful proof, and set the axioms to begin
 * again at the beginning.
 */
protected void cleanup() {
	consultingStructure.unbind();
	consultingStructure.axioms = null;
}
/**
 * Returns a string description of this Not.
 *
 * @return a string description of this Not
 */
public String toString() {
	return "not " + consultingStructure;
}
}
