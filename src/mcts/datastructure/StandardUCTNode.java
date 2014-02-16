package mcts.datastructure;

public class StandardUCTNode extends UCTVariantNode {

	public StandardUCTNode(MCTSNode parent, ChoiceSet choiceSet, double c) {
		super(parent, choiceSet, c);
	}
	
	@Override
	public double getSelectionValue() {
		return getValue() + getC()*Math.sqrt(Math.log(getParent().getVisits())/getVisits());
	}

	@Override
	protected MCTSNode generateChild(ChoiceSet choiceSet) {
		return new StandardUCTNode(this, choiceSet, getC());
	}
}
