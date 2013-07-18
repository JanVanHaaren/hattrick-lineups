package api.entity.arenadetails;

import api.util.Utils;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public class Region {

	private int regionID;

	private String regionName;

	public Region() {
		// NOP
	}

	public Region(String regionID, String regionName) {
		this.setRegionID(regionID);
		this.setRegionName(regionName);
	}

	public int getRegionID() {
		return this.regionID;
	}

	public void setRegionID(String regionID) {
		this.regionID = Utils.getIntFromString(regionID);
	}

	public String getRegionName() {
		return this.regionName;
	}

	public void setRegionName(String regionName) {
		this.regionName = regionName;
	}

}
