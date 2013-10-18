package api.entity.matchesarchive;

import api.util.Utils;

public class TeamIdentifier {

	private int teamID;
	
	private String teamName;

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
	
	
}
