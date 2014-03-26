package mcts.simulation;

import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;

public abstract class Simulation {

	public ChoiceSet complete(ChoiceSet choiceSet)
	{
		ChoiceSet currentChoiceSet = choiceSet;
		while(!currentChoiceSet.isComplete())
		{
			currentChoiceSet = selectExtension(currentChoiceSet);
		}
		
		return currentChoiceSet;
	}
	
	public double simulate(MCTSNode node)
	{
		ChoiceSet currentChoiceSet = node.getChoiceSet();
		while(!currentChoiceSet.isComplete())
		{
			currentChoiceSet = selectExtension(currentChoiceSet);
		}
		
		return currentChoiceSet.getSimulationResult();
	}
	
	protected abstract ChoiceSet selectExtension(ChoiceSet choiceSet);
}
