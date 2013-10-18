package api.entity;

import api.entity.matchesarchive.Team;

public class MatchesArchive extends Entity {
	
	private Team team;

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
