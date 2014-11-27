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
 * An order is a standing request from a customer for a
 * monthly supply of a number of bags of a type of chip.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Order {
	protected Customer customer;
	protected Chip chip;
	protected Integer bagsPerMonth;
/**
 * Create an order given a customer, a chip, and a number of 
 * bags to ship per month.
 */
public Order(Customer customer, Chip chip, int bagsPerMonth) {
	this(customer, chip, new Integer(bagsPerMonth));
}
/**
 * Create an order given a customer, a chip, and a number of 
 * bags to ship per month.
 */
public Order(Customer customer, Chip chip, Integer bagsPerMonth) {
		
	this.customer = customer;
	this.chip = chip;
	this.bagsPerMonth = bagsPerMonth;
}
/**
 * Return the number of bags per month to ship.
 *
 * @return the number of bags per month to ship
 */
public Integer getBagsPerMonth() {
	return bagsPerMonth;
}
/**
 * Return this order's chip type.
 *
 * @return this order's chip type
 */
public Chip getChip() {
	return chip;
}
/**
 * Return this order's customer.
 *
 * @return this order's customer
 */
public Customer getCustomer() {
	return customer;
}
/**
 * Return a textual description of this order.
 * 
 * @return a textual description of this order
 */
public String toString() {
	return "order(" +
		customer.getCustomerID() + ", " + 
		chip.getChipID() + ", " + 
		bagsPerMonth + ")";
}
}
