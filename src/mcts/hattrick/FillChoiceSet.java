package mcts.hattrick;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import mcts.datastructure.ChoiceSet;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.TrainerType;
import api.entity.playerdetails.Player;
import api.ratingpredictor.PlayerBehaviour;

public class FillChoiceSet extends HattrickChoiceSet{

	private ArrayList<Player> unassignedPlayers;
	private HashMap<MatchRoleID, ArrayList<MatchBehaviourID>> unassignedPositions;
	private HashMap<MatchRoleID,PlayerBehaviour> assignedMapping;
	
	public FillChoiceSet(TrainerType trainerType, Training training, TeamRatings awayRatings, boolean numeric,
			List<Player> unassignedPlayers,
			Map<MatchRoleID, ArrayList<MatchBehaviourID>> unassignedPositions) {
		this(trainerType, training, awayRatings, numeric,
				unassignedPlayers, unassignedPositions,
				new HashMap<MatchRoleID,PlayerBehaviour>());
	}
	
	public FillChoiceSet(TrainerType trainerType, Training training, TeamRatings awayRatings, boolean numeric,
			List<Player> unassignedPlayers,
			Map<MatchRoleID, ArrayList<MatchBehaviourID>> unassignedPositions,
			Map<MatchRoleID, PlayerBehaviour> assignedMapping) {
		super(trainerType, training, awayRatings, numeric);
		this.unassignedPlayers = new ArrayList<Player>(unassignedPlayers);
		this.unassignedPositions = new HashMap<MatchRoleID, ArrayList<MatchBehaviourID>>(unassignedPositions);
		this.assignedMapping = new HashMap<MatchRoleID,PlayerBehaviour>(assignedMapping);
	}

	
	
	private ArrayList<Player> getUnassignedPlayers() {
		return new ArrayList<Player>(unassignedPlayers);
	}

	private HashMap<MatchRoleID, ArrayList<MatchBehaviourID>> getUnassignedPositions() {
		return new HashMap<MatchRoleID, ArrayList<MatchBehaviourID>>(unassignedPositions);
	}

	private HashMap<MatchRoleID, PlayerBehaviour> getAssignedMapping() {
		return new HashMap<MatchRoleID, PlayerBehaviour>(assignedMapping);
	}

	@Override
	public ArrayList<ChoiceSet> expand() {
		ArrayList<ChoiceSet> expansion = new ArrayList<ChoiceSet>();
		for(MatchRoleID role : getUnassignedPositions().keySet())
			for(MatchBehaviourID behaviour : getUnassignedPositions().get(role))
				for(Player player : getUnassignedPlayers())
				{
					ArrayList<Player> expandedUnassignedPlayers = getUnassignedPlayers();
					Map<MatchRoleID, ArrayList<MatchBehaviourID>> expandedUnassignedPositions = getUnassignedPositions();
					Map<MatchRoleID, PlayerBehaviour> expandedAssignedMapping = getAssignedMapping();
					
					expandedUnassignedPlayers.remove(player);
					expandedUnassignedPositions.remove(role);
					expandedAssignedMapping.put(role, new PlayerBehaviour(player, behaviour));
					expansion.add(new FillChoiceSet(
							getTrainerType(), getTraining(), getAwayRatings(), isNumeric(),
							expandedUnassignedPlayers, expandedUnassignedPositions,
							expandedAssignedMapping));
				}
		return expansion;
	}

	@Override
	public boolean isComplete() {
		return assignedMapping.size() == 11;
	}

	@Override
	public Map<MatchRoleID, PlayerBehaviour> getFieldSetup() {
		return getAssignedMapping();
	}
}
