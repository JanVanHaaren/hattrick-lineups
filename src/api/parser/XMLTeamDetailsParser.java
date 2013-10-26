package api.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.TeamDetails;
import api.exception.IllegalXMLException;

public class XMLTeamDetailsParser extends XMLParser {
	
	public XMLTeamDetailsParser() {
		// NOP
	}
	

	public static TeamDetails parseTeamDetailsFromString(String string) throws IllegalXMLException {
		return parseTeamDetails(XMLParser.parseString(string));
	}
	
	private static TeamDetails parseTeamDetails(Document document) {

		TeamDetails teamDetails = new TeamDetails();

		try {

			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			Element teamsElement = getChildElement(rootElement, "Teams");
			Element teamElement = getChildElement(teamsElement, "Team");
			if(teamElement != null)
			{
				teamDetails.setTeamId(getElementValue(teamElement, "TeamID"));
				
				Element leagueElement = getChildElement(teamElement, "League");
				teamDetails.setLeagueId(getElementValue(leagueElement, "LeagueID"));
				
				Element botStatusElement = getChildElement(teamElement, "BotStatus");
				teamDetails.setBot(getElementValue(botStatusElement, "IsBot"));
			}

			
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// return map;
		return teamDetails;
	}

}
