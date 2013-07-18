package api.entity.arenadetails;

import api.util.Utils;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public class Arena {

	private int arenaID;

	private String arenaName;

	private Team team;

	private League league;

	private Region region;

	private CurrentCapacity currentCapacity;

	private ExtendedCapacity extendedCapacity;

	public Arena() {
		// NOP
	}

	public Arena(String arenaID, String arenaName) {
		this.setArenaID(arenaID);
		this.setArenaName(arenaName);
	}

	public int getArenaID() {
		return this.arenaID;
	}

	public void setArenaID(String arenaID) {
		this.arenaID = Utils.getIntFromString(arenaID);
	}

	public String getArenaName() {
		return this.arenaName;
	}

	public void setArenaName(String arenaName) {
		this.arenaName = arenaName;
	}

	public Team getTeam() {
		return this.team;
	}

	public void setTeam(Team team) {
		this.team = team;
	}

	public League getLeague() {
		return this.league;
	}

	public void setLeague(League league) {
		this.league = league;
	}

	public Region getRegion() {
		return this.region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public CurrentCapacity getCurrentCapacity() {
		return this.currentCapacity;
	}

	public void setCurrentCapacity(CurrentCapacity currentCapacity) {
		this.currentCapacity = currentCapacity;
	}

	public ExtendedCapacity getExtendedCapacity() {
		return this.extendedCapacity;
	}

	public void setExtendedCapacity(ExtendedCapacity extendedCapacity) {
		this.extendedCapacity = extendedCapacity;
	}

}
