package mcts.simulation;

import java.util.NavigableMap;
import java.util.TreeMap;

import mcts.algorithm.Epsilon;
import mcts.datastructure.ChoiceSet;
import mcts.hattrick.HattrickChoiceSet;

public class VnukStatsRouletteSimulation extends Simulation {

	@Override
	protected ChoiceSet selectExtension(ChoiceSet choiceSet) {
		double baseline = choiceSet.valuation();
		double total = 0;
		NavigableMap<Double, ChoiceSet> cdfMap = new TreeMap<Double, ChoiceSet>();
		
		for(ChoiceSet choiceSetE : choiceSet.expand())
		{
			HattrickChoiceSet choiceSetH = (HattrickChoiceSet) choiceSetE;
			total += Math.max(0, choiceSetH.valuation() - baseline);
			cdfMap.put(total + Epsilon.epsilon(), choiceSetH);
		}
		
		return cdfMap.ceilingEntry(Math.random()*total).getValue();
	}

}
