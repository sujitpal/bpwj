package sjm.examples.cloning;

import sjm.utensil.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**  
 * This class shows a typical clone() method.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class Course implements PubliclyCloneable {
	protected Professor professor;
	protected Textbook textbook;
/**
 * Return a copy of this object.
 *
 * @return a copy of this object
 */
public Object clone() {
	try {
		Course copy = (Course) super.clone();
		copy.setProfessor((Professor) professor.clone());
		copy.setTextbook((Textbook) textbook.clone());
		return copy;
	} catch (CloneNotSupportedException e) {
		// this shouldn't happen, since we are Cloneable
		throw new InternalError();
	}
}
/**
 * Get the professor.
 *
 * @return the professor
 */
public Professor getProfessor() {
	return professor;
}
/**
 * Get the textbook.
 *
 * @return the textbook
 */
public Textbook getTextbook() {
	return textbook;
}
/**
 * Set the professor.
 *
 * @param   Professor   professor
 */
public void setProfessor(Professor professor) {
	this.professor = professor;
}
/**
 * Set the textbook.
 *
 * @param   Textbook   textbook
 */
public void setTextbook(Textbook textbook) {
	this.textbook = textbook;
}
}
