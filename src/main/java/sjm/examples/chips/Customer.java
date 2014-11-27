package sjm.examples.chips;

import sjm.engine.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A customer has an ID, and a last and first name.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Customer {
	protected Integer customerID;
	protected String lastName;
	protected String firstName;
/**
 * Create a customer given his or her ID, last name, and
 * first name.
 */
public Customer(int customerID, String lastName, 
	String firstName) {
		
	this(new Integer(customerID), lastName, firstName);
}
/**
 * Create a customer given his or her ID, last name, and
 * first name.
 */
public Customer(Integer customerID, String lastName, 
	String firstName) {
		
	this.customerID = customerID;
	this.lastName = lastName;
	this.firstName = firstName;
}
/**
 * Return the ID of this customer.
 *
 * @return the ID of this customer
 */
public Integer getCustomerID() {
	return customerID;
}
/**
 * Return the first name of this customer.
 *
 * @return the first name of this customer
 */
public String getFirstName() {
	return firstName;
}
/**
 * Return the last name of this customer.
 *
 * @return the last name of this customer
 */
public String getLastName() {
	return lastName;
}
/**
 * Return a textual description of this customer.
 * 
 * @return a textual description of this customer
 */
public String toString() {
	return "customer(" +
		customerID + ", " + 
		lastName + ", " + 
		firstName + ")";
}
}
