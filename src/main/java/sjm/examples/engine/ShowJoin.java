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
 * Show a relational join in a coffee database.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowJoin {
/**
 * Return a small database of coffee types.
 *
 * @return a small database of coffee types.
 */
public static Program coffee() {
	Fact [] facts = new Fact[]{

		new Fact("coffee", new Object[]{
			"Launch Mi", 
			"french", 
			"kenya", 
			new Double(6.95)}),
		
		new Fact("coffee", new Object[]{
			"Simple Best", 
			"regular", 
			"columbia", 
			new Double(5.95)}),
		
		new Fact("coffee", new Object[]{
			"Revit", 
			"italian", 
			"guatemala", 
			new Double(7.95)}),
		
		new Fact("coffee", new Object[]{
			"Brimful", 
			"regular", 
			"kenya", 
			new Double(6.95)}),
		
		new Fact("coffee", new Object[]{
			"Smackin", 
			"french", 
			"columbia", 
			new Double(7.95)}) };

	
	Program p = new Program();
	for (int i = 0; i < facts.length; i++) {
		p.addAxiom(facts[i]);
	}
	return p; 
 
}
/**
 * Return a small database of coffee customers.
 *
 * @return a small database of coffee customers.
 */
public static Program customer() {
	Fact [] facts = new Fact[]{

		new Fact("customer", new Object[]{
			"Jim Johnson", new Integer(2024)}),

		new Fact("customer", new Object[]{
			"Jane Jerrod", new Integer(2077)}),

		new Fact("customer", new Object[]{
			"Jasmine Jones", new Integer(2093)})		
		};
	
	Program p = new Program();
	for (int i = 0; i < facts.length; i++) {
		p.addAxiom(facts[i]);
	}
	return p; 
 
}
/**
 * Show a relational join in a coffee database.
 */
public static void main(String[] args) {
	Program p = new Program();
	p.append(coffee());
	p.append(customer());
	p.append(order());
	
	Variable name    = new Variable("Name");
	Variable custNum = new Variable("CustNum");
	Variable type    = new Variable("Type");
	Variable pounds  = new Variable("Pounds");
	
	Structure s1 = new Structure(
		"customer", new Term[] {name, custNum});
	
	Structure s2 = new Structure(
		"order", new Term[] {custNum, type, pounds});

	// customer(Name, CustNum), order(CustNum, Type, Pounds)
	Query q = new Query(p, new Structure[] {s1, s2});
	
	while (q.canFindNextProof()) {
		System.out.println("Customer:     " + name);
		System.out.println("Type:         " + type);
		System.out.println("Pounds/Month: " + pounds);
		System.out.println();
	}
}
/**
 * Return a small database of coffee orders.
 *
 * @return a small database of coffee orders.
 */
public static Program order() {
	Fact [] facts = new Fact[]{

		new Fact("order", new Object[]{
			new Integer(2024), 
			"Simple Best", 
			new Integer(1)}),

		new Fact("order", new Object[]{
			new Integer(2077), 
			"Launch Mi", 
			new Integer(3)}),

		new Fact("order", new Object[]{
			new Integer(2077), 
			"Smackin", 
			new Integer(3)}),

		new Fact("order", new Object[]{
			new Integer(2093), 
			"Brimful", 
			new Integer(2)})
		
		};
	
	Program p = new Program();
	for (int i = 0; i < facts.length; i++) {
		p.addAxiom(facts[i]);
	}
	return p; 
 
}
}
