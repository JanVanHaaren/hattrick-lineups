package api.wekafilecreation;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileFilter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import api.LocalPaths;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.wekafilecreation.advancedgoaldiff.NaiveAdvancedWekaFileCreator2;
import api.wekafilecreation.advancedgoaldiff.RatingProportionsVnukStatsAdvancedWekaFileCreator2;

public abstract class AdvancedWekaFileCreator{
	
	public static void main(String[] args) {
		
//		createMask();
//		System.out.println(getMaskFromFile().size());
		
//		LocalPaths.createWekaDirectory();
//		TrainingDates.setUpTrainingDates();
//		
//		System.out.println("___CREATING NEW FILE: VnukStats");
//		AdvancedWekaFileCreator creator0 = new VnukStatsAdvancedWekaFileCreator();
//		creator0.createAndBuildWekaFiles();
//		System.out.println("\n___CREATING NEW FILE: HatStats");
//		AdvancedWekaFileCreator creator01 = new HatStatsAdvancedWekaFileCreator();
//		creator01.createAndBuildWekaFiles();
//		System.out.println("\n___CREATING NEW FILE: RatingProportionsVnukStats");
//		AdvancedWekaFileCreator creator1 = new RatingProportionsVnukStatsAdvancedWekaFileCreator();
//		creator1.createAndBuildWekaFiles();
//		System.out.println("\n___CREATING NEW FILE: DiscreteRatingProportionsVnukStats");
//		AdvancedWekaFileCreator creator2 = new DiscreteRatingProportionsVnukStatsAdvancedWekaFileCreator();
//		creator2.createAndBuildWekaFiles();
//		System.out.println("\n___CREATING NEW FILE: DiscreteRatingProportionsCharVnukStats");
//		AdvancedWekaFileCreator creator3 = new DiscreteRatingProportionsCharVnukStatsAdvancedWekaFileCreator();
//		creator3.createAndBuildWekaFiles();
//		System.out.println("___CREATING NEW FILE: VnukStats2");
//		AdvancedWekaFileCreator creator20 = new VnukStatsAdvancedWekaFileCreator2();
//		creator20.createAndBuildWekaFiles();
//		System.out.println("\n___CREATING NEW FILE: HatStats2");
//		AdvancedWekaFileCreator creator201 = new HatStatsAdvancedWekaFileCreator2();
//		creator201.createAndBuildWekaFiles();
		System.out.println("\n___CREATING NEW FILE: RatingProportionsVnukStats2");
		AdvancedWekaFileCreator creator21 = new RatingProportionsVnukStatsAdvancedWekaFileCreator2();
		creator21.createAndBuildWekaFiles();
		System.out.println("\n___CREATING NEW FILE: RatingProportionsVnukStats2");
		AdvancedWekaFileCreator creator25 = new NaiveAdvancedWekaFileCreator2();
		creator25.createAndBuildWekaFiles();
//		System.out.println("\n___CREATING NEW FILE: DiscreteRatingProportionsVnukStats2");
//		AdvancedWekaFileCreator creator22 = new DiscreteRatingProportionsVnukStatsAdvancedWekaFileCreator2();
//		creator22.createAndBuildWekaFiles();
//		System.out.println("\n___CREATING NEW FILE: DiscreteRatingProportionsCharVnukStats2");
//		AdvancedWekaFileCreator creator23 = new DiscreteRatingProportionsCharVnukStatsAdvancedWekaFileCreator2();
//		creator23.createAndBuildWekaFiles();
		System.out.println("ALL DONE");
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
	
	public static ArrayList<Integer> generateMask(Map<Integer,LeagueDirectoryWrapper> matchMap)
	{
		ArrayList<Integer> mask = new ArrayList<Integer>();
		for(int i = 0; i < matchMap.keySet().size(); i++)
		{
			if(Math.random() > 0.75)
				mask.add(1);
			else
				mask.add(0);
		}
		System.out.println(mask.size() + " " + matchMap.keySet().size());
		return mask;
	}
	
	public static ArrayList<Integer> getMaskFromFile()
	{
		ArrayList<Integer> mask = new ArrayList<Integer>();
		BufferedReader reader = null;
	        try {
	        	reader = new BufferedReader(new FileReader(LocalPaths.WEKA_LOCATION + "mask" + ".txt"));
	        	String sCurrentLine = reader.readLine();
		        while (sCurrentLine != null) {
		        	mask.add(Integer.parseInt(sCurrentLine));
		        	sCurrentLine = reader.readLine();
		        }
	        } catch (FileNotFoundException e) {
	        	throw new IllegalArgumentException("Illegal filename provided: file does not exist");
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
			return mask;
	}
	
	public String[] getData(Map<Integer,LeagueDirectoryWrapper> matchMap, ArrayList<Integer> mask)
	{
		StringBuilder sbTrain = new StringBuilder();
		sbTrain.append("@DATA\n");
		
		StringBuilder sbTest = new StringBuilder();
		sbTest.append("@DATA\n");
		
		System.out.println("start");
		System.out.println("matches total: " + matchMap.keySet().size());
		int j = 0;
		for(int i : matchMap.keySet())
		{
			if(mask.get(j).equals(0))
				sbTrain.append(this.getDataForValidMatch(matchMap.get(i).getLeagueID(), i, matchMap.get(i).getLocation()));
			else
				sbTest.append(this.getDataForValidMatch(matchMap.get(i).getLeagueID(), i, matchMap.get(i).getLocation()));
			if(j % 1000 == 0)
				System.out.println("current match parsed: " + j);
			j++;
		}
		System.out.println("matches parsed");
		return new String[]{sbTrain.toString().trim(), sbTest.toString().trim()};
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
		} catch (IllegalArgumentException e){
			return "";
		}
	}
	
	public static Map<Integer,LeagueDirectoryWrapper> getCorrectMatchDirectories(String directoryPath) throws IOException
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
	    System.out.println("structure collection");
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
	
	public static void createMask()
	{
		BufferedWriter writer = null;
		try {
			writer = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + "mask" + ".txt"));
			ArrayList<Integer> mask = generateMask(getCorrectMatchDirectories(LocalPaths.XML_LOCATION));
			for(int i = 0; i < mask.size(); i++){
				writer.write(mask.get(i).toString());
				writer.newLine();
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void createAndBuildWekaFiles()
	{
		System.out.println("create");
		BufferedWriter trainWriter = null;
		BufferedWriter testWriter = null;
		try {
			trainWriter = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + getFileName() + "_train.arff"));
			testWriter = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + getFileName() + "_test.arff"));
			System.out.println("get data");
			String[] data = this.getData(getCorrectMatchDirectories(LocalPaths.XML_LOCATION), getMaskFromFile());
			System.out.println("write");
			trainWriter.write(getHeaderSection() + data[0]);
			testWriter.write(getHeaderSection() + data[1]);
		} catch(IOException e) {
			e.printStackTrace();
		} finally {
			try {
				trainWriter.close();
				testWriter.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		System.out.println("file created");
	}
	
	protected abstract String getFileName();
	protected abstract List<AttributeAndType> getAttributeList();
	protected abstract String getDataForMatch(int leagueId, int matchId, String directoryPath)  throws IOException, IllegalXMLException, DiscardException;
}
