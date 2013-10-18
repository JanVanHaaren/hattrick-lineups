package api.parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.MatchesArchive;
import api.entity.matchesarchive.Match;
import api.entity.matchesarchive.Team;
import api.entity.matchesarchive.TeamIdentifier;
import api.exception.IllegalXMLException;

public class XMLMatchesArchiveParser extends XMLParser {
	
	public XMLMatchesArchiveParser() {
		// NOP
	}
	
	public static MatchesArchive parseMatchesArchiveFromString(String string) throws IllegalXMLException {
		return parseMatchesArchive(XMLParser.parseString(string));
	}

	private static MatchesArchive parseMatchesArchive(Document document) {

		MatchesArchive matchArchive = new MatchesArchive();

		try {

			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			matchArchive.setUserID(getElementValue(rootElement, "UserID"));
			matchArchive.setFetchedDate(getElementValue(rootElement, "FetchedDate"));
			
			// <HattrickData/Team>
			
			Element teamElement = getChildElement(rootElement, "Team");
			
			Team team = new Team();
			
			team.setTeamID(getElementValue(teamElement, "TeamID"));
			team.setTeamName(getElementValue(teamElement, "TeamName"));
			team.setFirstMatchDate(getElementValue(teamElement, "FirstMatchDate"));
			team.setLastMatchDate(getElementValue(teamElement, "LastMatchDate"));
			matchArchive.setTeam(team);
			
			// <HattrickData/Team/MatchList>
			Element matchListElement = getChildElement(teamElement, "MatchList");
			
			List<Match> matchList = new ArrayList<Match>();
			team.setMatchList(matchList);
			
			// <HattrickData/Team/MatchList>
			List<Element> matchElements = getChildElementList(matchListElement, "Match");
			
			for(Element matchElement : matchElements)
			{
				Match match = new Match();
				match.setMatchID(getElementValue(matchElement, "MatchID"));
				match.setMatchDate(getElementValue(matchElement, "MatchDate"));
				match.setMatchType(getElementValue(matchElement, "MatchType"));
				match.setHomeGoals(getElementValue(matchElement, "HomeGoals"));
				match.setAwayGoals(getElementValue(matchElement, "AwayGoals"));
				matchList.add(match);
				
				// <HattrickData/Team/MatchList/HomeTeam>
				Element homeTeamElement = getChildElement(matchElement, "HomeTeam");
				
				TeamIdentifier homeTeam = new TeamIdentifier();
				homeTeam.setTeamID(getElementValue(homeTeamElement, "HomeTeamID"));
				homeTeam.setTeamName(getElementValue(homeTeamElement, "HomeTeamName"));
				match.setHomeTeam(homeTeam);
				
				// <HattrickData/Team/MatchList/AwayTeam>
				Element awayTeamElement = getChildElement(matchElement, "AwayTeam");
				
				TeamIdentifier awayTeam = new TeamIdentifier();
				awayTeam.setTeamID(getElementValue(awayTeamElement, "AwayTeamID"));
				awayTeam.setTeamName(getElementValue(awayTeamElement, "AwayTeamName"));
				match.setAwayTeam(awayTeam);
			}
			Collections.reverse(matchList); //Most recent matches first
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return matchArchive;
	}

}
