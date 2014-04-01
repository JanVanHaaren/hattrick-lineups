package mcts.datastructure;

import org.apache.commons.math3.special.Erf;

public class SinglePlayerOMCNode extends OMCVariantNode {

	private double g; //Thesis chaslot: 50 <= g <= 100
	
	public SinglePlayerOMCNode(MCTSNode parent, ChoiceSet choiceSet, double g) {
		super(parent, choiceSet);
		this.g = g;
	}
	
	private double getG() {
		return g;
	}

	@Override
	protected double getUrgency(OMCVariantNode maxSibling){
		return Erf.erfc(getG()*((maxSibling.getValue() - getValue())/(Math.sqrt(2)*getStandardDeviation())));
	}

	@Override
	protected MCTSNode generateChild(ChoiceSet choiceSet) {
		return new SinglePlayerOMCNode(this, choiceSet, getG());
	}


}
