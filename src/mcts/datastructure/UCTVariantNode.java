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
	
	protected abstract double getSelectionValue();
	
	public UCTVariantNode select(){
		double maxSelectionValue = Double.NEGATIVE_INFINITY;
		UCTVariantNode maxChild = null;
		for(MCTSNode child : getChildren())
		{
			UCTVariantNode childCast = (UCTVariantNode) child;
			if(childCast.getSelectionValue() > maxSelectionValue)
			{
				maxSelectionValue = childCast.getSelectionValue();
				maxChild = childCast;
			}
		}
		return maxChild;
	}
}
