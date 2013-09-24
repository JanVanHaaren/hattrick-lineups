package api.entity;

import api.entity.matchdetails.Match;
import api.util.Utils;


public class MatchDetails extends Entity {
	
	private boolean isYouth;
	
	private boolean userIsSupporter;
	
	private Match match;
	
	public MatchDetails()
	{
		super();
	}

	public boolean isYouth() {
		return isYouth;
	}

	public void setYouth(String isYouth) {
		this.isYouth = Utils.getBooleanFromString(isYouth);
	}

	public boolean isUserIsSupporter() {
		return userIsSupporter;
	}

	public void setUserIsSupporter(String userIsSupporter) {
		this.userIsSupporter = Utils.getBooleanFromString(userIsSupporter);
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	
}
