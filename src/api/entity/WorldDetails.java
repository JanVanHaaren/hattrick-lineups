package api.entity;

import java.util.Map;

import api.entity.worlddetails.League;


public class WorldDetails extends Entity {
	
	private Map<Integer,League> leagueList;

	public Map<Integer, League> getLeagueList() {
		return leagueList;
	}

	public void setLeagueList(Map<Integer, League> leagueList) {
		this.leagueList = leagueList;
	}
	
}
