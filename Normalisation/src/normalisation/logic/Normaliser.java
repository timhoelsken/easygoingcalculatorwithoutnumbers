package normalisation.logic;

import java.util.ArrayList;
import java.util.Iterator;

import javax.naming.ConfigurationException;

import normalisation.logic.util.ListUtil;
import normalisation.objects.FunctionalDependency;
import normalisation.objects.Key;

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
    // get name of relationschema
    NodeList tmpRelationSchemaList = aNormalisationDocument.getElementsByTagName("RelationSchema");
    Element tmpRelationSchema = (Element) tmpRelationSchemaList.item(0);
    System.out.println("Normalising relation schema '" + tmpRelationSchema.getAttribute("name") + "':");

    // get columns
    NodeList tmpColumns = tmpRelationSchema.getElementsByTagName("Column");
    String tmpColumnsOut = new String();
    for (int i = 0; i < tmpColumns.getLength(); i++) {
      Node tmpColumn = tmpColumns.item(i);
      String tmpColumnName = tmpColumn.getTextContent().toUpperCase();
      columns.add(tmpColumnName);
      tmpColumnsOut += tmpColumnName;
      if (i < tmpColumns.getLength() - 1) {
        tmpColumnsOut += ", ";
      }
    }
    System.out.println("(" + tmpColumnsOut + ")");

    // get functional dependencies
    System.out.println();
    System.out.println("Functional dependencies:");
    NodeList tmpFunctionalDependencies = aNormalisationDocument.getElementsByTagName("FunctionalDependency");
    for (int i = 0; i < tmpFunctionalDependencies.getLength(); i++) {
      Element tmpFunctionalDependencyElement = (Element) tmpFunctionalDependencies.item(i);
      FunctionalDependency tmpFunctionalDependency = new FunctionalDependency();

      // get the left ones
      NodeList tmpAllFunctionallyDependentOn = tmpFunctionalDependencyElement.getElementsByTagName("FunctionallyDependentOn");
      for (int j = 0; j < tmpAllFunctionallyDependentOn.getLength(); j++) {
        Node tmpFunctionallyDependentOn = tmpAllFunctionallyDependentOn.item(j);
        String tmpDependentName = tmpFunctionallyDependentOn.getTextContent().toUpperCase();
        if (!checkIfIsColumn(tmpDependentName)) {
          throw new ConfigurationException("DependentOn '" + tmpDependentName + "' is not a column of the relation");
        }
        tmpFunctionalDependency.addDependent(tmpDependentName);
      }

      // get the right ones
      NodeList tmpAllFunctionallyAffected = tmpFunctionalDependencyElement.getElementsByTagName("FunctionallyAffected");
      for (int j = 0; j < tmpAllFunctionallyAffected.getLength(); j++) {
        Node tmpFunctionallyAffected = tmpAllFunctionallyAffected.item(j);
        String tmpAffectedName = tmpFunctionallyAffected.getTextContent().toUpperCase();
        if (!checkIfIsColumn(tmpAffectedName)) {
          throw new ConfigurationException("Affected '" + tmpAffectedName + "' is not a column of the relation");
        }
        if (!tmpFunctionalDependency.getFunctionallyDependentOn().contains(tmpAffectedName)) {
          tmpFunctionalDependency.addAffected(tmpAffectedName);
        }
      }
      tmpFunctionalDependency.out();
      functionalDependencies.add(tmpFunctionalDependency);
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

  /**
   * does the normalisation
   */
  public void normalise() {
    ArrayList<Key> tmpKeys = getKeys();
    System.out.println();
    System.out.println("Definite Keys:");
    for (Iterator<Key> i = tmpKeys.iterator(); i.hasNext();) {
      Key tmpKey = i.next();
      tmpKey.out();
    }
  }

  private ArrayList<Key> getKeys() {
    ArrayList<Key> tmpDefiniteKeys = new ArrayList<Key>();

    ArrayList<Key> possibleKeys = new ArrayList<Key>();
    ArrayList<Key> tmpCompareDependentOn = new ArrayList<Key>();
    ArrayList<Key> tmpCompareAffected = new ArrayList<Key>();
    for (Iterator<FunctionalDependency> i = functionalDependencies.iterator(); i.hasNext();) {
      FunctionalDependency tmpCompareDependency = i.next();
      ArrayList<String> tmpFunctionallyDependentOn = tmpCompareDependency.getFunctionallyDependentOn();
      ArrayList<String> tmpFunctionallyAffected = tmpCompareDependency.getFunctionallyAffected();
      if (!ListUtil.contains(possibleKeys, new Key(tmpFunctionallyDependentOn))) {
        possibleKeys.add(new Key(tmpFunctionallyDependentOn));
      }
      if (!ListUtil.contains(tmpCompareDependentOn, new Key(tmpFunctionallyDependentOn))) {
        tmpCompareDependentOn.add(new Key(tmpFunctionallyDependentOn));
      }
      if (!ListUtil.contains(tmpCompareAffected, new Key(tmpFunctionallyAffected))) {
        tmpCompareAffected.add(new Key(tmpFunctionallyAffected));
      }
    }
    getAllPossibleKeys(possibleKeys);
    System.out.println();
    System.out.println("Found possible keys:");
    for (Iterator<Key> i = possibleKeys.iterator(); i.hasNext();) {
      i.next().out();
    }
    for (Iterator<Key> i = possibleKeys.iterator(); i.hasNext();) {
      Key tmpPossibleKey = i.next();
      boolean tmpIsKey = true;
      // run through all left ones
      for (Iterator<Key> j = tmpCompareDependentOn.iterator(); j.hasNext();) {
        Key tmpCompareKey = j.next();
        // if the same we got even the key to check
        if (!tmpCompareKey.equals(tmpPossibleKey)) {
          // if the possibleKey is part of the compareKey...
          if (tmpCompareKey.contains(tmpPossibleKey)) {
            Key tmpComparePossibleKey = tmpPossibleKey.copy();

            ArrayList<String> tmpAllPossibleKeyAffected = new ArrayList<String>();
            getAllAffected(tmpPossibleKey, tmpAllPossibleKeyAffected);
            if (tmpAllPossibleKeyAffected != null) {
              for (Iterator<String> k = tmpAllPossibleKeyAffected.iterator(); k.hasNext();) {
                tmpComparePossibleKey.addKeyAttribute(k.next());
              }
              if (!tmpComparePossibleKey.containsOrEquals(tmpCompareKey)) {
                tmpIsKey = false;
              }
            }
            // if the compareKey is part of the possibleKey
          } else if (tmpPossibleKey.contains(tmpCompareKey)) {
            ArrayList<String> tmpPossibleKeyAffected = null;
            ArrayList<String> tmpCompareKeyAffected = null;
            // ... get the affected for both keys
            for (Iterator<FunctionalDependency> k = functionalDependencies.iterator(); k.hasNext();) {
              FunctionalDependency tmpDependency = k.next();
              if (ListUtil.equals(tmpDependency.getFunctionallyDependentOn(), tmpPossibleKey.getKeyAttributes())) {
                tmpPossibleKeyAffected = tmpDependency.getFunctionallyAffected();
              }
              if (ListUtil.equals(tmpDependency.getFunctionallyDependentOn(), tmpCompareKey.getKeyAttributes())) {
                tmpCompareKeyAffected = tmpDependency.getFunctionallyAffected();
              }
            }
            if ((tmpPossibleKeyAffected != null && tmpCompareKeyAffected != null && ListUtil.equals(tmpCompareKeyAffected, tmpPossibleKeyAffected))) {
              // || (tmpPossibleKeyAffected == null && !ListUtil.contains(
              // tmpFurtherPossibleKeys, tmpPossibleKey))) {
              tmpIsKey = false;
            }
          }
        }
      }
      for (Iterator<Key> j = tmpCompareAffected.iterator(); j.hasNext();) {
        Key tmpCompareKey = j.next();
        if (tmpCompareKey.containsOrEquals(tmpPossibleKey)) {
          tmpIsKey = false;
        }
      }
      if (tmpIsKey) {
        tmpDefiniteKeys.add(tmpPossibleKey);
      }
    }
    return tmpDefiniteKeys;
  }

  private void getAllAffected(Key aKey, ArrayList<String> anAffectedList) {
    ArrayList<String> tmpAllAffected = new ArrayList<String>();
    ArrayList<String> tmpOneAffected = null;
    for (Iterator<FunctionalDependency> k = functionalDependencies.iterator(); k.hasNext();) {
      FunctionalDependency tmpDependency = k.next();
      if (ListUtil.equals(tmpDependency.getFunctionallyDependentOn(), aKey.getKeyAttributes())) {
        tmpOneAffected = tmpDependency.getFunctionallyAffected();
        for (Iterator<String> j = tmpOneAffected.iterator(); j.hasNext();) {
          String tmpAffected = j.next();
          if (!anAffectedList.contains(tmpAffected)) {
            tmpAllAffected.add(tmpAffected);
          }
        }
      }
    }
    if (!tmpAllAffected.isEmpty()) {
      anAffectedList.addAll(tmpAllAffected);
      getAllAffected(new Key(tmpOneAffected), anAffectedList);
    }
  }

  private void getAllDirectlyAffected(Key aKey, ArrayList<String> anAffectedList) {
    ArrayList<String> tmpAllAffected = new ArrayList<String>();
    ArrayList<String> tmpOneAffected = null;
    for (Iterator<FunctionalDependency> k = functionalDependencies.iterator(); k.hasNext();) {
      FunctionalDependency tmpDependency = k.next();
      if (ListUtil.equals(tmpDependency.getFunctionallyDependentOn(), aKey.getKeyAttributes())) {
        tmpOneAffected = tmpDependency.getFunctionallyAffected();
        for (Iterator<String> j = tmpOneAffected.iterator(); j.hasNext();) {
          String tmpAffected = j.next();
          if (!anAffectedList.contains(tmpAffected)) {
            tmpAllAffected.add(tmpAffected);
          }
        }
      }
    }
    anAffectedList.addAll(tmpAllAffected);
  }

  /**
   * adds keys to the given list that are not an explicit part of any functional
   * dependency
   *
   * @param somePossibleKeys
   */
  private void getAllPossibleKeys(ArrayList<Key> somePossibleKeys) {
    ArrayList<Key> tmpFurtherPossibleKeys = new ArrayList<Key>();
    for (Key tmpPossibleKey : somePossibleKeys) {
      ArrayList<String> tmpOneAffected = null;
      for (FunctionalDependency tmpDependency : functionalDependencies) {
        // if found the dependency of this key...
        if (ListUtil.equals(tmpDependency.getFunctionallyDependentOn(), tmpPossibleKey.getKeyAttributes())) {
          tmpOneAffected = tmpDependency.getFunctionallyAffected();
          ArrayList<String> tmpAllDirectlyAffectedOfThisOne = new ArrayList<String>();
          // ...get all directly affected by the affected of that key
          getAllDirectlyAffected(new Key(tmpOneAffected), tmpAllDirectlyAffectedOfThisOne);
          // check if you can make a reverse pointer to the key
          if (!tmpAllDirectlyAffectedOfThisOne.isEmpty()) {
            boolean tmpCreateNewKey = false;
            Key tmpFurtherPossibleKey = tmpPossibleKey.copy();
            for (String tmpAffected : tmpAllDirectlyAffectedOfThisOne) {
              if (tmpFurtherPossibleKey.contains(tmpAffected)) {
                tmpCreateNewKey = true;
                tmpFurtherPossibleKey.removeKeyAttribute(tmpAffected);
              }
            }
            if (tmpCreateNewKey) {
              for (String tmpKeyAttribute : tmpOneAffected) {
                if (!tmpFurtherPossibleKey.contains(tmpKeyAttribute)) {
                  tmpFurtherPossibleKey.addKeyAttribute(tmpKeyAttribute);
                }
              }
              if (tmpFurtherPossibleKey.getKeyAttributes().size() != 0 && !somePossibleKeys.contains(tmpFurtherPossibleKey)) {
                tmpFurtherPossibleKeys.add(tmpFurtherPossibleKey);
              }
            }
          }
        }
      }
    }
    if (!tmpFurtherPossibleKeys.isEmpty()) {
      getAllPossibleKeys(tmpFurtherPossibleKeys);
    }
    removeInvalidPossibleKeys(tmpFurtherPossibleKeys, somePossibleKeys);
    somePossibleKeys.addAll(tmpFurtherPossibleKeys);
  }

  /**
   * @param aFurtherPossibleKeys
   * @param aSomePossibleKeys
   */
  private void removeInvalidPossibleKeys(ArrayList<Key> someFurtherPossibleKeys, ArrayList<Key> somePossibleKeys) {
    ArrayList<Key> tmpDeleteList = new ArrayList<Key>();
    for (Key tmpKey : someFurtherPossibleKeys) {
      for (Key tmpPossibleKey1 : somePossibleKeys) {
        for (Key tmpPossibleKey2 : somePossibleKeys) {
          ArrayList<String> tmpComparePseudoKeyAttributes = new ArrayList<String>();
          tmpComparePseudoKeyAttributes.addAll(tmpPossibleKey1.getKeyAttributes());
          tmpComparePseudoKeyAttributes.addAll(tmpPossibleKey2.getKeyAttributes());
          Key tmpComparePseudoKey = new Key(tmpComparePseudoKeyAttributes);
          if (tmpComparePseudoKey.equals(tmpKey)){
            tmpDeleteList.add(tmpKey);
          }
        }
      }
    }
    for (Key tmpDeleteKey : tmpDeleteList) {
      someFurtherPossibleKeys.remove(tmpDeleteKey);
    }
  }
}