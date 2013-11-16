package api.entity.matchlineup;

import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.OrderType;
import api.util.Utils;

public class Substitution {
	
	private int teamID;
	
	private int subjectPlayerID;
	
	private int objectPlayerID;
	
	private OrderType orderType;
	
	private MatchRoleID newPositionID;
	
	private MatchBehaviourID newPositionBehaviour;
	
	private int matchMinute;

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = Utils.getIntFromString(teamID);
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

	public OrderType getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = OrderType.getOrderType(orderType);
	}

	public MatchRoleID getNewPositionID() {
		return newPositionID;
	}

	public void setNewPositionID(String newPositionID) {
		this.newPositionID = MatchRoleID.getMatchRoleID(newPositionID);
	}

	public MatchBehaviourID getNewPositionBehaviour() {
		return newPositionBehaviour;
	}

	public void setNewPositionBehaviour(String newPositionBehaviour) {
		this.newPositionBehaviour = MatchBehaviourID.getMatchBehaviourID(newPositionBehaviour);
	}

	public int getMatchMinute() {
		return matchMinute;
	}

	public void setMatchMinute(String matchMinute) {
		this.matchMinute = Utils.getIntFromString(matchMinute);
	}
	
	
}
