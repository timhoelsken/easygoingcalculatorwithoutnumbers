package normalisation;

import java.io.File;
import java.io.FileNotFoundException;

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
   */
  public static void main(String[] args) throws FileNotFoundException, ConfigurationException {
    if (args.length != 1) {
      throw new IllegalArgumentException("Want to have a normalisation XML!");
    }
    File tmpXml = new File(args[0]);
    Document tmpDocument;
    if (tmpXml.exists()) {
      tmpDocument = XmlUtil.getDocumentFrom(tmpXml);
    } else {
      throw new FileNotFoundException("File '" + tmpXml.getAbsolutePath() + "' not found.");
    }

    Normaliser tmpNormaliser = new Normaliser();
    tmpNormaliser.init(tmpDocument);
  }
}
