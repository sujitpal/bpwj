package sjm.examples.query;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This interface defines the role of a speller, that returns
 * the proper spelling of a class or variable name, given
 * any spelling. For example, a speller might convert
 * CUSTOMERID and customerId to CustomerId. A speller can
 * set any policy regarding proper spelling.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public interface Speller {
/**
 * Return the proper spelling of a class name.
 */
String getClassName(String s);
/**
 * Return the proper spelling of a variable name. 
 */
String getVariableName(String s);
}
