package api.entity.matchlineup;

import datatype.MatchBehaviourID;
import datatype.MatchRoleID;
import api.util.Utils;

public class Player {
	
	private int playerID;
	
	private MatchRoleID roleID;
	
	private String firstName;
	
	private String lastName;

	private String nickName;
	
	private MatchBehaviourID behaviour;

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = Utils.getIntFromString(playerID);
	}

	public MatchRoleID getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = MatchRoleID.getMatchRoleID(roleID);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public MatchBehaviourID getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		if(behaviour != null)
			this.behaviour = MatchBehaviourID.getMatchBehaviourID(behaviour);
	}
	
	
}
