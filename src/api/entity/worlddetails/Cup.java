package api.entity.worlddetails;

import api.util.Utils;

public class Cup {
	
	private int cupID;
	
	private String cupName;

	public int getCupID() {
		return cupID;
	}

	public void setCupID(String cupID) {
		this.cupID = Utils.getIntFromString(cupID);
	}

	public String getCupName() {
		return cupName;
	}

	public void setCupName(String cupName) {
		this.cupName = cupName;
	}
	
	

}
