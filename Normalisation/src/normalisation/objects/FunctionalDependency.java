package normalisation.objects;

import java.util.ArrayList;

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
}
