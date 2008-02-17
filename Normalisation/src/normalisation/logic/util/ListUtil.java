/**
 *
 */
package normalisation.logic.util;

import java.util.ArrayList;
import java.util.Iterator;

import normalisation.objects.Key;
import normalisation.objects.RelationSchema;

/**
 * @author Tobias
 *
 */
public class ListUtil {

	/**
	 * @param aKeyList
	 * @param aKey
	 * @return if key is in given list
	 */
	public static boolean contains(ArrayList<Key> aKeyList, Key aKey) {
		for (Iterator<Key> i = aKeyList.iterator(); i.hasNext();) {
			Key tmpKey = i.next();
			if (tmpKey.equals(aKey)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param aRelationSchemaList
	 * @param aRelationSchema
	 * @return if key is in given list
	 */
	public static boolean contains(ArrayList<RelationSchema> aRelationSchemaList, RelationSchema aRelationSchema) {
		for (Iterator<RelationSchema> i = aRelationSchemaList.iterator(); i.hasNext();) {
			RelationSchema tmpRelationSchema = i.next();
			if (tmpRelationSchema.equals(aRelationSchema)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * @param aList
	 * @param anotherList
	 * @return true if the list elements are equal
	 */
	public static boolean equals(ArrayList<String> aList, ArrayList<String> anotherList) {
		if (aList.size() == anotherList.size()) {
			for (Iterator<String> i = aList.iterator(); i.hasNext();) {
				String tmpString = i.next();
				if (!anotherList.contains(tmpString)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * @param aContainer
	 * @param aContained
	 * @return true if the container contains or equals the contained
	 */
	public static boolean containsOrEquals(ArrayList<String> aContainer, ArrayList<String> aContained) {
		if (aContainer.size() >= aContained.size()) {
			for (Iterator<String> i = aContained.iterator(); i.hasNext();) {
				String tmpString = i.next();
				if (!aContainer.contains(tmpString)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}

	/**
	 * @param aContainer
	 * @param aContained
	 * @return true if the container contains the contained
	 */
	public static boolean contains(ArrayList<String> aContainer, ArrayList<String> aContained) {
		if (aContainer.size() > aContained.size()) {
			for (Iterator<String> i = aContained.iterator(); i.hasNext();) {
				String tmpString = i.next();
				if (!aContainer.contains(tmpString)) {
					return false;
				}
			}
			return true;
		}
		return false;
	}
}
