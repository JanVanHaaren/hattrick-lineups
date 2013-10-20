package api;

import java.io.File;

public class LocalPaths {
	
	//XML
	public static final String XML_LOCATION = "C:\\Users\\Verachtert Aäron\\Dropbox\\Backup\\Unief\\THESIS\\XML_Files\\";
	
	public final static String ARENA_DETAILS_DIRECTORY = "ArenaDetails\\";
	
	public final static String PLAYER_DETAILS_DIRECTORY = "PlayerDetails\\";
	
	public final static String MATCH_LINEUP_DIRECTORY = "MatchLineup\\";
	
	public final static String MATCH_DETAILS_DIRECTORY = "MatchDetails\\";
	
	public final static String LEAGUE_FIXTURES_DIRECTORY = "LeagueFixtures\\";
	
	public final static String WORLD_DETAILS_DIRECTORY = "WorldDetails\\";
	
	public final static String MATCHES_ARCHIVE_DIRECTORY = "MatchesArchive\\";
	
	public final static String TEAM_DETAILS_DIRECTORY = "TeamDetails\\";
	
	//WEKA
	public static final String WEKA_LOCATION = "C:\\Users\\Verachtert Aäron\\Dropbox\\Backup\\Unief\\THESIS\\Weka\\";
	
	//Creator
	public static void createDirectoryStructure()
	{
		createDirectory(XML_LOCATION);
		
		for(int leagueID : TrainingDates.nextTrainingDates.keySet())
		{
			String leagueLocation = XML_LOCATION + getLeagueDirectory(leagueID);
			createDirectory(leagueLocation);
			
			String weekLocation = leagueLocation + getWeekDirectory(leagueID);
			createDirectory(weekLocation);
			
			createDirectory(weekLocation + PLAYER_DETAILS_DIRECTORY);
			createDirectory(weekLocation + MATCH_DETAILS_DIRECTORY);
			createDirectory(weekLocation + MATCH_LINEUP_DIRECTORY);
		}
	}
	
	public static void createDirectory(String path)
	{
		File directory = new File(path);
		if (!directory.exists())
			directory.mkdir();  
	}
	
	public static String getLeagueDirectory(int leagueID)
	{
		return String.valueOf(leagueID) + "\\";
	}
	
	public static String getFullDirectoryPath(int leagueID)
	{
		return XML_LOCATION + getLeagueDirectory(leagueID) + getWeekDirectory(leagueID);
	}
	
	public static String getWeekDirectory(int leagueID)
	{
		String trainingDate = TrainingDates.getNextTrainingDateString(leagueID);
		return trainingDate + "\\";
	}
}
