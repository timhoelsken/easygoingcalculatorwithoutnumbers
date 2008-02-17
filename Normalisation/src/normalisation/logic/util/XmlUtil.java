package normalisation.logic.util;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;

/**
 * @author Tobias
 *
 * does xml things
 */
public class XmlUtil {

  /**
   * Reads an XML file into a w3c Document
   *
   * @param aFile
   * @return w3c Document
   */
  public static Document getDocumentFrom(File aFile) {
    DocumentBuilderFactory tmpFactory = DocumentBuilderFactory.newInstance();
    Document tmpDocument = null;

    try {
      DocumentBuilder tmpBuilder = tmpFactory.newDocumentBuilder();
      tmpDocument = tmpBuilder.parse(aFile);
    } catch (Exception e) {
      e.printStackTrace();
    }

    return tmpDocument;
  }
}
