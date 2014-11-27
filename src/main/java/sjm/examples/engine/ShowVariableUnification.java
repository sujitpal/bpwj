package sjm.examples.engine;

import sjm.engine.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show a variable unifying.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowVariableUnification {
/**
 * Show a variable unifying.
 */
public static void main(String args[]) {

	Variable x = new Variable("X");
	Structure denver = new Structure("denver");
	x.unify(denver);
	System.out.println(x);
}
}
