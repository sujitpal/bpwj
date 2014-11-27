package sjm.examples.pretty;

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
 * Push a <code>TerminalNode</code> that contains the word
 * "empty" on the assembly's stack.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PrettyEmptyAssembler extends Assembler {
/**
 * Push a <code>TerminalNode</code> that contains the word
 * "empty" on the assembly's stack.
 *
 * @param   Assembly   the assembly to work on
 */
public void workOn(Assembly a) {
	a.push(new TerminalNode("empty"));
}
}
