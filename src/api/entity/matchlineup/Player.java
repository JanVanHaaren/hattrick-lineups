package api.entity.matchlineup;

import api.util.Utils;

public class Player {
	
	private int playerID;
	
	private int roleID;
	
	private String firstName;
	
	private String lastName;

	private String nickName;
	
	private int behaviour;

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = Utils.getIntFromString(playerID);
	}

	public int getRoleID() {
		return roleID;
	}

	public void setRoleID(String roleID) {
		this.roleID = Utils.getIntFromString(roleID);
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

	public int getBehaviour() {
		return behaviour;
	}

	public void setBehaviour(String behaviour) {
		if(behaviour != null)
			this.behaviour = Utils.getIntFromString(behaviour);
	}
	
	
}
