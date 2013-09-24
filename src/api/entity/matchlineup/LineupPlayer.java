package api.entity.matchlineup;

import api.util.Utils;

public class LineupPlayer extends Player {
	
	private float ratingStars;
	
	private float ratingStarsEndOfMatch;

	public float getRatingStars() {
		return ratingStars;
	}

	public void setRatingStars(String ratingStars) {
		if(ratingStars != null)
			this.ratingStars = Utils.getFloatFromString(ratingStars);
	}

	public float getRatingStarsEndOfMatch() {
		return ratingStarsEndOfMatch;
	}

	public void setRatingStarsEndOfMatch(String ratingStarsEndOfMatch) {
		if(ratingStarsEndOfMatch != null)
			this.ratingStarsEndOfMatch = Utils.getFloatFromString(ratingStarsEndOfMatch);
	}
	
	
}
