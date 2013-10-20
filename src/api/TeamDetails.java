package api;

import api.entity.Entity;
import api.util.Utils;

public class TeamDetails extends Entity {
	
	private Integer teamId;
	
	private Integer leagueId;

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		if(teamId != null)
			this.teamId = Utils.getIntFromString(teamId);
	}

	public Integer getLeagueId() {
		return leagueId;
	}

	public void setLeagueId(String leagueId) {
		if(leagueId != null)
			this.leagueId = Utils.getIntFromString(leagueId);
	}
	
	public boolean isInLeague()
	{
		return (getLeagueId() != null);
	}
	
	

}
