package sjm.examples.robot;

import sjm.parse.*;
import sjm.parse.tokens.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This class's start() method provides a parser that
 * will recognize a command for a track robot and build a
 * corresponding command object.
 * <p>
 * The grammar for the language that this class recognizes
 * is:
 * 
 * <blockquote><pre>
 *     command      = pickCommand | placeCommand | 
 *                    scanCommand;
 *     pickCommand  = "pick" "carrier" "from" location;
 *     placeCommand = "place" "carrier" "at" location;
 *     scanCommand  = "scan" location;
 *     location     = Word; 
 * </pre></blockquote>
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0 
 */
public class RobotParser {
/**
 * Returns a parser that will recognize a command for a 
 * track robot and build a corresponding command object.
 *
 * (This method returns the same value as 
 * <code>start()</code>).
 *
 * @return   a parser that will recognize a track robot 
 *           command
 */
 
 /* The parser this method returns recognizes the grammar:
  *
  *     command = pickCommand | placeCommand | scanCommand;
  */
public Parser command() {
	Alternation a = new Alternation();
	a.add(pickCommand());
	a.add(placeCommand());
	a.add(scanCommand());
	return a;
}
/*
 * Returns a parser that will recognize the grammar:
 *
 *     location = Word;
 */
protected Parser location() {
	return new Word();
}
/*
 * Returns a parser that will recognize the grammar:
 *
 *     pickCommand  = "pick" "carrier" "from" location;
 */
 protected Parser pickCommand() {
	Sequence s = new Sequence();
	s.add(new CaselessLiteral("pick"));
	s.add(new CaselessLiteral("carrier"));
	s.add(new CaselessLiteral("from"));
	s.add(location());
	s.setAssembler(new PickAssembler());
	return s;
}
/*
 * Returns a parser that will recognize the grammar:
 *
 *     placeCommand = "place" "carrier" "at" location;
 */
 protected Parser placeCommand() {
	Sequence s = new Sequence();
	s.add(new CaselessLiteral("place"));
	s.add(new CaselessLiteral("carrier"));
	s.add(new CaselessLiteral("at"));
	s.add(location());
	s.setAssembler(new PlaceAssembler());
	return s;
}
/*
 * Returns a parser that will recognize the grammar:
 *
 *     scanCommand  = "scan" location;
 */
protected Parser scanCommand() {
	Sequence s = new Sequence();
	s.add(new CaselessLiteral("scan"));
	s.add(location());
	s.setAssembler(new ScanAssembler());
	return s;
}
/**
 * Returns a parser that will recognize a command for a 
 * track robot and build a corresponding command object.
 *
 * @return   a parser that will recognize a track robot 
 *           command
 */
public static Parser start() {
	return new RobotParser().command();
}
}
