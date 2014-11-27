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
 * A chip is a type of potato or corn chip that a mythical
 * company offers.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Chip {
	protected Integer chipID;
	protected String chipName;
	protected Double price;
	protected Double ounces;
	protected String oil;
/**
 * Create a chip given its ID, name, price per bag, ounces
 * per bag, and type of oil.
 */
public Chip(int chipID, String chipName, double price, 
	double ounces, String oil) {
		
	this(
		new Integer(chipID), chipName, new Double(price), 
		new Double(ounces), oil);
}
/**
 * Create a chip given its ID, name, price per bag, ounces
 * per bag, and type of oil.
 */
public Chip(Integer chipID, String chipName, Double price, 
	Double ounces, String oil) {
		
	this.chipID = chipID;
	this.chipName = chipName;
	this.price = price;
	this.ounces = ounces;
	this.oil = oil;
}
/**
 * Return the ID of this type of chip.
 *
 * @return the ID of this type of chip
 */
public Integer getChipID() {
	return chipID;
}
/**
 * Return the name of this type of chip.
 *
 * @return the name of this type of chip
 */
public String getChipName() {
	return chipName;
}
/**
 * Return the type of oil the company uses for this chip.
 *
 * @return the type of oil the company uses for this chip
 */
public String getOil() {
	return oil;
}
/**
 * Return the number of ounces in a bag of this type of chip.
 *
 * @return the number of ounces in a bag of this type of chip
 */
public Double getOunces() {
	return ounces;
}
/**
 * Return the prince of this type of chip.
 *
 * @return the price of this type of chip
 */
public Double getPrice() {
	return price;
}
/**
 * Return a textual description of this chip.
 * 
 * @return a textual description of this chip
 */
public String toString() {
	return "chip(" +
		chipID + ", " + 
		chipName + ", " + 
		price + ", " + 
		ounces + ", " + 
		oil + ")";
}
}
