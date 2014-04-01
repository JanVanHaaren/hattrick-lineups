package mcts.experimentSettings;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;
import mcts.datastructure.OMCVariantNode;
import mcts.datastructure.PBBMNode;
import mcts.datastructure.StandardOMCNode;
import mcts.hattrick.FillChoiceSet;
import mcts.hattrick.TeamRatings;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.SpecialtyID;
import api.entity.datatype.TrainerType;
import api.entity.playerdetails.Player;

public class NodeSpeedTest {
	public static void main(String[] args) {
		
		selectionPropagationTiming();
	}
	
	private static void partialUrgencyTiming()
	{
		long curTime = System.currentTimeMillis();
		for(int i = 0; i <= 100000; i++)
		{
			Math.pow(2.854,2);
			Math.pow(7.697,2);
		}
		System.out.println("Powtime: " + (System.currentTimeMillis() - curTime));
		
		curTime = System.currentTimeMillis();
		for(int i = 0; i <= 100000; i++)
		{
			Math.sqrt(25.598);
		}
		System.out.println("Sqrttime: " + (System.currentTimeMillis() - curTime));
		
		curTime = System.currentTimeMillis();
		for(int i = 0; i <= 100000; i++)
		{
			Math.exp(-7.598);
		}
		System.out.println("exptime: " + (System.currentTimeMillis() - curTime));
		
		curTime = System.currentTimeMillis();
		for(int i = 0; i <= 100000; i++)
		{
			Math.exp(-2.4*(2)/Math.sqrt(2*(Math.pow(2.8568,2) + Math.pow(4.25687,2))));
		}
		System.out.println("tottime: " + (System.currentTimeMillis() - curTime));
		
		
	}
	
	private static void fairnessUrgencyTiming()
	{
		OMCVariantNode root = new StandardOMCNode(null, getChoiceSet());
		root.expand();
		for(MCTSNode node : root.getChildren())
			node.backPropagate(Math.random()*5);

		
		System.out.println("urgencyTime: " + ((OMCVariantNode) root.getChildren().get(0)).getUrgencyTiming());
		System.out.println("fairnessTime: " + ((OMCVariantNode) root.getChildren().get(0)).getFairnessTiming());
	}

	private static void selectionPropagationTiming() {
		MCTSNode root = new PBBMNode(null, getChoiceSet());
		root.expand();
		for(MCTSNode node : root.getChildren())
			node.backPropagate(Math.random()*5);

		long selectionTime = 0;
		long propagationTime = 0;
		
		for(int i = 0; i <= 1000; i++)
		{
			System.out.println(i);
			long curTime = System.currentTimeMillis();
			MCTSNode node = root.select();
			selectionTime += System.currentTimeMillis() - curTime;
			curTime = System.currentTimeMillis();
			node.backPropagate(3.5);
			propagationTime += System.currentTimeMillis() - curTime;
		}
		
		System.out.println("selectionTime: " + selectionTime);
		System.out.println("propagationTime: " + propagationTime);
	}
	
	private static ChoiceSet getChoiceSet() {
		return new FillChoiceSet(TrainerType.BALANCED, new Training(4,4), new TeamRatings(20,20,20,20,20,20,20), true, false, getPlayers(), getPositions());
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
}
