package api.entity.matchlineup;

import java.util.ArrayList;
import java.util.Collection;


import api.datatype.SkillLevel;
import api.util.Utils;

public class Team {
	
	private int teamID;
	
	private String teamName;
	
	private SkillLevel experienceLevel;
	
	private Collection<Player> startingLineup;
	
	private Collection<Substitution> substitutions;
	
	private Collection<LineupPlayer> lineup;

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

	public SkillLevel getExperienceLevel() {
		return experienceLevel;
	}

	public void setExperienceLevel(String experienceLevel) {
		this.experienceLevel = new SkillLevel(experienceLevel);
	}

	public Collection<Player> getStartingLineup() {
		return new ArrayList<Player>(startingLineup);
	}

	public void setStartingLineup(Collection<Player> startingLineup) {
		this.startingLineup = startingLineup;
	}

	public Collection<Substitution> getSubstitutions() {
		return new ArrayList<Substitution>(substitutions);
	}

	public void setSubstitutions(Collection<Substitution> substitutions) {
		this.substitutions = substitutions;
	}

	public Collection<LineupPlayer> getLineup() {
		return new ArrayList<LineupPlayer>(lineup);
	}

	public void setLineup(Collection<LineupPlayer> lineup) {
		this.lineup = lineup;
	}
}
