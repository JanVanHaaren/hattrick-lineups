package api.entity.matchdetails;

import api.util.Utils;

public class Event {
	
	private int minute;
	
	private int eventTypeID;
	
	private int eventVariation;
	
	private String eventText;
	
	private int subjectTeamID;
	
	private int subjectPlayerID;
	
	private int objectPlayerID;

	public int getMinute() {
		return minute;
	}

	public void setMinute(String minute) {
		this.minute = Utils.getIntFromString(minute);
	}

	public int getEventTypeID() {
		return eventTypeID;
	}

	public void setEventTypeID(String eventTypeID) {
		this.eventTypeID = Utils.getIntFromString(eventTypeID);
	}

	public int getEventVariation() {
		return eventVariation;
	}

	public void setEventVariation(String eventVariation) {
		this.eventVariation = Utils.getIntFromString(eventVariation);
	}

	public String getEventText() {
		return eventText;
	}

	public void setEventText(String eventText) {
		this.eventText = eventText;
	}

	public int getSubjectTeamID() {
		return subjectTeamID;
	}

	public void setSubjectTeamID(String subjectTeamID) {
		this.subjectTeamID = Utils.getIntFromString(subjectTeamID);
	}

	public int getSubjectPlayerID() {
		return subjectPlayerID;
	}

	public void setSubjectPlayerID(String subjectPlayerID) {
		this.subjectPlayerID = Utils.getIntFromString(subjectPlayerID);
	}

	public int getObjectPlayerID() {
		return objectPlayerID;
	}

	public void setObjectPlayerID(String objectPlayerID) {
		this.objectPlayerID = Utils.getIntFromString(objectPlayerID);
	}
	
	
}
