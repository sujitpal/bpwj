package sjm.examples.sling;

import java.util.*;
import com.sun.java.swing.*;
import sjm.engine.*;
import sjm.utensil.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 */

/**
 * This class holds a collection of renderable Sling 
 * functions.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class RenderableCollection {
	protected Vector renderables = new Vector();
/**
 * Adds a renderable function to the collection.
 */
public void add(Renderable r) {
	renderables.addElement(r);
}
/**
 * Returns an enumeration of the renderable functions in 
 * this collection.
 */
public Enumeration elements() {
	return renderables.elements();
}
/**
 * Find the extreme points the functions will reach.
 *
 * @return the extreme points the functions will reach
 */
public Extrema getExtrema() {
	if (isEmpty()) {
		return 
			new Extrema(new Point(-1, -1), new Point(1, 1));
	}
	Point min = null, max = null;
	Enumeration e = elements();
	while (e.hasMoreElements()) {
		Renderable r = (Renderable) e.nextElement();
		Extrema ex = r.getExtrema();
		if (min == null) {
			min = ex.min;
			max = ex.max;
		} else {
			min.x = Math.min(min.x, ex.min.x);
			min.y = Math.min(min.y, ex.min.y);
			max.x = Math.max(max.x, ex.max.x);
			max.y = Math.max(max.y, ex.max.y);
		}
	}
	return new Extrema(min, max);
}
/**
 * Returns true if this collection contains no renderables.
 */
public boolean isEmpty() {
	return renderables.isEmpty();
}
}
