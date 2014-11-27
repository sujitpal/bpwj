package sjm.examples.sling;

import sjm.parse.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/** 
 * Pushes the function (t, pi).
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class PiAssembler extends Assembler {
/**
 * Push the function (t, pi).
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	a.push(new Cartesian(new T(), new Point(0, Math.PI)));
}
}
