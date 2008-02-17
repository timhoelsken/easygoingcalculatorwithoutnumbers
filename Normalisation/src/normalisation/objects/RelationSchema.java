package normalisation.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Tobias
 *
 */
public class RelationSchema extends AbstractListObject {

	/**
	 * default constructor
	 */
	public RelationSchema() {
		attributes = new ArrayList<String>();
	}

	/**
	 * constructor
	 *
	 * @param someColumns
	 */
	public RelationSchema(ArrayList<String> someColumns) {
		attributes = someColumns;
	}

	/**
	 * prints the relationSchema on console
	 */
	public void out() {
		String tmpRelationSchemaOut = new String();
		for (Iterator<String> i = attributes.iterator(); i.hasNext();) {
			String tmpRelationSchemaColumn = i.next();
			tmpRelationSchemaOut += tmpRelationSchemaColumn;
			if (i.hasNext()) {
				tmpRelationSchemaOut += ", ";
			}
		}
		System.out.println("(" + tmpRelationSchemaOut + ")");
	}
}
