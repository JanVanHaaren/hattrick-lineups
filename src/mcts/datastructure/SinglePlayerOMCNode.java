package mcts.datastructure;

import org.apache.commons.math3.special.Erf;

public class SinglePlayerOMCNode extends OMCVariantNode {

	private double g; //Thesis chaslot: 50 <= g <= 100
	
	public SinglePlayerOMCNode(MCTSNode parent, double g) {
		super(parent);
		this.g = g;
	}
	
	private double getG() {
		return g;
	}

	@Override
	protected double getUrgency(){
		return Erf.erfc(getG()*((getMaxSiblingValue() - getValue())/(Math.sqrt(2)*getStandardDeviation())));
	}

}
