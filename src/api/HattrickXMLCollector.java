package api;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class HattrickXMLCollector {
	
	private HattrickDownloader downloader;
	
	public HattrickXMLCollector()
	{
		this.downloader = new HattrickDownloader();
	}
	
	private HattrickDownloader getDownloader()
	{
		return this.downloader;
	}
	
	public static void main(String[] args) {
		HattrickXMLCollector collector = new HattrickXMLCollector();
		collector.downloadMatchDetailsXML(453287181, 453287182);
	}
	
	private void downloadMatchDetailsXML(int fromMatchId, int toMatchId)
	{
		for(int i = fromMatchId; i <= toMatchId; i++)
		{
			try {
				this.createMatchDetailsXML(i);
			} catch (IOException e) {
				e.printStackTrace();
			} finally {
				try {
					Thread.sleep(2000);
					System.out.println("ok");
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
			}
		}
	}
	
	public void createArenaDetailsXML(int arenaId) throws IOException
	{
		writeToXML(this.getDownloader().getArenaDetailsString(arenaId),
			LocalPaths.XML_LOCATION + LocalPaths.ARENA_DETAILS_DIRECTORY + arenaId + ".xml");
	}
	
	public void createMatchLineupXML(int matchId, int teamId) throws IOException
	{
		writeToXML(this.getDownloader().getMatchLineupString(matchId, teamId),
			LocalPaths.XML_LOCATION + LocalPaths.MATCH_LINEUP_DIRECTORY + matchId + "_" + teamId + ".xml");
	}
	
	public void createMatchDetailsXML(int matchId) throws IOException
	{
		writeToXML(this.getDownloader().getMatchDetailsString(matchId),
			LocalPaths.XML_LOCATION + LocalPaths.MATCH_DETAILS_DIRECTORY + matchId + ".xml");
	}
	
	public void createPlayerDetailsXML(int playerId) throws IOException
	{
		writeToXML(this.getDownloader().getPlayerDetailsString(playerId),
			LocalPaths.XML_LOCATION + LocalPaths.PLAYER_DETAILS_DIRECTORY + String.valueOf(playerId) + ".xml");
	}
	
	public void createLeagueFixturesXML(int leagueLevelUnitID) throws IOException
	{
		String xml = this.getDownloader().getLeagueFixturesString(leagueLevelUnitID);
		writeToXML(xml,LocalPaths.XML_LOCATION + LocalPaths.LEAGUE_FIXTURES_DIRECTORY + String.valueOf(leagueLevelUnitID) + ".xml");
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
