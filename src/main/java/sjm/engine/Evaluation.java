package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * 
 * An Evaluation unifies a term with the value of 
 * another term.
 * <p>
 * For example, 
 * 
 * <blockquote><pre>
 *     NumberFact two = new NumberFact(2);
 *     ArithmeticOperator x, y;
 *     x = new ArithmeticOperator('*', two, two);
 *     y = new ArithmeticOperator('+', x, two);
 *     
 *     Variable result = new Variable("Result");
 *     Evaluation e = new Evaluation(result, y);
 *     System.out.println(e);
 *     System.out.println(e.canFindNextProof());
 *     System.out.println(result);
 *     
 * </pre></blockquote>
 * 
 * prints out:
 * 
 * <blockquote><pre>
 *     #(Result, +(*(2.0, 2.0), 2.0))
 *     true
 *     6.0
 * </pre></blockquote>
 *
 * <p>
 * Since an Evaluation <i>unifies</i> the first term with the 
 * arithmetic value, the second term may have a value before 
 * the Evaluation proves itself. In this case, the Evaluation
 * checks that the second term's ground value equals the 
 * arithmetic value of the first term.
 * <p>
 * The Evaluation fails if the arithmetic value is invalid for
 * any reason. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */

public class Evaluation extends Gateway {
	Term term0;
	Term term1;
	protected Unification currentUnification;
/**
 * Constructs an Evaluation that will unify the first term 
 * with the second term during proofs.
 * 
 * @param Term the first term to unify
 *
 * @param Term the term whose value should unify 
 *             with the first term
 */
public Evaluation(Term term0, Term term1) {
	super("#", new Term[] {term0, term1});
	this.term0 = term0;
	this.term1 = term1;
}
/**
 * Returns true if this Evaluation can unify its first term 
 * with the value of its second term.
 * <p>
 * If the attempt to evaluate the second term causes an 
 * exception, this method swallows it and simply fails.
 * 
 * @return <code>true<</code>, if this Evaluation can unify 
 *         its first term with the arithmetic value of its 
 *         second term
 */
public boolean canProveOnce() {
	Object o;
	try {
		o = term1.eval();
	} catch (EvaluationException e) {
		return false;
	}
	currentUnification = term0.unify(new Atom(o));
	return currentUnification != null;
}
/*
 * The superclass calls this after the evaluation has
 * succeeded once, and rule is now failing backwards. The
 * assigment needs to undo any binding it did on the way
 * forward.
 */
protected void cleanup() {
	unbind();
}
/**
 * Create a copy that uses the provided scope.
 *
 * @param AxiomSource ignored
 *
 * @param Scope the scope to use for variables in the
 *              copy
 *
 * @return a copy that uses the provided scope
 */
public Term copyForProof(AxiomSource ignored, Scope scope) {
	return new Evaluation(
		term0.copyForProof(null, scope), 
		term1.copyForProof(null, scope));
}
/**
 * Releases the variable bindings that the last unification produced.
 *
 */
public void unbind() {	
	if (currentUnification != null) {
		currentUnification.unbind();
	}
	currentUnification = null;
}
}
