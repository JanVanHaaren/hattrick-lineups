package mcts.datastructure;

import mcts.algorithm.Epsilon;
import mcts.hattrick.Data;

public class UCB1TunedNode extends UCTVariantNode {

	private double standardDeviation;
	private double squaredValuesSum;
	private boolean recalculate;
	
	public UCB1TunedNode(MCTSNode parent, ChoiceSet choiceSet, double c) {
		super(parent, choiceSet, c);
	}

	private double getV() {
		return Math.pow(getStandardDeviation(), 2) + Math.sqrt(2*Math.log(getParent().getVisits())/getVisits());
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
		return ((UCB1TunedNode)getMaxSibling()).getStandardDeviation();
	}

	@Override
	public double getSelectionValue() {
		return getValue()
			+ getC()*Math.sqrt((Math.log(getParent().getVisits())/getVisits())*Math.min(0.25,  getV()))
			+ Math.random()*Epsilon.epsilon();
	}

	@Override
	protected UCB1TunedNode generateChild(ChoiceSet choiceSet) {
		return new UCB1TunedNode(this, choiceSet, getC());
	}
}
