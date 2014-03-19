package mcts.datastructure;

import mcts.algorithm.Epsilon;
import mcts.hattrick.Data;

public abstract class OMCVariantNode extends MCTSNode {

	private double standardDeviation;
	private double squaredValuesSum;
	private boolean recalculate;
	
	public OMCVariantNode(MCTSNode parent, ChoiceSet choiceSet) {
		super(parent, choiceSet);
		this.recalculate = true;
		this.squaredValuesSum = Math.pow(getValue(), 2);
	}
	
	@Override
	public void backPropagate(double value)
	{
		super.backPropagate(value);
		updateSquaredValuesSum(value);
		this.recalculate = true;
	}
	
	private double getSquaredValuesSum() {
		return squaredValuesSum;
	}

	private void setSquaredValuesSum(double getSquaredValuesSum) {
		this.squaredValuesSum = getSquaredValuesSum;
	}

	private void updateSquaredValuesSum(double value)
	{
		setSquaredValuesSum(getSquaredValuesSum() + Math.pow(value, 2));
	}

	protected double getStandardDeviation() {
		if(this.recalculate)
		{
			setStandardDeviation(Math.sqrt(getSquaredValuesSum()/getVisits() - Math.pow(getValue(), 2)));
			this.recalculate = false;
		}
		
		if(this.standardDeviation == 0)
		{
			if(getChoiceSet().isNumeric())
				return Data.DEFAULT_SD_NUMERIC;
			return Data.DEFAULT_SD_NOMINAL;
		}
		return this.standardDeviation;
	}

	private void setStandardDeviation(double standardDeviation) {
		this.standardDeviation = standardDeviation;
	}
	
	protected double getMaxSiblingStandardDeviation()
	{
		return ((OMCVariantNode)getMaxSibling()).getStandardDeviation();
	}
	
	private double getFairness(double urgency){
		double siblingUrgency = 0;
		for(MCTSNode sibling : getSiblings())
			siblingUrgency += ((OMCVariantNode)sibling).getUrgency();
		return (getParent().getVisits()*urgency)/(getVisits()*siblingUrgency);
	}
	
	protected abstract double getUrgency();
	
//	
	
	@Override
	protected double getSelectionValue(){
		
		return getFairness(getUrgency()) + Math.random()*Epsilon.epsilon();
	}
}
