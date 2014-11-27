package sjm.examples.design;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.engine.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This assembler updates a running average.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class AverageAssembler extends Assembler {
/**
 * Increases a running average, by the length of the string
 * on the stack.
 */
public void workOn(Assembly a) {
	Token t = (Token) a.pop();
	String s = t.sval();
	RunningAverage avg = (RunningAverage) a.getTarget();
	avg.add(s.length());
}
}
