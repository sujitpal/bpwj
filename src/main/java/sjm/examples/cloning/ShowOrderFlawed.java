package sjm.examples.cloning;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show the flaw in OrderFlawed.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowOrderFlawed {
/**
 * Show the flaw in OrderFlaw. OrderFlaw has an inadequate
 * clone() method, which just creates a new object with the 
 * same fields. Adjusting either an original or a cloned
 * OrderFlaw object will affect both objects. 
 */
public static void main(String args[]) {
	Customer al = new Customer("Albert", 180);

	OrderFlawed orig = new OrderFlawed(al);
	OrderFlawed bogus = (OrderFlawed) orig.clone();

	bogus.getCustomer().setIQ(100);
	System.out.println(orig.getCustomer().getIQ());
}
}
