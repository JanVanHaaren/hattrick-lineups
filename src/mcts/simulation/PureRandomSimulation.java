package mcts.simulation;

import java.util.ArrayList;
import java.util.Random;

import mcts.datastructure.ChoiceSet;

public class PureRandomSimulation extends Simulation {

	@Override
	protected ChoiceSet selectExtension(ChoiceSet choiceSet) {
		ArrayList<ChoiceSet> extensions = choiceSet.expand();
		return extensions.get(new Random().nextInt(extensions.size()));
	}

}
