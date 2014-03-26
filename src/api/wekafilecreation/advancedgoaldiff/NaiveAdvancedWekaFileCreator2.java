package api.wekafilecreation.advancedgoaldiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.matchdetails.Team;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.wekafilecreation.AdvancedWekaFileCreator;
import api.wekafilecreation.AttributeAndType;

public class NaiveAdvancedWekaFileCreator2 extends
		AdvancedWekaFileCreator {

	@Override
	protected String getFileName() {
		return "naive_shizzle";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		
		attributeList.add(new AttributeAndType("home_vnukStats", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_vnukStats", "NUMERIC"));

		attributeList.add(new AttributeAndType("result", "NUMERIC"));
		return attributeList;
	}

	@Override
	protected String getDataForMatch(int leagueId, int matchId, String directoryPath)
			throws IOException, IllegalXMLException, DiscardException {

		MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetailsFromAdvancedFile(directoryPath, matchId);
	
		Team homeTeam = matchDetails.getMatch().getHomeTeam();
		Team awayTeam = matchDetails.getMatch().getAwayTeam();
		int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
//		if(homeAdvantage == 0)
//			return "";
		
		String dataString = "";
		
		dataString += homeTeam.getRatingMidField().getValue() + ",";
		dataString += homeTeam.getRatingRightDef().getValue() + ",";
		dataString += homeTeam.getRatingMidDef().getValue() + ",";
		dataString += homeTeam.getRatingLeftDef().getValue() + ",";
		dataString += homeTeam.getRatingRightAtt().getValue() + ",";
		dataString += homeTeam.getRatingMidAtt().getValue() + ",";
		dataString += homeTeam.getRatingLeftAtt().getValue() + ",";
		
		dataString += awayTeam.getRatingMidField().getValue() + ",";
		dataString += awayTeam.getRatingRightDef().getValue() + ",";
		dataString += awayTeam.getRatingMidDef().getValue() + ",";
		dataString += awayTeam.getRatingLeftDef().getValue() + ",";
		dataString += awayTeam.getRatingRightAtt().getValue() + ",";
		dataString += awayTeam.getRatingMidAtt().getValue() + ",";
		dataString += awayTeam.getRatingLeftAtt().getValue() + ",";
		
		dataString += homeAdvantage;
		
		return dataString + "\n";
	}

}
