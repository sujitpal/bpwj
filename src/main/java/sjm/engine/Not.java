package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A Not is a structure that fails if it can prove itself 
 * against a program.
 * <p>
 * This simple behavior can have surprising results. For 
 * example:
 * <blockquote><pre>
 *     goodBachelor(X) :- male (X), not married(X);
 *     badBachelor(X) :- not married(X), male(X);
 *     married(jim);
 *     male(jeremy);
 *     male(jim);
 * </pre></blockquote>
 *
 * Against this program, a query <code>badBachelor(X)</code> 
 * will find no bachelors. This rule negates <code>married(X) 
 * </code> which will bind uninstantiated <code>X</code> to 
 * <code>jim</code> every time. Thus <code>married(X)</code> 
 * will always be true, <code>not married(X)</code> will 
 * always be false, and there are no bad bachelors, which is 
 * untrue. The good rule instantiates <code>X</code> to a good 
 * candidate first, and the negation will prove or disprove 
 * itself for that candidate.
 * <p>
 * (A parser of the logic code given above will produce 
 * engine calls similar to the following code.)
 *
 * <blockquote><pre>
 *     Program p = new Program();
 *     Variable x = new Variable("X");
 *     
 *     p.addAxiom(new Rule(new Structure[]{
 *         new Structure("goodBachelor", new Term[]{x}),
 *         new Structure("male",         new Term[]{x}),
 *         new Not      ("married",      new Term[]{x})}));
 *     
 *     p.addAxiom(new Rule(new Structure[]{
 *         new Structure("badBachelor",  new Term[]{x}),
 *         new Not      ("married",      new Term[]{x}),
 *         new Structure("male",         new Term[]{x})}));
 *     p.addAxiom(new Fact("married", "jim"));
 *     p.addAxiom(new Fact("male", "jeremy"));
 *     p.addAxiom(new Fact("male", "jim"));
 *
 *     Query qGood = new Query(
 *         p, new Structure("goodBachelor", new Term[]{x}));
 *     while(qGood.canFindNextProof()) {
 *         System.out.println(
 *             "goodBachelor query finds: " + qGood.lookup("X"));
 *     }
 *
 *     Query qBad = new Query(
 *         p, new Structure("badBachelor", new Term[]{x}));
 *     while(qBad.canFindNextProof()) {
 *         System.out.println(
 *             "badBachelor query finds: " + qBad.lookup("X"));
 *     }
 * 
 * </pre></blockquote>
 *
 * Running this code prints out:
 * <blockquote><pre>
 *     goodBachelor query finds: jeremy
 * </pre></blockquote>
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */

public class Not extends Structure {
/**
 * Contructs a Not from the specified object.
 * <p>
 * Such a Not will search a program for a fact that contains 
 * just the functor. For example, it might make sense to create a 
 * <code>Not("demo")</code> that would look for the fact 
 * <code>demo</code> to see if the program is in demo mode.
 *
 * @param   functor   the functor for this structure
 */
public Not(Object functor) {
	this(functor, new Term[0]);
}
/**
 * Constructs a Not with the specified functor and terms.
 *
 * This is the normal way to define a Not. This structure will
 * be true if there is no possible proof of it in a program.
 *
 * @param   functor   the functor of the structure
 * 
 * @param   terms   the terms of the structure, which may be either
 *                  variables or other structures
 */
public Not(Object functor, Term[] terms) {
	super(functor, terms);
}
/**
 * Constructs a Not version of the supplied structure.
 *
 * @param   structure   the structure to negate
 */
public Not(Structure s) {
	this(s.functor, s.terms);
}
/**
 * Create a <code>ConsultingNot</code> counterpart that
 * can prove itself.
 *
 * @param AxiomSource where to find axioms to prove
 *        against
 *
 * @param Scope the scope to use for variables in the
 *        <code>ConsultingStructure</code>
 *
 * @return a <code>ConsultingNot</code> counterpart that
 *         can prove itself
 */
public Term copyForProof(AxiomSource as, Scope scope) {
	Term[] newTerms = new Term[terms.length];
	for (int i = 0; i < terms.length; i++) {
		newTerms[i] = terms[i].copyForProof(as, scope);
	}
	return new ConsultingNot(new ConsultingStructure(as, functor, newTerms));
}
/**
 * Returns true if the supplied object is an equivalent 
 * not structure.
 *
 * @param   object   the object to compare
 *
 * @return   true, if the supplied object is a Not, and
 *           the two object's sub-structures are equal
 */
public boolean equals(Object o) {
	if (!(o instanceof Not))
		return false;
	Not n = (Not) o;
	if (!functorAndArityEquals(n)) {
		return false;
	}
	for (int i = 0; i < terms.length; i++) {
		if (!(terms[i].equals(n.terms[i]))) {
			return false;
		}
	}
	return true;
}
/**
 * Returns a string description of this Not.
 *
 * @return    a string description of this Not
 */
public String toString() {
	return "not " + super.toString();
}
}
