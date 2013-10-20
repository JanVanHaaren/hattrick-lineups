package api.weka;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import api.LocalPaths;
import api.exception.IllegalXMLException;
import api.weka.goaldiff.VnukStatsGoalDiffWekaFileCreator;
import api.weka.winloss.VnukStatsWinLossWekaFileCreator;
import api.weka.winlosstie.HatStatsWekaFileCreator;
import api.weka.winlosstie.VnukStatsWekaFileCreator;

public abstract class WekaFileCreator {
	
	public static void main(String[] args) {
//		WekaFileCreator creator1 = new NaiveGoalDiffWekaFileCreator();
//		creator1.createAndBuildWekaFile();
//		WekaFileCreator creator2 = new NaiveWithTacticGoalDiffWekaFileCreator();
//		creator2.createAndBuildWekaFile();
//		WekaFileCreator creator3 = new RatingDifferenceGoalDiffWekaFileCreator();
//		creator3.createAndBuildWekaFile();
//		WekaFileCreator creator4 = new RatingProportionGoalDiffWekaFileCreator();
//		creator4.createAndBuildWekaFile();
//		WekaFileCreator creator5 = new NaiveWinLossWekaFileCreator();
//		creator5.createAndBuildWekaFile();
//		WekaFileCreator creator6 = new NaiveWinLossWithTacticWekaFileCreator();
//		creator6.createAndBuildWekaFile();
//		WekaFileCreator creator7 = new RatingProportionWinLossWekaFileCreator();
//		creator7.createAndBuildWekaFile();
//		WekaFileCreator creator8 = new RatingDifferenceWinLossWekaFileCreator();
//		creator8.createAndBuildWekaFile();
//		WekaFileCreator creator9 = new NaiveWekaFileCreator();
//		creator9.createAndBuildWekaFile();
//		WekaFileCreator creator10 = new NaiveWithTacticWekaFileCreator();
//		creator10.createAndBuildWekaFile();
//		WekaFileCreator creator11 = new RatingDifferenceWekaFileCreator();
//		creator11.createAndBuildWekaFile();
//		WekaFileCreator creator12 = new RatingProportionWekaFileCreator();
//		creator12.createAndBuildWekaFile();
//		WekaFileCreator creator13 = new HatStatsGoalDiffWekaFileCreator();
//		creator13.createAndBuildWekaFile();
//		WekaFileCreator creator14 = new HatStatsWinLossWekaFileCreator();
//		creator14.createAndBuildWekaFile();
		WekaFileCreator creator15 = new HatStatsWekaFileCreator();
		creator15.createAndBuildWekaFile();
		WekaFileCreator creator16 = new VnukStatsGoalDiffWekaFileCreator();
		creator16.createAndBuildWekaFile();
		WekaFileCreator creator17 = new VnukStatsWinLossWekaFileCreator();
		creator17.createAndBuildWekaFile();
		WekaFileCreator creator18 = new VnukStatsWekaFileCreator();
		creator18.createAndBuildWekaFile();
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
	
	public String getData(Collection<Integer> matchIds)
	{
		StringBuilder sb = new StringBuilder();
		sb.append("@DATA\n");
		for(int i : matchIds)
			sb.append(this.getDataForValidMatch(i));
		
		return sb.toString().trim();
			
	}
	
	protected String getDataForValidMatch(int matchId)
	{
		try {
			return getDataForMatch(matchId);
		} catch (IOException e) {
			return "";
		} catch (IllegalXMLException e) {
			return "";
		}
	}
	
	public Collection<Integer> getIdsOfFetchedMatches(String directoryPath) {
	    File folder = new File(directoryPath);
	    Collection<Integer> idList = new ArrayList<Integer>();
		for (File fileEntry : folder.listFiles())
	             idList.add(Integer.parseInt(fileEntry.getName().replace(".xml", "")));
		return idList;
	}
	
	public void createAndBuildWekaFile()
	{
		BufferedWriter writer = null;
		try {
			Collection<Integer> idList = getIdsOfFetchedMatches(LocalPaths.XML_LOCATION + LocalPaths.MATCH_DETAILS_DIRECTORY);
			writer = new BufferedWriter(new FileWriter(LocalPaths.WEKA_LOCATION + getFileName() + ".arff"));
		    writer.write(getHeaderSection() + this.getData(idList));
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
	protected abstract String getDataForMatch(int matchId)  throws IOException, IllegalXMLException;

}
