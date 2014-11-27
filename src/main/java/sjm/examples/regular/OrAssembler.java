package sjm.examples.regular;

import sjm.parse.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pop two parsers from the stack and push a new <code>
 * Alternation</code> of them.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
 
 public class OrAssembler extends Assembler {


/**
 * Pop two parsers from the stack and push a new
 * <code>Alternation</code> of them.
 * 
 * @param   Assembly   the assembly whose stack to use
 */
public void workOn(Assembly a) {
	Object top = a.pop();
	Alternation alt = new Alternation();
	alt.add((Parser) a.pop());
	alt.add((Parser) top);
	a.push(alt);
}
}
