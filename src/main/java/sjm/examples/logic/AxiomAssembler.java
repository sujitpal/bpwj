package sjm.examples.logic;

import java.util.*;
import sjm.engine.*;
import sjm.parse.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Pops the structures of a rule from an assembly's stack, 
 * and constructs and pushes a rule.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AxiomAssembler extends Assembler {
/**
 * Pops all of the structures on the stack, builds a rule
 * from them, and pushes it.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	Stack s = a.getStack();
	Structure[] sa = new Structure[s.size()];
	for (int i = 0; i < s.size(); i++) {
		sa[i] = (Structure) s.elementAt(i);
	}
	s.removeAllElements();
	a.push(new Rule(sa));
}
}
