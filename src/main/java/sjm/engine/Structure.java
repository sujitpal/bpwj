package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * A Structure is a functor associated with a number of terms; 
 * a functor can be any object. A term is an object that 
 * implements the Term interface, including structures and 
 * variables. 
 * <p>
 * An example of a structure is: 
 * <blockquote><pre>
 *     starred(jamesCagney, "Yankee Doodle Dandy", Year)
 * </pre></blockquote>
 * This structure has the String <code>"starred"</code> as its 
 * functor. This structure's first term is another structure 
 * that has "jamesCagney" as its functor and no terms of its own. 
 * Similarly, the second term is a structure with the functor 
 * "Yankee Doodle Dandy" and no terms of its own. The third 
 * term is a variable, Year. 
 * <p> 
 * This particular example has two elements that favor a 
 * parser: the quotes around the film title and the 
 * capitalization of the variable. When using Structure and 
 * Variable directly, you do not need the kinds of clues a 
 * parser needs. So, Yankee Doodle Dandy is just another 
 * string, whose internal blanks are not at all confusing. 
 * Further, a variable can have any string as its name, not 
 * necessarily capitalized. 
 * <p>
 * You can create the starred example with 
 * <blockquote><pre> 
 *     Structure s = new Structure(
 *         "starred", 
 *         new Term[]{
 *             new Structure("jamesCagney"),
 *             new Structure("Yankee Doodle Dandy"),
 *             new Variable("Year")}); 
 * </pre></blockquote> 
 * To be able to prove itself against a program, a structure 
 * must appear in a Rule. Rules associate like-named variables 
 * in a "scope", which is essentially a dictionary. A rule 
 * makes an executable copy of itself by creating a new 
 * variable dictionary, and by making "consulting" copies of 
 * its structures. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Structure implements Term {
	protected Object functor;
	protected Term[] terms;
	/*
	 * the empty list singleton
	 */
	public final static EmptyList emptyList = 
		new EmptyList();
/**
 * Contructs a structure from the specified object.
 * 
 * @param Object the functor for this structure
 */
public Structure(Object functor) {
	this(functor, new Term[0]);
}
/**
 * Constructs a structure with the specified functor and terms.
 *
 * @param Object the functor of the structure
 * 
 * @param Term[] the terms of the structure, which may be 
 *               either variables or other structures
 */
public Structure(Object functor, Term[] terms) {
	this.functor = functor;
	this.terms = terms;
}
/**
 * Return the number of terms in this structure.
 *
 * @return the number of terms in this structure
 */
public int arity() {
	return terms.length;
}
/**
 * Returns <code>false</code>.
 * <p>
 * Objects of this class, the superclass of all structures,
 * should not appear in dynamic rules. When a nondynamic
 * rule creates its dynamic counterpart, it populates it
 * with provable versions of its structures. A general
 * <code>Structure</code> object will construct a <code>
 * ConsultingStructure</code> when it participates in building
 * a dynamic rule.
 * <p>
 * This particular method is almost never called. Subclasses
 * implement more interesting behavior.
 *
 * @return <code>false</code>
 */
public boolean canFindNextProof() {
	return false;
}
/**
 * Create a <code>ConsultingStructure</code> counterpart that
 * can unify with other structures.
 *
 * @param AxiomSource where to find axioms to prove
 *        against
 *
 * @param Scope the scope to use for variables in the
 *        <code>ConsultingStructure</code>
 *
 * @return a <code>ConsultingStructure</code> counterpart that
 *         can unify with other structures.
 */
public Term copyForProof(AxiomSource as, Scope scope) {
	Term[] newTerms = new Term[terms.length];
	for (int i = 0; i < terms.length; i++) {
		newTerms[i] = terms[i].copyForProof(as, scope);
	}
	return new ConsultingStructure(as, functor, newTerms);
}
/**
 * Returns true if the supplied object is an equivalent 
 * structure.
 *
 * @param   object   the object to compare
 *
 * @return   true, if the supplied object's functor equals
 *           this structure's functor, and both structures'
 *           terms are all equal
 */
public boolean equals(Object o) {
	if (!(o instanceof Structure))
		return false;
	Structure s = (Structure) o;
	if (!functorAndArityEquals(s)) {
		return false;
	}
	for (int i = 0; i < terms.length; i++) {
		if (!(terms[i].equals(s.terms[i]))) {
			return false;
		}
	}
	return true;
}
/**
 * Return this structure, if it is nonatomic, or just the
 * functor, if this is an atom.
 */
public Object eval() {
	if (terms().length > 0) {
		return this;
	}
	return functor;
}
/**
 * Returns <code>true</code> if this structure's functor and 
 * number of terms match the supplied structure.
 *
 * @param Structure the structure to compare this one against
 * 
 * @return <code>true</code> if this structure's functor and 
 *         number of terms match the supplied structure
 */
public boolean functorAndArityEquals(Structure s) {
	return arity() == s.arity() && functor.equals(s.functor);
}
/*
 * This method helps the static list factories.
 *
 * A list is a structure whose functor is "." and that has two 
 * terms. The first term of a list can be any term; the second 
 * term of a list is another list, an empty list, or a 
 * variable.
 * <p>
 * This method accepts an array of terms and a tail, which must 
 * be a list structure or a variable. This method composes and 
 * returns a two-element array.
 * <p>
 * The first element of the returned array will always be the 
 * first element of the supplied terms array. 
 * <p>
 * The second element of the returned array will be a list. 
 * This list will be a concatenation of the remainder of the 
 * given array with the supplied tail. 
 */
protected static Term[] headAndTail(
	Term[] terms, Term tail) {
		
	if (terms.length == 0) {
		throw new InternalError(
			"Cannot create a list with no head");
	}
		
	Term[] headAndTail = new Term[2];		
	headAndTail[0] = terms[0];
		
	if (terms.length == 1) {
		headAndTail[1] = tail;
	}		
	else {
		Term[] rest = new Term[terms.length - 1];
		System.arraycopy(terms, 1, rest, 0, rest.length);
		headAndTail[1] = list(rest, tail);
	}

	return headAndTail;    
}
/**
 * Return true, if this structure is a list, which means it
 * has an functor of ".", and has two terms, the second of which
 * must be a list.
 *
 * @return true   if this structure is a list
 */
public boolean isList() {
	return 
		terms.length == 2 && 
		functor.equals(".") && 
		terms[1].isList();
}
/**
 * Constructs a list that contains the supplied object, wrapped
 * as Facts.
 *
 * @param Object[] the contents of the list
 */
public static Structure list(Object[] objects) {
	return list(Fact.facts(objects));
}
/**
 * Constructs a list from the given terms.
 * <p>
 * This constructor creates a list of two terms, regardless of 
 * the number of terms supplied here. The new list's first 
 * term is the first term of the supplied array. Its second 
 * term is a list of the remaining terms.
 *
 * @param Term[] the terms of the list
 */

public static Structure list(Term[] terms) {
	return new Structure(".", headAndTail(terms, emptyList));
}
/**
 * Constructs a list that terminates with a known list, or a
 * variable.
 * <p>
 * This allows construction of a list such as:
 *
 * <blockquote><pre>
 *     Variable head = new Variable("Head");
 *     Variable tail = new Variable("Tail");       
 *     Structure ht = Structure.list(new Term[] {head}, tail);
 * </pre></blockquote>
 *
 * @param Term[] the leading terms of the list. In practice, 
 *               this array usually contains a single term.
 *
 * @param Term a list, or a variable that represents the tail 
 *             of the list
 */
public static Structure list(Term[] terms, Term tail) {
	return new Structure(".", headAndTail(terms, tail));
}
/**
 * Returns a representation of this list as the inner part of 
 * some other list. This method is used by <code>toString()
 * </code>.
 */
public String listTailString() {
	return ", " + listTermsToString();
}
/*
 * Return a textual represenation of this list's terms, with 
 * a normal representation of the first term, and with the
 * second term as the tail of a list.
 */
protected String listTermsToString() {
	String s = terms[0].toString();
	if (terms.length > 1) {
		s += terms[1].listTailString();
	}
	return s;
}
/**
 * Return the terms of this structure.
 *
 * @return the terms of this structure
 */
public Term[] terms() {
	return terms;
}
/**
 * Returns a string representation of this structure. 
 *
 * @return   a string representation of this structure
 */
public String toString() {
	if (isList()) {
		/* 
		 * Show the brackets, the head, and the tail. The 
		 * tail prints itself recursively, until the empty 
		 * list stops the recursion.
		 */
		return "[" + listTermsToString() + "]";
	}
	StringBuffer buf = new StringBuffer(functor.toString());
	if (terms.length > 0) {
		buf.append("(");
		for (int i = 0; i < terms.length; i++) {
			if (i > 0) {
				buf.append(", ");
			}
			buf.append(terms[i].toString());
		}
		buf.append(")");
	}
	return buf.toString();
}
/**
 * Unifies the terms in this structure with the terms in the
 * given structure, and returns the variable bindings that 
 * result.
 * <p>
 * If two structures have equal functors and the same number of
 * terms, they can unify if all of their terms unify. For 
 * example, the following structures can unify:
 * <blockquote><pre>
 *     address(Detail, city(City), state(State))
 *     address(mall(fayette), city(lexington), state(ky))
 * </pre></blockquote>
 * The unification of these structures is:
 * <blockquote><pre>
 *     Detail = mall(fayette),
 *     City = lexington,
 *     State = ky
 * </pre></blockquote>
 * 
 * @param Structure a structure to unify with
 *
 * @return the sum of the variables that bind to values to make
 *         the unification work. 
 */
public Unification unify(Structure s) {
	if (!functorAndArityEquals(s)) {
		return null;
	}
	Unification u = new Unification(); 
	Term others[] = s.terms();
	for (int i = 0; i < terms().length; i++) {
		Unification subUnification = terms()[i].unify(others[i]);
		if (subUnification == null) {
			u.unbind();
			return null;
		}
		u.append(subUnification);
	}
	return u;
}
/**
 * Unifies this structure with the supplied term.
 * <p>
 * This method dispatches the unify request to either a 
 * structure or a variable. The receiver will get a signature
 * match from this object as a Structure, not just a Term.
 *
 * @param Term a term to unify with
 *
 * @return the sum of the variables that bind to values to make
 *         the unification work. Returns null if the 
 *         unification fails.
 */
public Unification unify(Term t) {
	return t.unify(this);
}
/**
 * Unifies this structure with the supplied variable.
 * <p>
 * This method dispatches the unify request to the variable. 
 * Note that the variable may be instantiated to a structure 
 * that contains variables. An uninstantiated variable will 
 *  bind to this structure, but an instantiated variable will 
 * forward the unification request to its instantiation.
 *
 * @param Term a term to unify with
 *
 * @return the sum of the variables that bind to values to make
 *         the unification work. Returns null if the 
 *         unification fails.
 */
public Unification unify(Variable v) {
	return v.unify(this);
}
/**
 * Returns the variables of the terms of this structure.
 * <p>
 * Note that a structure may contain variables or other structures as 
 * terms. This method adds this structure's variables directly to the
 * returned unification. In addition, this method adds in all the 
 * variables from the structures among this structure's terms.
 * <p>
 * For example, the variables of:
 * <blockquote><pre>
 *     address(street(StreetName), city(CityName), State)
 * </pre></blockquote>
 * are StreetName, CityName, and State.
 *
 * @return unification all the variables of the terms of this 
 *         structure
 */
public Unification variables() {
	Unification u = new Unification();
	if (terms.length > 0) {
		for (int i = 0; i < terms().length; i++) {
			u.append(terms[i].variables());
		}
	}
	return u;
}
}
