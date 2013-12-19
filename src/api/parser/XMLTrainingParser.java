package api.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.Training;
import api.exception.IllegalXMLException;

public class XMLTrainingParser extends XMLParser {

	public XMLTrainingParser() {
		// NOP
	}
	

	public static Training parseTrainingFromString(String string) throws IllegalXMLException {
		return parseTraining(XMLParser.parseString(string));
	}
	
	private static Training parseTraining(Document document) {

		Training training = new Training();

		try {

			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			training.setUserID(getElementValue(rootElement, "UserID"));
			training.setFetchedDate(getElementValue(rootElement, "FetchedDate"));

			// <HattrickData/Team>
			Element teamElement = getChildElement(rootElement, "Team");
			
			//<HattrickData/Morale>
			Element moraleElement = getChildElement(teamElement, "Morale");
			if (hasAttributeValue(moraleElement, "Available", "True")) {
				training.setMorale(getElementValue(teamElement, "Morale"));
			}
			
			//<HattrickData/SelfConfidence>
			Element selfConfidenceElement = getChildElement(teamElement, "SelfConfidence");
			if (hasAttributeValue(selfConfidenceElement, "Available", "True")) {
				training.setConfidence(getElementValue(teamElement, "SelfConfidence"));
			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// return map;
		return training;
	}

	

}
