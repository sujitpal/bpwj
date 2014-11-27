package sjm.examples.cloning;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 *  
 * This class has a flawed public clone() method.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class OrderFlawed implements Cloneable {
	// this is a demo that skips most of the fields of an 
	// order
	// ...
	// protected Date date;
	// protected java.util.Vector items;
	// ... 
	
	protected Customer customer;
/**
 * Construct a customer.
 */
public OrderFlawed(Customer customer) {
	this.customer = customer;
}
/**
 * Return a copy of this object.
 *
 * @return a copy of this object
 */
public Object clone() {
	try {
		return super.clone();
	} catch (CloneNotSupportedException e) {
		// this shouldn't happen, since we are Cloneable
		throw new InternalError();
	}
}
/**
 * Get this order's customer.
 *
 * @return Customer
 */
public Customer getCustomer() {
	return customer;
}
/**
 * Set the customer for this order.
 *
 * @param customer Customer
 */
public void setCustomer(Customer customer) {
	this.customer = customer;
}
}
