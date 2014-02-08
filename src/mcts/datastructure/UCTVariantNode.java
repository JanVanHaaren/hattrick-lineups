package mcts.datastructure;

public abstract class UCTVariantNode extends MCTSNode {

	private double c;
	
	public UCTVariantNode(MCTSNode parent, double c) {
		super(parent);
		this.c = c;
	}
	
	protected double getC() {
		return c;
	}
}
