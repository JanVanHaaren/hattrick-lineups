package api.weka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.LocalPaths;
import api.TrainingDates;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.weka.advancedwinloss.RatingProportionsVnukStatsAdvancedWekaFileCreator;
import api.weka.advancedwinloss.RatingProportionsVnukStatsFormationAdvancedWekaFileCreator;
import api.weka.advancedwinloss.RatingProportionsVnukStatsIntFormationAdvancedWekaFileCreator;
import api.weka.advancedwinloss.RatingProportionsVnukStatsPlayerStatsAdvancedWekaFileCreator;

public abstract class AdvancedWekaFileCreator{
	
	public static void main(String[] args) {
		LocalPaths.createWekaDirectory();
		TrainingDates.setUpTrainingDates();
		
		AdvancedWekaFileCreator creator1 = new RatingProportionsVnukStatsAdvancedWekaFileCreator();
		creator1.createAndBuildWekaFile();
		AdvancedWekaFileCreator creator2 = new RatingProportionsVnukStatsFormationAdvancedWekaFileCreator();
		creator2.createAndBuildWekaFile();
		AdvancedWekaFileCreator creator3 = new RatingProportionsVnukStatsIntFormationAdvancedWekaFileCreator();
		creator3.createAndBuildWekaFile();
		AdvancedWekaFileCreator creator4 = new RatingProportionsVnukStatsPlayerStatsAdvancedWekaFileCreator();
		creator4.createAndBuildWekaFile();
	}
	
	private String getRelation()
	{
		return "@RELATION " + getFileName() + "\n\n";
	}
	
	protected String getHeaderSection()
	{
		List<AttributeAndType> attributeList = getAttributeList();
		String result = getRelation();
		for(AttributeAndType entry : attributeList)
		{
			result += "@ATTRIBUTE " + entry.getName() + "\t" + entry.getType() + "\n";
		}
		return result + "\n";
	}
	
	public String getData(Map<Integer,LeagueDirectoryWrapper> matchMap)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("@DATA\n");
		for(int i : matchMap.keySet())
		{
			sb.append(this.getDataForValidMatch(matchMap.get(i).getLeagueID(), i, matchMap.get(i).getLocation()));
		}
		
		return sb.toString().trim();
	}
	
	protected String getDataForValidMatch(int leagueId, int matchId, String directoryPath)
	{
		try {
			return getDataForMatch(leagueId, matchId, directoryPath);
		} catch (IOException e) {
			return "";
		} catch (IllegalXMLException e) {
			return "";
		} catch (DiscardException e) {
			return "";
		}
	}
	
	public Map<Integer,LeagueDirectoryWrapper> getCorrectMatchDirectories(String directoryPath) throws IOException
	{
		Map<Integer,LeagueDirectoryWrapper> map = new HashMap<Integer,LeagueDirectoryWrapper>();
		
		File folder = new File(directoryPath);
	    Collection<File> leagueFolders = Arrays.asList(
	    		folder.listFiles(new FileFilter() {
									@Override
									public boolean accept(File subFolder) {
										return !subFolder.getName().equals("TeamDetails");
									}
	    }));
	    for(File leagueFolder : leagueFolders)
	    {
	    	Collection<File> weekFolders = Arrays.asList(leagueFolder.listFiles());
	    	
	    	for(File weekFolder: weekFolders)
	    	{
	    		String correctDirectoryPath = weekFolder.getCanonicalPath() + LocalPaths.getDelimiter();
	    		File matchDetailsFolder = new File(correctDirectoryPath  + LocalPaths.MATCH_DETAILS_DIRECTORY);
	    		for (File matchDetailsFile : matchDetailsFolder.listFiles())
		             map.put(Integer.parseInt(matchDetailsFile.getName().replace(".xml", "")),
		            		 	new LeagueDirectoryWrapper(Integer.parseInt(leagueFolder.getName()), correctDirectoryPath));
	    	}
	    }
	    
	    return map;
	}
	
	public void createAndBuildWekaFile()
	{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + getFileName() + ".arff"));
			writer.write(getHeaderSection() + this.getData(getCorrectMatchDirectories(LocalPaths.XML_LOCATION)));
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
	
	protected abstract String getFileName();
	protected abstract List<AttributeAndType> getAttributeList();
	protected abstract String getDataForMatch(int leagueId, int matchId, String directoryPath)  throws IOException, IllegalXMLException, DiscardException;
}
