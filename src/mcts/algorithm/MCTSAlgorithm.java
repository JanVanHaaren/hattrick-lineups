package mcts.algorithm;

import mcts.datastructure.MCTSNode;
import mcts.datastructure.MCTSTree;
import mcts.expansion.Expansion;
import mcts.simulation.Simulation;

public class MCTSAlgorithm {

	private MCTSTree tree;
	private int maxIterations;
	private Expansion expansion;
	private Simulation simulation;
	
	public MCTSAlgorithm(int maxIterations){
		this.maxIterations = maxIterations;
	}
	
	private int getMaxIterations(){
		return maxIterations;
	}
	
	private MCTSTree getTree(){
		return tree;
	}

	private Expansion getExpansion() {
		return expansion;
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
		
		lastNode = getExpansion().expand(lastNode);
		
		double result = getSimulation().simulate(lastNode);
		
		currentNode = lastNode;
		
		while(currentNode != null)
		{
			currentNode.backPropagate(result);
			currentNode = currentNode.getParent();
		}
	}
	
	private double getBestMove(){
		//TODO: implement
		return -1;
	}
	
	public double execute(){
		for(int i = 0; i < getMaxIterations(); i++){
			executeStep();
		}
		return getBestMove();
	}
}
