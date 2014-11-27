package sjm.examples.engine;

/*
 * Copyright (c) 1999 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */

/**
 * This class shows a Java class counterpart to a simple 
 * structure.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class City {
	public String name;
	public int altitude;
/**
 * Constructs a city.
 *
 * @param String the city's name
 *
 * @param int the city's altitude
 */
public City(String name, int altitude) {
	this.name = name;
	this.altitude = altitude;
}
}
