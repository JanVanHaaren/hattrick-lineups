package api.entity;

import java.text.ParseException;
import java.util.Calendar;

import api.util.Utils;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public abstract class Entity {

	private int userID;

	private final Calendar fetchedDate;

	public Entity() {
		this.fetchedDate = Calendar.getInstance();
	}

	public Entity(String userID, String fetchedDate) {
		this();
		this.setUserID(userID);
		this.setFetchedDate(fetchedDate);
	}

	public int getUserID() {
		return this.userID;
	}

	public void setUserID(String userID) {
		this.userID = Utils.getIntFromString(userID);
	}

	public Calendar getFetchedDate() {
		return this.fetchedDate;
	}

	public void setFetchedDate(String fetchedDate) {
		try {
			this.getFetchedDate().setTime(Utils.getHattrickDateFormat().parse(fetchedDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
