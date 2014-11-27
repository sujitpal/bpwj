package sjm.parse;

import java.util.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Parsers that have an Assembler ask it to work on an
 * assembly after a successful match.
 * <p>
 * By default, terminals push their matches on a assembly's 
 * stack after a successful match. 
 * <p>
 * Parsers recognize text, and assemblers provide any 
 * sort of work that should occur after this recognition. 
 * This work usually has to do with the state of the assembly,
 * which is why assemblies have a stack and a target. 
 * Essentially, parsers trade advancement on a assembly 
 * for work on the assembly's stack or target.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public abstract class Assembler {
/**
 * Returns a vector of the elements on an assembly's stack 
 * that appear before a specified fence.
 * <p>
 * Sometimes a parser will recognize a list from within 
 * a pair of parentheses or brackets. The parser can mark 
 * the beginning of the list with a fence, and then retrieve 
 * all the items that come after the fence with this method.
 *
 * @param   assembly   a assembly whose stack should contain 
 * some number of items above a fence marker
 * 
 * @param   object   the fence, a marker of where to stop 
 *                   popping the stack
 * 
 * @return   Vector   the elements above the specified fence
 * 
 */
public static Vector elementsAbove(Assembly a, Object fence) {
	Vector items = new Vector();
	 
	while (!a.stackIsEmpty()) {
		Object top = a.pop();
		if (top.equals(fence)) {
			break;
		}
		items.addElement(top);
	}
	return items;
}
/**
 * This is the one method all subclasses must implement. It 
 * specifies what to do when a parser successfully 
 * matches against a assembly.
 *
 * @param   Assembly   the assembly to work on
 */
public abstract void workOn(Assembly a);
}
