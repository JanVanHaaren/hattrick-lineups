package api.entity.matchdetails;

import api.util.Utils;

public class Referee {
	
	private int refereeId;
	
	private String refereeName;
	
	private int refereeCountryId;
	
	private String refereeCountryName;
	
	private int refereeTeamId;
	
	private String refereeTeamName;

	public int getRefereeId() {
		return refereeId;
	}

	public void setRefereeId(String refereeId) {
		this.refereeId = Utils.getIntFromString(refereeId);
	}

	public String getRefereeName() {
		return refereeName;
	}

	public void setRefereeName(String refereeName) {
		this.refereeName = refereeName;
	}

	public int getRefereeCountryId() {
		return refereeCountryId;
	}

	public void setRefereeCountryId(String refereeCountryId) {
		this.refereeCountryId = Utils.getIntFromString(refereeCountryId);
	}

	public String getRefereeCountryName() {
		return refereeCountryName;
	}

	public void setRefereeCountryName(String refereeCountryName) {
		this.refereeCountryName = refereeCountryName;
	}

	public int getRefereeTeamId() {
		return refereeTeamId;
	}

	public void setRefereeTeamId(String refereeTeamId) {
		this.refereeTeamId = Utils.getIntFromString(refereeTeamId);
	}

	public String getRefereeTeamName() {
		return refereeTeamName;
	}

	public void setRefereeTeamName(String refereeTeamName) {
		this.refereeTeamName = refereeTeamName;
	}
}
