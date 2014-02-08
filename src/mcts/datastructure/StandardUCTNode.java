package mcts.datastructure;

public class StandardUCTNode extends UCTVariantNode {

	public StandardUCTNode(MCTSNode parent, double c) {
		super(parent, c);
	}
	
	@Override
	public double getSelectionValue() {
		return getValue() + getC()*Math.sqrt(Math.log(getParent().getVisits())/getVisits());
	}
}
