package api.wekafilecreation.advancedwinloss;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import api.HattrickObjectCreator;
import api.entity.MatchDetails;
import api.entity.MatchLineup;
import api.entity.PlayerDetails;
import api.entity.datatype.MatchRoleID;
import api.entity.matchdetails.Team;
import api.entity.matchlineup.Player;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;
import api.wekafilecreation.AdvancedWekaFileCreator;
import api.wekafilecreation.AttributeAndType;

public class RatingProportionsVnukStatsPlayerStatsAdvancedWekaFileCreator extends
		AdvancedWekaFileCreator {

	@Override
	protected String getFileName() {
		return "advancedRatingProportions_VnukStats_playerStats";
	}

	@Override
	protected List<AttributeAndType> getAttributeList() {
		List<AttributeAndType> attributeList = new ArrayList<AttributeAndType>();
		attributeList.add(new AttributeAndType("diff_ratingMidField", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingRightDef_LeftAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingMidDef_MidAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingLeftDef_RightAtt", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingRightAtt_LeftDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingMidAtt_MidDef", "NUMERIC"));
		attributeList.add(new AttributeAndType("diff_ratingLeftAtt_RightDef", "NUMERIC"));
		
		attributeList.add(new AttributeAndType("home_vnukStats", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_vnukStats", "NUMERIC"));
		
		attributeList.add(new AttributeAndType("home_att_TSI", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_mid_TSI", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_def_TSI", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_keeper_TSI", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_att_TSI", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_mid_TSI", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_def_TSI", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_keeper_TSI", "NUMERIC"));
		
		attributeList.add(new AttributeAndType("home_att_form", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_mid_form", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_def_form", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_keeper_form", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_att_form", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_mid_form", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_def_form", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_keeper_form", "NUMERIC"));
		
		attributeList.add(new AttributeAndType("home_att_stamina", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_mid_stamina", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_def_stamina", "NUMERIC"));
		attributeList.add(new AttributeAndType("home_keeper_stamina", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_att_stamina", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_mid_stamina", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_def_stamina", "NUMERIC"));
		attributeList.add(new AttributeAndType("away_keeper_stamina", "NUMERIC"));
		
		
		attributeList.add(new AttributeAndType("result", "{win,loss}"));
		return attributeList;
	}

	@Override
	protected String getDataForMatch(int leagueId, int matchId, String directoryPath)
			throws IOException, IllegalXMLException, DiscardException {

		HattrickObjectCreator hoc = new HattrickObjectCreator();
		MatchDetails matchDetails = hoc.getMatchDetailsFromAdvancedFile(directoryPath, matchId);
		
		int homeTeamID = matchDetails.getMatch().getHomeTeam().getTeamID();
		int awayTeamID = matchDetails.getMatch().getAwayTeam().getTeamID();
		
		MatchLineup homeMatchLineup = hoc.getMatchLineupFromAdvancedFile(directoryPath, matchId, homeTeamID);
		MatchLineup awayMatchLineup = hoc.getMatchLineupFromAdvancedFile(directoryPath, matchId, awayTeamID);
		
		int homeAttCounter = 0;
		int homeMidCounter = 0;
		int homeDefCounter = 0;
		int homeKeeperCounter = 0;
		
		int homeAttTSI = 0;
		int homeMidTSI = 0;
		int homeDefTSI = 0;
		int homeKeeperTSI = 0;
		
		int homeAttForm = 0;
		int homeMidForm = 0;
		int homeDefForm = 0;
		int homeKeeperForm = 0;
		
		int homeAttStamina = 0;
		int homeMidStamina = 0;
		int homeDefStamina = 0;
		int homeKeeperStamina = 0;
		
		for(Player player : homeMatchLineup.getTeam().getLineup())
		{
			int generalRole = player.getRoleID().getGeneralRole();
			PlayerDetails playerDetails = hoc.getPlayerDetailsFromFile(directoryPath, player.getPlayerID());
			api.entity.playerdetails.Player playerInfo = playerDetails.getPlayer();
			
			if (generalRole == MatchRoleID.KEEPER_ROLE)
			{
				homeKeeperCounter++;
				homeKeeperTSI += playerInfo.getTsi();
				homeKeeperForm += playerInfo.getPlayerForm().getValue();
				homeKeeperStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}
			
			else if (generalRole == MatchRoleID.MIDFIELD_ROLE)
			{
				homeMidCounter++;
				homeMidTSI += playerInfo.getTsi();
				homeMidForm += playerInfo.getPlayerForm().getValue();
				homeMidStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}
			
			else if (generalRole == MatchRoleID.DEFENDER_ROLE)
			{
				homeDefCounter++;
				homeDefTSI += playerInfo.getTsi();
				homeDefForm += playerInfo.getPlayerForm().getValue();
				homeDefStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}
			
			else if (generalRole == MatchRoleID.ATTACKER_ROLE)
			{
				homeAttCounter++;
				homeAttTSI += playerInfo.getTsi();
				homeAttForm += playerInfo.getPlayerForm().getValue();
				homeAttStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}	
		}
		
		int awayAttCounter = 0;
		int awayMidCounter = 0;
		int awayDefCounter = 0;
		int awayKeeperCounter = 0;
		
		int awayAttTSI = 0;
		int awayMidTSI = 0;
		int awayDefTSI = 0;
		int awayKeeperTSI = 0;
		
		int awayAttForm = 0;
		int awayMidForm = 0;
		int awayDefForm = 0;
		int awayKeeperForm = 0;
		
		int awayAttStamina = 0;
		int awayMidStamina = 0;
		int awayDefStamina = 0;
		int awayKeeperStamina = 0;
		
		for(Player player : awayMatchLineup.getTeam().getLineup())
		{
			int generalRole = player.getRoleID().getGeneralRole();
			PlayerDetails playerDetails = hoc.getPlayerDetailsFromFile(directoryPath, player.getPlayerID());
			api.entity.playerdetails.Player playerInfo = playerDetails.getPlayer();
			
			if (generalRole == MatchRoleID.KEEPER_ROLE)
			{
				awayKeeperCounter++;
				awayKeeperTSI += playerInfo.getTsi();
				awayKeeperForm += playerInfo.getPlayerForm().getValue();
				awayKeeperStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}
			
			else if (generalRole == MatchRoleID.MIDFIELD_ROLE)
			{
				awayMidCounter++;
				awayMidTSI += playerInfo.getTsi();
				awayMidForm += playerInfo.getPlayerForm().getValue();
				awayMidStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}
			
			else if (generalRole == MatchRoleID.DEFENDER_ROLE)
			{
				awayDefCounter++;
				awayDefTSI += playerInfo.getTsi();
				awayDefForm += playerInfo.getPlayerForm().getValue();
				awayDefStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}
			
			else if (generalRole == MatchRoleID.ATTACKER_ROLE)
			{
				awayAttCounter++;
				awayAttTSI += playerInfo.getTsi();
				awayAttForm += playerInfo.getPlayerForm().getValue();
				awayAttStamina += playerInfo.getPlayerSkills().getStaminaSkill().getValue();
			}
		}
		
		Team homeTeam = matchDetails.getMatch().getHomeTeam();
		Team awayTeam = matchDetails.getMatch().getAwayTeam();
		int homeAdvantage = homeTeam.getGoals() - awayTeam.getGoals();
		if(homeAdvantage == 0)
			return "";
		
		String dataString = "";
		dataString += (double)homeTeam.getRatingMidField().getValue()/((double)homeTeam.getRatingMidField().getValue() + (double)awayTeam.getRatingMidField().getValue()) + ",";
		dataString += (double)homeTeam.getRatingRightDef().getValue()/((double)homeTeam.getRatingRightDef().getValue() + (double)awayTeam.getRatingLeftAtt().getValue()) + ",";
		dataString += (double)homeTeam.getRatingMidDef().getValue()/((double)homeTeam.getRatingMidDef().getValue() + (double)awayTeam.getRatingMidAtt().getValue()) + ",";
		dataString += (double)homeTeam.getRatingLeftDef().getValue()/((double)homeTeam.getRatingLeftDef().getValue() + (double)awayTeam.getRatingRightAtt().getValue()) + ",";
		dataString += (double)homeTeam.getRatingRightAtt().getValue()/((double)homeTeam.getRatingRightAtt().getValue() + (double)awayTeam.getRatingLeftDef().getValue()) + ",";
		dataString += (double)homeTeam.getRatingMidAtt().getValue()/((double)homeTeam.getRatingMidAtt().getValue() + (double)awayTeam.getRatingMidDef().getValue()) + ",";
		dataString += (double)homeTeam.getRatingLeftAtt().getValue()/((double)homeTeam.getRatingLeftAtt().getValue() + (double)awayTeam.getRatingRightDef().getValue()) + ",";
		
		dataString += homeTeam.getVnukStats() + ",";
		dataString += awayTeam.getVnukStats() + ",";
		
		dataString += (double)homeAttTSI/(double)homeAttCounter + ",";
		dataString += (double)homeMidTSI/(double)homeMidCounter + ",";
		dataString += (double)homeDefTSI/(double)homeDefCounter + ",";
		dataString += (double)homeKeeperTSI/(double)homeKeeperCounter + ",";
		dataString += (double)awayAttTSI/(double)awayAttCounter + ",";
		dataString += (double)awayMidTSI/(double)awayMidCounter + ",";
		dataString += (double)awayDefTSI/(double)awayDefCounter + ",";
		dataString += (double)awayKeeperTSI/(double)awayKeeperCounter + ",";
		
		dataString += (double)homeAttForm/(double)homeAttCounter + ",";
		dataString += (double)homeMidForm/(double)homeMidCounter + ",";
		dataString += (double)homeDefForm/(double)homeDefCounter + ",";
		dataString += (double)homeKeeperForm/(double)homeKeeperCounter + ",";
		dataString += (double)awayAttForm/(double)awayAttCounter + ",";
		dataString += (double)awayMidForm/(double)awayMidCounter + ",";
		dataString += (double)awayDefForm/(double)awayDefCounter + ",";
		dataString += (double)awayKeeperForm/(double)awayKeeperCounter + ",";
		
		dataString += (double)homeAttStamina/(double)homeAttCounter + ",";
		dataString += (double)homeMidStamina/(double)homeMidCounter + ",";
		dataString += (double)homeDefStamina/(double)homeDefCounter + ",";
		dataString += (double)homeKeeperStamina/(double)homeKeeperCounter + ",";
		dataString += (double)awayAttStamina/(double)awayAttCounter + ",";
		dataString += (double)awayMidStamina/(double)awayMidCounter + ",";
		dataString += (double)awayDefStamina/(double)awayDefCounter + ",";
		dataString += (double)awayKeeperStamina/(double)awayKeeperCounter + ",";
		
		if(homeAdvantage > 0)
			dataString += "win";
		else
			dataString += "loss";
		return dataString + "\n";
	}

}
