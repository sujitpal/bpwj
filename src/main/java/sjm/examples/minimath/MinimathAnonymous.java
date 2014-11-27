package sjm.examples.minimath;

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
 *  
 * This class just gives a little demo of how to create
 * anonymous assemblers.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class MinimathAnonymous {
/**
 * Just a little demo.
 */
public static void main(String args[]) {
	Sequence e = new Sequence();

	Num n = new Num();
	n.setAssembler(new Assembler() {
		public void workOn(Assembly a) {
			Token t = (Token) a.pop();
			a.push(new Double(t.nval()));
		}
	});
	
	e.add(n);
	
	Sequence m = new Sequence();
	m.add(new Symbol('-').discard());
	m.add(n);
	m.setAssembler(new Assembler() {
		public void workOn(Assembly a) {
			Double d1 = (Double)a.pop();
			Double d2 = (Double)a.pop();
			Double d3 = new Double(d2.doubleValue() - d1.doubleValue());
			a.push(d3);
		}
	});
	
	e.add(new Repetition(m));
	
	TokenAssembly t = new TokenAssembly("25 - 16 - 9");
	Assembly out = e.completeMatch(t);
	System.out.println(out.pop());

}
}
