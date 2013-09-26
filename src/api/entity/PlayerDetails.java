package api.entity;

import datatype.SupporterTier;
import api.entity.playerdetails.Player;
import api.util.Utils;

public class PlayerDetails extends Entity {
	
	public PlayerDetails() {
		super();
	}
	
	private SupporterTier userSupporterTier;
	
	private Player player;

	public SupporterTier getUserSupporterTier() {
		return userSupporterTier;
	}

	public void setUserSupporterTier(String userSupporterTier) {
		this.userSupporterTier = SupporterTier.getSupporterTier(userSupporterTier);
	}

	public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}
	
	
}
