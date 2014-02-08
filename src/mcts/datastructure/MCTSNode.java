package mcts.datastructure;

import java.util.ArrayList;

public abstract class MCTSNode {

	private MCTSNode parent;
	private ArrayList<MCTSNode> children;
	
	private double value;
	private int visits;

	public MCTSNode(MCTSNode parent)
	{
		this.parent = parent;
		
		parent.addChild(this);
		this.children = new ArrayList<MCTSNode>();
		
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
	
	private MCTSNode getMaxChild() {
		double maxValue = Double.MIN_VALUE;
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
		return value;
	}

	private void setValue(double value) {
		this.value = value;
	}
	
	public void backPropagate(double value)
	{
		setValue((getValue()*getVisits()+value)/(getVisits()+1));
		addVisit();
	}
	
	protected int getVisits() {
		return visits;
	}

	private void addVisit() {
		this.visits++;
	}
	
	protected abstract double getSelectionValue();

	public MCTSNode select(){
		double maxSelectionValue = Double.MIN_VALUE;
		MCTSNode maxChild = null;
		for(MCTSNode child : getChildren())
		{
			if(child.getSelectionValue() > maxSelectionValue)
			{
				maxSelectionValue = child.getSelectionValue();
				maxChild = child;
			}
		}
		return maxChild;
	}
	
	
}
