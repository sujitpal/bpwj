package sjm.examples.robot;

/**
 * Just for demonstration.
 */
public class PickCommand extends RobotCommand {
/**
 * Return a textual description of this object.
 * 
 * @return a textual description of this object
 */
public String toString() {
	return "pick " + location;
}
}
