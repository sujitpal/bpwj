package sjm.examples.query;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.engine.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * Pops a class name, and informs a QueryBuilder that this
 * is a class to select from.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class ClassNameAssembler extends Assembler {
 
/**
 * Pop a class name, and inform a QueryBuilder that this
 * is a class to select from.
 */
public void workOn(Assembly a) {
	QueryBuilder b = (QueryBuilder) a.getTarget();
	Token t = (Token) a.pop();
	b.addClassName(t.sval());
}
}
