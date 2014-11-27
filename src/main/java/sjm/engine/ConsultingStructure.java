package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * A ConsultingStructure is structure that can prove itself 
 * against an axiom source supplied with the constructor.
 * <p>
 * When a rule creates a dynamic version of itself, a normal 
 * structure will create a ConsultingStructure counterpart 
 * that uses the rule's scope, and that consults a given 
 * axiom source (such as a Program).
 * <p>
 * The primary behavior that a ConsultingStructure adds
 * is the ability to prove itself.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ConsultingStructure extends Structure { 
	protected AxiomSource source;
	protected AxiomEnumeration axioms;
	protected Unification currentUnification;
	protected DynamicRule resolvent;
/*
 * Constructs a consulting structure with the specified functor 
 * and terms, to consult against the supplied axiom source.
 * This constructor is for use by Structure. 
 */
protected ConsultingStructure(
	AxiomSource source, Object functor, Term[] terms) {
		
	super(functor, terms);
	this.source = source;
}
/*
 * Returns the axioms that a consulting structure can
 * consult. Note that after canUnify fails, this object will
 * set its axioms to null, which forces its proving
 * attempts to start over at the beginning of the source.
 */
protected AxiomEnumeration axioms() {
	if (axioms == null) {
		axioms = source.axioms(this);
	}
	return axioms;
}
/**
 * Tests if this structure can find another proof, and, if so, 
 * sets this structure's variables to the values that make the 
 * proof true.
 *
 * @return <code>true</code> if this structure can find another 
 *         proof.
 */
public boolean canFindNextProof() { 

/*
 * A consulting structure proves itself by unifying with rule 
 * in a program. When that rule has more than one structure, 
 * the proving structure takes the tail of the rule as its 
 * "resolvent". The resolvent is the remainder of the rule, 
 * which, if proven true, confirms the truth of this 
 * structure. The resolvent may have multiple different 
 * proofs, and each of these counts as a new proof of this 
 * structure.
 */
	if (resolvent != null) {
		if (resolvent.canFindNextProof()) {
			return true;
		}	
	}
	while (true) {
/*
 * No hogging the CPU! Who knows? We might be in a tight 
 * loop that the user wants to halt. For example, with the
 * program "loop :- loop;" and the query "loop", we need
 * to distract the proof dog enough to let a halt button 
 * click come through.
 */
		Thread.yield();
/*
 * Find a new axiom to prove.
 */
		unbind();
		if (!canUnify()) {
			axioms = null;
			return false;
		}
/*
 * Show that the unifying axiom's remainder is either
 * empty or provable.
 */
		if (resolvent.canEstablish()) {
			return true;
		}		
	}	
}
/**
 * Return true if this structure can unify with another rule in the 
 * program.
 * <p>
 * A consulting structure proves itself true by unifying with 
 * the head of a rule in a program, and then asking the tail 
 * of that rule to prove itself. Unification is a kind of 
 * matching. Two structures unify if they have equal functors, 
 * and the terms all unify. A variable unifies with a 
 * structure simply by setting its instantiation to be the 
 * structure.	
 * 
 * <p>
 * For example, the following structures can unify:
 * <blockquote><pre>
 *     starred(jamesCagney, Title, Year)
 *     starred(jamesCagney, "Yankee Doodle Dandy", 1942)
 * </pre></blockquote>
 * 
 * When these structures unify, the variable Title will bind 
 * itself to "Yankee Doodle Dandy", and Year will bind to 1942.
 * To be more specific, Title will bind to the atom
 * whose functor is "Yankee Doodle Dandy". Year will bind to the
 * atom whose functor is the number 1942.
 * 
 * @return <code>true</code>, if this structure can unify with 
 *         an axiom in the axiom source
 */
protected boolean canUnify() {
	while (axioms().hasMoreAxioms()) {
		Axiom a = axioms().nextAxiom();
		Structure h = a.head();
		if (!functorAndArityEquals(h)) {
			continue;
		}
		DynamicAxiom aCopy = a.dynamicAxiom(source);
		 
		currentUnification = aCopy.head().unify(this);
		resolvent = null;
		if (currentUnification != null) {
			resolvent = aCopy.resolvent();
			return true;
		}
	}
	return false;
}
/**
 * Release the variable bindings that the last unification 
 * produced.
 */
protected void unbind() {	
	if (currentUnification != null) {
		currentUnification.unbind();
	}
	currentUnification = null;
	resolvent = null;
}
}
