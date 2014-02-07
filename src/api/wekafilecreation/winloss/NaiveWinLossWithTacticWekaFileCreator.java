package api.wekafilecreation.winloss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.matchdetails.Team;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.wekafilecreation.AttributeAndType;
import api.wekafilecreation.WekaFileCreator;

public class NaiveWinLossWithTacticWekaFileCreator extends WekaFileCreator {

	public static void main(String[] args) {
		NaiveWinLossWithTacticWekaFileCreator creator = new NaiveWinLossWithTacticWekaFileCreator();
		creator.createAndBuildWekaFile();
	}
	
	@Override
	protected String getDataForMatch(int matchId) throws IOException, IllegalXMLException, DiscardException {
		MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetailsFromSimpleFile(matchId);
		Team homeTeam = matchDetails.getMatch().getHomeTeam();
		Team awayTeam = matchDetails.getMatch().getAwayTeam();
		int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
		if(homeAdvantage == 0)
			return "";
		
		String dataString = "";
		dataString += homeTeam.getRatingMidField().getValue() + ",";
		dataString += homeTeam.getRatingRightDef().getValue() + ",";
		dataString += homeTeam.getRatingMidDef().getValue() + ",";
		dataString += homeTeam.getRatingLeftDef().getValue() + ",";
		dataString += homeTeam.getRatingRightAtt().getValue() + ",";
		dataString += homeTeam.getRatingMidAtt().getValue() + ",";
		dataString += homeTeam.getRatingLeftAtt().getValue() + ",";
		dataString += homeTeam.getTacticType().toString() + ",";
		dataString += homeTeam.getTacticSkill().getValue() + ",";
		
		dataString += awayTeam.getRatingMidField().getValue() + ",";
		dataString += awayTeam.getRatingRightDef().getValue() + ",";
		dataString += awayTeam.getRatingMidDef().getValue() + ",";
		dataString += awayTeam.getRatingLeftDef().getValue() + ",";
		dataString += awayTeam.getRatingRightAtt().getValue() + ",";
		dataString += awayTeam.getRatingMidAtt().getValue() + ",";
		dataString += awayTeam.getRatingLeftAtt().getValue() + ",";
		dataString += awayTeam.getTacticType().toString() + ",";
		dataString += awayTeam.getTacticSkill().getValue() + ",";
		
		if(homeAdvantage > 0)
			dataString += "win";
		else
			dataString += "loss";
		return dataString + "\n";
	}

	@Override
	protected String getFileName() {
		return "NaiveWithTactic_WinLoss";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		attributeList.add(new AttributeAndType("home_ratingMidField", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingRightDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingMidDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingLeftDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingRightAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingMidAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_ratingLeftAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_tacticType", "{NORMAL, PRESSING, COUNTER_ATTACKS, ATTACK_IN_THE_MIDDLE, ATTACK_INWINGS, PLAY_CREATIVELY, LONG_SHOTS}"));
		attributeList.add(new AttributeAndType("home_tacticSkill", "NUMERIC"));
		
		attributeList.add(new AttributeAndType("away_ratingMidField", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingRightDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingMidDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingLeftDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingRightAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingMidAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_ratingLeftAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_tacticType", "{NORMAL, PRESSING, COUNTER_ATTACKS, ATTACK_IN_THE_MIDDLE, ATTACK_INWINGS, PLAY_CREATIVELY, LONG_SHOTS}"));
		attributeList.add(new AttributeAndType("away_tacticSkill", "NUMERIC"));

		attributeList.add(new AttributeAndType("result", "{win,loss}"));
		return attributeList;
	}

}
