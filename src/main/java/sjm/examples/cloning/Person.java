package sjm.examples.cloning;

/**
 * This type was created by Steve Metsker
 */
public class Person implements Cloneable {
	String name;
	Person spouse;
/**
 * This method was created by Steve Metsker
 * @param name java.lang.String
 */
public Person(String name) {
	this.name = name;
}
/**
 * This method was created by Steve Metsker
 * @return java.lang.Object
 */
public Object clone() {
	try {
		return super.clone();
	} catch (CloneNotSupportedException e) {
		throw new InternalError();
	}
}
/**
 * This method was created by Steve Metsker
 * @return java.lang.String
 */
public String getName() {
	return name;
}
/**
 * This method was created by Steve Metsker
 * @return sjm.examples.cloning.Person
 */
public Person getSpouse() {
	return spouse;
}
/**
 * This method was created by Steve Metsker
 * @param name java.lang.String
 */
public void setName(String name) {
	this.name = name;
}
/**
 * This method was created by Steve Metsker
 * @param spouse sjm.examples.cloning.Person
 */
public void setSpouse(Person spouse) {
	this.spouse = spouse;
}
}
