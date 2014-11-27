package sjm.parse;

import java.util.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class provides a "visitor" hierarchy in support of
 * the Visitor pattern -- see the book, "Design Patterns" for
 * an explanation of this pattern.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public abstract class ParserVisitor {
/**
 * Visit an alternation.
 *
 * @param   Alternation   the parser to visit
 *
 * @param   Vector   a collection of previously visited parsers
 *
 */
public abstract void visitAlternation(
	Alternation a, Vector visited);
/**
 * Visit an empty parser.
 *
 * @param   Empty   the parser to visit
 *
 * @param   Vector   a collection of previously visited parsers
 *
 */
public abstract void visitEmpty(Empty e, Vector visited);
/**
 * Visit a repetition.
 *
 * @param   Repetition   the parser to visit
 *
 * @param   Vector   a collection of previously visited parsers
 *
 */
public abstract void visitRepetition(
	Repetition r, Vector visited);
/**
 * Visit a sequence.
 *
 * @param   Sequence   the parser to visit
 *
 * @param   Vector   a collection of previously visited parsers
 *
 */
public abstract void visitSequence(Sequence s, Vector visited);
/**
 * Visit a terminal.
 *
 * @param   Terminal   the parser to visit
 *
 * @param   Vector   a collection of previously visited parsers
 *
 */
public abstract void visitTerminal(Terminal t, Vector visited);
}
