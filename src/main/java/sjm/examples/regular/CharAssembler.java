package sjm.examples.regular;

import sjm.parse.*;
import sjm.parse.chars.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pop a <code>Character</code> from the stack and push a 
 * <code>SpecificChar</code> parser in its place.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class CharAssembler extends Assembler {
/**
 * Pop a <code>Character</code> from the stack and push a 
 * <code>SpecificChar</code> interpeter in its place.
 * 
 * @param   Assembly   the assembly whose stack to use
 */
public void workOn(Assembly a) {
	a.push(new SpecificChar((Character) a.pop()));
}
}
