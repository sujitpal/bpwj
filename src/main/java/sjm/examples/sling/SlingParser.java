package sjm.examples.sling;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.track.*;
import sjm.examples.reserved.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * 
 * This class provides a parser for Sling, an imperative
 * language that plots the path of a sling stone.
 *
 * <p>
 * The grammar this class supports is:
 * <blockquote><pre>
 * 
 * statements    = statement statement*;
 * statement     = assignment | forStatement | plotStatement;
 * assignment    = variable '=' expression ';' ; 
 * plotStatement = "plot" expression ';';
 * forStatement  = 
 *     "for" '(' variable ',' expression ',' expression  ')' 
 *     '{' statements '}';
 * <br>
 * variable   = Word;
 * <br>
 * expression       = term (plusTerm | minusTerm)*;
 * plusTerm         = '+' term;
 * minusTerm        = '-' term;
 * term             = element (timesElement | divideElement |
 *                             remainderElement)*;
 * timesElement     = '*' element;
 * divideElement    = '/' element;
 * remainderElement = '%' element;
 * element          = '(' expression ')' | baseElement | 
 *                    negative;
 * <br>
 * negative    = '-' baseElement; 
 * <br>
 * baseElement = 
 *     Num | "pi" | "random" | "s1" | "s2" | "t" | variable | 
 *     oneArg("abs")    | oneArg("ceil")       | 
 *     oneArg("cos")    | oneArg("floor")      | 
 *     oneArg("sin")    | oneArg("tan")        |
 *     twoArgs("polar") | twoArgs("cartesian") | 
 *     twoArgs("scale") | twoArgs("sling");
 * <br>
 * oneArg(i)  = i '(' expression ')';
 * twoArgs(i) = i '(' expression ',' expression ')';
 * 
 * </pre></blockquote>
 * 
 * The following program describes about 10,000 interesting
 * plots:
 *
 * <blockquote><pre>
 *     plot sling(1, 1) + sling(s1, 100*s2);
 * </pre></blockquote>
 *
 * <p>
 * The class <code>SlingIde</code> provides an interactive
 * development environment for Sling.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class SlingParser {
	protected Sequence expression;
	protected Alternation statement;
	protected Alternation baseElement;
	protected WordOrReservedState wors;
	protected Tokenizer tokenizer;
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     assignment = variable '=' expression ';' ; 
 */
protected Parser assignment() {
	Track t = new Track("assignment");
	t.add(variable());
	t.add(new Symbol('=').discard());
	t.add(expression());
	t.add(new Symbol(';').discard());
	t.setAssembler(new AssignmentAssembler());
	return t;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     baseElement = 
 *        Num | "pi" | "random" | "s1" | "s2" | "t" | variable |
 *       ("abs" |"ceil" |"cos" | "floor" |"sin" |"tan") oneArg |
 *       ("polar" | "cartesian" | "scale" | "sling") twoArgs;
 */
protected Parser baseElement() {
	if (baseElement == null) {
		baseElement = new Alternation("base elements");
		baseElement.add(oneArg("abs", new Abs()));
		baseElement.add(twoArg("cartesian", new Cartesian()));
		baseElement.add(oneArg("ceil", new Ceil()));
		baseElement.add(oneArg("cos", new Cos()));
		baseElement.add(oneArg("floor", new Floor()));
		baseElement.add(num());
		baseElement.add(noArgs("random", new Random()));
		baseElement.add(pi());
		baseElement.add(twoArg("polar", new Polar()));
		baseElement.add(s1());
		baseElement.add(s2());
		baseElement.add(scale());
		baseElement.add(oneArg("sin", new Sin()));
		baseElement.add(twoArg("sling", new Sling()));
		baseElement.add(noArgs("t", new T()));
		baseElement.add(oneArg("tan", new Tan()));
		baseElement.add(variable());
	}
	return baseElement;
}
/*
 * Recognize a comma.
 */
protected static Parser comma() {
	return new Symbol(',').discard();
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     divideElement  = '/' element;
 */
protected Track divideElement() {
	Track t = new Track();
	t.add(new Symbol('/').discard());
	t.add(element());
	t.setAssembler(new FunctionAssembler(new Arithmetic('/')));
	return t;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     element = '(' expression ')' | baseElement | negative;
 */
protected Parser element() {
	
	Alternation a = new Alternation("element");
	Sequence s = new Sequence();
	s.add(new Symbol('(').discard());
	s.add(expression());
	s.add(new Symbol(')').discard());
	a.add(s); 
	a.add(baseElement());
	a.add(negative());
	return a;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     expression = term (plusTerm | minusTerm)*;
 */
protected Parser expression() {

	if (expression == null) {
		expression = new Sequence("expression");
		expression.add(term());
		Alternation rest = new Alternation();
		rest.add(plusTerm());
		rest.add(minusTerm());
		expression.add(new Repetition(rest));
	}
	return expression;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     forStatement  = 
 *       "for" '(' variable ',' expression ',' expression  ')' 
 *       '{' statements '}';
 */
protected Parser forStatement() {
	Track t = new Track();
	t.add(reserve("for"));
	t.add(lParen());

	// variable
	t.add(variable());
	t.add(comma());

	// from
	t.add(expression());
	t.add(comma());

	// to
	t.add(expression());
	t.add(rParen());

	// commands
	t.add(lBrace());
	t.add(statements());
	t.add(rBrace());
	t.setAssembler(new ForAssembler());
	return t;
}
/*
 * Recognize a left brace, and leave it on the stack as
 * a fence.
 */
protected static Parser lBrace() {
	return new Symbol('{');
}
/*
 * Recognize a left parenthesis.
 */
protected static Parser lParen() {
	return new Symbol('(').discard();
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     minusTerm  = '-' term;
 */
protected Track minusTerm() {
	Track t = new Track();
	t.add(new Symbol('-').discard());
	t.add(term());
	t.setAssembler(new FunctionAssembler(new Arithmetic('-')));
	return t;
}
/*
 *  Returns a parser that will recognize the grammar:
 * 
 *      negative = '-' baseElement; 
 */
protected Parser negative() {
	Sequence s = new Sequence("negative baseElement");
	s.add(new Symbol('-').discard());
	s.add(baseElement());
	s.setAssembler(new NegativeAssembler());
	return s;
}
/*
 * Reserves the given name, and creates and returns an 
 * parser that recognizes the name. Sets the assembler of
 * the parser to be a <code>FunctionAssembler</code> for
 * the given function.
 */
protected Parser noArgs(String name, SlingFunction f) {
	Parser p = reserve(name);
	p.setAssembler(new FunctionAssembler(f));
	return p;
}
/*
 * Constructs and returns a parser that recognizes a
 * number and that uses a <code>NumAssembler</code>.
 */
protected Parser num() {
	return new Num().setAssembler(new NumAssembler());
}
/*
 * Return a parser that recognizes and stacks a one-
 * argument function.
 */
protected Parser oneArg(String name, SlingFunction f) {
	Track t = new Track(name);
	t.add(reserve(name));
	t.add(lParen());
	t.add(expression());
	t.add(rParen());
	t.setAssembler(new FunctionAssembler(f));
	return t;
}
/*
 * Returns a parser that recognizes the literal "pi". Sets
 * the parser's assembler to be a <code>PiAssembler</code>.
 */
protected Parser pi() {
	ReservedLiteral pi = reserve("pi");
	pi.setAssembler(new PiAssembler());
	return pi;
}
/*
 * Return a parser that recognizes the grammar:
 *
 *     plotStatement = "plot" expression ';';
 */
protected Parser plotStatement() {
	Track t = new Track();
	t.add(reserve("plot"));
	t.add(expression());
	t.add(semicolon());
	t.setAssembler(new PlotAssembler());
	return t;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     plusTerm  = '+' term;
 */
protected Track plusTerm() {
	Track t = new Track();
	t.add(new Symbol('+').discard());
	t.add(term());
	t.setAssembler(new FunctionAssembler(new Arithmetic('+')));
	return t;
}
/*
 * Recognize a right brace.
 */
protected static Parser rBrace() {
	return new Symbol('}').discard();
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     remainderElement  = '%' element;
 */
protected Track remainderElement() {
	Track t = new Track();
	t.add(new Symbol('%').discard());
	t.add(element());
	t.setAssembler(new FunctionAssembler(new Arithmetic('%')));
	return t;
}
/*
 * Mark the given word as reserved, meaning users cannot use
 * the word as a variable. Create a special literal parser
 * to recognize the word, and return this parser. 
 */
protected ReservedLiteral reserve(String s) {
	wors().addReservedWord(s);
	ReservedLiteral lit = new ReservedLiteral(s);
	lit.discard();
	return lit;
}
/*
 * Recognize a right parenthesis.
 */
protected static Parser rParen() {
	return new Symbol(')').discard();
}
/*
 * Recognize the first slider variable.
 */
protected Parser s1() { 
	Parser p = reserve("s1");
	// the index here recovers a real slider from the target
	p.setAssembler(new SliderAssembler(1)); 
	return p;

}
/*
 * Recognize the second slider variable.
 */
protected Parser s2() {
	Parser p = reserve("s2");
	p.setAssembler(new SliderAssembler(2));
	return p;
}
/*
 * Returns a parser that recognizes scale functions, and
 * sets the parser's assembler to be a <code>ScaleAssembler
 * </code>.
 */
protected Parser scale() {
	Track t = new Track("scale");
	t.add(reserve("scale"));
	t.add(lParen());
	
	t.add(expression());
	
	t.add(comma());
	t.add(expression());

	t.add(rParen());

	t.setAssembler(new ScaleAssembler()); 
	return t;
}
/*
 * Recognize a semicolon.
 */
protected static Parser semicolon() {
	return new Symbol(';').discard();
}
/*
 * Recoginze Sling <code>statements</code>.
 */
public Parser start() {
	return statements();
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     statement = assignment | forStatement | plotStatement;
 */
protected Parser statement() {
	if (statement == null) {
		statement = new Alternation("Statement");
		statement.add(assignment());
		statement.add(forStatement());
		statement.add(plotStatement());
	}
	return statement;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     statements = statement statement*;
 */
protected Parser statements() {
	Sequence s = new Sequence();
	s.add(statement());
	s.add(new Repetition(statement()));
	return s;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     term = element (timesElement | divideElement | 
 *                     remainderElement)*;
 */
protected Parser term() {
	Sequence s = new Sequence("term");
	s.add(element());
	Alternation a = new Alternation();
	a.add(timesElement());
	a.add(divideElement());
	a.add(remainderElement());
	s.add(new Repetition(a));
	return s;
}
/*
 * Returns a parser that will recognize the grammar:
 * 
 *     timesElement  = '*' element;
 */
protected Track timesElement() {
	Track t = new Track();
	t.add(new Symbol('*').discard());
	t.add(element());
	t.setAssembler(new FunctionAssembler(new Arithmetic('*')));
	return t;
}
/**
 * Creates a tokenizer that uses a <code>WordOrReservedState
 * </code> instead of a normal <code>WordState</code>.
 */
public Tokenizer tokenizer() {
	if (tokenizer == null) {
		start(); // to reserve the key words
		tokenizer = new Tokenizer();
		tokenizer.setCharacterState('a', 'z', wors());
		tokenizer.setCharacterState('A', 'Z', wors());
		tokenizer.setCharacterState(0xc0, 0xff, wors());
	}
	return tokenizer;
}
/*
 * Return a parser that recognizes and stacks a one-
 * argument function.
 */
protected Parser twoArg(String name, SlingFunction f) {
	Track t = new Track(name);
	t.add(reserve(name));
	t.add(lParen());
	t.add(expression());
	t.add(comma());
	t.add(expression());
	t.add(rParen());
	t.setAssembler(new FunctionAssembler(f));
	return t;
}
/*
 * Recognize a word as a variable.
 */
protected Parser variable() {
	return new Word().setAssembler(new VariableAssembler());
}
/*
 * Returns a WordOrReservedState object, which is a tokenizer 
 * state that differentiates reserved words from nonreserved 
 * words.
 */
protected WordOrReservedState wors() {
	if (wors == null) {
		wors = new WordOrReservedState();
	}
	return wors;
}
}
