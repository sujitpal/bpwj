package sjm.examples.mechanics;

import java.util.*;
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
 * This class uses a <code>VerboseRepetition</code> to show
 * the progress a repetition makes during matching.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowVacation {
/**
 * Using a <code>VerboseRepetition</code>, show the progress 
 * a repetition makes during matching.
 */
public static void main(String args[]) {

	Parser prepare = new Alternation()
		.add(new Literal("plan").discard())
		.add(new Literal("shop").discard())
		.add(new Literal("pack").discard());
	
	Parser enjoy = new Alternation()
		.add(new Literal("swim").discard())
		.add(new Literal("hike").discard())
		.add(new Literal("relax").discard());
	
	Parser vacation = new Sequence()
		.add(new VerboseRepetition(prepare))
		.add(new VerboseRepetition(enjoy));

	Vector v = new Vector();
	v.addElement(new TokenAssembly("plan pack hike relax"));

	vacation.match(v);
}
}
