package sjm.examples.coffee;

import sjm.utensil.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A Coffee object represents a type of coffee. Each type
 * of coffee has a name, and may have a former name. Each 
 * type also has a roast, which may be Regular, French, or
 * Italian. We offer some types of coffee in two roasts,
 * where French is an alternative to the normal roast. 
 * Finally, each type has a country of origin, and a price 
 * per pound.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */

public class Coffee implements PubliclyCloneable {
	protected String name;
	protected String formerName;
	protected String roast;
	protected boolean alsoOfferFrench;
	protected String country;
	protected double price;
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
 * Compares two objects for equality, treating nulls carefullly,
 * and relying on the first object's implementation of <code>
 * equals()</code>.
 *
 * @param   o1   one object
 *
 * @param   o2   the other
 *
 * @return  <code>true</code> if the objects are equal and
 *          <code>false</code> otherwise.
 */
public static boolean equal(Object o1, Object o2) {
	if (o1 == null || o2 == null) {
		return o1 == null && o2 == null;
	}
	return o1.equals(o2);
}
/**
 * Compares this object against the specified object. The 
 * result is <code>true</code> if and only if the argument is 
 * not <code>null</code> and is a <code>Coffee</code> object 
 * whose attributes all equal this object's attributes.
 *
 * @param   o   the object to compare with.
 *
 * @return  <code>true</code> if the objects are equal and
 *          <code>false</code> otherwise.
 */
public boolean equals(Object o) {
	if (!(o instanceof Coffee)) {
		return false;
	}
	Coffee c = (Coffee) o;
	if (!equal(name, c.name)) {
		return false;
	}
	if (!equal(formerName, c.formerName)) {
		return false;
	}
	if (!equal(roast, c.roast)) {
		return false;
	}
	if (alsoOfferFrench != c.alsoOfferFrench) {
		return false;
	}
	if (!equal(country, c.country)) {
		return false;
	}
	if (price != c.price) {
		return false;
	}
	return true;
}
/**
 * Return true, if we offer a french roast version of this
 * coffee in addition to another roast.
 *
 * @return true, if we offer a french roast version of this
 * coffee in addition to another roast
 */
public boolean getAlsoOfferFrench() {
	return alsoOfferFrench;
}
/**
 * Return the country of origin for of this type of coffee's
 * beans.
 *
 * @return the country of origin for of this type of coffee's
 *         beans
 */
public String getCountry() {
	return country;
}
/**
 * Return a former name for this type of coffee.
 *
 * @return a former name for this type of coffee
 */
public String getFormerName() {
	return name;
}
/**
 * Return the name of this type of coffee.
 *
 * @return the name of this type of coffee
 */
public String getName() {
	return name;
}
/**
 * Return the price per pound of this coffee.
 *
 * @return the price per pound of this coffee
 */
public double getPrice() {
	return price;
}
/**
 * Return the name of the roast of this coffee.
 *
 * @return the name of the roast of this coffee
 */
public String getRoast() {
	return roast;
}
/**
 * Set the truth of the notion that, in addition to some
 * other type of roast, we offer a french roast version of 
 * this coffee.
 *
 * @param   boolean   true, if we offer a french roast 
 *                    version of this coffee
 */
public void setAlsoOfferFrench(boolean alsoOfferFrench) {
	this.alsoOfferFrench = alsoOfferFrench;
}
/**
 * Set the country of origin for of this type of coffee's
 * beans.
 *
 * @param   String   the country
 */
public void setCountry(String country) {
	this.country = country;
}
/**
 * Set a former name of this coffee.
 *
 * @param   String   the name
 */
public void setFormerName(String formerName) {
	this.formerName = formerName;
}
/**
 * Set the name of this coffee.
 *
 * @param   String   the name
 */
public void setName(String name) {
	this.name = name;
}
/**
 * Set the price per pound of this coffee
 *
 * @param   double   the price
 */
public void setPrice(double price) {
	this.price = price;
}
/**
 * Set the roast of this coffee. 
 *
 * @param   String   the roast
 */
public void setRoast(String roast) {
	this.roast = roast;
}
/**
 * Return a textual description of this coffee type.
 * 
 * @return a textual description of this coffee type
 */
public String toString() {
	StringBuffer buf = new StringBuffer();
	buf.append(name);
	if (formerName != null) {
		buf.append('(');
		buf.append(formerName);
		buf.append(')');
	}
	
	buf.append(", ");
	buf.append(roast);
	if (alsoOfferFrench) {
		buf.append("/French");
	}
	
	buf.append(", ");
	buf.append(country);
	
	buf.append(", ");
	buf.append(price);
	
	return buf.toString();
}
}
