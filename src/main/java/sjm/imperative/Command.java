package sjm.imperative;

import java.util.*;
/*
 * Copyright (c) 2000 Steven J. Metsker. All Rights Reserved.
 * 
 * Steve Metsker makes no representations or warranties about
 * the fitness of this software for any particular purpose, 
 * including the implied warranty of merchantability.
 */
 
/**
 * This abstract class represents a hierarchy of classes
 * that encapsulate commands. A command object is a request
 * that is dormant until a caller asks it to execute.
 * <p>
 * Subclasses typically encapsulate some primary function, 
 * and allow for parameters that tailor a command to a 
 * purpose. All subclasses must implement an <code>execute
 * </code> command, which is abstract here.
 * 
 * @author Steven J. Metsker
 *
 * @version 1.0
 */
public abstract class Command {
/**
 * Perform the request encapsulated in this command.
 */
public abstract void execute();
}
