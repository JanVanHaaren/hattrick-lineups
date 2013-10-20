package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;

import api.entity.MatchDetails;
import api.entity.MatchLineup;
import api.entity.MatchesArchive;
import api.entity.matchesarchive.Match;
import api.entity.matchlineup.Player;
import api.exception.IllegalXMLException;

public class HattrickXMLCollector {
	
	private HattrickDownloader downloader = new HattrickDownloader();	
	
	private HattrickDownloader getDownloader()
	{
		return downloader;
	}
	
	public static void main(String[] args) throws InterruptedException {
		TrainingDates.refreshIfNeeded();
		HattrickXMLCollector collector = new HattrickXMLCollector();
		HattrickObjectCreator creator = new HattrickObjectCreator();
		
		for(int teamID = 1; teamID < 2045907; teamID++) //2045907 experimenteel bepaald als max valid teamID
		{
			TrainingDates.refreshIfNeeded();
			try {
				collector.createTeamDetails(teamID);
				Thread.sleep(2000);
				
				TeamDetails teamDetails = creator.getTeamDetailsFromFile(teamID);
				
				if(!teamDetails.isInLeague())
					continue;
				
				int leagueID = teamDetails.getLeagueId();
				
				Calendar fromDate = TrainingDates.getNextTrainingDate(teamDetails.getLeagueId());
				fromDate.add(Calendar.DAY_OF_YEAR, -6);
				
				MatchesArchive matchesArchive = creator.getMatchesArchive(teamID, fromDate);
				Thread.sleep(2000);
				
				for(Match match : matchesArchive.getTeam().getMatchList())
				{
					int matchID = match.getMatchID();
					collector.createMatchDetailsXML(teamDetails.getLeagueId(), matchID);
					Thread.sleep(2000);
					
					MatchDetails matchDetails = creator.getMatchDetailsFromFile(teamDetails.getLeagueId(), matchID);
					
					int homeTeamID = matchDetails.getMatch().getHomeTeam().getTeamID();
					int awayTeamID = matchDetails.getMatch().getAwayTeam().getTeamID();
					
					collector.createMatchLineupXML(leagueID, matchID, homeTeamID);
					MatchLineup lineupHome = creator.getMatchLineupFromFile(leagueID, matchID, homeTeamID);
					Thread.sleep(2000);
					collector.createMatchLineupXML(leagueID, matchID, awayTeamID);
					MatchLineup lineupAway = creator.getMatchLineupFromFile(leagueID, matchID, awayTeamID);
					Thread.sleep(2000);
					
					for(Player player : lineupHome.getTeam().getStartingLineup())
					{
						collector.createPlayerDetailsXML(teamDetails.getLeagueId(), player.getPlayerID());
						Thread.sleep(2000);
					}
					
					for(Player player : lineupAway.getTeam().getStartingLineup())
					{
						collector.createPlayerDetailsXML(teamDetails.getLeagueId(), player.getPlayerID());
						Thread.sleep(2000);
					}
				}
				
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IllegalXMLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	
//	public static void main(String[] args) {
//		HattrickXMLCollector collector = new HattrickXMLCollector();
//		collector.downloadMatchDetailsXML(453287181, 453287182);
//	}
	
//	private void downloadMatchDetailsXML(int fromMatchId, int toMatchId)
//	{
//		for(int i = fromMatchId; i <= toMatchId; i++)
//		{
//			try {
//				this.createMatchDetailsXML(i);
//			} catch (IOException e) {
//				e.printStackTrace();
//			} finally {
//				try {
//					Thread.sleep(2000);
//					System.out.println("ok");
//				} catch (InterruptedException e) {
//					Thread.currentThread().interrupt();
//				}
//			}
//		}
//	}
	
	//NOT USED
	public void createArenaDetailsXML(int arenaId) throws IOException
	{
		writeToXML(this.getDownloader().getArenaDetailsString(arenaId),
			LocalPaths.XML_LOCATION + LocalPaths.ARENA_DETAILS_DIRECTORY + arenaId + ".xml");
	}
	
	//NOT USED
	public void createMatchLineupXML(int leagueId, int matchId, int teamId) throws IOException
	{
		writeToXML(this.getDownloader().getMatchLineupString(matchId, teamId),
				LocalPaths.getFullDirectoryPath(leagueId) + LocalPaths.MATCH_LINEUP_DIRECTORY + matchId + "_" + teamId + ".xml");
	}
	
	public void createMatchDetailsXML(int leagueId, int matchId) throws IOException
	{
		writeToXML(this.getDownloader().getMatchDetailsString(matchId),
				LocalPaths.getFullDirectoryPath(leagueId) + LocalPaths.MATCH_DETAILS_DIRECTORY + matchId + ".xml");
	}
	
	public void createPlayerDetailsXML(int leagueId, int playerId) throws IOException
	{
		File file = new File(LocalPaths.getFullDirectoryPath(leagueId) + LocalPaths.PLAYER_DETAILS_DIRECTORY + String.valueOf(playerId) + ".xml");
		if(file.exists())
			return;
		writeToXML(this.getDownloader().getPlayerDetailsString(playerId),
				LocalPaths.getFullDirectoryPath(leagueId) + LocalPaths.PLAYER_DETAILS_DIRECTORY + String.valueOf(playerId) + ".xml");
	}
	
	//NOT USED
	public void createLeagueFixturesXML(int leagueLevelUnitID) throws IOException
	{
		String xml = this.getDownloader().getLeagueFixturesString(leagueLevelUnitID);
		writeToXML(xml,LocalPaths.XML_LOCATION + LocalPaths.LEAGUE_FIXTURES_DIRECTORY + String.valueOf(leagueLevelUnitID) + ".xml");
	}
	
	public void createMatchesArchive(int teamId) throws IllegalXMLException
	{
		String xml = this.getDownloader().getMatchesArchiveString(teamId);
		writeToXML(xml, LocalPaths.XML_LOCATION + LocalPaths.MATCHES_ARCHIVE_DIRECTORY + teamId + ".xml");
	}
	
	public void createMatchesArchive(int teamId, Calendar fromDate) throws IllegalXMLException
	{
		String xml = this.getDownloader().getMatchesArchiveString(teamId, fromDate);
		writeToXML(xml, LocalPaths.XML_LOCATION + LocalPaths.MATCHES_ARCHIVE_DIRECTORY + teamId + ".xml");
	}
	
	public void createTeamDetails(int teamId) throws IllegalXMLException
	{
		String xml = this.getDownloader().getTeamDetailsString(teamId);
		writeToXML(xml, LocalPaths.XML_LOCATION + LocalPaths.TEAM_DETAILS_DIRECTORY + teamId + ".xml");
	}
	
	String readStringFromXMLFile(String fileName)
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
}
