package sjm.examples.coffee;

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
 * This assembler sets a target coffee object boolean that
 * indicates the type of coffee also comes in a french
 * roast.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public class AlsoFrenchAssembler extends Assembler {
/** 
 * Set a target coffee object's boolean to indicate that this 
 * type of coffee also comes in a french roast.
 *
 * @param  Assembly  the assembly to work on
 */
public void workOn(Assembly a) {
	Coffee c = (Coffee) a.getTarget();
	c.setAlsoOfferFrench(true);
}
}
