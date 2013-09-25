package api.entity;

import api.entity.playerdetails.Player;
import api.util.Utils;

public class PlayerDetails extends Entity {
	
	public PlayerDetails() {
		super();
	}
	
	private Boolean userIsSupporter;
	
	private Player player;

	public Boolean getUserIsSupporter() {
		return userIsSupporter;
	}

	public void setUserIsSupporter(String userIsSupporter) {
		this.userIsSupporter = Utils.getBooleanFromString(userIsSupporter);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
