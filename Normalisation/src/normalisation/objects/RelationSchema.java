package normalisation.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Tobias
 *
 */
public class RelationSchema {
  //TODO make abstract class for list objects
  private ArrayList<String> columns;

  /**
   * default constructor
   */
  public RelationSchema() {
    columns = new ArrayList<String>();
  }

  /**
   * default constructor
   *
   * @param someColumns
   */
  public RelationSchema(ArrayList<String> someColumns) {
    columns = someColumns;
  }

  /**
   * adds a dependentOn
   *
   * @param aColumn
   */
  public void addColumn(String aColumn) {
    columns.add(aColumn);
  }

  /**
   * removes a dependentOn
   *
   * @param aColumn
   */
  public void removeColumn(String aColumn) {
    columns.remove(aColumn);
  }

  /**
   * @param aRelationSchema
   * @return true if 'this' contains or equals the parameter relationSchema
   */
  public boolean containsOrEquals(RelationSchema aRelationSchema) {
    if (columns.size() >= aRelationSchema.columns.size()) {
      return doContainsAndEqualCheck(aRelationSchema);
    }
    return false;
  }

  /**
   * @param aRelationSchema
   * @return true if 'this' contains the parameter relationSchema, false if relationSchemas are equal
   *         or 'this' does not contain it
   */
  public boolean contains(RelationSchema aRelationSchema) {
    if (columns.size() > aRelationSchema.columns.size()) {
      return doContainsAndEqualCheck(aRelationSchema);
    }
    return false;
  }

  /**
   * @param aRelationSchema
   * @return true if relationSchemas are equal
   */
  public boolean equals(RelationSchema aRelationSchema) {
    if (columns.size() == aRelationSchema.columns.size()) {
      return doContainsAndEqualCheck(aRelationSchema);
    }
    return false;
  }

  private boolean doContainsAndEqualCheck(RelationSchema aRelationSchema) {
    for (Iterator<String> i = aRelationSchema.columns.iterator(); i.hasNext();) {
      String tmpRelationSchemaColumn = i.next();
      if (!columns.contains(tmpRelationSchemaColumn)) {
        return false;
      }
    }
    return true;
  }

  /**
   * @return a deep copy of the calling object
   */
  public RelationSchema copy() {
    RelationSchema tmpReturnRelationSchema = new RelationSchema();
    for (Iterator<String> i = columns.iterator(); i.hasNext();) {
      String tmpRelationSchemaColumn = i.next();
      tmpReturnRelationSchema.addColumn(tmpRelationSchemaColumn);
    }
    return tmpReturnRelationSchema;
  }

  /**
   * @return the columns
   */
  public ArrayList<String> getColumns() {
    return columns;
  }

  /**
   * prints the relationSchema on console
   */
  public void out() {
    String tmpRelationSchemaOut = new String();
    for (Iterator<String> i = columns.iterator(); i.hasNext();) {
      String tmpRelationSchemaColumn = i.next();
      tmpRelationSchemaOut += tmpRelationSchemaColumn;
      if (i.hasNext()) {
        tmpRelationSchemaOut += ", ";
      }
    }
    System.out.println("(" + tmpRelationSchemaOut + ")");
  }

  /**
   * @param aRelationSchemaColumn
   * @return true if the given column is column of this relationSchema
   */
  public boolean contains(String aRelationSchemaColumn) {
    for (String tmpRelationSchemaColumn : columns) {
      if (tmpRelationSchemaColumn.equals(aRelationSchemaColumn)) {
        return true;
      }
    }
    return false;
  }
}
