package mcts.experimentSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mcts.algorithm.MCTSAlgorithm;
import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;
import mcts.hattrick.FillChoiceSet;
import mcts.hattrick.HattrickChoiceSet;
import mcts.hattrick.TeamGenerator;
import mcts.hattrick.TeamRatings;
import mcts.simulation.Simulation;
import api.LocalPaths;
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
		HattrickChoiceSet rootChoiceSet = getRootChoiceSet(getTrainerType(), getTraining(),
				getOpponentRatings(), isNumeric(), isHomeMatch(),
				getPlayers(), getPositions());
		MCTSAlgorithm algorithm = new MCTSAlgorithm(getMaxIterations(),
				generateRoot(rootChoiceSet), getSimulation());
		return algorithm.execute();
	}
	
	public static void main(String[] args) {
		ArrayList<String> teamFiles = new ArrayList<String>();
		teamFiles.add(LocalPaths.TEAM_FILES + "team20_11.txt");
		teamFiles.add(LocalPaths.TEAM_FILES + "team30_11.txt");
		teamFiles.add(LocalPaths.TEAM_FILES + "team40_11.txt");
		teamFiles.add(LocalPaths.TEAM_FILES + "team50_11.txt");
		teamFiles.add(LocalPaths.TEAM_FILES + "team60_11.txt");
		teamFiles.add(LocalPaths.TEAM_FILES + "team70_11.txt");
		
		ArrayList<ArrayList<Player>> playerLists = new ArrayList<ArrayList<Player>>();
		for(String teamFile : teamFiles)
			playerLists.add(TeamGenerator.readTeamFromFile(teamFile));
		
		ArrayList<TeamRatings> opponentRatingsList = TeamGenerator.readTeamRatingsFromFile(LocalPaths.TEAM_FILES + "opponents.txt");
		
		for(final ArrayList<Player> playerList : playerLists)
		{
			for(final TeamRatings opponentRatings : opponentRatingsList)
			{
				Experiment experiment = new Experiment(){
					@Override
					protected ArrayList<Player> getPlayers() {
						return playerList;
					}
	
					@Override
					protected Map<MatchRoleID, ArrayList<MatchBehaviourID>> getPositions() {
						return getBasicPositions();
					}
	
					@Override
					protected TrainerType getTrainerType() {
						return TrainerType.BALANCED;
					}
	
					@Override
					protected Training getTraining() {
						return new Training(4,4);
					}
	
					@Override
					protected TeamRatings getOpponentRatings() {
						return opponentRatings;
					}
	
					@Override
					protected HattrickChoiceSet getRootChoiceSet(
							TrainerType trainerType, Training training,
							TeamRatings opponentRatings, boolean numeric,
							boolean homeMatch, ArrayList<Player> players,
							Map<MatchRoleID, ArrayList<MatchBehaviourID>> positions) {
						return new FillChoiceSet(trainerType, training, opponentRatings, numeric, homeMatch, players, positions);
					}
	
					@Override
					protected boolean isNumeric() {
						// TODO Auto-generated method stub
						return false;
					}
	
					@Override
					protected boolean isHomeMatch() {
						// TODO Auto-generated method stub
						return false;
					}
	
					@Override
					protected MCTSNode generateRoot(ChoiceSet choiceSet) {
						// TODO Auto-generated method stub
						return null;
					}
	
					@Override
					protected int getMaxIterations() {
						// TODO Auto-generated method stub
						return 0;
					}
	
					@Override
					protected Simulation getSimulation() {
						// TODO Auto-generated method stub
						return null;
					}
				};
			}
		}
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