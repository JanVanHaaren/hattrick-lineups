package mcts.simulation;

import java.util.ArrayList;
import java.util.Random;

import mcts.datastructure.ChoiceSet;

public class PureRandomSimulation extends Simulation {

	@Override
	protected ChoiceSet selectChoice(ArrayList<ChoiceSet> extensions) {
		return extensions.get(new Random().nextInt(extensions.size()));
	}

}
