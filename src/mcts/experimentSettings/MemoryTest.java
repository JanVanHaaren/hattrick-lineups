package mcts.experimentSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;
import mcts.datastructure.PBBMNode;
import mcts.hattrick.FillChoiceSet;
import mcts.hattrick.HattrickChoiceSet;
import mcts.hattrick.TeamRatings;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.SpecialtyID;
import api.entity.datatype.TrainerType;
import api.entity.playerdetails.Player;

public class MemoryTest {
	public static void main(String[] args) {
		ChoiceSet choiceSet = getChoiceSet();
		MCTSNode root = new PBBMNode(null, choiceSet);
		
		int i = 0;
		while(true)
		{
			new PBBMNode(root, choiceSet.expand().get(0));
			i++;
			if(i % 1000 == 0)
				System.out.println(root.getChildren().size());
		}
	}
	
	private static ArrayList<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(new Player(-1, false, null, 6, 6, 7, 20, 10, 4, 4, 3, 5, 3)); //Fehér
		players.add(new Player(-1, false, null, 4, 7, 7, 20, 1, 10, 6, 3, 10, 4)); //Haberecht
		players.add(new Player(-1, false, null, 5, 7, 7, 20, 1, 13, 7, 4, 7, 3)); //Alba Tercedor
		players.add(new Player(-1, false, SpecialtyID.HEAD_SPECIALIST, 6, 7, 7, 20, 1, 13, 7, 5, 7, 3)); //Filar
		players.add(new Player(-1, true, SpecialtyID.QUICK, 5, 6, 7, 20, 1, 3, 11, 9, 16, 4)); //Clompen
		players.add(new Player(-1, false, null, 7, 8, 6, 20, 1, 6, 16, 8, 5, 2)); //Erdin
		players.add(new Player(-1, false, null, 5, 8, 8, 20, 1, 5, 12, 9, 8, 3)); //Havelange
		players.add(new Player(-1, false, null, 6, 8, 7, 20, 1, 5, 16, 10, 5, 2)); //Putek
		players.add(new Player(-1, false, SpecialtyID.QUICK, 4, 7, 8, 20, 1, 4, 10, 7, 15, 2)); //De Vadder
		players.add(new Player(-1, false, SpecialtyID.UNPERDICTABLE, 7, 8, 7, 20, 1, 3, 5, 9, 6, 12)); //Javier Moreno
		players.add(new Player(-1, false, null, 6, 6, 7, 20, 1, 4, 4, 9, 8, 12)); //Spacic
		return players;
	}

	private static Map<MatchRoleID, ArrayList<MatchBehaviourID>> getPositions() {
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

	private static TrainerType getTrainerType() {
		return TrainerType.BALANCED;
	}

	private static Training getTraining() {
		return new Training(4,4);
	}

	private static TeamRatings getAwayRatings() {
		TeamRatings awayRatings = new TeamRatings(20, 20, 20, 20, 20, 20, 20);
		return awayRatings;
	}

	private static HattrickChoiceSet getRootChoiceSet(TrainerType trainerType,
			Training training, TeamRatings awayRatings, boolean numeric, boolean homeMatch,
			ArrayList<Player> players,
			Map<MatchRoleID, ArrayList<MatchBehaviourID>> positions) {
		return new FillChoiceSet(trainerType, training, awayRatings, numeric, homeMatch, players, positions);
	}
	
	public static HattrickChoiceSet getChoiceSet()
	{
		return getRootChoiceSet(getTrainerType(), getTraining(), getAwayRatings(), false, false, getPlayers(), getPositions());
	}

}
