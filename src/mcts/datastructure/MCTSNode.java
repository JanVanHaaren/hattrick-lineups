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
		return value;
	}

	private void setValue(double value) {
		this.value = value;
	}
	
	public void backPropagate(double value)
	{
		setValue((getValue()*getVisits()+value)/(getVisits()+1));
		addVisit();
		if(parent != null)
			parent.backPropagate(value);
	}
	
	protected int getVisits() {
		return visits;
	}

	private void addVisit() {
		this.visits++;
	}
	
	protected abstract double getSelectionValue();

	public MCTSNode select(){
		double maxSelectionValue = Double.NEGATIVE_INFINITY;
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
