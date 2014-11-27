package sjm.examples.logic;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.track.*;
import sjm.examples.mechanics.LowercaseWord;
import sjm.examples.mechanics.UppercaseWord;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class provides a parser for Logikus, a logic 
 * language similar to Prolog.
 * <p>
 * The grammar this class supports is:
 * <blockquote><pre> 
 * 
 *     axiom        = structure (ruleDef | Empty);
 *     structure    = functor ('(' commaList(term) ')' | Empty);
 *     functor      = '.' | LowercaseWord | QuotedString;
 *     term         = structure | Num | list | variable;
 *     variable     = UppercaseWord | '_';
 * <br>
 *     ruleDef      = ":-" commaList(condition);
 *     condition    = structure | not | evaluation | 
 *                    comparison | list;
 * <br>
 *     not          = "not" structure ;
 * <br>
 *     evaluation   =      '#' '(' arg ',' arg ')';
 *     comparison   = operator '(' arg ',' arg ')';
 *     arg          = expression | functor;
 *     operator     = '<' | '>' | '=' | "<=" | ">=" | "!=" ;
 *     expression   = phrase ('+' phrase | '-' phrase)*;
 *     phrase       = factor ('*' factor | '/' factor)*;
 *     factor       = '(' expression ')' | Num | variable;
 * <br>
 *     list         = '[' (listContents | Empty) ']';
 *     listContents = commaList(term) listTail;
 *     listTail     = ('|' (variable | list)) | Empty;
 * <br>
 *     commaList(p) = p (',' p)*;
 * </pre></blockquote>
 * 
 * The following program and query use most of the features of 
 * the Logikus grammar:
 *
 * <blockquote><pre>
 *     // program
 *     member(X, [X | Rest]);
 *     member(X, [Y | Rest]) :- member(X, Rest);
 *     primes([2, 3, 5, 7, 11, 13]);
 *     factor(X, P, Q) :- 
 *         primes(Primes), 
 *         member(P, Primes), member(Q, Primes), =(P*Q, X); 
 * <br>
 *     // query
 *     factor(91, A, B)
 * <br>
 *     // results
 *     A = 7.0, B = 13.0
 *     A = 13.0, B = 7.0
 *     no
 * </pre></blockquote>
 *
 * The class <code>LogikusFacade</code> simplifies the 
 * construction of <code>Program</code> and <code>Query</code> 
 * objects from the text given above. A Java program can prove 
 * the query to generate the results. 
 * 
 * <p>
 * The class <code>LogikusIde</code> is an example of using the 
 * <code>Logikus</code> parser in practice. It uses 
 * <code>LogikusFacade</code> to create a <code>Query</code>, 
 * proves the query, and displays the query's variables for 
 * each proof. As in Prolog, the Logikus development 
 * environment prints "no" when no further proofs remain.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class LogikusParser {
	protected Sequence structure;
	protected Sequence expression;
	protected Sequence list;
/*
 * Return a parser that recognizes the grammar:
 * 
 *    arg = expression | functor;
 */
protected Parser arg() {
	Alternation a = new Alternation();
	a.add(expression());
	a.add(functor().setAssembler(new AtomAssembler()));
	return a;
}
/**
 * Return a parser that recognizes the grammar:
 *
 * <blockquote><pre>
 *    axiom = structure (ruleDef | Empty);
 * </pre></blockquote>
 *
 * @return a parser that recognizes an axiom
 */
public Parser axiom() {
	Sequence s = new Sequence("axiom");

	s.add(structure());
	Alternation a = new Alternation();
	a.add(ruleDef());
	a.add(new Empty());
	s.add(a);
	
	s.setAssembler(new AxiomAssembler());
	return s;
}
/*
 * Using the given parser, this method composes a new
 * parser with the grammar:
 * 
 *     commaList(p) = p (',' p)*;
 *
 * The Logikus language uses this construction several 
 * times.
 */
protected static Sequence commaList(Parser p) {
	Sequence commaP = new Track();
	commaP.add(new Symbol(',').discard());
	commaP.add(p);
	
	Sequence s = new Sequence();
	s.add(p);
	s.add(new Repetition(commaP));
	return s;
}
/**
 * Return a parser that recognizes the grammar:
 *
 * <blockquote><pre>
 *    comparison = operator '(' arg ',' arg ')';
 * </pre></blockquote>
 *
 * @return a parser that recognizes a comparison
 */
public Sequence comparison() {
	Track t = new Track("comparison");
	t.add(operator());
	t.add(new Symbol('(').discard());
	t.add(arg());
	t.add(new Symbol(',').discard());
	t.add(arg());
	t.add(new Symbol(')').discard());
	t.setAssembler(new ComparisonAssembler());
	return t;
}
/**
 * Return a parser that recognizes the grammar:
 *
 * <blockquote><pre>
 *    condition = structure | not | evaluation | comparison | 
 *                list;
 * </pre></blockquote>
 *
 * @return a parser that recognizes a condition
 */
public Alternation condition() {
	Alternation a = new Alternation("condition");
	a.add(structure());
	a.add(not());
	a.add(evaluation());
	a.add(comparison());
	a.add(list());
	return a;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    divideFactor = '/' factor;
 */
protected Parser divideFactor() {
	Sequence s = new Sequence("divideFactor");
	s.add(new Symbol('/').discard());
	s.add(factor());
	s.setAssembler(new ArithmeticAssembler('/'));
	return s;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *     evaluation = '#' '(' arg ',' arg ')';
 *
 * For example, this parser will recognize 
 * "#(X, 12321/111)", translating it to an Evaluation 
 * object. When asked to prove itself, the Evaluation 
 * object will unify its first term with the value of 
 * its second term.
 */
protected Parser evaluation() {
	
	Track t = new Track("evaluation");
	t.add(new Symbol('#').discard());
	t.add(new Symbol('(').discard());
	t.add(arg());
	t.add(new Symbol(',').discard());
	t.add(arg());
	t.add(new Symbol(')').discard());
	t.setAssembler(new EvaluationAssembler());
	return t;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    expression = phrase ('+' phrase | '-' phrase)*; 
 */
protected Parser expression() {
	/*
	 * This use of a static variable avoids the infinite 
	 * recursion inherent in the language definition.
	 */
	if (expression == null) {
		expression = new Sequence("expression");
		expression.add(phrase());
		Alternation a = new Alternation();
		a.add(plusPhrase());
		a.add(minusPhrase());
		expression.add(new Repetition(a));
	}
	return expression;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    factor = '(' expression ')' | Num | variable;
 */
protected Parser factor() {
	Alternation a = new Alternation("factor");
	Sequence s = new Sequence();
	s.add(new Symbol('(').discard());
	s.add(expression());
	s.add(new Symbol(')').discard());
	a.add(s);
	a.add(num());
	a.add(variable());
	return a;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    functor = '.' | LowercaseWord | QuotedString;
 */
protected Parser functor() {
	Alternation a = new Alternation("functor");
	a.add(new Symbol('.'));
	a.add(new LowercaseWord());
	a.add(new QuotedString());
	return a;
}
/**
 * Return a parser that recognizes the grammar:
 *
 * <blockquote><pre>
 *    list = '[' (listContents | Empty) ']';
 * </pre></blockquote>
 *
 * The class comment gives the complete grammar for lists,
 * as part of the Logikus grammar.
 *
 * @return a parser that recognizes a list
 */
public Sequence list() {
	if (list == null) {
		list = new Track("list");
		list.add(new Symbol('[')); // push this, as a fence
		
		Alternation a = new Alternation();
		a.add(listContents());
		a.add(new Empty().setAssembler(
			new ListAssembler()));

		list.add(a);
		list.add(new Symbol(']').discard());
	}
	return list;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    listContents = commaList(term) listTail;
 */
protected Parser listContents() {
 	Sequence s = commaList(term());
	s.add(listTail());
	return s;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    listTail = ('|' (variable | list)) | Empty;
 */
protected Parser listTail() {
	Alternation tail = new Alternation();
	tail.add(variable());
	tail.add(list());
	
	Track barTail = new Track("bar tail");
	barTail.add(new Symbol('|').discard());	
	barTail.add(tail);
	barTail.setAssembler(new ListWithTailAssembler());
	
	Alternation a = new Alternation();
	a.add(barTail);
	a.add(new Empty().setAssembler(new ListAssembler()));
	return a;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    minusPhrase = '-' phrase;
 */
protected Parser minusPhrase() {
	Sequence s = new Sequence("minusPhrase");
	s.add(new Symbol('-').discard());
	s.add(phrase());
	s.setAssembler(new ArithmeticAssembler('-'));
	return s;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *     not = "not" structure;
 */
protected Parser not() {
	Track t = new Track("not");
	t.add(new Literal("not").discard());
	t.add(structure());
	t.setAssembler(new NotAssembler());
	return t;
}
/*
 * Return a parser that recognizes a number and
 * stacks a corresponding atom.
 */
public Parser num() {
	Parser n = new Num();
	n.setAssembler(new AtomAssembler());
	return n;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *     operator = '<' | '>' | '=' | "<=" | ">=" | "!=" ;
 */
protected Parser operator() {
	Alternation a = new Alternation("operator");
	a.add(new Symbol('<'));
	a.add(new Symbol('>'));
	a.add(new Symbol('='));
	a.add(new Symbol("<="));
	a.add(new Symbol(">="));
	a.add(new Symbol("!="));
	return a;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    phrase = factor ('*' factor | '/' factor)*;
 */
protected Parser phrase() {
	Sequence phrase = new Sequence("phrase");
	phrase.add(factor());
	Alternation a = new Alternation();
	a.add(timesFactor());
	a.add(divideFactor());
	phrase.add(new Repetition(a));
	return phrase;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    plusPhrase = '+' phrase;
 */
protected Parser plusPhrase() {
	Sequence s = new Sequence("plusPhrase");
	s.add(new Symbol('+').discard());
	s.add(phrase());
	s.setAssembler(new ArithmeticAssembler('+'));
	return s;
}
/**
 * Return a parser that recognizes the grammar:
 *
 * <blockquote><pre>
 *    query = commaList(condition);
 * </pre></blockquote>
 *
 * @return a parser that recognizes a query
 */
public static Parser query() {
	Parser p = commaList(new LogikusParser().condition());
	p.setAssembler(new AxiomAssembler());
	return p;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    ruleDef = ":-" commaList(condition); 
 */
protected Parser ruleDef() {
	Track t = new Track("rule definition");
	t.add(new Symbol(":-").discard());
	t.add(commaList(condition()));
	return t;
}
/**
 * Return a parser that recognizes the grammar:
 *
 * <blockquote><pre>
 *    axiom = condition (ruleDefinition | empty);
 * </pre></blockquote>
 *
 * @return a parser that recognizes an axiom
 */
public static Parser start() {
	return new LogikusParser().axiom();
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    structure = functor ( '(' commaList(term) ')' | Empty);
 *
 * This definition of structure accounts for normal-looking 
 * structures that have a string as a functor. Strictly 
 * speaking, numbers and lists are also structures. The 
 * definition for <code>term</code> includes these.
 */
protected Parser structure() {
	if (structure == null) {
		structure = new Sequence("structure");
		
		Sequence s = new Sequence("s");
		structure.add(functor());
		
		Track t = new Track("list in parens");
		t.add(new Symbol('(')); // push this as a fence
		t.add(commaList(term()));
		t.add(new Symbol(')').discard());
		
		Alternation a = new Alternation();
		a.add(
			t.setAssembler(
				new StructureWithTermsAssembler()));
		a.add(
			new Empty().setAssembler(
				new AtomAssembler()));
		
		structure.add(a);
	}
	return structure;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    term = structure | Num | list | variable;
 */
protected Parser term() {
	Alternation a = new Alternation("term");
	a.add(structure());
	a.add(num());
	a.add(list());
	a.add(variable());
	return a;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    timesFactor = '*' factor;
 */
protected Parser timesFactor() {
	Sequence s = new Sequence("timesFactor");
	s.add(new Symbol('*').discard());
	s.add(factor());
	s.setAssembler(new ArithmeticAssembler('*'));
	return s;
}
/*
 * Return a parser that recognizes the grammar:
 * 
 *    variable = UppercaseWord | '_';
 *
 * The underscore represents and will translate to an 
 * anonymous variable.
 */
protected Parser variable() {
	Parser v = new UppercaseWord();
	v.setAssembler(new VariableAssembler());
	
	Parser anon = new Symbol('_').discard();
	anon.setAssembler(new AnonymousAssembler());
	
	Alternation a = new Alternation();
	a.add(v);
	a.add(anon);
	return a;
}
}
