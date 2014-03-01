package mcts.datastructure;


public class MCTSTree {
	
	private MCTSNode root;
	
	public MCTSTree(MCTSNode root) {
		super();
		this.root = root;
	}

	public MCTSNode getRoot(){
		return root;
	}
	
	public int getNodeCount(){
		return getRoot().getNodeCount();
	}
	
	public MCTSNode getBestNode()
	{
		MCTSNode bestNode = getRoot().getMaxChild();
		while(bestNode.getMaxChild() != null)
			bestNode = bestNode.getMaxChild();
		return bestNode;
	}
}
