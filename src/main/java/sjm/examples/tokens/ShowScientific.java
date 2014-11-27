package sjm.examples.tokens;

import sjm.parse.*;
import sjm.parse.tokens.*;
import sjm.examples.arithmetic.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows how to use a tokenizer that accepts 
 * scientific notation with an arithmetic parser. 
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowScientific {
/**
 * Show how to use a tokenizer that accepts scientific 
 * notation with an arithmetic parser.
 */
public static void main(String[] args) throws Exception {
	
	Tokenizer t = new Tokenizer();
	ScientificNumberState sns = new ScientificNumberState();
	t.setCharacterState('0', '9', sns);
	t.setCharacterState('.', '.', sns);
	t.setCharacterState('-', '-', sns);
	
	t.setString("1e2 + 1e1 + 1e0 + 1e-1 + 1e-2 + 1e-3");
	
	Parser p = ArithmeticParser.start();
	Assembly a = p.bestMatch(new TokenAssembly(t));
	System.out.println(a.pop());
}
}
