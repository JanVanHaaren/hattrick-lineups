package api.entity;

import api.util.Utils;

public class Training extends Entity{
	private int morale;
	
	private int confidence;
	
	public Training() { }

	public Training(int morale, int confidence) {
		this.morale = morale;
		this.confidence = confidence;
	}

	public int getMorale() {
		return morale;
	}

	public void setMorale(String morale) {
		this.morale = Utils.getIntFromString(morale);
	}

	public int getConfidence() {
		return confidence;
	}

	public void setConfidence(String confidence) {
		this.confidence = Utils.getIntFromString(confidence);
	}
	
	

}
