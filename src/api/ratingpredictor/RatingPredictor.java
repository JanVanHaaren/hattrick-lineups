package api.ratingpredictor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.SpecialtyID;
import api.entity.playerdetails.Player;
import api.entity.playerdetails.PlayerSkills;
import api.exception.IllegalXMLException;
import api.exception.InvalidBehaviourForRoleException;

public class RatingPredictor {
	
	private Map<MatchRoleID,PlayerBehaviour> positions;
	
	private double ratingMidField;
	private double ratingRightDef;
	private double ratingMidDef;
	private double ratingLeftDef;
	private double ratingRightAtt;
	private double ratingMidAtt;
	private double ratingLeftAtt;
	
	private boolean ratingsPredicted;
	
	public static void main(String[] args) throws IOException, IllegalXMLException, InvalidBehaviourForRoleException {
		Map<MatchRoleID,PlayerBehaviour> positions = new HashMap<MatchRoleID, PlayerBehaviour>();
		positions.put(MatchRoleID.KEEPER, new PlayerBehaviour(386236363, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_BACK, new PlayerBehaviour(386236365, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_CENTRAL_DEFENDER, new PlayerBehaviour(386236368, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_CENTRAL_DEFENDER, new PlayerBehaviour(386236371, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_BACK, new PlayerBehaviour(386236367, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_WINGER, new PlayerBehaviour(386236379, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_INNER_MIDFIELD, new PlayerBehaviour(386236370, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_INNER_MIDFIELD, new PlayerBehaviour(386236375, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_WINGER, new PlayerBehaviour(386236369, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_FORWARD, new PlayerBehaviour(386236373, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_FORWARD, new PlayerBehaviour(386236372, MatchBehaviourID.NORMAL));
		
		RatingPredictor rp = new RatingPredictor(positions);
		rp.producePredictions();
		System.out.println("Leftdef: " + rp.predictRatingLeftDef());
		System.out.println("Middef: " + rp.predictRatingMidDef());
		System.out.println("Rightdef: " + rp.predictRatingRightDef());
		System.out.println("LeftAtt: " + rp.predictRatingLeftAtt());
		System.out.println("MidAtt: " + rp.predictRatingMidAtt());
		System.out.println("RightAtt: " + rp.predictRatingRightAtt());
		System.out.println("Midfield: " + rp.predictRatingMidField());
	}
	
	public RatingPredictor(Map<MatchRoleID,PlayerBehaviour> positions) throws IOException, IllegalXMLException
	{
		this.positions = positions;
		
		this.ratingMidField = 0;
		this.ratingRightDef = 0;
		this.ratingMidDef = 0;
		this.ratingLeftDef = 0;
		this.ratingRightAtt = 0;
		this.ratingMidAtt = 0;
		this.ratingLeftAtt = 0;
		
		this.ratingsPredicted = false;
		
	}
	
	public void producePredictions() throws InvalidBehaviourForRoleException
	{
		this.ratingsPredicted = true;
		
		this.ratingMidField = 0;
		this.ratingRightDef = 0;
		this.ratingMidDef = 0;
		this.ratingLeftDef = 0;
		this.ratingRightAtt = 0;
		this.ratingMidAtt = 0;
		this.ratingLeftAtt = 0;
		
		for(MatchRoleID position : this.positions.keySet())
		{
			Player player = this.positions.get(position).getPlayer();
			MatchBehaviourID behaviour = this.positions.get(position).getBehaviour();
			PlayerSkills playerSkills = player.getPlayerSkills();
			
			if(position.equals(MatchRoleID.KEEPER))
			{
				addKeeperFactors(player, playerSkills);
				continue;
			}
			
			if(position.equals(MatchRoleID.MIDDLE_CENTRAL_DEFENDER))
			{
				addMiddleCentralDefenderFactors(player, behaviour, playerSkills);
				continue;
			}
			
			if(position.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER) || position.equals(MatchRoleID.LEFT_CENTRAL_DEFENDER))
			{
				addSideCentralDefenderFactors(position, player, behaviour,
						playerSkills);
				continue;
			}
			
			
			if(position.equals(MatchRoleID.LEFT_BACK) || position.equals(MatchRoleID.RIGHT_BACK))
			{
				addWingBackFactors(position, player, behaviour, playerSkills);
				continue;
			}
			
			if(position.equals(MatchRoleID.LEFT_WINGER) || position.equals(MatchRoleID.RIGHT_WINGER))
			{
				addWingerFactors(position, player, behaviour, playerSkills);
				continue;
			}
			
			if(position.equals(MatchRoleID.MIDDLE_INNER_MIDFIELD))
			{
				addMiddleInnerMidfieldFactors(player, behaviour, playerSkills);
				continue;
			}
			
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD) || position.equals(MatchRoleID.LEFT_INNER_MIDFIELD))
			{
				addSideInnerMidfieldFactors(position, player, behaviour,
						playerSkills);
				continue;
			}
			
			if(position.equals(MatchRoleID.MIDDLE_FORWARD))
			{
				addMiddleForwardFactors(player, behaviour, playerSkills);
				continue;
			}
			
			if(position.equals(MatchRoleID.RIGHT_FORWARD) || position.equals(MatchRoleID.LEFT_FORWARD))
			{
				addSideForwardFactors(position, player, behaviour, playerSkills);
				continue;
			}
		}
	}

	private void addSideForwardFactors(MatchRoleID position, Player player,
			MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case NORMAL:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier();
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.369;
			if(position.equals(MatchRoleID.RIGHT_FORWARD))
			{
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.224;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.122;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.19;
			}
			else
			{
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.224;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.122;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.19;
			}
			break;
		case DEFENSIVE:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.583;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.543;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.406;
			if(position.equals(MatchRoleID.RIGHT_FORWARD))
			{
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.109;
				if(player.getSpecialty().equals(SpecialtyID.TECHNICAL))
					this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.279;
				else	
					this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.215;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.124;
			}
			else
			{
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.109;
				if(player.getSpecialty().equals(SpecialtyID.TECHNICAL))
					this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.279;
				else
					this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.215;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.124;
			}
			break;
		case TOWARDS_WING:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.607;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.261;
			if(position.equals(MatchRoleID.RIGHT_FORWARD))
			{
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.451;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.18;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.522;
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.182;
			}
			else
			{
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.451;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.18;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.522;
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.182;
			}
			break;
		default:
			throw new InvalidBehaviourForRoleException("Side forward players must be normal, defensive or towards wing");
		}
	}

	private void addMiddleForwardFactors(Player player,
			MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case NORMAL:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier();
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.369;
			this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.224*0.5;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.122*0.5;
			this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.19*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.224*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.122*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.19*0.5;
			break;
		case DEFENSIVE:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.583;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.543;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.406;
			this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.109*0.5;
			if(player.getSpecialty().equals(SpecialtyID.TECHNICAL))
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.279*0.5;
			else	
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.215*0.5;
			this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.124*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValue())*player.getPerformanceMultiplier()*0.109*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.215*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.124*0.5;
			break;
		default:
			throw new InvalidBehaviourForRoleException("Middle forward players must be normal or defensive");
		}
	}

	private void addSideInnerMidfieldFactors(MatchRoleID position,
			Player player, MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case NORMAL:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier();
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.4;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.189;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.218;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.189;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.218;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.325;
			break;
		case DEFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.944;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.594;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.219;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.944;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.594;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.219;
			break;
		case TOWARDS_WING:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.881;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.348;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.291;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.271;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.291;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.271;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.227;
			break;
		default:
			throw new InvalidBehaviourForRoleException("Side inner midfielders must be normal, defensive, offensive or towards wing");
		}
	}

	private void addMiddleInnerMidfieldFactors(Player player,
			MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case NORMAL:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier();
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.4;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.189*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.189*0.5;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.325;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.218*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.218*0.5;
			break;
		case DEFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.944;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.594;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27*0.5;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.219;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14*0.5;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.944;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.594;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.27*0.5;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.219;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.14*0.5;
			break;
		default:
			throw new InvalidBehaviourForRoleException("Middle inner midfielders must be normal, defensive or offensive");
		}
	}

	private void addWingerFactors(MatchRoleID position, Player player,
			MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.381;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.085;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.135;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.18;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.246;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier();
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.18;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.246;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier();
			}
			break;
		case TOWARDS_MIDDLE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.574;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.244;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.148;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.284;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.133;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.564;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.284;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.133;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.564;
			}
			break;
		case NORMAL:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.455;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.201;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.104;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.349;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.21;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.854;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.349;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.21;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.854;
			}
			break;
		case DEFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.381;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.264;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.052;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.485;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.173;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.723;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.485;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValue())*player.getPerformanceMultiplier()*0.173;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.723;
			}
			break;
		default:
			throw new InvalidBehaviourForRoleException("Wingers must be normal, offensive, defensive or towards middle");
		}
	}

	private void addWingBackFactors(MatchRoleID position, Player player,
			MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case DEFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.06;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.479;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier();
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.323;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier();
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.323;
			}
			break;
		case NORMAL:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.45;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.919;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.506;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.919;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.506;
			}
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.167;
			break;
		case OFFENSIVE:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.382;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.698;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.618;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.698;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.618;
			}
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.23;
			break;
		case TOWARDS_MIDDLE:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.683;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.687;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.279;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.687;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.279;
			}
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.167;
			break;
		default:
			throw new InvalidBehaviourForRoleException("Wing backs must be normal, offensive, defensive or towards middle");
		}
	}

	private void addSideCentralDefenderFactors(MatchRoleID position,
			Player player, MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case NORMAL:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier();
			if(position.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER))
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.516;
			else
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.516;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.236;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.318;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.725;
			if(position.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER))
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.378;
			else
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.378;
			break;
		case TOWARDS_WING:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.165;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.778;
			if(position.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.711;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.246;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.711;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValue())*player.getPerformanceMultiplier()*0.246;
			}
			break;
		default:
			throw new InvalidBehaviourForRoleException("Side central defender must be normal, offensive or towards wing");
		}
	}

	private void addMiddleCentralDefenderFactors(Player player,
			MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case NORMAL:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier();
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.516*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.516*0.5;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.236;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValue())*player.getPerformanceMultiplier()*0.318;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.725;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.378;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.378;
			break;
		default:
			throw new InvalidBehaviourForRoleException("Middle central defender must be normal or offensive");
		}
	}

	private void addKeeperFactors(Player player, PlayerSkills playerSkills) {
		this.ratingMidDef += ((double)playerSkills.getKeeperSkill().getValue())*player.getPerformanceMultiplier()*0.866;
		this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.425;
		this.ratingRightDef += ((double)playerSkills.getKeeperSkill().getValue())*player.getPerformanceMultiplier()*0.597;
		this.ratingLeftDef += ((double)playerSkills.getKeeperSkill().getValue())*player.getPerformanceMultiplier()*0.597;
		this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.425;
		this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValue())*player.getPerformanceMultiplier()*0.425;
	}
	
	public double predictRatingMidField() throws InvalidBehaviourForRoleException
	{
		if(!ratingsPredicted)
			producePredictions();
		return this.ratingMidField;
	}
	
	public double predictRatingRightDef() throws InvalidBehaviourForRoleException
	{
		if(!ratingsPredicted)
			producePredictions();
		return this.ratingRightDef;
	}
	
	public double predictRatingMidDef() throws InvalidBehaviourForRoleException
	{
		if(!ratingsPredicted)
			producePredictions();
		return this.ratingMidDef;
	}
	
	public double predictRatingLeftDef() throws InvalidBehaviourForRoleException
	{
		if(!ratingsPredicted)
			producePredictions();
		return this.ratingLeftDef;
	}
	
	public double predictRatingRightAtt() throws InvalidBehaviourForRoleException
	{
		if(!ratingsPredicted)
			producePredictions();
		return this.ratingRightAtt;
	}
	
	public double predictRatingMidAtt() throws InvalidBehaviourForRoleException
	{
		if(!ratingsPredicted)
			producePredictions();
		return this.ratingMidAtt;
	}
	
	public double predictRatingLeftAtt() throws InvalidBehaviourForRoleException
	{
		if(!ratingsPredicted)
			producePredictions();
		return this.ratingLeftAtt;
	}
}
