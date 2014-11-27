package sjm.examples.introduction;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/** 
 * Show how to use <code>Assembler.elementsAbove()</code>.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowElementsAbove {
/**
 * Show how to use <code>Assembler.elementsAbove()</code>.
 */
public static void main(String args[]) {
	
	Parser list = new Sequence()
		.add(new Symbol('{'))
		.add(new Repetition(new Word()))
		.add(new Symbol('}').discard());
		
	list.setAssembler(new Assembler() {
		public void workOn(Assembly a) {
			Token fence = new Token('{');
			System.out.println(elementsAbove(a, fence));
		}
	});
	
	list.bestMatch(
		new TokenAssembly("{ Washington Adams Jefferson }"));
}
}
