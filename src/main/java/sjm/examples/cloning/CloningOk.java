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
 * This class will compile and run, since the class 
 * implements Cloneable.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class CloningOk implements Cloneable {
/**
 * Just a demo, this will compile and run fine.
 */
public static void main(String args[]) {
	
	CloningOk co = new CloningOk();
	try {
		CloningOk co2 = (CloningOk) co.clone();
	} catch (CloneNotSupportedException e) {
		// this shouldn't happen, since we are Cloneable
		throw new InternalError();
	}
}
}
