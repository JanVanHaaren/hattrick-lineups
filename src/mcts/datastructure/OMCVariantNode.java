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
			if(getSquaredValuesSum()/getVisits() - Math.pow(getValue(), 2) <= 0)
				setStandardDeviation(0);
			else
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
	
	@Override
	public OMCVariantNode select(){
		double maxSelectionValue = Double.NEGATIVE_INFINITY;
		OMCVariantNode maxChild = null;
		OMCVariantNode maxValueChild = (OMCVariantNode) getMaxChild();
		double childUrgencySum = getChildUrgencySum(maxValueChild);
		for(MCTSNode child : getChildren())
		{
			OMCVariantNode childCast = (OMCVariantNode) child;
			if(childCast.getSelectionValue(maxValueChild, childUrgencySum) > maxSelectionValue)
			{
				maxSelectionValue = childCast.getSelectionValue(maxValueChild, childUrgencySum);
				maxChild = childCast;
			}
		}
		return maxChild;
	}

	
	private double getFairness(double urgency, OMCVariantNode maxSibling, double siblingUrgencySum){
		
		return (getParent().getVisits()*urgency)/(getVisits()*siblingUrgencySum);
	}
	
	protected abstract double getUrgency(OMCVariantNode maxSibling);
	
	protected double getSelectionValue(OMCVariantNode maxSibling, double siblingUrgencySum){
		return getFairness(getUrgency(maxSibling), maxSibling, siblingUrgencySum) + Math.random()*Epsilon.epsilon();
	}

	double getChildUrgencySum(OMCVariantNode maxSibling) {
		double siblingUrgencySum = 0;
		for(MCTSNode sibling : getChildren())
			siblingUrgencySum += ((OMCVariantNode)sibling).getUrgency(maxSibling);
		return siblingUrgencySum;
	}
	
	public double getFairnessTiming() {
		long curTime = System.currentTimeMillis();
		for(int i = 1; i <= 10000; i++)
		{
			this.recalculate = true;
			getFairness(5, (OMCVariantNode) getMaxSibling(), ((OMCVariantNode) getParent()).getChildUrgencySum((OMCVariantNode) getMaxSibling()));
		}
		return System.currentTimeMillis() - curTime;
	}
	
	public double getUrgencyTiming() {
		long curTime = System.currentTimeMillis();
		MCTSNode maxValueSibling = getMaxSibling();
		for(int i = 1; i <= 10000; i++)
		{
			this.recalculate = true;
			getUrgency((OMCVariantNode) maxValueSibling);
		}
		return System.currentTimeMillis() - curTime;
	}
}
