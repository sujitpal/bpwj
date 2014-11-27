package sjm.examples.sling;

//import com.sun.java.swing.*;
import javax.swing.*;
import java.util.*;
import java.awt.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * A <code>SlingPlanel</code> draws a <code>
 * RenderableCollection</code>.
 * 
 * @author Steven J. Metsker
 * 
 * @version 1.0 
 */
public class SlingPanel extends JPanel {
	protected RenderableCollection renderables;
	protected Point lowerLeft;
	protected Point upperRight;
	protected int[] pointsX = new int[0];
	protected int[] pointsY = new int[0];
/*
 * We will plot in the largest possible rectangle that has
 * the same ratio of width to height as ratio the plot
 * requires. This method determins the pixel numbers of
 * the lower left and upper right corners we will plot to.
 */
protected void calculateCorners(double aspectRatio) {
	double w = this.getWidth();
	double h = this.getHeight();

	/*
	 * The constraints are:
	 *     thickness/tallness = aspectRatio of the plot;
	 *     thickness <= the width of this component;
	 *     tallness <= the height of this component.
	 */ 
	
	double thickness = w;
	double tallness = thickness / aspectRatio;
	if (tallness > h) {
		tallness = h;
		thickness = tallness * aspectRatio;
	}

	/*
	 * Note that the coordinate system goes from 0 to w-1.
	 * Thus, the middle of the plot is at (w-1)/2.
	 */
	lowerLeft = new Point(
		(w - 1) / 2 - (thickness - 1) / 2, 
		(h - 1) / 2 + (tallness - 1) / 2);
		
	upperRight = new Point(
		(w - 1) / 2 + (thickness - 1) / 2, 
		(h - 1) / 2 - (tallness - 1) / 2);
}
/*
 * Ensure there is enough space to print all these points.
 */
protected void checkBuf(int nLine) {
	int nPoint = nLine + 1;
	if (nPoint > pointsX.length) {
		pointsX = new int[nPoint];
		pointsY = new int[nPoint];
	}
}
/*
 * Display one scaled function. The scale translates the
 * function's points to pixel values.
 */
protected void drawFunction(Graphics g, Scale s, int nLine) {
	checkBuf(nLine);
	int nPoint = nLine + 1;
	for (int i = 0; i < nPoint; i++) {
		double t = ((double) i) / (nLine);
		Point p = s.f(t);
		pointsX[i] = (int) Math.round(p.x);
		pointsY[i] = (int) Math.round(p.y);
	}
	g.drawPolyline(pointsX, pointsY, nPoint);
}
/**
 * Display the functions set by <code>setPlot()</code>.
 *
 * @param g the Graphics context in which the painting occurs
 */
public void paintComponent(Graphics g) {
	super.paintComponent(g); // paint the background
	if (renderables == null || renderables.isEmpty()) {
		return;
	}
	Extrema ex = renderables.getExtrema();
	calculateCorners(ex.aspectRatio());
	g.setColor(Color.black);
	Enumeration e = renderables.elements();
	while (e.hasMoreElements()) {
		Renderable ren = (Renderable) e.nextElement();
		SlingFunction f = ren.function;
		Scale s = new Scale(
			ex.min, f, ex.max, lowerLeft, upperRight);
		drawFunction(g, s, ren.nLine());
	}
}
/**
 * Set the renderables this component will display, and set
 * the number of lines to use for the display of each
 * function.
 *
 * @param   RenderableCollection  the renderable collection 
 *                                to plot
 */
public void setPlot(RenderableCollection renderables) {
	this.renderables = renderables;
	repaint();
}
}
