package api.entity.matchdetails;

import api.util.Utils;

public class Arena {
	
	private int arenaID;
	
	private String arenaName;
	
	private int weatherID;
	
	private int soldTotal;
	
	private int soldTerraces;
	
	private int soldBasic;
	
	private int soldRoof;
	
	private int soldVIP;

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

	public int getWeatherID() {
		return weatherID;
	}

	public void setWeatherID(String weatherID) {
		this.weatherID = Utils.getIntFromString(weatherID);
	}

	public int getSoldTotal() {
		return soldTotal;
	}

	public void setSoldTotal(String soldTotal) {
		this.soldTotal = Utils.getIntFromString(soldTotal);
	}

	public int getSoldTerraces() {
		return soldTerraces;
	}

	public void setSoldTerraces(String soldTerraces) {
		if(soldTerraces != null)
			this.soldTerraces = Utils.getIntFromString(soldTerraces);
	}

	public int getSoldBasic() {
		return soldBasic;
	}

	public void setSoldBasic(String soldBasic) {
		if(soldBasic != null)
			this.soldBasic = Utils.getIntFromString(soldBasic);
	}

	public int getSoldRoof() {
		return soldRoof;
	}

	public void setSoldRoof(String soldRoof) {
		if(soldRoof != null)
			this.soldRoof = Utils.getIntFromString(soldRoof);
	}

	public int getSoldVIP() {
		return soldVIP;
	}

	public void setSoldVIP(String soldVIP) {
		if(soldVIP != null)
			this.soldVIP = Utils.getIntFromString(soldVIP);
	}
	
}
