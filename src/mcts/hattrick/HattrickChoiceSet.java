package mcts.hattrick;

import java.util.Map;

import mcts.algorithm.Epsilon;
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
	private TeamRatings opponentRatings;
	private boolean numeric;
	private boolean homeMatch;

	public HattrickChoiceSet(TrainerType trainerType, Training training, TeamRatings awayRatings, boolean numeric, boolean homeMatch) {
		super();
		this.trainerType = trainerType;
		this.training = training;
		this.opponentRatings = awayRatings;
		this.numeric = numeric;
		this.homeMatch = homeMatch;
	}
	
	public TrainerType getTrainerType() {
		return trainerType;
	}

	public Training getTraining() {
		return training;
	}
	
	public TeamRatings getOpponentRatings() {
		return opponentRatings;
	}
	
	public boolean isNumeric() {
		return numeric;
	}
	
	public boolean isHomeMatch() {
		return homeMatch;
	}

	@Override
	public double valuation() {
		TeamRatings teamRatings;
		try {
			if(isHomeMatch())
				teamRatings = TeamRatings.predictHomeTeamRatings(this);
			else
				teamRatings = TeamRatings.predictAwayTeamRatings(this);
		} catch (InvalidBehaviourForRoleException e) {
			e.printStackTrace();
			return 0;
		}
		return teamRatings.getVnukStats();
	}

	public double getSimulationResult(){
		Instance predictionInstance;
		try {
			if(isHomeMatch())
			{
				TeamRatings teamRatings = TeamRatings.predictHomeTeamRatings(this);
				predictionInstance = HattrickInstanceBuilder.buildInstance(teamRatings, this.getOpponentRatings());
			}
			else
			{
				TeamRatings teamRatings = TeamRatings.predictAwayTeamRatings(this);
				predictionInstance = HattrickInstanceBuilder.buildInstance(this.getOpponentRatings(), teamRatings);
			}
			HattrickClassifier hc = isNumeric() ? HattrickClassifier.getNumericInstance() : HattrickClassifier.getNominalInstance();
			if(isHomeMatch())
				return hc.getPredictionResult(predictionInstance);
			return getComplement(hc.getPredictionResult(predictionInstance));
		} catch (InvalidBehaviourForRoleException e1) {
			e1.printStackTrace();
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return 0;
	}
	
	private double getComplement(double simulationResult)
	{
		if(isNumeric())
			return -1*simulationResult;
		return (simulationResult + 1) % 2;
	}
	
	public double getNeutralValue()
	{
		return isNumeric() ? 0 : 0.5;
	}
	
	public abstract Map<MatchRoleID,PlayerBehaviour> getFieldSetup();

}
