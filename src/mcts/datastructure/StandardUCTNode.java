package mcts.datastructure;

import mcts.algorithm.Epsilon;

public class StandardUCTNode extends UCTVariantNode {

	public StandardUCTNode(MCTSNode parent, ChoiceSet choiceSet, double c) {
		super(parent, choiceSet, c);
	}
	
	@Override
	public double getSelectionValue() {
		if (getVisits() == 0)
			return getChoiceSet().getNeutralValue() + getC() + Math.random()*Epsilon.epsilon(); //TODO: + C?
		return getValue() + getC()*Math.sqrt(Math.log(getParent().getVisits())/getVisits());
	}

	@Override
	protected MCTSNode generateChild(ChoiceSet choiceSet) {
		return new StandardUCTNode(this, choiceSet, getC());
	}
}
