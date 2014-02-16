package mcts.datastructure;

public class PBBMNode extends OMCVariantNode {

	public PBBMNode(MCTSNode parent, ChoiceSet choiceSet) {
		super(parent, choiceSet);
	}

	@Override
	protected double getUrgency(){
		return Math.exp(-2.4*(getMaxSiblingValue() - getValue())/Math.sqrt(2*(Math.pow(getStandardDeviation(),2) + Math.pow(getMaxSiblingStandardDeviation(),2))));
	}

	@Override
	protected MCTSNode generateChild(ChoiceSet choiceSet) {
		return new PBBMNode(this, choiceSet);
	}

}
