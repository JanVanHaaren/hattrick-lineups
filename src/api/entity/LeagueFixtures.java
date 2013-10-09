package api.entity;

import java.util.ArrayList;
import java.util.Collection;

import api.entity.leaguefixtures.Match;
import api.util.Utils;

public class LeagueFixtures extends Entity {
	
	private int LeagueLevelUnitID;
	
	private String LeagueLevelUnitName;
	
	private String Season;
	
	private Collection<Match> matches;

	public int getLeagueLevelUnitID() {
		return LeagueLevelUnitID;
	}

	public void setLeagueLevelUnitID(String leagueLevelUnitID) {
		LeagueLevelUnitID = Utils.getIntFromString(leagueLevelUnitID);
	}

	public String getLeagueLevelUnitName() {
		return LeagueLevelUnitName;
	}

	public void setLeagueLevelUnitName(String leagueLevelUnitName) {
		LeagueLevelUnitName = leagueLevelUnitName;
	}

	public String getSeason() {
		return Season;
	}

	public void setSeason(String season) {
		Season = season;
	}

	public Collection<Match> getMatches() {
		return new ArrayList<Match>(matches);
	}

	public void setMatches(Collection<Match> match) {
		this.matches = match;
	}
	
	

}
