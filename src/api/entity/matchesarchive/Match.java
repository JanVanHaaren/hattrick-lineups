package api.entity.matchesarchive;

import java.text.ParseException;
import java.util.Calendar;

import api.datatype.MatchType;
import api.util.Utils;

public class Match {
	
	private int matchID;
	
	private TeamIdentifier homeTeam;
	
	private TeamIdentifier awayTeam;
	
	private Calendar matchDate;
	
	private MatchType matchType;
	
	private Integer homeGoals;
	
	private Integer awayGoals;

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = Utils.getIntFromString(matchID);
	}

	public TeamIdentifier getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamIdentifier homeTeam) {
		this.homeTeam = homeTeam;
	}

	public TeamIdentifier getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamIdentifier awayTeam) {
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

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = MatchType.getMatchType(matchType);
	}

	public Integer getHomeGoals() {
		return homeGoals;
	}

	public void setHomeGoals(String homeGoals) {
		this.homeGoals = Utils.getIntFromString(homeGoals);
	}

	public Integer getAwayGoals() {
		return awayGoals;
	}

	public void setAwayGoals(String awayGoals) {
		this.awayGoals = Utils.getIntFromString(awayGoals);
	}
	
	

}
