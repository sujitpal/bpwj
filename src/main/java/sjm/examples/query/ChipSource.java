package sjm.examples.query;

import java.util.*;
import sjm.engine.*;
import sjm.examples.chips.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class draws on data from the class ChipBase in 
 * sjm.examples.chips, to supplies facts about chips,
 * customers, and orders.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ChipSource implements AxiomSource {
	protected static Dictionary queries;
	public static ChipSpeller speller = new ChipSpeller();
/**
 * Returns all the data in the chip database.
 *
 * @return all the data in the chip database.
 */
public AxiomEnumeration axioms() {
	return new ProgramEnumerator(program());
}
/**
 * Returns all the data in the chip database.
 *
 * If there were thousands of chip facts, this method
 * could use the querying structure to limit the number
 * of facts it returns.
 *
 * @return all the data in the chip database.
 */
public AxiomEnumeration axioms(Structure s) {
	return axioms();
}
/**
 * Create a chip fact from a chip object.
 *
 * @return a chip fact, given a chip object.
 */
public static Fact fact(Chip c) {

	return new Fact("chip", new Object[]{
		c.getChipID(),
		c.getChipName(),
		c.getPrice(),
		c.getOunces(),
		c.getOil()});

}
/**
 * Create a customer fact from a customer object.
 *
 * @return a customer fact, given a customer object
 */
public static Fact fact(Customer c) {
	
	return new Fact("customer", new Object[] {
		c.getCustomerID(), 
		c.getLastName(), 
		c.getFirstName()});
}
/**
 * Create an order fact from an order object.
 *
 * @return an order fact, given an order object
 */
public static Fact fact(Order o) {
	
	return new Fact("order", new Object[] {
		o.getCustomer().getCustomerID(), 
		o.getChip().getChipID(), 
		o.getBagsPerMonth()});
}
/**
 * Returns all the data in the chip database.
 *
 * @return all the data in the chip database.
 */
public static Program program() {
	Program p = new Program();
	Dictionary d;
	Vector v;
	Enumeration e;

	// chips

	d = ChipBase.chip();
	e = d.elements();
	while (e.hasMoreElements()) {
		p.addAxiom(fact((Chip) e.nextElement()));
	}

	// customers

	d = ChipBase.customer();
	e = d.elements();
	while (e.hasMoreElements()) {
		p.addAxiom(fact((Customer) e.nextElement()));
	}

	// orders

	v = ChipBase.order();
	e = v.elements();
	while (e.hasMoreElements()) {
		p.addAxiom(fact((Order) e.nextElement()));
	}
	return p;
}
/*
 * Returns a dictionary of query structures for the chip
 * classes, keyed by the class name. 
 */
public static Dictionary queries() {
	if (queries == null) {
		queries = new Hashtable();
		queries.put("chip", queryChip());
		queries.put("customer", queryCustomer());
		queries.put("order", queryOrder());
	}
	return queries;
}
/*
 * Returns a query that matches chip facts. 
 */
public static Structure queryChip() {
	return new Structure("chip", new Term[]{
		new Variable("ChipID"),
		new Variable("ChipName"),
		new Variable("PricePerBag"),
		new Variable("Ounces"),
		new Variable("Oil")});
}
/*
 * Returns a query that matches chip facts. 
 */
public static Structure queryCustomer() {
	return new Structure("customer", new Term[]{
		new Variable("CustomerID"),
		new Variable("LastName"),
		new Variable("FirstName")});
}
/*
 * Returns a query that matches chip facts. 
 */
public static Structure queryOrder() {
	return new Structure("order", new Term[]{
		new Variable("CustomerID"),
		new Variable("ChipID"),
		new Variable("BagsPerMonth")});
}
/**
 * Given the name of a class, return a query that will
 * match against facts that represent objects of the class.
 * 
 * @return a query structure
 *
 * @param className the class name (from the chip object 
 *                  model) for which to return a query
 *
 * @exception UnrecognizedClassException if the class name
 *            is not part of the chip object model
 */
public static Structure queryStructure(String className) {
	if (className.equals("chip")) {
		return queryChip();
	}
	if (className.equals("customer")) {
		return queryCustomer();
	}
	if (className.equals("order")) {
		return queryOrder();
	}
	throw new UnrecognizedClassException(
		className + " is not a recognized class name");
}
}
