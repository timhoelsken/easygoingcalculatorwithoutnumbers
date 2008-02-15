/**
 *
 */
package normalisation.logic;

import java.util.ArrayList;

import javax.naming.ConfigurationException;

import normalisation.objects.FunctionalDependency;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Tobias
 *
 */
public class Normaliser {

  ArrayList<String> columns;
  ArrayList<FunctionalDependency> functionalDependencies;

  /**
   * default constructor
   */
  public Normaliser() {
    columns = new ArrayList<String>();
    functionalDependencies = new ArrayList<FunctionalDependency>();
  }

  /**
   * gets all information of the input XML
   *
   * @param aNormalisationDocument
   * @throws ConfigurationException
   */
  public void init(Document aNormalisationDocument) throws ConfigurationException {
    // get name of relation
    NodeList tmpRelationList = aNormalisationDocument.getElementsByTagName("Relation");
    Element tmpRelation = (Element) tmpRelationList.item(0);
    System.out.println("Normalising relation '" + tmpRelation.getAttribute("name") + "'...");

    // get columns
    NodeList tmpColumns = tmpRelation.getChildNodes();
    for (int i = 0; i < tmpColumns.getLength(); i++) {
      Node tmpColumn = tmpColumns.item(i);
      String tmpColumnName = tmpColumn.getTextContent();
      columns.add(tmpColumnName);
    }

    // get functional dependencies
    NodeList tmpFunctionalDependencies = aNormalisationDocument.getElementsByTagName("FunctionalDependency");
    for (int i = 0; i < tmpFunctionalDependencies.getLength(); i++) {
      Element tmpFunctionalDependencyElement = (Element) tmpFunctionalDependencies.item(i);
      FunctionalDependency tmpFunctionalDependency = new FunctionalDependency();

      // get the left ones
      NodeList tmpAllFunctionallyDependentOn = tmpFunctionalDependencyElement.getElementsByTagName("FunctionallyDependentOn");
      for (int j = 0; j < tmpAllFunctionallyDependentOn.getLength(); j++) {
        Node tmpFunctionallyDependentOn = tmpAllFunctionallyDependentOn.item(j);
        String tmpDependentName = tmpFunctionallyDependentOn.getTextContent();
        if (!checkIfIsColumn(tmpDependentName)) {
          throw new ConfigurationException("DependentOn '" + tmpDependentName + "' is not a column of the relation");
        }
        tmpFunctionalDependency.addDependent(tmpDependentName);
      }

      // get the right ones
      NodeList tmpAllFunctionallyAffected = tmpFunctionalDependencyElement.getElementsByTagName("FunctionallyAffected");
      for (int j = 0; j < tmpAllFunctionallyAffected.getLength(); j++) {
        Node tmpFunctionallyAffected = tmpAllFunctionallyAffected.item(j);
        String tmpAffectedName = tmpFunctionallyAffected.getTextContent();
        if (!checkIfIsColumn(tmpAffectedName)) {
          throw new ConfigurationException("Affected '" + tmpAffectedName + "' is not a column of the relation");
        }
        tmpFunctionalDependency.addAffected(tmpAffectedName);
      }
    }
  }

  private boolean checkIfIsColumn(String aColumn) {
    for (int i = 0; i < columns.size(); i++) {
      if (columns.get(i).equals(aColumn)) {
        return true;
      }
    }
    return false;
  }
}
