package mcts.hattrick;

import java.util.Map;

import mcts.datastructure.ChoiceSet;
import weka.core.Instance;
import api.entity.Training;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.TrainerType;
import api.exception.InvalidBehaviourForRoleException;
import api.ratingpredictor.PlayerBehaviour;

public abstract class HattrickChoiceSet extends ChoiceSet {
	
	private TrainerType trainerType;
	private Training training;
	private TeamRatings awayRatings;
	private boolean numeric;

	public HattrickChoiceSet(TrainerType trainerType, Training training, TeamRatings awayRatings, boolean numeric) {
		super();
		this.trainerType = trainerType;
		this.training = training;
		this.awayRatings = awayRatings;
		this.numeric = numeric;
	}
	
	public TrainerType getTrainerType() {
		return trainerType;
	}

	public Training getTraining() {
		return training;
	}
	
	public TeamRatings getAwayRatings() {
		return awayRatings;
	}
	
	public boolean isNumeric() {
		return numeric;
	}
	
	@Override
	public double valuation() {
		TeamRatings teamRatings;
		try {
			teamRatings = TeamRatings.predictHomeTeamRatings(this);
		} catch (InvalidBehaviourForRoleException e) {
			e.printStackTrace();
			return 0;
		}
		return teamRatings.getVnukStats();
	}

	public double getSimulationResult(){
		Instance predictionInstance;
		try {
			TeamRatings homeTeamRatings = TeamRatings.predictHomeTeamRatings(this);
			predictionInstance = HattrickInstanceBuilder.buildInstance(homeTeamRatings, this.getAwayRatings());
			HattrickClassifier hc = isNumeric() ? HattrickClassifier.getNumericInstance() : HattrickClassifier.getNominalInstance();
			return hc.getPredictionResult(predictionInstance);
			//TODO: beide
		} catch (InvalidBehaviourForRoleException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	
	public double getNeutralValue()
	{
		return isNumeric() ? 0 : 0.5;
	}
	
	public abstract Map<MatchRoleID,PlayerBehaviour> getFieldSetup();

}
