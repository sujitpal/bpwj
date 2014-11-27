package sjm.examples.coffee;

import java.util.*;
import org.xml.sax.*;
import org.xml.sax.helpers.DefaultHandler;
import org.apache.xerces.parsers.SAXParser;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * Show the recognition of a list of types of coffee, 
 * reading from an XML file.
 * <p>
 * This class keeps a hashtable whose keys are element 
 * names, such as "roast", and whose values are subclasses 
 * of <code>Helper</code>, such as <code>RoastHelper</code>.
 * <p>
 * When an object of this class receives an event indicating 
 * that the parser has found a new element, it looks up the 
 * element name in the hashtable, to find the right helper 
 * for the element. For example, on seeing the "roast" 
 * element, this class sets its <code>helper</code> object
 * to be an instance of <code>RoastHelper</code>. Then, when 
 * this class receives characters from the parser, it passes 
 * the characters to the current helper.
 * <p>
 * Helpers expect a target object, and this class 
 * consistently uses a <code>Vector</code> of <code>Coffee
 * </code> objects as the target for helpers.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class ShowCoffeeXML extends DefaultHandler {
	protected Hashtable helpers;
	protected Helper helper;
	protected Vector coffees = new Vector();
/**
 * Receive notification of character data inside an element.
 * If there is a helper ready to go, ask the helper to
 * process these characters.
 *
 * @param   ch    The characters
 *
 * @param   start The start position in the character array
 *
 * @param   len   The number of characters to use from 
 *                the character array
 * 
 */
public void characters(char ch[], int start, int len) {
	if (helper != null) {
		helper.characters(
			new String(ch, start, len), coffees);
	}
}
/**
 * Receive notification of the end of an element, which
 * means that no helper should be active.
 *
 * @param   all   all arguments ignored
 */
public void endElement(
	String uri, String localName, String rawName) {
		
	helper = null;
}
/*
 * Returns the lookup table that tells which helper to
 * use for which element.
 */
protected Hashtable helpers() {
	if (helpers == null) {
		helpers = new Hashtable();
		helpers.put("coffee", new NewCoffeeHelper());
		helpers.put("name", new NameHelper());
		helpers.put("formerName", new FormerNameHelper());
		helpers.put("roast", new RoastHelper());
		helpers.put("orFrench", new OrFrenchHelper());
		helpers.put("country", new CountryHelper());
		helpers.put("price", new PriceHelper());
	}
	return helpers;
}
/**
 * Show how to recognize coffees in an XML file.
 */
public static void main(String argv[]) throws Exception {
	SAXParser parser = new SAXParser();
	ShowCoffeeXML x = new ShowCoffeeXML();
	parser.setContentHandler(x);
	parser.setErrorHandler(x);
	parser.parse("coffee.xml");
	
	Enumeration e = x.coffees.elements();
	while (e.hasMoreElements()) {
		Coffee c = (Coffee) e.nextElement();
		System.out.println(c);
	}
}
/**
 * Receive notification of the start of an element.
 *
 * <p>If the <code>helpers</code> hashtable has a key of
 * for the given element name, then inform the helper that
 * this element has appeared.</p>
 *
 * @param   uri   the uniform resource identifier, ignored
 *
 * @param   local   the local name, ignored
 *
 * @param   raw   the raw XML 1.0 name, which is the helper
 *                lookup key
 *
 * @param   attributes   the attributes attached to the 
 *                       element, ignored
 */
public void startElement(
	String uri, String local, String raw, Attributes atts) {
		
	helper = (Helper) helpers().get(raw);
	if (helper != null) {
		helper.startElement(coffees);
	}
}
}
