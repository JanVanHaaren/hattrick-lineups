package mcts.datastructure;

import java.util.ArrayList;

public abstract class ChoiceSet {
	
	public abstract ArrayList<ChoiceSet> expand();
	
	public abstract double valuation();
	
	public abstract int getDepth();
	
	public abstract boolean isComplete();
	
	public abstract double getSimulationResult();
	
	public abstract double getNeutralValue();
	
	public abstract boolean isNumeric();
	
	public abstract ChoiceSet getGreedyCompletion();
}
