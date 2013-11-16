package api.ratingpredictor;

import java.io.IOException;

import api.HattrickObjectCreator;
import api.entity.datatype.MatchBehaviourID;
import api.entity.playerdetails.Player;
import api.exception.IllegalXMLException;

public class PlayerBehaviour {
	
	private Player player;
	private MatchBehaviourID behaviour;
	
	public PlayerBehaviour(Integer playerID, MatchBehaviourID behaviour) throws IOException, IllegalXMLException {
		this.player = new HattrickObjectCreator().getPlayerDetails(playerID).getPlayer();
		this.behaviour = behaviour;
	}
	
	public PlayerBehaviour(Player player, MatchBehaviourID behaviour) throws IOException, IllegalXMLException {
		this.player = player;
		this.behaviour = behaviour;
	}

	public Player getPlayer() {
		return player;
	}

	public MatchBehaviourID getBehaviour() {
		return behaviour;
	}
}
