package api.entity.playerdetails;

import java.text.ParseException;
import java.util.Calendar;


import api.datatype.MatchRoleID;
import api.util.Utils;

public class Match {
	
	private Calendar date;
	
	private int matchId;
	
	private MatchRoleID positionCode;
	
	private int playedMinutes;
	
	private Float rating;
	
	private Float ratingEndOfGame;

	public Calendar getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = Calendar.getInstance();

		try {
			this.getDate().setTime(Utils.getHattrickDateFormat().parse(date));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public int getMatchId() {
		return matchId;
	}

	public void setMatchId(String matchId) {
		this.matchId = Utils.getIntFromString(matchId);
	}

	public MatchRoleID getPositionCode() {
		return positionCode;
	}

	public void setPositionCode(String positionCode) {
		this.positionCode = MatchRoleID.getMatchRoleID(positionCode);
	}

	public int getPlayedMinutes() {
		return playedMinutes;
	}

	public void setPlayedMinutes(String playedMinutes) {
		this.playedMinutes = Utils.getIntFromString(playedMinutes);
	}

	public Float getRating() {
		return rating;
	}

	public void setRating(String rating) {
		this.rating = Utils.getFloatFromString(rating);
	}

	public Float getRatingEndOfGame() {
		return ratingEndOfGame;
	}

	public void setRatingEndOfGame(String ratingEndOfGame) {
		this.ratingEndOfGame = Utils.getFloatFromString(ratingEndOfGame);
	}

	
}
