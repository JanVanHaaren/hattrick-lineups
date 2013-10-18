package api.parser;

import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import api.exception.IllegalXMLException;

//FIXME: credit to original author!

/**
 * @author	Jan Van Haaren
 * @date	27 June 2013
 */
public abstract class XMLParser  {

	protected XMLParser() {
		// NOP
	}

	protected static boolean hasAttributeValue(Element element, String attributeName, String value) {
		return element.getAttribute(attributeName).equals(value);
	}

	protected static Element getChildElement(Element parentElement, String name) {
		return (Element) parentElement.getElementsByTagName(name).item(0);
	}
	
	protected static List<Element> getChildElementList(Element parentElement, String name)
	{
		List<Element> result = new ArrayList<Element>();
		NodeList list = parentElement.getElementsByTagName(name);
		for(int i = 0; i < list.getLength(); i++)
			result.add((Element)list.item(i));
		return result;
	}

	protected static String getElementValue(Element element, String name) {
		if(element.getElementsByTagName(name).item(0) != null && element.getElementsByTagName(name).item(0).getFirstChild() != null)
			return element.getElementsByTagName(name).item(0).getFirstChild().getNodeValue();
		return null;
	}

	protected static Document parseString(String inputString) throws IllegalXMLException {

		if(inputString.contains("<FileName>chpperror.xml</FileName>"))
			throw new IllegalXMLException("The XML file represents an error message");
		
		int indexCommentStart = inputString.indexOf("<!--");

		while (indexCommentStart > -1) {
			final int indexCommentEnd = inputString.indexOf("-->");
			final String comment = inputString.substring(indexCommentStart, indexCommentEnd + 3);
			inputString = inputString.replaceAll(comment, "");
			indexCommentStart = inputString.indexOf("<!--");
		}

		Document document = null;

		try {
			final ByteArrayInputStream inputStream = new ByteArrayInputStream(inputString.getBytes("UTF-8"));
			final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			document = builder.parse(inputStream);
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		return document;
	}

}
