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
 * This assembler pops a comparison term, an operator, and
 * another comparison term. It builds the comparison and
 * passes the comparison to the query builder that this
 * assembler expects to find in the assembly's target.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ComparisonAssembler extends Assembler {
/**
 * Pops a comparison term, an operator, and another 
 * comparison term. Builds the comparison and passes the 
 * comparison to the query builder that this assembler 
 * expects to find in the assembly's target.
 */
public void workOn(Assembly a) {
	ComparisonTerm second = (ComparisonTerm) a.pop();
	Token t = (Token) a.pop();
	ComparisonTerm first = (ComparisonTerm) a.pop();
	QueryBuilder b = (QueryBuilder) a.getTarget();
	b.addComparison(new Comparison(t.sval(), first, second));
}
}
