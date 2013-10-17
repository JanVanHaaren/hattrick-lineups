package api.entity;

import api.datatype.MatchType;
import api.entity.matchlineup.Arena;
import api.entity.matchlineup.Team;
import api.entity.matchlineup.TeamIdentifier;
import api.util.Utils;

public class MatchLineup extends Entity{
	
	public MatchLineup() {
		super();
	}
	
	private boolean isYouth;
	
	private int matchID;
	
	private TeamIdentifier homeTeam;
	
	private TeamIdentifier awayTeam;
	
	private MatchType matchType;
	
	private Arena arena;
	
	private Team team;

	public boolean isYouth() {
		return isYouth;
	}

	public void setYouth(String isYouth) {
		this.isYouth = Utils.getBooleanFromString(isYouth);
	}

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = Utils.getIntFromString(matchID);
	}

	public TeamIdentifier getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(TeamIdentifier homeTeam) {
		this.homeTeam = homeTeam;
	}

	public TeamIdentifier getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(TeamIdentifier awayTeam) {
		this.awayTeam = awayTeam;
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = MatchType.getMatchType(matchType);
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Team getTeam() {
		return team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}
}
