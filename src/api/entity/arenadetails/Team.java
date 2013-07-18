package api.entity.arenadetails;

import api.util.Utils;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public class Team {

	private int teamID;

	private String teamName;

	public Team() {
		// NOP
	}

	public Team(String teamID, String teamName) {
		this.setTeamID(teamID);
		this.setTeamName(teamName);
	}

	public int getTeamID() {
		return this.teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = Utils.getIntFromString(teamID);
	}

	public String getTeamName() {
		return this.teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

}
