package api.parser;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.WorldDetails;
import api.entity.worlddetails.Country;
import api.entity.worlddetails.Cup;
import api.entity.worlddetails.League;
import api.exception.IllegalXMLException;

public class XMLWorldDetailsParser extends XMLParser {
	
	public XMLWorldDetailsParser() {
		// NOP
	}
	
	public static WorldDetails parseWorldDetailsFromString(String string) throws IllegalXMLException {
		return parseWorldDetails(XMLParser.parseString(string));
	}

	private static WorldDetails parseWorldDetails(Document document) {

		WorldDetails worldDetails = new WorldDetails();
		
		try {
			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			
			worldDetails.setUserID(getElementValue(rootElement, "UserID"));
			worldDetails.setFetchedDate(getElementValue(rootElement, "FetchedDate"));
			
			Element leagueListElement = getChildElement(rootElement, "LeagueList");
			Collection<Element> leagueElements = getChildElementList(leagueListElement, "League");
			// <HattrickData/LeagueList>
			Map<Integer, League> leagueList = new HashMap<Integer,League>();
			
			// <HattrickData/LeagueList/League>
			for(Element leagueElement : leagueElements)
			{
				League league = new League();
				
				league.setLeagueID(getElementValue(leagueElement, "LeagueID"));
				league.setLeagueName(getElementValue(leagueElement, "LeagueName"));
				league.setSeason(getElementValue(leagueElement, "Season"));
				league.setSeasonOffset(getElementValue(leagueElement, "SeasonOffset"));
				league.setMatchRound(getElementValue(leagueElement, "MatchRound"));
				league.setShortName(getElementValue(leagueElement, "ShortName"));
				league.setContinent(getElementValue(leagueElement, "Continent"));
				league.setZoneName(getElementValue(leagueElement, "ZoneName"));
				league.setEnglishName(getElementValue(leagueElement, "EnglishName"));
				league.setNationalTeamId(getElementValue(leagueElement, "NationalTeamId"));
				league.setU20TeamId(getElementValue(leagueElement, "U20TeamId"));
				league.setActiveTeams(getElementValue(leagueElement, "ActiveTeams"));
				league.setActiveUsers(getElementValue(leagueElement, "ActiveUsers"));
				league.setWaitingUsers(getElementValue(leagueElement, "WaitingUsers"));
				league.setTrainingDate(getElementValue(leagueElement, "TrainingDate"));
				league.setEconomyDate(getElementValue(leagueElement, "EconomyDate"));
				league.setCupMatchDate(getElementValue(leagueElement, "CupMatchDate"));
				league.setSeriesMatchDate(getElementValue(leagueElement, "SeriesMatchDate"));
				league.setNumberOfLevels(getElementValue(leagueElement, "NumberOfLevels"));
				
				// <HattrickData/LeagueList/League/Country>
				Element countryElement = getChildElement(leagueElement, "Country");
				
				Country country = new Country();
				
				country.setCountryID(getElementValue(countryElement, "CountryID"));
				country.setCountryName(getElementValue(countryElement, "CountryName"));
				country.setCurrencyName(getElementValue(countryElement, "CurrencyName"));
				country.setCurrencyRate(getElementValue(countryElement, "CurrencyRate"));
				country.setDateFormat(getElementValue(countryElement, "DateFormat"));
				country.setTimeFormat(getElementValue(countryElement, "TimeFormat"));
				
				league.setCountry(country);
				
				// <HattrickData/LeagueList/League/Cup>
				Element cupElement = getChildElement(leagueElement, "Cup");
				
				Cup cup = new Cup();
				
				cup.setCupID(getElementValue(cupElement, "CupID"));
				cup.setCupName(getElementValue(cupElement, "CupName"));
				
				league.setCup(cup);
				
				leagueList.put(league.getLeagueID(), league);
			}
			
			worldDetails.setLeagueList(leagueList);
			
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return worldDetails;
	}

}
