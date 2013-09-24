package api.entity.matchlineup;

import api.util.Utils;

public class TeamIdentifier {
	
	private int teamID;
	
	//volgens site een int
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
