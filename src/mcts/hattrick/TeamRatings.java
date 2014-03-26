package mcts.hattrick;

import api.exception.InvalidBehaviourForRoleException;
import api.ratingpredictor.RatingPredictor;

public class TeamRatings {
	
	private double ratingMidField;
	private double ratingRightDef;
	private double ratingMidDef;
	private double ratingLeftDef;
	private double ratingRightAtt;
	private double ratingMidAtt;
	private double ratingLeftAtt;
	
	public TeamRatings(double ratingMidField, double ratingRightDef,
			double ratingMidDef, double ratingLeftDef, double ratingRightAtt,
			double ratingMidAtt, double ratingLeftAtt) {
		this.ratingMidField = ratingMidField;
		this.ratingRightDef = ratingRightDef;
		this.ratingMidDef = ratingMidDef;
		this.ratingLeftDef = ratingLeftDef;
		this.ratingRightAtt = ratingRightAtt;
		this.ratingMidAtt = ratingMidAtt;
		this.ratingLeftAtt = ratingLeftAtt;
	}
	
	public static TeamRatings predictHomeTeamRatings(HattrickChoiceSet choiceSet) throws InvalidBehaviourForRoleException
	{
		RatingPredictor rp = new RatingPredictor(choiceSet.getFieldSetup(), choiceSet.getTrainerType(), choiceSet.getTraining(), true);
		
		TeamRatings teamRatings = predictRatings(rp);
		
		return teamRatings;
	}
	
	public static TeamRatings predictAwayTeamRatings(HattrickChoiceSet choiceSet) throws InvalidBehaviourForRoleException
	{
		RatingPredictor rp = new RatingPredictor(choiceSet.getFieldSetup(), choiceSet.getTrainerType(), choiceSet.getTraining(), false);
		
		TeamRatings teamRatings = predictRatings(rp);
		
		return teamRatings;
	}

	private static TeamRatings predictRatings(RatingPredictor rp)
			throws InvalidBehaviourForRoleException {
		double predictRatingMidField = rp.predictRatingMidField();
		double predictRatingRightDef = rp.predictRatingRightDef();
		double predictRatingMidDef = rp.predictRatingMidDef();
		double predictRatingLeftDef = rp.predictRatingLeftDef();
		double predictRatingRightAtt = rp.predictRatingRightAtt();
		double predictRatingMidAtt = rp.predictRatingMidAtt();
		double predictRatingLeftAtt = rp.predictRatingLeftAtt();
		
		TeamRatings teamRatings = new TeamRatings(predictRatingMidField,
				predictRatingRightDef, predictRatingMidDef, predictRatingLeftDef,
				predictRatingRightAtt, predictRatingMidAtt, predictRatingLeftAtt);
		return teamRatings;
	}

	public double getRatingMidField() {
		return ratingMidField;
	}

	public double getRatingRightDef() {
		return ratingRightDef;
	}

	public double getRatingMidDef() {
		return ratingMidDef;
	}

	public double getRatingLeftDef() {
		return ratingLeftDef;
	}

	public double getRatingRightAtt() {
		return ratingRightAtt;
	}

	public double getRatingMidAtt() {
		return ratingMidAtt;
	}

	public double getRatingLeftAtt() {
		return ratingLeftAtt;
	}
	
	public double getVnukStats()
	{
		return 5*getRatingMidField()
				+ getRatingLeftAtt() + getRatingMidAtt() + getRatingRightAtt()
				+ getRatingLeftDef() + getRatingMidDef() + getRatingRightDef();
	}
	
	@Override
	public String toString() {
		String result = "";
		result += "\tMidfield = " + getRatingMidField();
		result += "\tRightDef = " + getRatingRightDef();
		result += "\tMidDef = " + getRatingMidDef();
		result += "\tLeftDef = " + getRatingLeftDef();
		result += "\tRightAtt = " + getRatingRightAtt();
		result += "\tMidAtt = " + getRatingMidAtt();
		result += "\tLeftAtt = " + getRatingLeftAtt();
		return result;
	}
}
