package api.entity;

import api.util.Utils;

public class TeamDetails extends Entity {
	
	private Integer teamId;
	
	private Integer leagueId;
	
	private Boolean isBot;

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

	public boolean isBot() {
		return isBot;
	}
	
	public void setBot(String isBot)
	{
		if(isBot != null)
			this.isBot = Utils.getBooleanFromString(isBot);
	}
	
	

}
