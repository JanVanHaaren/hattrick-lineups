package mcts.datastructure;

import java.util.ArrayList;

public abstract class ChoiceSet {
	
	public abstract ArrayList<ChoiceSet> expand();

}
