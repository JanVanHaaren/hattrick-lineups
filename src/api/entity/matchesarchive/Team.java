package api.entity.matchesarchive;

import java.text.ParseException;
import java.util.Calendar;
import java.util.List;

import api.util.Utils;

public class Team {
	
	private int teamID;
	
	private String teamName;
	
	private Calendar firstMatchDate;
	
	private Calendar lastMatchDate;
	
	private List<Match> matchList;

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = Utils.getIntFromString(teamID);
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public Calendar getFirstMatchDate() {
		return firstMatchDate;
	}

	public void setFirstMatchDate(String firstMatchDate) {
		if(firstMatchDate != null)
		{
			this.firstMatchDate = Calendar.getInstance();
	
			try {
				this.getFirstMatchDate().setTime(Utils.getHattrickDateFormat().parse(firstMatchDate));
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public Calendar getLastMatchDate() {
		return lastMatchDate;
	}

	public void setLastMatchDate(String lastMatchDate) {
		if(lastMatchDate != null)
		{
			this.lastMatchDate = Calendar.getInstance();
	
			try {
				this.getLastMatchDate().setTime(Utils.getHattrickDateFormat().parse(lastMatchDate));
			}
			catch (ParseException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Match> getMatchList() {
		return matchList;
	}

	public void setMatchList(List<Match> matchList) {
		this.matchList = matchList;
	}
	
	

}
