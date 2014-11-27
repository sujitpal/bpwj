package sjm.examples.sling;

//import com.sun.java.swing.*;
import javax.swing.*;
import java.util.*;
import sjm.engine.*;
import sjm.utensil.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 */

/** 
 * This class provides a target for a <code>SlingParser
 * </code>. The target holds a scope for variables, two 
 * sliders, and the <code>RenderableCollection</code> object 
 * which the user's commands will fill with renderable
 * functions.
 *
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class SlingTarget implements PubliclyCloneable {
	protected static int DEFAULT_NLINE = 100;
	protected Scope scope = new Scope();
	protected JSlider s1;
	protected JSlider s2; 
	protected RenderableCollection renderables = 
		new RenderableCollection();
/**
 * Construct a target, given two sliders.
 */
public SlingTarget(JSlider s1, JSlider s2) {
	this.s1 = s1;
	this.s2 = s2;
}
/**
 * Return this object. The sliders do not change, there is
 * not enough ambiguity in the language for the variables
 * in a scope to change, and the sling plot collection is
 * empty until parsing completes.
 *
 * @return this object
 */
public Object clone() {
	return this;
}
/**
 * Return the renderables collection which is the 
 * destination for the user's renderable functions.
 *
 * @return the renderables collection which is the 
 *         destination for the user's renderable functions
 */
public RenderableCollection getRenderables() {
	return renderables;
}
/**
 * Return true, if the given variable name exists in this
 * target's scope.
 */
public boolean isDefined(String s) {
	return scope.isDefined(s);
}
/**
 * Return a variable of the given name.
 *
 * @return a variable of the given name
 *
 * @param String the name to look up
 */
public Variable lookup(String name) {
	return scope.lookup(name);
}
/**
 * Return the special variable "nLine", which establishes
 * the number of lines to use in rendering a function.
 *
 * @return the special variable "nLine", which establishes
 *         the number of lines to use in rendering a 
 *         function
 */
public Variable nLine() {
	Variable v;
	if (isDefined("nLine")) {
		v = lookup("nLine");
	} else {
		v = lookup("nLine");
		v.setValue(new Point(0, DEFAULT_NLINE));
	}
	return v;
}
/**
 * Return the first slider.
 * 
 * @return the first slider
 */
public JSlider s1() {
	return s1;
}
/**
 * Return the second slider.
 * 
 * @return the second slider
 */
public JSlider s2() {
	return s2;
}
}
