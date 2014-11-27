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
 * Replace a <code>Token</code> object on the stack with a 
 * <code>TerminalNode</code> that holds the token's value.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class PrettyTerminalAssembler extends Assembler {
/**
 * Replace a <code>Token</code> object on the stack with a 
 * <code>TerminalNode</code> that holds the token's value.
 *
 * @param   Assembly   the assembly to work on
 */
public void workOn(Assembly a) {
	Token t = (Token) a.pop();
	a.push(new TerminalNode(t.value()));
}
}
