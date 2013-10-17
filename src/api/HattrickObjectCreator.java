package api;

import java.io.IOException;

import api.entity.ArenaDetails;
import api.entity.LeagueFixtures;
import api.entity.MatchDetails;
import api.entity.MatchLineup;
import api.entity.PlayerDetails;
import api.entity.WorldDetails;
import api.exception.IllegalXMLException;
import api.parser.XMLArenaDetailsParser;
import api.parser.XMLLeagueFixturesParser;
import api.parser.XMLMatchDetailsParser;
import api.parser.XMLMatchLineupParser;
import api.parser.XMLPlayerDetailsParser;
import api.parser.XMLWorldDetailsParser;

public class HattrickObjectCreator {
	
	private HattrickDownloader downloader;
	private HattrickXMLCollector xmlCollector;
	
	public HattrickObjectCreator()
	{
		this.downloader = new HattrickDownloader();
		this.xmlCollector = new HattrickXMLCollector();
	}
	
	public static void main(String[] args) {
		HattrickObjectCreator creator = new HattrickObjectCreator();
		WorldDetails w = null;
		try {
			w = creator.getWorldDetails();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalXMLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("ok");
	}
	
	private HattrickDownloader getDownloader() {
		return downloader;
	}

	private HattrickXMLCollector getXmlCollector() {
		return xmlCollector;
	}

	public ArenaDetails getArenaDetails(int arenaId) throws IOException, IllegalXMLException {
		return XMLArenaDetailsParser.parseArenaDetailsFromString(getDownloader().getArenaDetailsString(arenaId));
	}
	
	public ArenaDetails getArenaDetailsFromFile(int arenaId) throws IllegalXMLException
	{
		return XMLArenaDetailsParser.parseArenaDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.ARENA_DETAILS_DIRECTORY + arenaId + ".xml"));
	}
	
	public MatchLineup getMatchLineup(int matchId, int teamId) throws IOException, IllegalXMLException {
		return XMLMatchLineupParser.parseMatchLineupFromString(getDownloader().getMatchLineupString(matchId, teamId));
	}
	
	public MatchLineup getMatchLineupFromFile(int matchId, int teamId) throws IllegalXMLException
	{
		return XMLMatchLineupParser.parseMatchLineupFromString(
				getXmlCollector().readStringFromXMLFile(
						LocalPaths.XML_LOCATION + LocalPaths.MATCH_LINEUP_DIRECTORY + matchId + "_" + teamId + ".xml"));
	}
	
	public MatchDetails getMatchDetails(int matchId) throws IOException, IllegalXMLException {
		return XMLMatchDetailsParser.parseMatchDetailsFromString(getDownloader().getMatchDetailsString(matchId));
	}
	
	public MatchDetails getMatchDetailsFromFile(int matchId) throws IllegalXMLException
	{
		return XMLMatchDetailsParser.parseMatchDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.MATCH_DETAILS_DIRECTORY + matchId + ".xml"));
	}
	
	public PlayerDetails getPlayerDetails(int playerId) throws IOException, IllegalXMLException {
		return XMLPlayerDetailsParser.parsePlayerDetailsFromString(getDownloader().getPlayerDetailsString(playerId));
	}
	
	public PlayerDetails getPlayerDetailsFromFile(int playerId) throws IllegalXMLException
	{
		return XMLPlayerDetailsParser.parsePlayerDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.PLAYER_DETAILS_DIRECTORY + String.valueOf(playerId) + ".xml"));
	}
	
	public LeagueFixtures getLeagueFixtures(int leagueLevelUnitID) throws IOException, IllegalXMLException {
		return XMLLeagueFixturesParser.parseLeagueFixturesFromString(getDownloader().getLeagueFixturesString(leagueLevelUnitID));
	}
	
	public LeagueFixtures getLeagueFixturesFromFile(int leagueLevelUnitID) throws IllegalXMLException
	{
		return XMLLeagueFixturesParser.parseLeagueFixturesFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.LEAGUE_FIXTURES_DIRECTORY + String.valueOf(leagueLevelUnitID) + ".xml"));
	}
	
	public WorldDetails getWorldDetails() throws IOException, IllegalXMLException {
		return XMLWorldDetailsParser.parseWorldDetailsFromString(getDownloader().getWorldDetailsString());
	}
	
	public WorldDetails getWorldDetailsFromFile() throws IllegalXMLException
	{
		return XMLWorldDetailsParser.parseWorldDetailsFromString(
				getXmlCollector().readStringFromXMLFile(
					LocalPaths.XML_LOCATION + LocalPaths.WORLD_DETAILS_DIRECTORY + "worldDetails.xml"));
	}
	
	

}
