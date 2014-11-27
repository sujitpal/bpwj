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
 * Pop a parser from the stack and push a new <code>
 * Repetition</code> of it.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
 
 public class StarAssembler extends Assembler {


/**
 * Pop a parser from the stack and push a new <code>
 * Repetition</code> of it.
 * 
 * @param   Assembly   the assembly whose stack to use
 */
public void workOn(Assembly a) {
	a.push(new Repetition((Parser) a.pop()));
}
}
