package api.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.LeagueFixtures;
import api.entity.leaguefixtures.Match;
import api.entity.leaguefixtures.Team;
import api.exception.IllegalXMLException;

public class XMLLeagueFixturesParser extends XMLParser {
	
	public XMLLeagueFixturesParser() {
		// NOP
	}
	
	public static LeagueFixtures parseLeagueFixturesFromString(String string) throws IllegalXMLException {
		return parseLeagueFixtures(XMLParser.parseString(string));
	}

	private static LeagueFixtures parseLeagueFixtures(Document document) {

		LeagueFixtures leagueFixtures = new LeagueFixtures();

		try {
			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			
			leagueFixtures.setUserID(getElementValue(rootElement, "UserID"));
			leagueFixtures.setFetchedDate(getElementValue(rootElement, "FetchedDate"));
			leagueFixtures.setLeagueLevelUnitID(getElementValue(rootElement, "LeagueLevelUnitID"));
			leagueFixtures.setLeagueLevelUnitName(getElementValue(rootElement, "LeagueLevelUnitName"));
			leagueFixtures.setSeason(getElementValue(rootElement, "Season"));
			
			// <HattrickData/Match>
			Collection<Element> matchElements = getChildElementList(rootElement, "Match");
			
			Collection<Match> matches = new ArrayList<Match>();
			for(Element matchElement : matchElements)
			{
			Match match = new Match();
			match.setMatchID(getElementValue(matchElement, "MatchID"));
			match.setMatchRound(getElementValue(matchElement, "MatchRound"));
			match.setMatchDate(getElementValue(matchElement, "MatchDate"));
			match.setHomeGoals(getElementValue(matchElement, "HomeGoals"));
			match.setAwayGoals(getElementValue(matchElement, "AwayGoals"));
			
			// <HattrickData/Match/HomeTeam
			Element homeTeamElement = getChildElement(matchElement, "HomeTeam");
			
			Team homeTeam = new Team();
			homeTeam.setTeamID(getElementValue(homeTeamElement, "HomeTeamID"));
			homeTeam.setTeamName(getElementValue(homeTeamElement, "HomeTeamName"));
			match.setHomeTeam(homeTeam);
			
			// <HattrickData/Match/AwayTeam
			Element awayTeamElement = getChildElement(matchElement, "AwayTeam");
			
			Team awayTeam = new Team();
			awayTeam.setTeamID(getElementValue(awayTeamElement, "AwayTeamID"));
			awayTeam.setTeamName(getElementValue(awayTeamElement, "AwayTeamName"));
			match.setAwayTeam(awayTeam);
			
			matches.add(match);
			}
			leagueFixtures.setMatches(matches);
		
		}
		catch (Exception e) {
			e.printStackTrace();
		}

		// return map;
		return leagueFixtures;
	}

}
