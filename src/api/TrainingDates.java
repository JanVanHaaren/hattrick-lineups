package api;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import api.entity.WorldDetails;
import api.entity.worlddetails.League;
import api.exception.IllegalXMLException;
import api.util.Utils;

public class TrainingDates {
	
	public static Map<Integer,Calendar> nextTrainingDates = new HashMap<Integer,Calendar>();
	public static Calendar closestTrainingDate = null;
	
	public static void setTrainingDate(int leagueID, Calendar nextTrainingDate)
	{
		nextTrainingDates.put(leagueID, nextTrainingDate);
	}
	
	public static Calendar getNextTrainingDate(int leagueID)
	{
		return (Calendar) nextTrainingDates.get(leagueID).clone();
	}
	
	public static String getNextTrainingDateString(int leagueID)
	{
		SimpleDateFormat formatter = Utils.getHattrickDateFormat();
		return formatter.format(TrainingDates.getNextTrainingDate(leagueID).getTime());
	}
	
	public static void setUpTrainingDates()
	{
		WorldDetails worldDetails = null;
		try {
			worldDetails = new HattrickObjectCreator().getWorldDetails();
			for(League league : worldDetails.getLeagueList().values())
			{
				int leagueID = league.getLeagueID();
				Calendar trainingDate = league.getTrainingDate();
				setTrainingDate(leagueID, trainingDate);
				if(closestTrainingDate == null || trainingDate.before(closestTrainingDate))
					closestTrainingDate = trainingDate;
			}
		} catch (IOException e) {
			e.printStackTrace();
		} catch (IllegalXMLException e) {
			e.printStackTrace();
		}
	}
	
	public static void refreshIfNeeded() throws InterruptedException
	{
		if (closestTrainingDate == null || Calendar.getInstance().after(closestTrainingDate))
		{
			setUpTrainingDates();
			LocalPaths.createDirectoryStructure();
			Thread.sleep(2000);
		}
	}
	
	

}
