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
 * This assembler pops a term and passes it to a query
 * builder.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class SelectTermAssembler extends Assembler {
/**
 * Pop a term and pass it to a query builder.
 */
public void workOn(Assembly a) {
	QueryBuilder b = (QueryBuilder) a.getTarget();
	Term t = (Term) a.pop();
	b.addTerm(t);
}
}
