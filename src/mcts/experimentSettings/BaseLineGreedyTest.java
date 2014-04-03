package mcts.experimentSettings;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mcts.hattrick.FillChoiceSet;
import mcts.hattrick.TeamGenerator;
import mcts.hattrick.TeamRatings;
import api.LocalPaths;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.TrainerType;
import api.entity.playerdetails.Player;

public class BaseLineGreedyTest {
	public static void main(String[] args) throws IOException {
		ArrayList<String> teamFiles = new ArrayList<String>();
		teamFiles.add("team20_11");
		teamFiles.add("team30_11");
		teamFiles.add("team40_11");
		teamFiles.add("team50_11");
		teamFiles.add("team60_11");
		teamFiles.add("team70_11");
		
		ArrayList<TeamRatings> opponentRatingsList = TeamGenerator.readTeamRatingsFromFile(LocalPaths.TEAM_FILES + "opponent.txt");
		System.out.println(opponentRatingsList.size());
		
		for(String  teamFile : teamFiles)
		{
			ArrayList<Player> playerList = TeamGenerator.readTeamFromFile(LocalPaths.TEAM_FILES + teamFile + ".txt");
			BufferedWriter writer = new BufferedWriter(new FileWriter(LocalPaths.EVALUATION_FILES + "baseLine2_" + teamFile + ".txt"));
			
			for(int t = 0; t < opponentRatingsList.size(); t++)
			{
				writer.write("OpponentRatings Number = " + t);
				writer.newLine();
				TeamRatings opponentRatings = opponentRatingsList.get(t);
				FillChoiceSet choiceSet = new FillChoiceSet(TrainerType.BALANCED, new Training(4, 4), opponentRatings, false, true, playerList, getBasicPositions());
				
				writer.write("\t" + choiceSet.getGreedyCompletion().getSimulationResult());
				writer.newLine();
			}
			writer.close();
		}
	}
	
	public static double getNumericBaseLine(String teamName, int opponentNumber)
	{
		ArrayList<Player> playerList = TeamGenerator.readTeamFromFile(LocalPaths.TEAM_FILES + teamName + ".txt");
		TeamRatings opponentRatings = TeamGenerator.readTeamRatingsFromFile(LocalPaths.TEAM_FILES + "opponent.txt").get(opponentNumber);
		FillChoiceSet choiceSet = new FillChoiceSet(TrainerType.BALANCED, new Training(4, 4), opponentRatings, true, true, playerList, getBasicPositions());
		return choiceSet.getGreedyCompletion().getSimulationResult();
	}
	
	public static double getNominalBaseLine(String teamName, int opponentNumber)
	{
		ArrayList<Player> playerList = TeamGenerator.readTeamFromFile(LocalPaths.TEAM_FILES + teamName + ".txt");
		TeamRatings opponentRatings = TeamGenerator.readTeamRatingsFromFile(LocalPaths.TEAM_FILES + "opponent.txt").get(opponentNumber);
		FillChoiceSet choiceSet = new FillChoiceSet(TrainerType.BALANCED, new Training(4, 4), opponentRatings, false, true, playerList, getBasicPositions());
		return choiceSet.getGreedyCompletion().getSimulationResult();
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
