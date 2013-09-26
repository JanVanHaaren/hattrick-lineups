package api.entity;

import datatype.SourceSystem;
import datatype.SupporterTier;
import api.entity.matchdetails.Match;
import api.util.Utils;


public class MatchDetails extends Entity {
	
	private SourceSystem sourceSystem;
	
	private SupporterTier userSupporterTier;
	
	private Match match;
	
	public MatchDetails() {
		super();
	}

	public SourceSystem getSourceSystem() {
		return sourceSystem;
	}

	public void setSourceSystem(String sourceSystem) {
		this.sourceSystem = SourceSystem.getSourceSystem(sourceSystem);
	}

	public SupporterTier isUserIsSupporter() {
		return userSupporterTier;
	}

	public void setUserSupporterTier(String userSupporterTier) {
		this.userSupporterTier = SupporterTier.getSupporterTier(userSupporterTier);
	}

	public Match getMatch() {
		return match;
	}

	public void setMatch(Match match) {
		this.match = match;
	}
	
	
}
