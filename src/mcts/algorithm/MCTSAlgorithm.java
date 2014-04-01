package mcts.algorithm;

import mcts.datastructure.ChoiceSet;
import mcts.datastructure.MCTSNode;
import mcts.datastructure.MCTSTree;
import mcts.simulation.Simulation;

public class MCTSAlgorithm {

	private MCTSTree tree;
	private int maxIterations;
	private Simulation simulation;
	
	public MCTSAlgorithm(int maxIterations, MCTSNode root, Simulation simulation){
		this.maxIterations = maxIterations;
		this.tree = new MCTSTree(root);
		this.simulation = simulation;
	}
	
	private int getMaxIterations(){
		return maxIterations;
	}
	
	private MCTSTree getTree(){
		return tree;
	}

	private Simulation getSimulation() {
		return simulation;
	}

	private void executeStep(){
		MCTSNode currentNode = getTree().getRoot();
		MCTSNode lastNode = null;
		
		while(currentNode != null)
		{
			lastNode = currentNode;
			currentNode = currentNode.select();
		}
		
		lastNode.expand();
		MCTSNode wut = lastNode;
		wut.expand();
		if(wut.select() == null)
			System.out.println("wot");
		if(!lastNode.getChildren().isEmpty())
			lastNode = lastNode.select();
		
		if(lastNode == null)
			System.out.println("wut");
		double result = getSimulation().simulate(lastNode);
		
		lastNode.backPropagate(result);
	}
	
	public ChoiceSet execute(){
		for(int i = 0; i < getMaxIterations(); i++){
			executeStep();
		}
		return getTree().getBestNode().getChoiceSet();
	}
}
