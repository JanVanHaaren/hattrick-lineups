package api.entity.matchlineup;

import api.util.Utils;

public class Arena {
	
	private int arenaID;
	
	private String arenaName;

	public int getArenaID() {
		return arenaID;
	}

	public void setArenaID(String arenaID) {
		this.arenaID = Utils.getIntFromString(arenaID);
	}

	public String getArenaName() {
		return arenaName;
	}

	public void setArenaName(String arenaName) {
		this.arenaName = arenaName;
	}

}
