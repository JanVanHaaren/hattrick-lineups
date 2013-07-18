package api.entity.arenadetails;

import api.util.Utils;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public class League {

	private int leagueID;

	private String leagueName;

	public League() {
		// NOP
	}

	public League(String leagueID, String leagueName) {
		this.setLeagueID(leagueID);
		this.setLeagueName(leagueName);
	}

	public int getLeagueID() {
		return this.leagueID;
	}

	public void setLeagueID(String leagueID) {
		this.leagueID = Utils.getIntFromString(leagueID);
	}

	public String getLeagueName() {
		return this.leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

}
