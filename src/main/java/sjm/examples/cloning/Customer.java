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
 * This class has a properly functioning, public clone() 
 * method.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Customer implements Cloneable {
	protected String name;
	protected int IQ;
/**
 * Construct a customer.
 */
public Customer(String name, int IQ) {
	this.name = name;
	this.IQ = IQ;
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
 * Check this customer's IQ.
 *
 * @return int
 */
public int getIQ() {
	return IQ;
}
/**
 * Set this customer's IQ.
 *
 * @param IQ int
 */
public void setIQ(int IQ) {
	this.IQ = IQ;
}
}
