package mcts.experimentSettings;

import java.util.ArrayList;
import java.util.Map;

import mcts.algorithm.MCTSAlgorithm;
import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;
import mcts.hattrick.HattrickChoiceSet;
import mcts.hattrick.TeamRatings;
import mcts.simulation.Simulation;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.TrainerType;
import api.entity.playerdetails.Player;

public abstract class Experiment {
	
	protected abstract ArrayList<Player> getPlayers();
	protected abstract Map<MatchRoleID, ArrayList<MatchBehaviourID>> getPositions();
	protected abstract TrainerType getTrainerType();
	protected abstract Training getTraining();
	protected abstract TeamRatings getAwayRatings();
	protected abstract HattrickChoiceSet getRootChoiceSet(TrainerType trainerType, Training training,
			TeamRatings awayRatings, boolean numeric, ArrayList<Player> players,
			Map<MatchRoleID, ArrayList<MatchBehaviourID>> positions);
	protected abstract boolean isNumeric();
	
	protected abstract MCTSNode generateRoot(ChoiceSet choiceSet);
	protected abstract int getMaxIterations();
	protected abstract Simulation getSimulation();
	
	public double execute()
	{
		HattrickChoiceSet rootChoiceSet = getRootChoiceSet(getTrainerType(), getTraining(),
				getAwayRatings(), isNumeric(),
				getPlayers(), getPositions());
		MCTSAlgorithm algorithm = new MCTSAlgorithm(getMaxIterations(),
				generateRoot(rootChoiceSet), getSimulation());
		return algorithm.execute();
	}

}