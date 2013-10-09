package api.entity.leaguefixtures;

import java.text.ParseException;
import java.util.Calendar;

import api.util.Utils;

public class Match {

	private int matchID;
	
	private int matchRound;
	
	private Team homeTeam;
	
	private Team awayTeam;
	
	private Calendar matchDate;
	
	private Integer homeGoals;
	
	private Integer awayGoals;

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = Utils.getIntFromString(matchID);
	}

	public int getMatchRound() {
		return matchRound;
	}

	public void setMatchRound(String matchRound) {
		this.matchRound = Utils.getIntFromString(matchRound);
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Calendar getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = Calendar.getInstance();
		
		try {
			this.getMatchDate().setTime(Utils.getHattrickDateFormat().parse(matchDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(String homeGoals) {
		if(homeGoals != null)
			this.homeGoals = Utils.getIntFromString(homeGoals);
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(String awayGoals) {
		if(awayGoals != null)
			this.awayGoals = Utils.getIntFromString(awayGoals);
	}
	
	
}
