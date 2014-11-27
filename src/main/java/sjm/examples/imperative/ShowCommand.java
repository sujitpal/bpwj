package sjm.examples.imperative;

import sjm.engine.*;
import sjm.imperative.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows a simple composition of commands from
 * <code>sjm.imperative</code>.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowCommand {
/**
 * Show a simple composition of commands from <code>
 * sjm.imperative</code>.
 */
public static void main(String[] args) {
	Fact go          = new Fact("go!");
	PrintlnCommand p = new PrintlnCommand(go);
	
	Variable i       = new Variable("i");
	ForCommand f     = new ForCommand(i, 1, 5, p);
	
	f.execute(); 
}
}
