package api.entity.matchdetails;

import api.util.Utils;

public class Goal {
	
	private int scorerPlayerID;
	
	private String scorerPlayerName;
	
	private int scorerTeamID;
	
	private int scorerHomeGoals;
	
	private int scorerAwayGoals;
	
	private int scorerMinute;

	public int getScorerPlayerID() {
		return scorerPlayerID;
	}

	public void setScorerPlayerID(String scorerPlayerID) {
		this.scorerPlayerID = Utils.getIntFromString(scorerPlayerID);
	}

	public String getScorerPlayerName() {
		return scorerPlayerName;
	}

	public void setScorerPlayerName(String scorerPlayerName) {
		this.scorerPlayerName = scorerPlayerName;
	}

	public int getScorerTeamID() {
		return scorerTeamID;
	}

	public void setScorerTeamID(String scorerTeamID) {
		this.scorerTeamID = Utils.getIntFromString(scorerTeamID);
	}

	public int getScorerHomeGoals() {
		return scorerHomeGoals;
	}

	public void setScorerHomeGoals(String scorerHomeGoals) {
		this.scorerHomeGoals = Utils.getIntFromString(scorerHomeGoals);
	}

	public int getScorerAwayGoals() {
		return scorerAwayGoals;
	}

	public void setScorerAwayGoals(String scorerAwayGoals) {
		this.scorerAwayGoals = Utils.getIntFromString(scorerAwayGoals);
	}

	public int getScorerMinute() {
		return scorerMinute;
	}

	public void setScorerMinute(String scorerMinute) {
		this.scorerMinute = Utils.getIntFromString(scorerMinute);
	}

	
}
