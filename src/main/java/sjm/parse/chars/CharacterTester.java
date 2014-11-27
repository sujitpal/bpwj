package sjm.parse.chars;

import sjm.parse.*;
public class CharacterTester extends ParserTester {
/**
 * 
 */
public CharacterTester(Parser p) {
	super(p);
}
/**
 * assembly method comment.
 */
protected Assembly assembly(String s) {
	return new CharacterAssembly(s);
}
/**
 * 
 * @return java.lang.String
 */
protected String separator() {
	return "";
}
}
