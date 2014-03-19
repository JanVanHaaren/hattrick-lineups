package mcts.datastructure;

import mcts.algorithm.Epsilon;

public class StandardUCTNode extends UCTVariantNode {

	public StandardUCTNode(MCTSNode parent, ChoiceSet choiceSet, double c) {
		super(parent, choiceSet, c);
	}
	
	@Override
	public double getSelectionValue() {
		return getValue() + getC()*Math.sqrt(Math.log(getParent().getVisits())/getVisits()) + Math.random()*Epsilon.epsilon();
	}

	@Override
	protected MCTSNode generateChild(ChoiceSet choiceSet) {
		return new StandardUCTNode(this, choiceSet, getC());
	}
}
