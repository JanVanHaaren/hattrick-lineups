package mcts.datastructure;

import java.util.ArrayList;

public abstract class MCTSNode {

	private MCTSNode parent;
	private ArrayList<MCTSNode> children;
	
	private ChoiceSet choiceSet;
	
	private double value;
	private int visits;

	public MCTSNode(MCTSNode parent, ChoiceSet choiceSet)
	{
		this.parent = parent;
		this.visits = 0;
		if(parent != null)
			parent.addChild(this);
		this.children = new ArrayList<MCTSNode>();
		
		this.choiceSet = choiceSet;
	}
	
	private void addChild(MCTSNode node){
		this.children.add(node);
	}
	
	protected MCTSNode getMaxSibling()
	{
		if(getParent() == null)
			return null;
		return getParent().getMaxChild();
	}
	
	public MCTSNode getMaxChild() {
		double maxValue = Double.NEGATIVE_INFINITY;
		MCTSNode maxChild = null;
		for(MCTSNode child : getChildren())
		{
			if(child.getValue() > maxValue)
			{
				maxValue = child.getValue();
				maxChild = child;
			}	
		}
		return maxChild;
	}
	
	protected double getMaxSiblingValue()
	{
		return getMaxSibling().getValue();
	}
	
	public MCTSNode getParent() {
		return parent;
	}

	public ArrayList<MCTSNode> getChildren() {
		return new ArrayList<MCTSNode>(children);
	}
	
	protected ArrayList<MCTSNode> getSiblings(){
		return getParent().getChildren();
	}

	protected double getValue() {
		if(this.visits == 0 && getParent() != null)
			return getParent().getActualChildValueAverage();
		return value;
	}
	
	private double getActualValue() {
		return this.value;
	}
	
	private double getActualChildValueAverage() {
		double totalValue = 0;
		double totalVisits = 0;
		for(MCTSNode node : getChildren())
		{
			totalValue += node.getActualValue()*node.getActualVisits();
			totalVisits += node.getActualVisits();
		}
		
		if(totalVisits == 0)
			return 0;
		return totalValue / totalVisits;
	}

	private void setValue(double value) {
		this.value = value;
	}
	
	public void backPropagate(double value)
	{
		setValue((getValue()*getActualVisits()+value)/(getActualVisits()+1));
		addVisit();
		if(parent != null)
			parent.backPropagate(value);
	}
	
	protected int getVisits() {
		if(this.visits == 0)
			return 1;
		return visits;
	}
	
	private int getActualVisits() {
		return visits;
	}

	private void addVisit() {
		this.visits++;
	}
	
	public abstract MCTSNode select();
	
	public ChoiceSet getChoiceSet() {
		return choiceSet;
	}
	
	protected abstract MCTSNode generateChild(ChoiceSet choiceSet);
	
	public void expand() {
		for(ChoiceSet expandedSet : getChoiceSet().expand())
			generateChild(expandedSet);
	}
	
	public int getNodeCount(){
		int count = 1;
		for(MCTSNode node : getChildren())
			count += node.getNodeCount();
		return count;
	}
	
	public int getExpandedChildrenCount(){
		int i = 0;
		for(MCTSNode node : getChildren())
			if(!node.getChildren().isEmpty())
				i++;
		return i;
	}
}
