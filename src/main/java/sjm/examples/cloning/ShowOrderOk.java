package sjm.examples.cloning;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show an Ok order, to compare OrderOk with OrderFlawed.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowOrderOk {
/**
 * Show an OrderOk, which makes a proper copy of an order,
 * including copying an order's customer.
 */
public static void main(String args[]) {
	Customer al = new Customer("Albert", 180);

	OrderOk orig = new OrderOk(al);
	OrderOk copy = (OrderOk) orig.clone();

	copy.getCustomer().setIQ(100);
	System.out.println(orig.getCustomer().getIQ());
}
}
