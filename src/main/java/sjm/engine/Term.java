package sjm.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * The Term interface defines the core elements of the logic 
 * engine. 
 * 
 * <p> Terms are the central objects in the logic programming 
 * data model, which is basically as follows: 
 * 
 * <ul>
 * <li>A Program is a collection of Rules.
 * <li>A Rule is a series of Structures.
 * <li>A Structure is an Object associated with a collection of 
 * Terms.
 * <li>Structures and Variables are Terms. 
 * </ul>
 * 
 * <p> The statement that "Structures and Variables are Terms" 
 * has both a loose meaning and a literal meaning. Loosely, the 
 * statement means that the contents of a structure are other 
 * structures and variables. For example, the terms of 
 * <code>plays(jim, Game)</code> are the structure 
 * <code>jim</code> and the variable <code>Game</code>. The 
 * literal meaning is that class <code>Structure</code> and 
 * class <code>Variable</code> implement the interface 
 * <code>Term</code>. 
 * 
 * <p> In addition to residing at the core of the data model, 
 * the Term interface also defines unification. Unification is 
 * a kind of matching, and is the basic step in the execution 
 * of a logic program. Roughly speaking, two structures can 
 * unify if their variables can take on values to make the 
 * structures equal. To prove itself against a program, a 
 * Structure: 
 * 
 * <ul>
 * <li>Unifies with the head of a Rule.
 * <li>Asks the Rule to prove its remaining structures.
 * </ul> 
 * 
 * This simple algorithm is the execution model of a logic 
 * engine. A structure can unify with another structure if 
 * their functors are equal, and if their terms can unify. An 
 * uninstantiated variable unifies with a term by instantiating 
 * to it. An instantiated variable can unify with another term 
 * if its instantiation can unify with the term. 
 * 
 * <p> The other methods declared by the Term interface define 
 * behavior that must exist in all terms, whether they are 
 * structures or variables. This behavior includes a method for 
 * creating provable version of a term, and a method for 
 * returning the value of term in a function.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0   
 */
public interface Term {
/**
 * Returns a copy of the term for use in a proof.
 * <p>
 * When a structure proves itself against a program, it
 * unifies with the head of a rule in the program, and then
 * asks the remaining structures in that rule to prove
 * themselves. For this to work, the rule has to provide
 * a proving copy, which has a new Scope. To provide a
 * proving copy, a rule needs proving copies of its structures,
 * and ultimately every term needs to be able to produce
 * such a copy.
 *
 * @param PosultateSource where the term can look for rules
 *
 * @param Scope variables for the provable rule copy
 *
 * @return a provable copy of this Term, that will use the 
 *         supplied axiom source and scope
 */
Term copyForProof(AxiomSource as, Scope scope);
/**
 * The value that this term should present to an evaluating
 * function.
 *
 * @return the value that this term should present to an 
 * evaluating function, such as an ArithmeticOperator.
 */
Object eval();
/**
 * Return true, if this term is a list
 *
 * @return true, if this term is a list
 */
boolean isList();
/**
 * Returns a string representation of this listTailTerm.
 *
 * That is, return a string representation of this term, 
 * given that it is the tail of a list.
 *
 * @return   a string representation of this listTailTerm
 */
String listTailString();
/**
 * Returns a collection of variables that allow this term to
 * unify with a structure.
 *
 * @param Structure the structure to unify with
 *
 * @return a collection of variables that allow this term
 *         to unify with a structure
 */
Unification unify(Structure s);
/** 
 * Returns a set of variable instantiations that allow two 
 * terms to unify. 
 * <p>
 * When a term unifies with another term, the necessary 
 * behavior can be different depending on whether the objects 
 * involved are structures or variables. To allow the right 
 * behavior to occur, the Term interface defines two 
 * <code>unify</code> methods. This allows an implementing 
 * class to use a "double dispatching" scheme to get the right 
 * behavior for unification. 
 * <p>
 * <code>Structure.unify(Term t)</code> employs double 
 * dispatching by returning <code>t.unify(this)</code>. This is 
 * a call to an implementation of <code>unify(Structure 
 * s)</code>, which is a different method from than 
 * <code>unify(Term t)</code>. The receiver thus knows it is 
 * unifying with a Structure and can act accordingly. 
 * <p> 
 * Structure implements <code>unify(Structure s)</code> to 
 * provide the unification of two structures. That is, it 
 * returns the combined unification of its terms with the other 
 * structure's terms, provided both have the same functor. 
 * <p>
 * Variable implements both its <code>unify()</code> 
 * methods the same way. If the variable is uninstantiated, is 
 * instantiates to the supplied term. If the variable is 
 * already instantiated, it returns the unification of its 
 * instantiation with the supplied term. 
 * <p>
 * Unification of an uninstantiated variable always 
 * succeeds. If a variable is instantiated, its success at 
 * unification depends on its instantiation. Unification of two 
 * structures succeeds if the structures have equal functors, the 
 * same number of terms, and if all their terms unify 
 * successfully. When unification fails, the <code>unify</code> 
 * methods return <code>null</code>. 
 * 
 * @param Term a term to unify with
 * 
 * @return Unification a collection of variable assignments
 *         that allow the unification to succeed
 */

Unification unify(Term t);
/**
 * Returns a collection of variables that allow this term to
 * unify with a variable.
 *
 * @param Variable the variable to unify with
 *
 * @return a collection of variables that allow this term to
 *         unify with a variable
 */
Unification unify(Variable v);
/**
 * Returns the variables associated with this term.
 * <p>
 * For a variable, this method returns a unification that contains 
 * just the variable itself. For a structure, this method returns a 
 * collection of the variables from each of its terms. For example, 
 * the variables in
 * <blockquote><pre>
 *     address(street(Street), city(City), state(State))
 * </pre></blockquote>
 * are <code>Street, City, State</code>.
 * 
 * @return   the variables associated with this term
 */
Unification variables();
}
