package sjm.examples.tokens;

import java.io.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows a Tokenizer consuming a word.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowWord {
/**
 * Just an example.
 */
public static void main(String[] args) throws IOException {
	Tokenizer t = new Tokenizer("A65 B66");
	System.out.println(t.nextToken());
	System.out.println(t.nextToken());
}
}
