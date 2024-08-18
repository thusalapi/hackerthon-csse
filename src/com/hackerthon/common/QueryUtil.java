package com.hackerthon.common;

import static com.hackerthon.common.XSLTransformUtil.properties;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerFactoryConfigurationError;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 * Class responsible for loading and parsing XML queries.
 */
public class QueryUtil extends CommonUtil {

    private static final Logger LOGGER = Logger.getLogger(QueryUtil.class.getName());

    public static String getQueryById(String id) {
        String result = null;
        try {
            // Load the XML document
            Document document = DocumentBuilderFactory.newInstance()
                    .newDocumentBuilder()
                    .parse(new File(properties.getProperty(CommonConstants.Employee_QueryXml_Path)));
            // Get all "query" elements from the XML
            NodeList queryNodes = document.getElementsByTagName("query");
            // Iterate through each element to find the one with the matching ID
            for (int i = 0; i < queryNodes.getLength(); i++) {
                Element element = (Element) queryNodes.item(i);
                if (element.getAttribute("id").equals(id)) {
                    result = element.getTextContent().trim();
                    break;
                }
            }
            if (result == null) {
                LOGGER.warning("No matching element found with ID: " + id);
            } else {
                LOGGER.info("Element found with ID: " + id);
            }
        } catch (ParserConfigurationException e) {
            LOGGER.log(Level.SEVERE, "Parser configuration error", e);
        } catch (SAXException e) {
            LOGGER.log(Level.SEVERE, "SAX error during XML parsing", e);
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "IO error during file access", e);
        } catch (TransformerFactoryConfigurationError e) {
            LOGGER.log(Level.SEVERE, "Transformer factory configuration error", e);
        }
        return result;
    }
}