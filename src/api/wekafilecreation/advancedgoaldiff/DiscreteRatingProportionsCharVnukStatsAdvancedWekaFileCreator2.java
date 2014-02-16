package api.wekafilecreation.advancedgoaldiff;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.matchdetails.Team;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.util.Utils;
import api.wekafilecreation.AdvancedWekaFileCreator;
import api.wekafilecreation.AttributeAndType;

public class DiscreteRatingProportionsCharVnukStatsAdvancedWekaFileCreator2 extends
		AdvancedWekaFileCreator {

	@Override
	protected String getFileName() {
		return "advancedDiscreteRatingProportionsChar_VnukStats2";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		attributeList.add(new AttributeAndType("diff_ratingMidField", "{a,b,c,d,e,f,g,h,i,j}"));
		attributeList.add(new AttributeAndType("diff_ratingRightDef_LeftAtt", "{a,b,c,d,e,f,g,h,i,j}"));
		attributeList.add(new AttributeAndType("diff_ratingMidDef_MidAtt", "{a,b,c,d,e,f,g,h,i,j}"));
		attributeList.add(new AttributeAndType("diff_ratingLeftDef_RightAtt", "{a,b,c,d,e,f,g,h,i,j}"));
		attributeList.add(new AttributeAndType("diff_ratingRightAtt_LeftDef", "{a,b,c,d,e,f,g,h,i,j}"));
		attributeList.add(new AttributeAndType("diff_ratingMidAtt_MidDef", "{a,b,c,d,e,f,g,h,i,j}"));
		attributeList.add(new AttributeAndType("diff_ratingLeftAtt_RightDef", "{a,b,c,d,e,f,g,h,i,j}"));
		
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
		if(homeAdvantage == 0)
			return "";
		
		String dataString = "";
		dataString += Utils.getDoubleCharacter((double)homeTeam.getRatingMidField().getValue()/((double)homeTeam.getRatingMidField().getValue() + (double)awayTeam.getRatingMidField().getValue())) + ",";
		dataString += Utils.getDoubleCharacter((double)homeTeam.getRatingRightDef().getValue()/((double)homeTeam.getRatingRightDef().getValue() + (double)awayTeam.getRatingLeftAtt().getValue())) + ",";
		dataString += Utils.getDoubleCharacter((double)homeTeam.getRatingMidDef().getValue()/((double)homeTeam.getRatingMidDef().getValue() + (double)awayTeam.getRatingMidAtt().getValue())) + ",";
		dataString += Utils.getDoubleCharacter((double)homeTeam.getRatingLeftDef().getValue()/((double)homeTeam.getRatingLeftDef().getValue() + (double)awayTeam.getRatingRightAtt().getValue())) + ",";
		dataString += Utils.getDoubleCharacter((double)homeTeam.getRatingRightAtt().getValue()/((double)homeTeam.getRatingRightAtt().getValue() + (double)awayTeam.getRatingLeftDef().getValue())) + ",";
		dataString += Utils.getDoubleCharacter((double)homeTeam.getRatingMidAtt().getValue()/((double)homeTeam.getRatingMidAtt().getValue() + (double)awayTeam.getRatingMidDef().getValue())) + ",";
		dataString += Utils.getDoubleCharacter((double)homeTeam.getRatingLeftAtt().getValue()/((double)homeTeam.getRatingLeftAtt().getValue() + (double)awayTeam.getRatingRightDef().getValue())) + ",";
		
		dataString += homeTeam.getVnukStats() + ",";
		dataString += awayTeam.getVnukStats() + ",";
		
		dataString += homeAdvantage;
		
		return dataString + "\n";
	}
}
