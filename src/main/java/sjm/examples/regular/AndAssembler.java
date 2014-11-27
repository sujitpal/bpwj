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
 * Pop two Parsers from the stack and push a new <code>
 * Sequence</code> of them.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AndAssembler extends Assembler {
/**
 * Pop two parsers from the stack and push a new 
 * <code>Sequence</code> of them.
 * 
 * @param   Assembly   the assembly whose stack to use
 */
public void workOn(Assembly a) {
	Object top = a.pop();
	Sequence s = new Sequence();
	s.add((Parser) a.pop());
	s.add((Parser) top);
	a.push(s);
}
}
