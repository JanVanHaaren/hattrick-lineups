package api.wekafilecreation.goaldiff;

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

public class HatStatsGoalDiffWekaFileCreator extends WekaFileCreator {

	@Override
	protected String getFileName() {
		return "HatStats_GoalDiff";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		attributeList.add(new AttributeAndType("home_hatStats", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_hatStats", "NUMERIC"));
		attributeList.add(new AttributeAndType("result", "NUMERIC"));
		
		return attributeList;
	}

	@Override
	protected String getDataForMatch(int matchId) throws IOException,
			IllegalXMLException, DiscardException {
		MatchDetails matchDetails = new HattrickObjectCreator().getMatchDetailsFromSimpleFile(matchId);
		Team homeTeam = matchDetails.getMatch().getHomeTeam();
		Team awayTeam = matchDetails.getMatch().getAwayTeam();
		int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
		
		String dataString = "";
		dataString += homeTeam.getHatStats() + ",";
		
		dataString += awayTeam.getHatStats() + ",";
		
		dataString += homeAdvantage;
		return dataString + "\n";
	}

}
