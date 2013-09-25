package api.entity.matchdetails;

import api.util.Utils;
import datatype.WeatherID;

public class Arena {
	
	private int arenaID;
	
	private String arenaName;
	
	private WeatherID weatherID;
	
	private Integer soldTotal;
	
	private Integer soldTerraces;
	
	private Integer soldBasic;
	
	private Integer soldRoof;
	
	private Integer soldVIP;

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

	public WeatherID getWeatherID() {
		return weatherID;
	}

	public void setWeatherID(String weatherID) {
		this.weatherID = WeatherID.getWeatherID(weatherID);
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
