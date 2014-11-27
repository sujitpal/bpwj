package sjm.examples.karate;

import sjm.combinatorics.*;
/**
 * Each of four martial arts students has a different 
 * specialty. From the following clues, can you determine 
 * each student’s full name and her special skill?
 * <ol>
 * <li>Ms. Ellis (whose instructor is Mr. Caldwell), Amy, and
 *     Ms. Fowler are all martial arts students.
 * <li>Sparring isn’t the specialty of either Carla or 
 *     Dianne.
 * <li>Neither the shoot-fighting expert nor the pressure 
 *     point fighter is named Fowler.
 * <li>Children’s techniques aren’t the specialty of Dianne
 *     (whose instructor is Ms. Sherman).
 * <li>Amy, who disdains pressure point fighting, isn’t 
 *     Ms. Goodrich.
 * <li>Betti and Ms. Fowler are roommates.
 * <li>Ms. Hightower avoids sparring because of its point 
 *     scoring nature.
 * </ol>
 * 
 */
public class KaratePuzzle {
	protected Student amy = new Student("Amy");
	protected Student betti = new Student("Betti");
	protected Student carla = new Student("Carla");
	protected Student dianne = new Student("Dianne");
	protected Student[] students = 
		{amy, betti, carla, dianne};
	protected String[] lastNames = 
	    {"Ellis", "Fowler", "Goodrich", "Hightower"};
	protected String[] specialties = 
	    {"Sparring", "Shoot Fighting", 
		 "Pressure Points", "Childrens"};		


/*
 * Set the student objects' last names and specialties
 * from the provided arrays.
 */
protected void assembleStudents(
	Object[] lasts, Object[] specs) 
{
	for (int i = 0; i < students.length; i++) {
		students[i].lastName = (String) lasts[i];
		students[i].specialty = (String) specs[i];
	}
}
/**
 * @return true, if the student objects meet all the clues
 *         in the puzzle
 */
protected boolean cluesVerify() {
	return
	// Clue 1
	amy.lastName != "Ellis" && amy.lastName != "Fowler" &&
	// Clue 2
	carla.specialty != "Sparring" && 
	dianne.specialty != "Sparring" &&
	// Clue 3
	studentNamed("Fowler").specialty != "Shoot Fighting" && 
	studentNamed("Fowler").specialty != "Pressure Points" &&
	// Clue 4
	dianne.specialty != "Childrens" &&
	// Clue 5
	amy.lastName != "Goodrich" && 
	amy.specialty != "Pressure Points" &&
	// Clue 6
	betti.lastName != "Fowler" &&
	// Clue 7
	studentNamed("Hightower").specialty != "Sparring" &&
	// Clue 4, 1
	dianne.lastName != "Ellis";
}
/**
 * Solve the karate puzzle.
 *
 * @exception CombinatoricException Shouldn't happen
 */
public static void main(String[] args)
	throws CombinatoricException {
		
	new KaratePuzzle().solve();
}
/*
 * Display the student objects.
 */
protected void showStudents() {
	for (int i = 0; i < students.length; i++)
		System.out.println("\t" + students[i] + " ");
}
/**
 * Generate all permutations of last names and specialties, 
 * and check each arrangement to see if it passes all the 
 * clues that the puzzle specifies.
 *
 * @exception CombinatoricException Shouldn't happen
 */
protected void solve() throws CombinatoricException {
	Object[] lasts, specs;
	Permutations lastNamePerm, specPerm;
	lastNamePerm = new Permutations(lastNames);
	
	while (lastNamePerm.hasMoreElements()) {
		lasts = (Object[]) lastNamePerm.nextElement();
		specPerm = new Permutations(specialties);
		
		while (specPerm.hasMoreElements()) {
			specs = (Object[]) specPerm.nextElement();
			assembleStudents(lasts, specs);
			if (cluesVerify()) {
				System.out.println("Solution:");
				showStudents();
			}
		}
	}
}
/**
 * Return the Student who has the given last name
 *
 * @return Student, the Student with the given last name
 *
 * @param lastName String
 */
protected Student studentNamed(String lastName) {
	for (int i = 0; i < students.length; i++) {
		if (students[i].lastName.equals(lastName)) {
			return students[i];
		}	
	}
	throw new InternalError("Bad last name");
}
}
