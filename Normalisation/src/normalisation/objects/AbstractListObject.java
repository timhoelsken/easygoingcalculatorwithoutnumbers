/**
 *
 */
package normalisation.objects;

import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Tobias
 *
 */
public abstract class AbstractListObject {

	/**
	 * the contained list elements
	 */
	protected ArrayList<String> attributes;

	/**
	 * adds a dependentOn
	 *
	 * @param anAttribute
	 */
	public void addAttribute(String anAttribute) {
		attributes.add(anAttribute);
	}

	/**
	 * removes a dependentOn
	 *
	 * @param anAttribute
	 */
	public void removeAttribute(String anAttribute) {
		attributes.remove(anAttribute);
	}

	/**
	 * @param anObject
	 * @return true if 'this' contains or equals the parameter object
	 */
	public boolean containsOrEquals(AbstractListObject anObject) {
		if (attributes.size() >= anObject.getAttributes().size()) {
			return doContainsAndEqualCheck(anObject);
		}
		return false;
	}

	/**
	 * @param anObject
	 * @return true if 'this' contains the parameter object, false if objects are equal
	 *         or 'this' does not contain it
	 */
	public boolean contains(AbstractListObject anObject) {
		if (attributes.size() > anObject.getAttributes().size()) {
			return doContainsAndEqualCheck(anObject);
		}
		return false;
	}

	/**
	 * @param anObject
	 * @return true if objects are equal
	 */
	public boolean equals(AbstractListObject anObject) {
		if (attributes.size() == anObject.getAttributes().size()) {
			return doContainsAndEqualCheck(anObject);
		}
		return false;
	}

	private boolean doContainsAndEqualCheck(AbstractListObject anObject) {
		for (Iterator<String> i = anObject.getAttributes().iterator(); i.hasNext();) {
			String tmpAttribute = i.next();
			if (!attributes.contains(tmpAttribute)) {
				return false;
			}
		}
		return true;
	}

	/**
	 * @return a deep copy of the calling object
	 * @throws UnknownObjectException
	 */
	public AbstractListObject copy() throws UnknownObjectException {
		AbstractListObject tmpReturnObject;
		if (this instanceof Key) {
			tmpReturnObject = new Key();
		} else if (this instanceof RelationSchema) {
			tmpReturnObject = new RelationSchema();
		} else {
			throw new UnknownObjectException("Abstract class does not known this inheritor.");
		}
		for (Iterator<String> i = attributes.iterator(); i.hasNext();) {
			String tmpAttribute = i.next();
			tmpReturnObject.addAttribute(tmpAttribute);
		}
		return tmpReturnObject;
	}

	/**
	 * @return the attributes
	 */
	public ArrayList<String> getAttributes() {
		return attributes;
	}

	/**
	 * prints the list object on the console
	 */
	public abstract void out();

	/**
	 * @param anAttribute
	 * @return true if the given attribute is attribute of this object
	 */
	public boolean contains(String anAttribute) {
		for (String tmpAttribute : attributes) {
			if (tmpAttribute.equals(anAttribute)) {
				return true;
			}
		}
		return false;
	}
}
