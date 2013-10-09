package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

import api.entity.ArenaDetails;
import api.entity.LeagueFixtures;
import api.entity.MatchDetails;
import api.entity.MatchLineup;
import api.entity.PlayerDetails;
import api.exception.IllegalXMLException;
import api.parser.XMLArenaDetailsParser;
import api.parser.XMLLeagueFixturesParser;
import api.parser.XMLMatchDetailsParser;
import api.parser.XMLMatchLineupParser;
import api.parser.XMLPlayerDetailsParser;

/**
 * @author	Jan Van Haaren
 * @date	27 June 2013
 */
public class HattrickDownloader {

	private final static String ARENA_DETAILS_VERSION = "1.5";
	private final static String ARENA_DETAILS_DIRECTORY = "ArenaDetails\\";
	
	private final static String PLAYERS_VERSION = "2.3";

	private final static String PLAYER_DETAILS_VERSION = "2.4";
	private final static String PLAYER_DETAILS_DIRECTORY = "PlayerDetails\\";

	private final static String MATCH_LINEUP_VERSION = "1.8";
	private final static String MATCH_LINEUP_DIRECTORY = "MatchLineup\\";
	
	private final static String MATCH_DETAILS_VERSION = "2.5";
	private final static String MATCH_DETAILS_DIRECTORY = "MatchDetails\\";
	
	private final static String LEAGUE_FIXTURES_VERSION = "1.2";
	private final static String LEAGUE_FIXTURES_DIRECTORY = "LeagueFixtures\\";
	

	public static void main(String[] arguments) throws IOException {
		HattrickDownloader downloader = new HattrickDownloader();

		try{
			ArenaDetails arenaDetails = downloader.getArenaDetails(2009310);
			downloader.createArenaDetailsXML(2009310);
			downloader.getArenaDetailsFromFile(2009310);
			
			MatchDetails matchDetails = downloader.getMatchDetails(237985);
			downloader.createMatchDetailsXML(237985);
			downloader.getMatchDetailsFromFile(237985);
			
			MatchLineup matchLineup = downloader.getMatchLineup(9213693,3000);
			downloader.createMatchLineupXML(9213693,3000);
			downloader.getMatchLineupFromFile(9213693,3000);
	
			PlayerDetails playerDetails = downloader.getPlayerDetails(322536294);
			downloader.createPlayerDetailsXML(322536294);
			downloader.getPlayerDetailsFromFile(322536294);
			
			LeagueFixtures leagueFixtures = downloader.getLeagueFixtures(1);
			downloader.createLeagueFixturesXML(94036);
			downloader.getLeagueFixturesFromFile(940365);
		} catch(IllegalXMLException e) {
			e.printStackTrace();
		} catch(IllegalArgumentException e) {
			e.printStackTrace();
		}
	}

	public ArenaDetails getArenaDetails(int arenaId) throws IOException, IllegalXMLException {
		return XMLArenaDetailsParser.parseArenaDetailsFromString(this.getArenaDetailsString(arenaId));
	}
	
	public void createArenaDetailsXML(int arenaId) throws IOException
	{
		writeToXML(this.getArenaDetailsString(arenaId),
			XMLSaveSpace.XML_LOCATION + ARENA_DETAILS_DIRECTORY + arenaId + ".xml");
	}
	
	public ArenaDetails getArenaDetailsFromFile(int arenaId) throws IllegalXMLException
	{
		return XMLArenaDetailsParser.parseArenaDetailsFromString(
				readStringFromXMLFile(
					XMLSaveSpace.XML_LOCATION + ARENA_DETAILS_DIRECTORY + arenaId + ".xml"));
	}
	
	public MatchLineup getMatchLineup(int matchId, int teamId) throws IOException, IllegalXMLException {
		return XMLMatchLineupParser.parseMatchLineupFromString(this.getMatchLineupString(matchId, teamId));
	}
	
	public void createMatchLineupXML(int matchId, int teamId) throws IOException
	{
		writeToXML(this.getMatchLineupString(matchId, teamId),
			XMLSaveSpace.XML_LOCATION + MATCH_LINEUP_DIRECTORY + matchId + "_" + teamId + ".xml");
	}
	
	public MatchLineup getMatchLineupFromFile(int matchId, int teamId) throws IllegalXMLException
	{
		return XMLMatchLineupParser.parseMatchLineupFromString(
				readStringFromXMLFile(
						XMLSaveSpace.XML_LOCATION + MATCH_LINEUP_DIRECTORY + matchId + "_" + teamId + ".xml"));
	}
	
	public MatchDetails getMatchDetails(int matchId) throws IOException, IllegalXMLException {
		return XMLMatchDetailsParser.parseMatchDetailsFromString(this.getMatchDetailsString(matchId));
	}
	
	public void createMatchDetailsXML(int matchId) throws IOException
	{
		writeToXML(this.getMatchDetailsString(matchId),
			XMLSaveSpace.XML_LOCATION + MATCH_DETAILS_DIRECTORY + matchId + ".xml");
	}
	
	public MatchDetails getMatchDetailsFromFile(int matchId) throws IllegalXMLException
	{
		return XMLMatchDetailsParser.parseMatchDetailsFromString(
				readStringFromXMLFile(
					XMLSaveSpace.XML_LOCATION + MATCH_DETAILS_DIRECTORY + matchId + ".xml"));
	}
	
	public PlayerDetails getPlayerDetails(int playerId) throws IOException, IllegalXMLException {
		return XMLPlayerDetailsParser.parsePlayerDetailsFromString(this.getPlayerDetailsString(playerId));
	}
	
	public void createPlayerDetailsXML(int playerId) throws IOException
	{
		writeToXML(this.getPlayerDetailsString(playerId),
			XMLSaveSpace.XML_LOCATION + PLAYER_DETAILS_DIRECTORY + String.valueOf(playerId) + ".xml");
	}
	
	public PlayerDetails getPlayerDetailsFromFile(int playerId) throws IllegalXMLException
	{
		return XMLPlayerDetailsParser.parsePlayerDetailsFromString(
				readStringFromXMLFile(
					XMLSaveSpace.XML_LOCATION + PLAYER_DETAILS_DIRECTORY + String.valueOf(playerId) + ".xml"));
	}
	
	public LeagueFixtures getLeagueFixtures(int leagueLevelUnitID) throws IOException, IllegalXMLException {
		return XMLLeagueFixturesParser.parseLeagueFixturesFromString(this.getLeagueFixturesString(leagueLevelUnitID));
	}
	
	public void createLeagueFixturesXML(int leagueLevelUnitID) throws IOException
	{
		String xml = this.getLeagueFixturesString(leagueLevelUnitID);
		writeToXML(xml,XMLSaveSpace.XML_LOCATION + LEAGUE_FIXTURES_DIRECTORY + String.valueOf(leagueLevelUnitID) + ".xml");
	}
	
	public LeagueFixtures getLeagueFixturesFromFile(int leagueLevelUnitID) throws IllegalXMLException
	{
		return XMLLeagueFixturesParser.parseLeagueFixturesFromString(
				readStringFromXMLFile(
					XMLSaveSpace.XML_LOCATION + LEAGUE_FIXTURES_DIRECTORY + String.valueOf(leagueLevelUnitID) + ".xml"));
	}
	
	private String readStringFromXMLFile(String fileName)
	{
        StringBuilder sb = new StringBuilder();
        BufferedReader reader = null;
        try {
        	reader = new BufferedReader(new FileReader(fileName));
        	String sCurrentLine = reader.readLine();
	        while (sCurrentLine != null) {
	            sb.append(sCurrentLine + "\n");
	            sCurrentLine = reader.readLine();
	        }
        } catch (FileNotFoundException e) {
        	throw new IllegalArgumentException("Illegal filename " + fileName + " provided: file does not exist");
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if(reader != null)
					reader.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
        return sb.toString().trim();
    }
	
	private void writeToXML(String content, String fileName)
	{
		BufferedWriter writer = null;
		try {
			
			writer = new BufferedWriter(new FileWriter(fileName));
		    writer.write(content);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	private String getArenaDetailsString(int arenaId) throws IOException {
		// file and version
		String sURL = "?file=arenadetails&version=" + ARENA_DETAILS_VERSION;

		// parameters
		if (arenaId > 0) {
			sURL += "&arenaID=" + arenaId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}

	public String getPlayersString(int teamId) throws IOException {
		// file and version
		String sURL = "?file=players&version=" + PLAYERS_VERSION;

		// parameters
		if (teamId > 0) {
			sURL += "&teamID=" + teamId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}

	public String getPlayerDetailsString(int playerId) throws IOException {
		// file and version
		String sURL = "?file=playerdetails&version=" + PLAYER_DETAILS_VERSION;

		// parameters
		if (playerId > 0) {
			sURL += "&playerID=" + playerId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	private String getMatchLineupString(int matchId, int teamId) {
		// file and version
		String sURL = "?file=matchlineup&version=" + MATCH_LINEUP_VERSION;

		// parameters
		if (matchId > 0) {
			sURL += "&matchID=" + matchId;
		}
		
		if (teamId > 0) {
			sURL += "&teamID=" + teamId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	private String getMatchDetailsString(int matchId) {
		// file and version
		String sURL = "?file=matchdetails&version=" + MATCH_DETAILS_VERSION + "&matchEvents=true";

		// parameters
		if (matchId > 0) {
			sURL += "&matchID=" + matchId;
		}
		
		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	private String getLeagueFixturesString(int leagueLevelUnitID) {
		// file and version
		String sURL = "?file=leaguefixtures&version=" + LEAGUE_FIXTURES_VERSION;
		
		// parameters
		if (leagueLevelUnitID > 0) {
			sURL += "&leagueLevelUnitID=" + leagueLevelUnitID;
		}
		
		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	private HattrickConnector getConnector() {
		return HattrickConnector.getInstance();
	}

}
