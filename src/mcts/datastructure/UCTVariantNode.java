package mcts.datastructure;

public abstract class UCTVariantNode extends MCTSNode {

	private double c;
	
	public UCTVariantNode(MCTSNode parent, ChoiceSet choiceSet, double c) {
		super(parent, choiceSet);
		this.c = c;
	}
	
	protected double getC() {
		return c;
	}
}
