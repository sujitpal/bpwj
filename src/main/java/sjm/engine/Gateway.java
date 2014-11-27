package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * A Gateway is a structure that can prove its truth at most 
 * once before failing. 
 * <p>
 * Examples of gateways are comparisons, negations, and 
 * mathematical evaluations. For example, <code>Age > 
 * 18</code> is either true or not, it cannot prove itself in 
 * more than one way. Also, <code> not married(X)</code> is 
 * true only if the structure <code>married(X)</code> cannot 
 * prove itself at all. 
 * <p>
 * The fact that gateways can be true only once is important 
 * in rules. For example, a rule might contain: 
 * <blockquote><pre>
 *     ..., plays(jim, Game, Rating), 
 *         Rating >= 7, likes(jane, Game), ... 
 * </pre></blockquote> 
 * As this rule proves itself, if it finds a game that Jim 
 * plays with a rating of, say, 8, the rule will accept that 
 * <code>Rating >= 7</code>. The rule will then proceed to 
 * prove the structures after the comparison. The rule may 
 * succeed with the latter structures many times, but 
 * eventually these structures will run out of proofs, and the 
 * rule will fail back to the comparison structure. 
 * <p>
 * On failing back, the rule will ask the comparison for its 
 * next proof. This amounts to asking if there is a different 
 * way to prove that 8 is greater than or equal to 7. Since 
 * the comparison has already succeeded once, which allowed 
 * the rule to check the latter structures, on failing back 
 * the comparison will fail. This will cause the rule to fail 
 * back to the preceding <code>plays</code> structure, which 
 * may be able to find other proofs. 
 * <p>
 * If a preceding structure succeeds, the rule will move 
 * forward again, and ask a gateway to prove itself. Since the 
 * gateway has failed, it will try again to prove itself.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 * 
 */
public abstract class Gateway extends Structure {
	/*
	 * If this structure is involved in a proof, the gate is
	 * open and will shut when the rule fails back to this 
	 * structure.
	 */
	protected boolean open = false;
/**
 * Allows subclasses to use this form of constructor. This typically
 * happens when the subclass object is creating an executable copy
 * of itself.
 *
 * @param   functor   the functor for this gateway
 * 
 * @param   terms   the terms of the gateway
 *
 * @param   program   the program the gateway will prove itself
 *                    against
 */
protected Gateway(Object functor, Term[] terms) {
	super(functor, terms);
}
/** 
 * Returns true if the gate is closed and this gateway can find a new 
 * proof.
 * <p>
 * A gateway is a structure that can prove itself in at most one 
 * way. After a successful proof, a gateway leaves its gate open.
 * <p>
 * If the gate is open when this method executes, this method will 
 * shut the gate and return false. This occurs after a gateway has 
 * proven itself true once, and a rule has failed back to the point 
 * where it is asking the gateway for another proof.
 * <p>
 * If the gate is not open, this gateway will try to prove itself. 
 * Then,
 * <ul>
 * <p><li>
 * If the gate is not open and this gateway can prove itself, then 
 * this method will return true and leave the gate open. Returning 
 * true allows the containing rule to go on to prove whatever 
 * structures follow this one. When the rule fails back to this 
 * gateway, the gate will be open, and at that time this gateway 
 * will fail.
 * <p><li>
 * If the gate is not open and this gateway can not prove itself, 
 * this method returns false.
 * </ul>
 * <p>
 * Upon leaving the gate closed, this method unbinds any variables 
 * that instantiated as part of this gateway's proof. This method 
 * also sets rule checking to begin again at the first program rule, 
 * upon the next request for a proof sent to this gateway.
 *
 * @param   depth   the depth at which to print tracing information
 *
 * @return   true   if the gate is closed and this gateway can find a 
 *                  new proof
 */
public boolean canFindNextProof() {
	if (open) {
		open = false;
	} else {
		open = canProveOnce();
	}
	if (!open) {
		cleanup();
	}
	return open;
}
/**
 * Returns true if the comparison operator holds true between each 
 * pair of terms.
 * <p>
 * This method recovers the comparison operator from the Token which
 * is the functor for this Comparison. This method applies this 
 * comparison to each pair of terms. That is, this method compares
 * term 0 with term 1, term 1 with term 2, term 2 with term 3, and
 * so on. This method returns true if the comparison holds between
 * each adjacent pair of terms.
 * <p>
 * If a term is a variable, this method uses the term's ground value
 * in the comparison.
 *
 * @param   depth   the depth at which to print trace information
 */
public boolean canProveOnce() {
	return true;
}
/**
 * Insert the method's description here.
 * Creation date: (12/7/99 11:09:44 AM)
 */
protected void cleanup() {}
}
