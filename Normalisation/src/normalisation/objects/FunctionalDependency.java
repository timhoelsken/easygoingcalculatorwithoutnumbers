package normalisation.objects;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * @author Tobias
 *
 * functionallyDependentOn => functionallyAffected
 */
public class FunctionalDependency {

  ArrayList<String> functionallyDependentOn;

  ArrayList<String> functionallyAffected;

  /**
   * default constructor
   */
  public FunctionalDependency() {
    functionallyDependentOn = new ArrayList<String>();
    functionallyAffected = new ArrayList<String>();
  }

  /**
   * adds a dependentOn
   *
   * @param aDependent
   */
  public void addDependent (String aDependent) {
    functionallyDependentOn.add(aDependent);
  }

  /**
   * adds an affected
   *
   * @param anAffected
   */
  public void addAffected (String anAffected) {
    functionallyAffected.add(anAffected);
  }

  /**
   * @return the functionallyDependentOn
   */
  public ArrayList<String> getFunctionallyDependentOn() {
    return functionallyDependentOn;
  }

  /**
   * @return the functionallyAffected
   */
  public ArrayList<String> getFunctionallyAffected() {
    return functionallyAffected;
  }

  /**
   * prints the functional dependency on console
   */
  public void out() {
    String tmpFunctionalDependencyOut = new String();
    for (Iterator<String> i = functionallyDependentOn.iterator(); i.hasNext();) {
      String tmpFunctionallyDependentOn = i.next();
      tmpFunctionalDependencyOut += tmpFunctionallyDependentOn;
      if (i.hasNext()) {
        tmpFunctionalDependencyOut += ", ";
      }
    }
    tmpFunctionalDependencyOut += " -> ";
    for (Iterator<String> i = functionallyAffected.iterator(); i.hasNext();) {
      String tmpFunctionallyAffected = i.next();
      tmpFunctionalDependencyOut += tmpFunctionallyAffected;
      if (i.hasNext()) {
        tmpFunctionalDependencyOut += ", ";
      }
    }
    System.out.println(tmpFunctionalDependencyOut);
  }
}
