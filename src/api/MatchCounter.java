package api;

import java.io.File;
import java.io.FileFilter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;

public class MatchCounter {
	
	public static void main(String[] args) {
		try {
			System.out.println("Count: " + getMatchCount());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static int getMatchCount() throws IOException
	{
		int count = 0;
		File folder = new File(LocalPaths.XML_LOCATION);
	    Collection<File> leagueFolders = Arrays.asList(
	    		folder.listFiles(new FileFilter() {
									@Override
									public boolean accept(File subFolder) {
										return !subFolder.getName().equals("TeamDetails");
									}
	    }));
	    for(File leagueFolder : leagueFolders)
	    {
	    	System.out.println("counting league " + leagueFolder.getName());
	    	Collection<File> weekFolders = Arrays.asList(leagueFolder.listFiles());
	    	
	    	for(File weekFolder: weekFolders)
	    	{
	    		String correctDirectoryPath = weekFolder.getCanonicalPath() + LocalPaths.getDelimiter();
	    		File matchDetailsFolder = new File(correctDirectoryPath  + LocalPaths.MATCH_DETAILS_DIRECTORY);
	    		count += matchDetailsFolder.list().length;
	    	}
	    }
	    
	    return count;
	}
}
