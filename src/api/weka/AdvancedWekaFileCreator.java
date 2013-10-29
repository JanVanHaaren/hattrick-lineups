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
import api.exception.IllegalXMLException;
import api.weka.advancedwinloss.RatingProportionsVnukStatsAdvancedWekaFileCreator;

public abstract class AdvancedWekaFileCreator{
	
	public static void main(String[] args) {
		AdvancedWekaFileCreator creator1 = new RatingProportionsVnukStatsAdvancedWekaFileCreator();
		creator1.createAndBuildWekaFile();
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
	
	public String getData(Map<Integer,String> matchMap)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("@DATA\n");
		for(int i : matchMap.keySet())
		{
			sb.append(this.getDataForValidMatch(i, matchMap.get(i)));
		}
		
		return sb.toString().trim();
	}
	
	protected String getDataForValidMatch(int matchId, String directoryPath)
	{
		try {
			return getDataForMatch(matchId, directoryPath);
		} catch (IOException e) {
			return "";
		} catch (IllegalXMLException e) {
			return "";
		}
	}
	
	public Map<Integer,String> getCorrectMatchDirectories(String directoryPath) throws IOException
	{
		System.out.println(directoryPath);
		Map<Integer,String> map = new HashMap<Integer,String>();
		
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
	    		System.out.println(matchDetailsFolder.getCanonicalPath());
	    		for (File matchDetailsFile : matchDetailsFolder.listFiles())
		             map.put(Integer.parseInt(matchDetailsFile.getName().replace(".xml", "")),
		            		 	correctDirectoryPath);
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
	protected abstract String getDataForMatch(int matchId, String directoryPath)  throws IOException, IllegalXMLException;
}
