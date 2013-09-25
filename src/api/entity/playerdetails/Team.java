package api.entity.playerdetails;

import api.util.Utils;

public class Team {
	
	private int teamID;
	
	private String teamName;
	
	private int leagueID;

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

	public int getLeagueID() {
		return leagueID;
	}

	public void setLeagueID(String leagueID) {
		this.leagueID = Utils.getIntFromString(leagueID);
	}	
}
