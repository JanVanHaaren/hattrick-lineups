package api.entity.matchdetails;

import api.util.Utils;
import datatype.InjuryType;

public class Injury {
	
	private int injuryPlayerId;
	
	private String injuryPlayerName;
	
	private int injuryTeamId;
	
	private InjuryType injuryType;
	
	private int injuryMinute;

	public int getInjuryPlayerId() {
		return injuryPlayerId;
	}

	public void setInjuryPlayerId(String injuryPlayerId) {
		this.injuryPlayerId = Utils.getIntFromString(injuryPlayerId);
	}

	public String getInjuryPlayerName() {
		return injuryPlayerName;
	}

	public void setInjuryPlayerName(String injuryPlayerName) {
		this.injuryPlayerName = injuryPlayerName;
	}

	public int getInjuryTeamId() {
		return injuryTeamId;
	}

	public void setInjuryTeamId(String injuryTeamId) {
		this.injuryTeamId = Utils.getIntFromString(injuryTeamId);
	}

	public InjuryType getInjuryType() {
		return injuryType;
	}

	public void setInjuryType(String injuryType) {
		this.injuryType = InjuryType.getInjuryType(injuryType);
	}

	public int getInjuryMinute() {
		return injuryMinute;
	}

	public void setInjuryMinute(String injuryMinute) {
		this.injuryMinute = Utils.getIntFromString(injuryMinute);
	}
}
