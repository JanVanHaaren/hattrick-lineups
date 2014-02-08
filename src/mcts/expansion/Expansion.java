package mcts.expansion;

import mcts.datastructure.MCTSNode;

public abstract class Expansion {

	public abstract MCTSNode expand(MCTSNode node);

}
