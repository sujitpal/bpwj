package sjm.examples.karate;

/**
 * KaratePuzzle uses this class as a data structure. 
 */
public class Student {
	public String firstName;
	public String lastName;
	public String specialty;
/**
 * Create a Student with the specified first name.
 *
 * @param firstName java.lang.String
 */
public Student(String firstName) {
	this.firstName = firstName;
}
/**
 * @return String, a textual description of the Student
 */
public String toString() {
	return firstName + " " + lastName + ": " + specialty;
}
}
