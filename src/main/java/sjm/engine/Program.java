package sjm.engine;

import java.util.*;
/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * A Program is a collection of rules and facts that together
 * form a logical model.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */

public class Program implements AxiomSource {
	protected Vector axioms = new Vector();
/**
 * Create a new program with no axioms.
 */
public Program() {
}
/**
 * Create a new program with the given axioms.
 */
public Program(Axiom[] axioms) {
	for (int i = 0; i < axioms.length; i++) {
		addAxiom(axioms[i]);
	}
}
/**
 * Adds an axiom to this program.
 *
 * @param Axiom the axiom to add.
 */
public void addAxiom(Axiom a) {
	axioms.addElement(a);
}
/**
 * Appends all the axioms of another source to this one.
 *
 * @param   program   the source of the new axioms
 */
public void append(AxiomSource as) {
	AxiomEnumeration e = as.axioms();
	while (e.hasMoreAxioms()) {
		addAxiom(e.nextAxiom());
	}	
}
/**
 * Returns an enumeration of the axioms in this program.
 * 
 * @return an enumeration of the axioms in this program.
 */
public AxiomEnumeration axioms() {
	return new ProgramEnumerator(this);
}
/**
 * Returns an enumeration of the axioms in this program.
 * 
 * @return an enumeration of the axioms in this program.
 */
public AxiomEnumeration axioms(Structure ignored) {
	return axioms();
}
/**
 * Returns a string representation of this program. 
 *
 * @return a string representation of this program.
 */
public String toString() {
	StringBuffer buf = new StringBuffer();
	boolean haveShownALine = false;
	Enumeration e = axioms.elements();
	while (e.hasMoreElements()) {
		if (haveShownALine) {
			buf.append("\n");
		}
		buf.append(e.nextElement().toString());
		buf.append(";");
		haveShownALine = true;
	}	
	return buf.toString();
}
}
