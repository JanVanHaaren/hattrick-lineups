package mcts.hattrick;

import weka.core.DenseInstance;
import weka.core.Instance;
import api.exception.InvalidBehaviourForRoleException;

public class HattrickInstanceBuilder {
	
	public static Instance buildInstance(TeamRatings homeTeamRatings, TeamRatings awayTeamRatings) throws InvalidBehaviourForRoleException
	{
		double homeVnukStats = homeTeamRatings.getVnukStats();
		double awayVnukStats = awayTeamRatings.getVnukStats();
		
		Instance instance = new DenseInstance(10);
		instance.setMissing(9);
		instance.setValue(0, homeTeamRatings.getRatingMidField()/( homeTeamRatings.getRatingMidField() + awayTeamRatings.getRatingMidField()));
		instance.setValue(1,  homeTeamRatings.getRatingRightDef()/( homeTeamRatings.getRatingRightDef() + awayTeamRatings.getRatingLeftAtt()));
		instance.setValue(2,  homeTeamRatings.getRatingMidDef()/( homeTeamRatings.getRatingMidDef() + awayTeamRatings.getRatingMidAtt()));
		instance.setValue(3,  homeTeamRatings.getRatingLeftDef()/( homeTeamRatings.getRatingLeftDef() + awayTeamRatings.getRatingRightAtt()));
		instance.setValue(4,  homeTeamRatings.getRatingRightAtt()/( homeTeamRatings.getRatingRightAtt() + awayTeamRatings.getRatingLeftDef()));
		instance.setValue(5,  homeTeamRatings.getRatingMidAtt()/( homeTeamRatings.getRatingMidAtt() + awayTeamRatings.getRatingMidDef()));
		instance.setValue(6,  homeTeamRatings.getRatingLeftAtt()/( homeTeamRatings.getRatingLeftAtt() + awayTeamRatings.getRatingRightDef()));
		instance.setValue(7, homeVnukStats);
		instance.setValue(8, awayVnukStats);
		
		return instance;
	}

}
