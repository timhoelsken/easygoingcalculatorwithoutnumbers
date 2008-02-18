package normalisation.logic;

import java.rmi.activation.UnknownObjectException;
import java.util.ArrayList;
import java.util.Iterator;

import javax.activity.InvalidActivityException;
import javax.naming.ConfigurationException;

import normalisation.logic.util.ListUtil;
import normalisation.objects.FunctionalDependency;
import normalisation.objects.Key;
import normalisation.objects.RelationSchema;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * @author Tobias
 *
 */
public class Normaliser {

	private ArrayList<String> columns;
	private ArrayList<FunctionalDependency> functionalDependencies;
	private ArrayList<Key> keys;
	private ArrayList<RelationSchema> relationSchemas;
	private int normalForm;

	/**
	 * default constructor
	 */
	public Normaliser() {
		columns = new ArrayList<String>();
		functionalDependencies = new ArrayList<FunctionalDependency>();
		keys = new ArrayList<Key>();
		relationSchemas = new ArrayList<RelationSchema>();
		normalForm = 0;
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
		System.out.println("Normalising relation schema '" + tmpRelationSchema.getAttribute("name") + "' [1st Normal Form]:");

		// get columns
		NodeList tmpColumns = tmpRelationSchema.getElementsByTagName("Column");
		for (int i = 0; i < tmpColumns.getLength(); i++) {
			Node tmpColumn = tmpColumns.item(i);
			String tmpColumnName = tmpColumn.getTextContent().toUpperCase();
			columns.add(tmpColumnName);
		}
		createRelationSchema(columns);
		setNormalForm(1);
		relationSchemas.get(0).out();

		// get functional dependencies
		System.out.println();
		System.out.println("Functional dependencies:");
		NodeList tmpFunctionalDependencies = aNormalisationDocument.getElementsByTagName("FunctionalDependency");
		for (int i = 0; i < tmpFunctionalDependencies.getLength(); i++) {
			Element tmpFunctionalDependencyElement = (Element) tmpFunctionalDependencies.item(i);
			FunctionalDependency tmpFunctionalDependency = new FunctionalDependency();

			// get the left ones (functionally dependent on)
			NodeList tmpAllFunctionallyDependentOn = tmpFunctionalDependencyElement.getElementsByTagName("FunctionallyDependentOn");
			for (int j = 0; j < tmpAllFunctionallyDependentOn.getLength(); j++) {
				Node tmpFunctionallyDependentOn = tmpAllFunctionallyDependentOn.item(j);
				String tmpDependentName = tmpFunctionallyDependentOn.getTextContent().toUpperCase();
				if (!checkIfIsColumn(tmpDependentName)) {
					throw new ConfigurationException("DependentOn '" + tmpDependentName + "' is not a column of the relation");
				}
				tmpFunctionalDependency.addDependent(tmpDependentName);
			}

			// get the right ones (functionally affected)
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
	 * does the normalisation step by step
	 *
	 * @throws InvalidActivityException
	 * @throws UnknownObjectException
	 */
	public void normalise() throws InvalidActivityException, UnknownObjectException {
		getKeys();
		keysOut();
		bringTo2ndNormalForm();
		relationSchemasOut();
		bringTo3rdNormalForm();
		relationSchemasOut();
	}

	private ArrayList<Key> getKeys() throws UnknownObjectException {
		ArrayList<Key> tmpPossibleKeys = new ArrayList<Key>();
		ArrayList<Key> tmpCompareDependentOn = new ArrayList<Key>();
		ArrayList<Key> tmpCompareAffected = new ArrayList<Key>();
		for (Iterator<FunctionalDependency> i = functionalDependencies.iterator(); i.hasNext();) {
			FunctionalDependency tmpCompareDependency = i.next();
			ArrayList<String> tmpFunctionallyDependentOn = tmpCompareDependency.getFunctionallyDependentOn();
			ArrayList<String> tmpFunctionallyAffected = tmpCompareDependency.getFunctionallyAffected();
			if (!ListUtil.contains(tmpPossibleKeys, new Key(tmpFunctionallyDependentOn))) {
				tmpPossibleKeys.add(new Key(tmpFunctionallyDependentOn));
			}
			if (!ListUtil.contains(tmpCompareDependentOn, new Key(tmpFunctionallyDependentOn))) {
				tmpCompareDependentOn.add(new Key(tmpFunctionallyDependentOn));
			}
			if (!ListUtil.contains(tmpCompareAffected, new Key(tmpFunctionallyAffected))) {
				tmpCompareAffected.add(new Key(tmpFunctionallyAffected));
			}
		}
		getAllPossibleKeys(tmpPossibleKeys);
		System.out.println();
		System.out.println("Possible keys:");
		for (Iterator<Key> i = tmpPossibleKeys.iterator(); i.hasNext();) {
			i.next().out();
		}
		for (Iterator<Key> i = tmpPossibleKeys.iterator(); i.hasNext();) {
			Key tmpPossibleKey = i.next();
			boolean tmpIsKey = true;
			// run through all left ones
			for (Iterator<Key> j = tmpCompareDependentOn.iterator(); j.hasNext();) {
				Key tmpCompareKey = j.next();
				// if the same we got even the key to check
				if (!tmpCompareKey.equals(tmpPossibleKey)) {
					// if the possibleKey is part of the compareKey...
					if (tmpCompareKey.contains(tmpPossibleKey)) {
						Key tmpComparePossibleKey = (Key) tmpPossibleKey.copy();

						ArrayList<String> tmpAllPossibleKeyAffected = new ArrayList<String>();
						getAllAffected(tmpPossibleKey, tmpAllPossibleKeyAffected);
						if (tmpAllPossibleKeyAffected != null) {
							for (Iterator<String> k = tmpAllPossibleKeyAffected.iterator(); k.hasNext();) {
								tmpComparePossibleKey.addAttribute(k.next());
							}
							if (!tmpComparePossibleKey.containsOrEquals(tmpCompareKey)) {
								tmpIsKey = false;
							}
						}
					} else if (tmpPossibleKey.contains(tmpCompareKey)) {
						// if the compareKey is part of the possibleKey...
						ArrayList<String> tmpPossibleKeyAffected = new ArrayList<String>();
						ArrayList<String> tmpCompareKeyAffected = new ArrayList<String>();
						// ... get the directly affected for both keys
						getAllDirectlyAffected(tmpPossibleKey, tmpPossibleKeyAffected);
						getAllDirectlyAffected(tmpCompareKey, tmpCompareKeyAffected);
						if ((!tmpPossibleKeyAffected.isEmpty() && !tmpCompareKeyAffected.isEmpty() && ListUtil.equals(tmpCompareKeyAffected,
								tmpPossibleKeyAffected))) {
							tmpIsKey = false;
						}
					}
				}
			}
			for (Key tmpCompareKey : tmpCompareAffected) {
				if (tmpCompareKey.containsOrEquals(tmpPossibleKey)) {
					tmpIsKey = false;
				}
			}
			if (tmpIsKey) {
				keys.add(tmpPossibleKey);
			}
		}
		return keys;
	}

	private void getAllAffected(Key aKey, ArrayList<String> anAffectedList) {
		ArrayList<String> tmpAllDirectlyAffected = new ArrayList<String>();
		getAllDirectlyAffected(aKey, tmpAllDirectlyAffected);
		if (!tmpAllDirectlyAffected.isEmpty()) {
			anAffectedList.addAll(tmpAllDirectlyAffected);
			getAllAffected(new Key(tmpAllDirectlyAffected), anAffectedList);
		}
	}

	private void getAllDirectlyAffected(Key aKey, ArrayList<String> anAffectedList) {
		for (Iterator<FunctionalDependency> k = functionalDependencies.iterator(); k.hasNext();) {
			FunctionalDependency tmpDependency = k.next();
			if (ListUtil.equals(tmpDependency.getFunctionallyDependentOn(), aKey.getAttributes())) {
				ArrayList<String> tmpAllDirectlyAffected = null;
				tmpAllDirectlyAffected = tmpDependency.getFunctionallyAffected();
				for (Iterator<String> j = tmpAllDirectlyAffected.iterator(); j.hasNext();) {
					String tmpAffected = j.next();
					if (!anAffectedList.contains(tmpAffected)) {
						anAffectedList.add(tmpAffected);
					}
				}
			}
		}
	}

	/**
	 * adds keys to the given list that are not an explicit part of any
	 * functional dependency
	 *
	 * @param somePossibleKeys
	 * @throws UnknownObjectException
	 */
	private void getAllPossibleKeys(ArrayList<Key> somePossibleKeys) throws UnknownObjectException {
		ArrayList<Key> tmpFurtherPossibleKeys = new ArrayList<Key>();
		for (Key tmpPossibleKey : somePossibleKeys) {
			ArrayList<String> tmpOneAffected = new ArrayList<String>();
			// get all directly affected of that key
			getAllDirectlyAffected(tmpPossibleKey, tmpOneAffected);
			ArrayList<String> tmpAllDirectlyAffectedOfThisOne = new ArrayList<String>();
			// and then get all directly affected by the affected-key
			getAllDirectlyAffected(new Key(tmpOneAffected), tmpAllDirectlyAffectedOfThisOne);
			// check if you can make a reverse pointer to this affected-key
			if (!tmpAllDirectlyAffectedOfThisOne.isEmpty()) {
				boolean tmpCreateNewKey = false;
				Key tmpFurtherPossibleKey = (Key) tmpPossibleKey.copy();
				for (String tmpAffected : tmpAllDirectlyAffectedOfThisOne) {
					if (tmpFurtherPossibleKey.contains(tmpAffected)) {
						tmpCreateNewKey = true;
						tmpFurtherPossibleKey.removeAttribute(tmpAffected);
					}
				}
				if (tmpCreateNewKey) {
					for (String tmpKeyAttribute : tmpOneAffected) {
						if (!tmpFurtherPossibleKey.contains(tmpKeyAttribute)) {
							tmpFurtherPossibleKey.addAttribute(tmpKeyAttribute);
						}
					}
					if (tmpFurtherPossibleKey.getAttributes().size() != 0 && !somePossibleKeys.contains(tmpFurtherPossibleKey)) {
						tmpFurtherPossibleKeys.add(tmpFurtherPossibleKey);
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

	private void removeInvalidPossibleKeys(ArrayList<Key> someFurtherPossibleKeys, ArrayList<Key> somePossibleKeys) {
		ArrayList<Key> tmpDeleteList = new ArrayList<Key>();
		for (Key tmpKey : someFurtherPossibleKeys) {
			for (Key tmpPossibleKey1 : somePossibleKeys) {
				for (Key tmpPossibleKey2 : somePossibleKeys) {
					ArrayList<String> tmpComparePseudoKeyAttributes = new ArrayList<String>();
					tmpComparePseudoKeyAttributes.addAll(tmpPossibleKey1.getAttributes());
					tmpComparePseudoKeyAttributes.addAll(tmpPossibleKey2.getAttributes());
					Key tmpComparePseudoKey = new Key(tmpComparePseudoKeyAttributes);
					if (tmpComparePseudoKey.equals(tmpKey)) {
						tmpDeleteList.add(tmpKey);
					}
				}
			}
		}
		for (Key tmpDeleteKey : tmpDeleteList) {
			someFurtherPossibleKeys.remove(tmpDeleteKey);
		}
	}

	private void bringTo2ndNormalForm() throws InvalidActivityException {
		if (normalForm == 1) {
			for (Key tmpKey : keys) {
				for (FunctionalDependency tmpFunctionalDependency : functionalDependencies) {
					Key tmpCompareKey = new Key(tmpFunctionalDependency.getFunctionallyDependentOn());
					if (tmpKey.contains(tmpCompareKey)) {
						extractRelationSchema(tmpFunctionalDependency);
					}
				}
			}
			setNormalForm(2);
		} else {
			throw new InvalidActivityException("Relation Schema already is in " + getNormalFormForOut());
		}
	}

	private void bringTo3rdNormalForm() throws InvalidActivityException {
		if (normalForm == 1) {
			bringTo2ndNormalForm();
		}
		if (normalForm == 2) {
			// for every functional dependency...
			for (FunctionalDependency tmpFunctionalDependency : functionalDependencies) {
				ArrayList<String> tmpDirectlyAffected = new ArrayList<String>();
				tmpDirectlyAffected = tmpFunctionalDependency.getFunctionallyAffected();
				ArrayList<ArrayList<String>> tmpAllCombinations = getAllCombinations(tmpDirectlyAffected);
				// ... and all combinations of the affected of all these dependencies...
				for (ArrayList<String> tmpCombination : tmpAllCombinations) {
					Key tmpPseudoKey = new Key(tmpCombination);
					ArrayList<FunctionalDependency> tmpPseudoDependencies = getFunctionalDependenciesFor(tmpPseudoKey);
					// check if the combination is also a dependentOn
					if (!tmpPseudoDependencies.isEmpty()) {
						ArrayList<FunctionalDependency> tmpExtractDependencies = new ArrayList<FunctionalDependency>();
						for (RelationSchema tmpRelationSchema : relationSchemas) {
							// if so check the relationSchemas to extract a new relationSchema
							if (ListUtil.contains(tmpRelationSchema.getAttributes(), tmpPseudoKey.getAttributes())) {
								for (FunctionalDependency tmpDependency : tmpPseudoDependencies) {
									tmpExtractDependencies.add(tmpDependency);
								}
							}
						}
						for (FunctionalDependency tmpDependency : tmpExtractDependencies) {
							extractRelationSchema(tmpDependency);
						}
					}
				}
			}
			setNormalForm(3);
		} else {
			throw new InvalidActivityException("Relation Schema already is in " + getNormalFormForOut());
		}
	}

	private ArrayList<ArrayList<String>> getAllCombinations(ArrayList<String> aList) {
		ArrayList<ArrayList<String>> tmpAllCombinations = new ArrayList<ArrayList<String>>();
		if (aList.size() == 1) {
			tmpAllCombinations.add(aList);
		} else {
			ArrayList<ArrayList<String>> tmpCombinations = new ArrayList<ArrayList<String>>();
			for (int i = 0; i < aList.size(); i++) {
				tmpCombinations = getAllCombinations(aList, tmpCombinations);
				tmpAllCombinations.addAll(tmpCombinations);
			}
		}
		return tmpAllCombinations;
	}

	private ArrayList<ArrayList<String>> getAllCombinations(ArrayList<String> aList, ArrayList<ArrayList<String>> anotherList) {
		ArrayList<ArrayList<String>> tmpAllCombinations = new ArrayList<ArrayList<String>>();
		for (String tmpString : aList) {
			if (anotherList.isEmpty()) {
				for (String tmpStringFromList : aList) {
					ArrayList<String> tmpCombination = new ArrayList<String>();
					tmpCombination.add(tmpStringFromList);
					tmpAllCombinations.add(tmpCombination);
				}
			} else {
				for (ArrayList<String> tmpList : anotherList) {
					if (!tmpList.contains(tmpString)) {
						ArrayList<String> tmpCombination = new ArrayList<String>();
						tmpCombination.add(tmpString);
						tmpCombination.addAll(tmpList);
						tmpAllCombinations.add(tmpCombination);
					}
				}
			}
		}
		return tmpAllCombinations;
	}

	private ArrayList<FunctionalDependency> getFunctionalDependenciesFor(Key aKey) {
		ArrayList<FunctionalDependency> tmpFunctionalDependencies = new ArrayList<FunctionalDependency>();
		for (FunctionalDependency tmpFunctionalDependency : functionalDependencies) {
			Key tmpCompareKey = new Key(tmpFunctionalDependency.getFunctionallyDependentOn());
			if (tmpCompareKey.equals(aKey)) {
				tmpFunctionalDependencies.add(tmpFunctionalDependency);
			}
		}
		return tmpFunctionalDependencies;
	}

	/**
	 * @param aFunctionalDependency
	 */
	private void extractRelationSchema(FunctionalDependency aFunctionalDependency) {
		ArrayList<String> tmpAllAffected = aFunctionalDependency.getFunctionallyAffected();
		ArrayList<String> tmpAllDependentOn = aFunctionalDependency.getFunctionallyDependentOn();
		for (RelationSchema tmpRelationSchema : relationSchemas) {
			if (tmpRelationSchema.contains(new RelationSchema(tmpAllAffected))) {
				boolean tmpExtract = true;
				for (Key tmpKey : keys) {
					for (String tmpAffected : tmpAllAffected) {
						if (tmpKey.contains(tmpAffected)) {
							tmpExtract = false;
						}
					}
				}
				if (tmpExtract) {
					for (String tmpAffected : tmpAllAffected) {
						if (tmpRelationSchema.getAttributes().contains(tmpAffected)) {
							tmpRelationSchema.removeAttribute(tmpAffected);
						}
					}
					if (tmpRelationSchema.getAttributes().size() == 1) {
						// the same relation schema will come again right now
						relationSchemas.remove(tmpRelationSchema);
					}
					ArrayList<String> tmpNewRelationSchemaColumns = new ArrayList<String>();
					tmpNewRelationSchemaColumns.addAll(tmpAllDependentOn);
					tmpNewRelationSchemaColumns.addAll(tmpAllAffected);
					createRelationSchema(tmpNewRelationSchemaColumns);
					break;
				}
			}
		}
	}

	/**
	 * @param aFunctionalDependency
	 */
	private void createRelationSchema(ArrayList<String> someColumns) {
		RelationSchema tmpRelationSchema = new RelationSchema(someColumns);
		if (!ListUtil.contains(relationSchemas, tmpRelationSchema)) {
			relationSchemas.add(tmpRelationSchema);
		}
	}

	/**
	 * @param aNormalForm
	 *            the normalForm to set
	 */
	private void setNormalForm(int aNormalForm) {
		if (aNormalForm > 0 && aNormalForm > normalForm) {
			normalForm = aNormalForm;
		} else {
			throw new IllegalArgumentException("New Normal Form has to be higher than old (" + normalForm + ")!");
		}
	}

	/**
	 * @return the normalForm as String for output
	 */
	private String getNormalFormForOut() {
		String tmpNormalForm = " Normal Form";
		switch (normalForm) {
		case 0:
			tmpNormalForm = "no" + tmpNormalForm;
			break;
		case 1:
			tmpNormalForm = "1st" + tmpNormalForm;
			break;
		case 2:
			tmpNormalForm = "2nd" + tmpNormalForm;
			break;
		case 3:
			tmpNormalForm = "3rd" + tmpNormalForm;
			break;
		default:
			tmpNormalForm = "not implemented" + tmpNormalForm;
		}
		return tmpNormalForm;
	}

	/**
	 * puts the keys on the console
	 */
	private void keysOut() {
		System.out.println();
		System.out.println("Definite keys:");
		for (Key tmpKey : keys) {
			tmpKey.out();
		}
	}

	/**
	 * puts the current relationSchemas and the Normal Form on the console
	 */
	public void relationSchemasOut() {
		System.out.println();
		System.out.println(getNormalFormForOut() + ":");
		for (RelationSchema tmpRelationSchema : relationSchemas) {
			tmpRelationSchema.out();
		}
	}
}