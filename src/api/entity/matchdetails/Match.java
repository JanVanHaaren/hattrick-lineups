package api.entity.matchdetails;

import java.text.ParseException;
import java.util.Calendar;
import java.util.Collection;

import datatype.MatchType;

import api.util.Utils;

public class Match {
	
	private int matchID;
	
	private MatchType matchType;
	
	private Calendar matchDate;
	
	private Calendar finishedDate;
	
	private Team homeTeam;
	
	private Team awayTeam;
	
	private Arena arena;
	
	private Collection<Goal> scorers;
	
	private Collection<Booking> bookings;
	
	private int possessionFirstHalfHome;

	private int possessionFirstHalfAway;
	
	private int possessionSecondHalfHome;
	
	private int possessionSecondHalfAway;

	public int getMatchID() {
		return matchID;
	}

	public void setMatchID(String matchID) {
		this.matchID = Utils.getIntFromString(matchID);
	}

	public MatchType getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = MatchType.getMatchType(matchType);
	}

	public Calendar getMatchDate() {
		return matchDate;
	}

	public void setMatchDate(String matchDate) {
		this.matchDate = Calendar.getInstance();
		
		try {
			this.getMatchDate().setTime(Utils.getHattrickDateFormat().parse(matchDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Calendar getFinishedDate() {
		return finishedDate;
	}

	public void setFinishedDate(String finishedDate) {
		this.finishedDate = Calendar.getInstance();
		
		try {
			this.getMatchDate().setTime(Utils.getHattrickDateFormat().parse(finishedDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Arena getArena() {
		return arena;
	}

	public void setArena(Arena arena) {
		this.arena = arena;
	}

	public Collection<Goal> getScorers() {
		return scorers;
	}

	public void setScorers(Collection<Goal> scorers) {
		this.scorers = scorers;
	}

	public Collection<Booking> getBookings() {
		return bookings;
	}

	public void setBookings(Collection<Booking> bookings) {
		this.bookings = bookings;
	}

	public int getPossessionFirstHalfHome() {
		return possessionFirstHalfHome;
	}

	public void setPossessionFirstHalfHome(String possessionFirstHalfHome) {
		this.possessionFirstHalfHome = Utils.getIntFromString(possessionFirstHalfHome);
	}

	public int getPossessionFirstHalfAway() {
		return possessionFirstHalfAway;
	}

	public void setPossessionFirstHalfAway(String possessionFirstHalfAway) {
		this.possessionFirstHalfAway = Utils.getIntFromString(possessionFirstHalfAway);
	}

	public int getPossessionSecondHalfHome() {
		return possessionSecondHalfHome;
	}

	public void setPossessionSecondHalfHome(String possessionSecondHalfHome) {
		this.possessionSecondHalfHome = Utils.getIntFromString(possessionSecondHalfHome);
	}

	public int getPossessionSecondHalfAway() {
		return possessionSecondHalfAway;
	}

	public void setPossessionSecondHalfAway(String possessionSecondHalfAway) {
		this.possessionSecondHalfAway = Utils.getIntFromString(possessionSecondHalfAway);
	}
	
	
	
//	public void setRebuiltDate(String rebuiltDate) {
//		this.rebuiltDate = Calendar.getInstance();
//
//		try {
//			this.getRebuiltDate().setTime(Utils.getHattrickDateFormat().parse(rebuiltDate));
//		}
//		catch (ParseException e) {
//			e.printStackTrace();
//		}
//	}

}
