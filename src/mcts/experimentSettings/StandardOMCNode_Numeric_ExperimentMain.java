package mcts.experimentSettings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;
import mcts.datastructure.StandardOMCNode;
import mcts.hattrick.FillChoiceSet;
import mcts.hattrick.HattrickChoiceSet;
import mcts.hattrick.TeamGenerator;
import mcts.hattrick.TeamRatings;
import mcts.simulation.PureRandomSimulation;
import mcts.simulation.Simulation;
import api.LocalPaths;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.TrainerType;
import api.entity.playerdetails.Player;

public class StandardOMCNode_Numeric_ExperimentMain {
	public static void main(String[] args) {
		LocalPaths.createMCTSEvaluationDirectory();
		int x = 1;
		while(true)
		{
			pureRandomMain(x);
			vnukStatsRouletteMain(x);
			x++;
		}
	}
	
	public static void pureRandomMain(int nb)
	{
		System.out.println("+++NEW EXPERIMENT+++\n");
		System.out.println("---SELECTION = STANDARD OMC, SIMULATION = PURE RANDOM, RESULT = NUMERIC---\n"); //TODO: aanpassen
		ArrayList<String> teamFiles = new ArrayList<String>();
		teamFiles.add("team20_11");
		teamFiles.add("team30_11");
		teamFiles.add("team40_11");
		teamFiles.add("team50_11");
		teamFiles.add("team60_11");
		teamFiles.add("team70_11");
		
		
		ArrayList<TeamRatings> opponentRatingsList = TeamGenerator.readTeamRatingsFromFile(LocalPaths.TEAM_FILES + "opponent.txt");
		
		for(String  teamFile : teamFiles)
		{
			String evaluationFileName = LocalPaths.EVALUATION_FILES + "STANDARDOMC_NUMERIC_RANDOM_" + teamFile + "-" + nb + ".txt"; //TODO: aanpassen
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(evaluationFileName));
				System.out.println("***Working on: " + teamFile + "***\n");
				final ArrayList<Player> playerList = TeamGenerator.readTeamFromFile(LocalPaths.TEAM_FILES + teamFile + ".txt");
	
				for(int t = 0; t < opponentRatingsList.size(); t++)
				{
					final TeamRatings opponentRatings = opponentRatingsList.get(t);
					System.out.println("OpponentRatings number " + t + ":\n" + opponentRatings);
					writer.write("OpponentRatings Number = " + t);
					writer.newLine();
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
							return true; //TODO: aanpassen
						}
						
						@Override
						protected boolean isHomeMatch() {
							return true;
						}
						
						@Override
						protected MCTSNode generateRoot(ChoiceSet choiceSet) {
							return new StandardOMCNode(null, choiceSet); //TODO: aanpassen
						}
						
						@Override
						protected int getMaxIterations() {
							return 1000;
						}
						
						@Override
						protected Simulation getSimulation() {
							return new PureRandomSimulation();
						}
					};
					for(int i = 1; i <= 50; i++) //TODO: aanpassen
					{
						System.out.println("\t\tMax iterations = " + i*1000);
						writer.write("\tMax iterations = " + i*1000);
						writer.newLine();
						
						ChoiceSet result = experiment.execute();
						writer.write("\t\t"+ result.getDepth()
								+ "\t" + result.getSimulationResult()
								+ "\t" + result.getGreedyCompletion().getSimulationResult());
						writer.newLine();
					}
				}
				System.out.println("");
				System.out.println("");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static void vnukStatsRouletteMain(int nb)
	{
		System.out.println("+++NEW EXPERIMENT+++\n");
		System.out.println("---SELECTION = STANDARDOMC, SIMULATION = ROULETTE, RESULT = NUMERIC---\n"); //TODO: aanpassen
		ArrayList<String> teamFiles = new ArrayList<String>();
		teamFiles.add("team20_11");
		teamFiles.add("team30_11");
		teamFiles.add("team40_11");
		teamFiles.add("team50_11");
		teamFiles.add("team60_11");
		teamFiles.add("team70_11");
		
		
		ArrayList<TeamRatings> opponentRatingsList = TeamGenerator.readTeamRatingsFromFile(LocalPaths.TEAM_FILES + "opponent.txt");
		
		for(String  teamFile : teamFiles)
		{
			String evaluationFileName = LocalPaths.EVALUATION_FILES + "STANDARDOMC_NUMERIC_ROULETTE_" + teamFile + "-" + nb + ".txt"; //TODO: aanpassen
			BufferedWriter writer = null;
			try {
				writer = new BufferedWriter(new FileWriter(evaluationFileName));
				System.out.println("***Working on: " + teamFile + "***\n");
				final ArrayList<Player> playerList = TeamGenerator.readTeamFromFile(LocalPaths.TEAM_FILES + teamFile + ".txt");
				
				for(int t = 0; t < opponentRatingsList.size(); t++)
				{
					final TeamRatings opponentRatings = opponentRatingsList.get(t);
					System.out.println("OpponentRatings number " + t + ":\n" + opponentRatings);
					writer.write("OpponentRatings Number = " + t);
					writer.newLine();
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
							return true; //TODO: aanpassen
						}
						
						@Override
						protected boolean isHomeMatch() {
							return true;
						}
						
						@Override
						protected MCTSNode generateRoot(ChoiceSet choiceSet) {
							return new StandardOMCNode(null, choiceSet); //TODO: aanpassen
						}
						
						@Override
						protected int getMaxIterations() {
							return 1000;
						}
						
						@Override
						protected Simulation getSimulation() {
							return new PureRandomSimulation();
						}
					};
					for(int i = 1; i <= 50; i++) //TODO: aanpassen
					{
						System.out.println("\t\tMax iterations = " + i*1000);
						writer.write("\tMax iterations = " + i*1000);
						writer.newLine();
						
						ChoiceSet result = experiment.execute();
						writer.write("\t\t"+ result.getDepth()
								+ "\t" + result.getSimulationResult()
								+ "\t" + result.getGreedyCompletion().getSimulationResult());
						writer.newLine();
					}
				}
				System.out.println("");
				System.out.println("");
				writer.close();
			} catch (IOException e) {
				e.printStackTrace();
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
