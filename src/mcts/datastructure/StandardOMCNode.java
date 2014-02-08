package mcts.datastructure;

import org.apache.commons.math3.special.Erf;

public class StandardOMCNode extends OMCVariantNode {

	public StandardOMCNode(MCTSNode parent) {
		super(parent);
	}

	@Override
	protected double getUrgency(){
		return Erf.erfc((getMaxSiblingValue() - getValue())/(Math.sqrt(2)*getStandardDeviation()));
	}
}
