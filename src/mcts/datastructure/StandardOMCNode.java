package mcts.datastructure;

import org.apache.commons.math3.special.Erf;

public class StandardOMCNode extends OMCVariantNode {

	public StandardOMCNode(MCTSNode parent, ChoiceSet choiceSet) {
		super(parent, choiceSet);
	}

	@Override
	protected double getUrgency(){
		return Erf.erfc((getMaxSiblingValue() - getValue())/(Math.sqrt(2)*getStandardDeviation()));
	}

	@Override
	protected MCTSNode generateChild(ChoiceSet choiceSet) {
		return new StandardOMCNode(this, choiceSet);
	}
}
