package mcts.hattrick;

import java.util.ArrayList;
import java.util.Map;

import mcts.datastructure.ChoiceSet;
import api.entity.Training;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.TrainerType;
import api.ratingpredictor.PlayerBehaviour;

public class SwapChoiceSet extends HattrickChoiceSet {


	public SwapChoiceSet(TrainerType trainerType, Training training,
			TeamRatings awayRatings, boolean numeric, boolean homeMatch) {
		super(trainerType, training, awayRatings, numeric, homeMatch);
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<ChoiceSet> expand() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean isComplete() {
		// TODO Auto-generated method stub
		return false;
	}
	
	@Override
	public int getDepth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Map<MatchRoleID, PlayerBehaviour> getFieldSetup() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChoiceSet getGreedyCompletion() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
