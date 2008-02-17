package normalisation;

import java.io.File;
import java.io.FileNotFoundException;

import javax.activity.InvalidActivityException;
import javax.naming.ConfigurationException;

import normalisation.logic.Normaliser;
import normalisation.logic.util.XmlUtil;

import org.w3c.dom.Document;

/**
 * @author Tobias
 *
 */
public class Normalisation {

	/**
	 * start normalisation here
	 *
	 * @param args [0]: a normalisation XML
	 * @throws FileNotFoundException
	 * @throws ConfigurationException
	 * @throws InvalidActivityException
	 */
	public static void main(String[] args) throws FileNotFoundException, ConfigurationException, InvalidActivityException {
		if (args.length == 0) {
			throw new IllegalArgumentException("Want to have at least normalisation XML!");
		}
		for (int i = 0; i < args.length; i++) {
			System.out.println("================================================================");
			File tmpXml = new File(args[i]);
			Document tmpDocument;
			if (tmpXml.exists()) {
				tmpDocument = XmlUtil.getDocumentFrom(tmpXml);
			} else {
				throw new FileNotFoundException("File '" + tmpXml.getAbsolutePath() + "' not found.");
			}

			Normaliser tmpNormaliser = new Normaliser();
			tmpNormaliser.init(tmpDocument);
			tmpNormaliser.normalise();
			System.out.println("================================================================");
			System.out.println();
		}
	}
}
