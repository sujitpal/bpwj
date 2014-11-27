package sjm.examples.coffee;

import java.util.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This helper sets a target coffee object's <code>
 * alsoOfferFrench</code> attribute to true.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class OrFrenchHelper extends Helper {
/**
 * Sets a target coffee object's <code>alsoOfferFrench
 * </code> attribute to true. The target coffee is the last
 * coffee in a Vector of coffees.
 */
public void startElement(Object target) {
	Coffee c = (Coffee) ((Vector) target).lastElement();
	c.setAlsoOfferFrench(true);
}
}
