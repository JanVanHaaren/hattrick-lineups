package mcts.experimentSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mcts.algorithm.MCTSAlgorithm;
import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;
import mcts.hattrick.HattrickChoiceSet;
import mcts.hattrick.TeamRatings;
import mcts.simulation.Simulation;
import api.LocalPaths;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.TrainerType;
import api.entity.playerdetails.Player;

public abstract class Experiment {
	
	private MCTSAlgorithm algorithm;
	
	public Experiment()
	{
		HattrickChoiceSet rootChoiceSet = getRootChoiceSet(getTrainerType(), getTraining(),
				getOpponentRatings(), isNumeric(), isHomeMatch(),
				getPlayers(), getPositions());
		this.algorithm = new MCTSAlgorithm(getMaxIterations(),
				generateRoot(rootChoiceSet), getSimulation());
	}
	
	protected abstract ArrayList<Player> getPlayers();
	protected abstract Map<MatchRoleID, ArrayList<MatchBehaviourID>> getPositions();
	protected abstract TrainerType getTrainerType();
	protected abstract Training getTraining();
	protected abstract TeamRatings getOpponentRatings();
	protected abstract HattrickChoiceSet getRootChoiceSet(TrainerType trainerType, Training training,
			TeamRatings opponentRatings, boolean numeric, boolean homeMatch, ArrayList<Player> players,
			Map<MatchRoleID, ArrayList<MatchBehaviourID>> positions);
	protected abstract boolean isNumeric();
	protected abstract boolean isHomeMatch();
	
	protected abstract MCTSNode generateRoot(ChoiceSet choiceSet);
	protected abstract int getMaxIterations();
	protected abstract Simulation getSimulation();
	
	public ChoiceSet execute()
	{
		return algorithm.execute();
	}
	
	private static Map<MatchRoleID, ArrayList<MatchBehaviourID>> getBasicPositions() {
		Map<MatchRoleID, ArrayList<MatchBehaviourID>> positions = new HashMap<MatchRoleID, ArrayList<MatchBehaviourID>>();
		ArrayList<MatchBehaviourID> onlyNormal = new ArrayList<MatchBehaviourID>();
		onlyNormal.add(MatchBehaviourID.NORMAL);
		positions.put(MatchRoleID.KEEPER, onlyNormal);
		positions.put(MatchRoleID.LEFT_CENTRAL_DEFENDER, onlyNormal);
		positions.put(MatchRoleID.RIGHT_CENTRAL_DEFENDER, onlyNormal);
		positions.put(MatchRoleID.MIDDLE_CENTRAL_DEFENDER, onlyNormal);
		positions.put(MatchRoleID.LEFT_INNER_MIDFIELD, onlyNormal);
		positions.put(MatchRoleID.RIGHT_INNER_MIDFIELD, onlyNormal);
		positions.put(MatchRoleID.MIDDLE_INNER_MIDFIELD, onlyNormal);
		positions.put(MatchRoleID.LEFT_WINGER, onlyNormal);
		positions.put(MatchRoleID.RIGHT_WINGER, onlyNormal);
		positions.put(MatchRoleID.LEFT_FORWARD, onlyNormal);
		positions.put(MatchRoleID.RIGHT_FORWARD, onlyNormal);
		return positions;
	}
}