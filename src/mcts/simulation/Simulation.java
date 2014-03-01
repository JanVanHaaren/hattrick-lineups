package mcts.simulation;

import java.util.ArrayList;

import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;

public abstract class Simulation {

	public double simulate(MCTSNode node)
	{
		ChoiceSet currentChoiceSet = node.getChoiceSet();
		while(!currentChoiceSet.isComplete())
		{
			ArrayList<ChoiceSet> extensions = currentChoiceSet.expand();
			currentChoiceSet = selectChoice(extensions);
		}
		
		return currentChoiceSet.getSimulationResult();
	}
	
	protected abstract ChoiceSet selectChoice(ArrayList<ChoiceSet> extensions);
}
