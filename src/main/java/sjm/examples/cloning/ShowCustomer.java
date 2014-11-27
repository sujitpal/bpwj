package sjm.examples.cloning;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show how to clone a customer.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowCustomer {
/**
 * Just a little demo.
 */
public static void main(String args[]) {
	Customer al = new Customer("Albert", 180);
	Customer al2 = (Customer) al.clone();
}
}
