package sjm.examples.sling;

//import com.sun.java.swing.*;
import javax.swing.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class holds a JSlider component, and returns the
 * the slider's value in its <code>f(t)</code> call.
 * <p>
 * The slider, which this class's constructor requires,
 * should vary from 0 to 100. This class divides that value
 * by 100, so that the slider will effectively vary from
 * 0 to 1. The y component of the return value of <code>f(t)
 * </code> ignores time, and depends only on the slider value.
 * Like all one-dimensional functions, however, this function
 * uses time as the x component of the value it returns.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0  
 */
public class Slider extends SlingFunction {
	protected JSlider slider;
/**
 * Constructs a function that wraps the given slider.
 */
public Slider(JSlider slider) {
	this.slider = slider;
}
/** 
 * Returns, essentially, a slider's value, which should be
 * a number between 0 and 1.
 *
 * @param   t   a number that represents how far along a plot 
 *              is, and thus tells which point to return
 *
 * @return a new point: <code>(t, slider value)</code>
 *
 * @see sjm.examples.sling.Abs
 * @see sjm.examples.sling.Abs#f(double)
 */
public Point f(double t) {
	return new Point(t, slider.getValue() / 100.0);
}
/**
 * Return a string representation of this slider.
 */
public String toString() {
	return "slider";
}
}
