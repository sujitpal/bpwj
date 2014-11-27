package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A variable is a named term that can unify with  other 
 * terms. 
 * <p>
 * A variable has a name, such as "X" or "Person", and an 
 * instantiation. When a variable unifies with a term, it 
 * "instantiates" to it, taking the term as its value. The 
 * instantiation of a variable may be another variable, or a 
 * structure. 
 * <p>
 * The scope of a variable is the rule in which it is 
 * contained. For example, consider the member program: 
 * <blockquote><pre>
 *     member(X, [X | Rest]);
 *     member(X, [Y | Rest]) :- member(X, Rest);
 * </pre></blockquote>
 * In this program, the variable "X" in the first rule is the 
 * same variable both times it appears in the rule. However, 
 * this variable is completely independent of the variable 
 * named "X" in the second rule. Variables with the same name 
 * in a rule are the same variable, but variables with the 
 * same name in different rules are different variables. This 
 * is another way of saying that a variable's scope is the 
 * rule in which it appears. 
 * <p>
 * To be more specific, the scope of a variable is the 
 * <i>dynamic</i> rule in which the variable appears. Since 
 * rules may execute recursively, dynamic rules each need an 
 * independent copy of a defining rule's variables. In the 
 * member program, for example, the second rule may prove 
 * itself by reinvoking itself, with a (slightly) different 
 * set of variable instantiations. 
 * <p>
 * Consider the query <code>member(c, [a, b, c])</code>. This 
 * query will unify with the second rule, and try to prove the 
 * second rule's tail, which will be <code>member(c, [b, 
 * c])</code>. This structure will try to prove itself, and it 
 * too will unify with the second rule. At this point, the 
 * proof of <code>member(c, [a, b, c])</code> will be waiting 
 * upon the proof of <code>member(c, [b, c]). That is, the two 
 * dynamic copies of the rule, will be in different states, 
 * because of their variables. For example, the instantiation 
 * of <code>Rest</code> in the first execution of the rule 
 * will be <code>[b, c]<code>, and the value of 
 * <code>Rest</code> in the second rule will <code>[c]</code>.
 * <p>
 * Variables have a name and an instantiation which is unique 
 * within a scope; each dynamic version of a rule has a unique 
 * Scope.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */

public class Variable
	implements ArithmeticTerm, ComparisonTerm {
		
	public final String name;
	protected Term instantiation;
/**
 * Create a variable with the given name.
 */
public Variable(String name) {
	this.name = name;
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
	return scope.lookup(name);
}
/**
 * Returns string representation of this variable, showing 
 * both its name and its value.
 *
 * @return a string representation of this variable, showing 
 *         both its name and its value.
 */
public String definitionString() {
	if (instantiation != null) {
		return name + " = " + instantiation;
	}
	return name;
}
/**
 * Returns true if the supplied object is an equivalent 
 * variable.
 *
 * @param   object   the object to compare
 *
 * @return   true, if the supplied object has the same
 *           name, and it the two variables' instantiations
 *           are equal
 */
public boolean equals(Object o) {
	if (!(o instanceof Variable))
		return false;
	Variable v = (Variable) o;
	if (!name.equals(v.name)) {
		return false;
	}
	if (instantiation == null) {
		return v.instantiation == null;
	}
	return instantiation.equals(instantiation);
}
/**
 * Returns the value of this variable.
 *
 * @return the value of this variable
 *
 * @exception EvaluationException if this variable's
 *            value is undefined
 */
public Object eval() {
	if (instantiation == null) {
		throw new EvaluationException(
			"Variable " + name + " is undefined");
	}
	return instantiation.eval();
}
/**
 * Returns true if this variable is uninstantiated, or if it
 * contains a list.
 *
 * @Returns true    if this variable is uninstantiated, or if
 *                  it contains a list
 */
public boolean isList() {
	if (instantiation != null) {
		return instantiation.isList();
	}
	return true;
}
/**
 * Returns a string representation of this variable as the 
 * tail of a list.
 * 
 * @return a string representation of this variable as the 
 *         tail of a list
 */
public String listTailString() {
	if (instantiation != null) {
		return instantiation.listTailString();
	}
	return "|" + name;
}
/**
 * Returns a string representation of this variable.
 *
 * @return   a string representation of this variable
 */
public String toString() { 
	if (instantiation != null) {
		return instantiation.toString();
	}
	return name;
}
/**
 * Marks this variable as no longer having an instantiated 
 * value.
 */
public void unbind() {
	instantiation = null;
}
/**
 * Instantiates this variable with the supplied structure, or 
 * forwards the request to its instantiation if it already has 
 * one.
 * 
 * @param Structure a structure to unify with
 *
 * @return a unification. If this variable is already
 *         instantiated, the unification is the result of
 *         unifying with the input structure. Otherwise, the
 *         result is a new unification containing just this
 *         variable, instantiated to the input structure.
 */
public Unification unify (Structure s) {
	if (instantiation != null) {
		return instantiation.unify(s);
	}
	instantiation = s;
	return new Unification(this);
}
/**
 * Unifies this variable with the supplied term.
 * <p>
 * This method dispatches the unify request to either a 
 * structure or a variable. The receiver will get a signature 
 * match from this object as a Variable, not just a Term.
 *
 * @param Term a term to unify with
 *
 * @return the sum of the variables that bind to values to 
 *         make the unification work; Returns null if the 
 *         unification fails.
 */
public Unification unify(Term t) {
	return t.unify(this);
}
/**
 * Instantiates this variable with the supplied variable, or 
 * forwards the request to its instantiation if it already has
 * one.
 * 
 * @param Variable a variable to unify with
 *
 * @return the sum of the variables that bind to values to make
 *         the unification work; Returns null if the 
 *         unification fails.
 */
public Unification unify(Variable v) {
	if (this == v) {
		return new Unification();
	}	
	if (instantiation != null) {
		return instantiation.unify(v);
	}
	if (v.instantiation != null) {
		return v.instantiation.unify(this);
	}	
	instantiation = v;
	return new Unification(this);
}
/**
 * Returns a unification containing just this variable.
 *
 * @return a unification containing just this variable
 */
public Unification variables() {
	return new Unification(this);
}
}
