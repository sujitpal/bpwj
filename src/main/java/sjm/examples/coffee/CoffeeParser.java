package sjm.examples.coffee;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class provides a parser that recognizes a
 * textual description of a type of coffee, and builds a
 * corresponding coffee object.
 * <p>
 * The grammar this class supports is:
 * <blockquote><pre>
 * 
 *     coffee     = name ',' roast ',' country ',' price;
 *     name       = Word (formerName | Empty);
 *     formerName = '(' Word ')';
 *     roast      = Word (orFrench | Empty);
 *     orFrench   = '/' "french";
 *     country    = Word;
 *     price      = Num;
 * 
 * </pre></blockquote>
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */

public class CoffeeParser {
/**
 * Return a parser that will recognize the grammar:
 * 
 * <blockquote><pre>
 * 
 *     coffee  = name ',' roast ',' country ',' price;
 *
 *  </pre></blockquote>
 *
 * This parser creates a <code>Coffee</code> object
 * as an assembly's target.
 *
 * @return a parser that will recognize and build a 
 *         <code>Coffee</object> from a textual description.
 */
public Parser coffee() {
	Symbol comma = new Symbol(',');
	comma.discard();
	Sequence s = new Sequence();
	s.add(name());
	s.add(comma);
	s.add(roast());
	s.add(comma);
	s.add(country());
	s.add(comma);
	s.add(price());
	return s;
}
/*
 * Return a parser that will recognize the grammar:
 * 
 *    country = Word;
 *
 * Use a CountryAssembler to update the target coffee 
 * object.
 */
protected Parser country() {
	return new Word().setAssembler(new CountryAssembler());
}
/*
 * Return a parser that will recognize the grammar:
 * 
 *     formerName = '(' Word ')';
 *
 * Use a FormerNameAssembler to update the target coffee 
 * object.
 */
protected Parser formerName() {
	Sequence s = new Sequence(); 
	s.add(new Symbol('(').discard());
	s.add(new Word().setAssembler(
		new FormerNameAssembler()));
	s.add(new Symbol(')').discard()); 
	return s;
}
/*
 * Return a parser that will recognize the grammar:
 * 
 *     name = Word (formerName | empty);
 *
 * Use a NameAssembler to update the target coffee object 
 * with the recognized Word; formerName also uses an
 * assembler.
 */
protected Parser name() {
	Sequence s = new Sequence();
	s.add(new Word().setAssembler(new NameAssembler()));
	Alternation a = new Alternation();
	a.add(formerName());
	a.add(new Empty());
	s.add(a);
	return s;
}
/*
 * Return a parser that will recognize the sequence:
 * 
 *    orFrench = '/' "french";
 *
 * Use an AlsoFrenchAssembler to update the target coffee 
 * object.
 */
protected Parser orFrench() {
	Sequence s = new Sequence();
	s.add(new Symbol('/').discard());
	s.add(new CaselessLiteral("french").discard());
	s.setAssembler(new AlsoFrenchAssembler());
	return s;
}
/*
 * Return a parser that will recognize the sequence:
 * 
 *    price = Num;
 *
 * Use a PriceAssembler to update the target coffee object.
 */
protected Parser price() {
	return new Num().setAssembler(new PriceAssembler());
}
/*
 * Return a parser that will recognize the grammar:
 * 
 *     roast = Word (orFrench | Empty);
 *
 * Use a RoastAssembler to update the target coffee object 
 * with the recognized Word; orFrench also uses an 
 * assembler.
 */
protected Parser roast() {
	Sequence s = new Sequence();
	s.add(new Word().setAssembler(new RoastAssembler()));
	Alternation a = new Alternation();
	a.add(orFrench());
	a.add(new Empty());
	s.add(a);
	return s;
}
/**
 * Return the primary parser for this class -- coffee().
 *
 * @return the primary parser for this class -- coffee()
 */
public static Parser start() {
	return new CoffeeParser().coffee();
}
/**
 * Returns a tokenizer that allows spaces to appear inside
 * the "words" that identify a coffee's name.
 *
 * @return a tokenizer that allows spaces to appear inside
 * the "words" that identify a coffee's name.
 */
public static Tokenizer tokenizer() {
	Tokenizer t = new Tokenizer();
	t.wordState().setWordChars(' ', ' ', true);
	return t;
}
}
