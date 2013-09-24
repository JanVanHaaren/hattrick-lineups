package api.entity.matchdetails;

import api.util.Utils;

public class Booking {

	private int bookingPlayerID;
	
	private String bookingPlayerName;
	
	private int bookingTeamID;
	
	private int bookingType;
	
	private int bookingMinute;

	public int getBookingPlayerID() {
		return bookingPlayerID;
	}

	public void setBookingPlayerID(String bookingPlayerID) {
		this.bookingPlayerID = Utils.getIntFromString(bookingPlayerID);
	}

	public String getBookingPlayerName() {
		return bookingPlayerName;
	}

	public void setBookingPlayerName(String bookingPlayerName) {
		this.bookingPlayerName = bookingPlayerName;
	}

	public int getBookingTeamID() {
		return bookingTeamID;
	}

	public void setBookingTeamID(String bookingTeamID) {
		this.bookingTeamID = Utils.getIntFromString(bookingTeamID);
	}

	public int getBookingType() {
		return bookingType;
	}

	public void setBookingType(String bookingType) {
		this.bookingType = Utils.getIntFromString(bookingType);
	}

	public int getBookingMinute() {
		return bookingMinute;
	}

	public void setBookingMinute(String bookingMinute) {
		this.bookingMinute = Utils.getIntFromString(bookingMinute);
	}
	
	
}
