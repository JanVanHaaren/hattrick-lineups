package api.ratingpredictor;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import api.HattrickObjectCreator;
import api.entity.Training;
import api.entity.datatype.MatchBehaviourID;
import api.entity.datatype.MatchRoleID;
import api.entity.datatype.SpecialtyID;
import api.entity.datatype.TrainerType;
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
	
	private double forwardPenalty;
	private double innerMidfieldPenalty;
	private double centralDefenderPenalty;
	
	private boolean ratingsPredicted;

	private TrainerType trainerType;
	
	private Training training;
	
	private boolean homeMatch;
	
	public static void main(String[] args) throws IOException, IllegalXMLException, InvalidBehaviourForRoleException {
		Map<MatchRoleID,PlayerBehaviour> positions = new HashMap<MatchRoleID, PlayerBehaviour>();
		
		RatingPredictor rp = aaronPlayers2(positions);
		rp.producePredictions();
		System.out.println("Rightdef: " + rp.predictRatingRightDef());
		System.out.println("Middef: " + rp.predictRatingMidDef());
		System.out.println("Leftdef: " + rp.predictRatingLeftDef());
		
		System.out.println("Midfield: " + rp.predictRatingMidField());

		System.out.println("RightAtt: " + rp.predictRatingRightAtt());
		System.out.println("MidAtt: " + rp.predictRatingMidAtt());
		System.out.println("LeftAtt: " + rp.predictRatingLeftAtt());
	}
	
	private static RatingPredictor janPlayers(Map<MatchRoleID,PlayerBehaviour> positions) throws IOException, IllegalXMLException
	{
		Player keeper = new Player(-1, false, null, 6, 6, 7, 20, 10, 4, 4, 3, 5, 3); //Fehér
		Player wingBackR = new Player(-1, false, null, 4, 7, 7, 20, 1, 10, 6, 3, 10, 4); //Haberecht
		Player centralDefenderC = new Player(-1, false, null, 5, 7, 7, 20, 1, 13, 7, 4, 7, 3); //Alba Tercedor
		Player wingBackL = new Player(-1, false, SpecialtyID.HEAD_SPECIALIST, 6, 7, 7, 20, 1, 13, 7, 5, 7, 3); //Filar
		Player wingerR = new Player(-1, true, SpecialtyID.QUICK, 5, 6, 7, 20, 1, 3, 11, 9, 16, 4); //Clompen
		Player innerMidfieldR = new Player(-1, false, null, 7, 8, 6, 20, 1, 6, 16, 8, 5, 2); //Erdin
		Player innerMidfieldC = new Player(-1, false, null, 5, 8, 8, 20, 1, 5, 12, 9, 8, 3); //Havelange
		Player innerMidfieldL = new Player(-1, false, null, 6, 8, 7, 20, 1, 5, 16, 10, 5, 2); //Putek
		Player wingerL = new Player(-1, false, SpecialtyID.QUICK, 4, 7, 8, 20, 1, 4, 10, 7, 15, 2); //De Vadder
		Player forwardR = new Player(-1, false, SpecialtyID.UNPERDICTABLE, 7, 8, 7, 20, 1, 3, 5, 9, 6, 12); //Javier Moreno
		Player forwardL = new Player(-1, false, null, 6, 6, 7, 20, 1, 4, 4, 9, 8, 12); //Spacic
		positions.put(MatchRoleID.KEEPER, new PlayerBehaviour(keeper, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_BACK, new PlayerBehaviour(wingBackR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.MIDDLE_CENTRAL_DEFENDER, new PlayerBehaviour(centralDefenderC, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_BACK, new PlayerBehaviour(wingBackL, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_WINGER, new PlayerBehaviour(wingerR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_INNER_MIDFIELD, new PlayerBehaviour(innerMidfieldR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.MIDDLE_INNER_MIDFIELD, new PlayerBehaviour(innerMidfieldC, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_INNER_MIDFIELD, new PlayerBehaviour(innerMidfieldL, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_WINGER, new PlayerBehaviour(wingerL, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_FORWARD, new PlayerBehaviour(forwardR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_FORWARD, new PlayerBehaviour(forwardL, MatchBehaviourID.NORMAL));
		
		return new RatingPredictor(positions, TrainerType.BALANCED, new Training(4,4), false);
	}
	
	private static RatingPredictor aaronPlayers2(Map<MatchRoleID,PlayerBehaviour> positions) throws IOException, IllegalXMLException
	{
		Player keeper = new Player(-1, true, SpecialtyID.TECHNICAL, 3, 5, 5, 20, 8, 1, 1, 1, 2, 1); //Florin
		Player wingBackR = new Player(-1, true, null, 4, 5, 6, 20, 1, 5, 2, 5, 5, 4); //Wenzel
		Player centralDefenderR = new Player(-1, true, null, 3, 5, 5, 20, 2, 5, 4, 4, 3, 3); //Termont
		Player centralDefenderL = new Player(-1, true, SpecialtyID.QUICK, 2, 4, 6, 20, 1, 4, 4, 2, 4, 5); //Verhoene
		Player wingBackL = new Player(-1, true, null, 3, 6, 6, 20, 1, 5, 2, 3, 3, 1); //Arnould
		Player wingerR = new Player(-1, true, SpecialtyID.HEAD_SPECIALIST, 1, 5, 6, 20, 2, 1, 4, 3, 3, 3); //Lafalize
		Player innerMidfieldR = new Player(-1, true, null, 1, 5, 7, 20, 1, 6, 6, 5, 3, 6); //Ghaye
		Player innerMidfieldL = new Player(-1, true, SpecialtyID.POWERFUL, 2, 7, 5, 20, 1, 5, 6, 5, 6, 4); //Branswyck
		Player wingerL = new Player(-1, true, null, 4, 4, 5, 20, 1, 2, 5, 4, 3, 3); //Debaveye
		Player forwardR = new Player(-1, true, SpecialtyID.UNPERDICTABLE, 2, 6, 8, 20, 1, 3, 3, 3, 6, 6); //Godfroid
		Player forwardL = new Player(-1, true, null, 3, 5, 6, 20, 1, 5, 2, 5, 3, 6); //Pauwels
		positions.put(MatchRoleID.KEEPER, new PlayerBehaviour(keeper, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_BACK, new PlayerBehaviour(wingBackR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_CENTRAL_DEFENDER, new PlayerBehaviour(centralDefenderR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_CENTRAL_DEFENDER, new PlayerBehaviour(centralDefenderL, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_BACK, new PlayerBehaviour(wingBackL, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_WINGER, new PlayerBehaviour(wingerR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_INNER_MIDFIELD, new PlayerBehaviour(innerMidfieldR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_INNER_MIDFIELD, new PlayerBehaviour(innerMidfieldL, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_WINGER, new PlayerBehaviour(wingerL, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.RIGHT_FORWARD, new PlayerBehaviour(forwardR, MatchBehaviourID.NORMAL));
		positions.put(MatchRoleID.LEFT_FORWARD, new PlayerBehaviour(forwardL, MatchBehaviourID.NORMAL));
		
		return new RatingPredictor(positions, TrainerType.DEFENSIVE, new Training(4,5), true);
	}
	
	private static RatingPredictor aaronPlayers(Map<MatchRoleID,PlayerBehaviour> positions) throws IOException, IllegalXMLException
	{
		positions.put(MatchRoleID.KEEPER, new PlayerBehaviour(386236363, MatchBehaviourID.NORMAL)); //Florin
		positions.put(MatchRoleID.RIGHT_BACK, new PlayerBehaviour(386236365, MatchBehaviourID.NORMAL)); //Wenzel
		positions.put(MatchRoleID.RIGHT_CENTRAL_DEFENDER, new PlayerBehaviour(386236368, MatchBehaviourID.NORMAL)); //Termont
		positions.put(MatchRoleID.LEFT_CENTRAL_DEFENDER, new PlayerBehaviour(386236371, MatchBehaviourID.NORMAL)); //Verhoene
		positions.put(MatchRoleID.LEFT_BACK, new PlayerBehaviour(386236367, MatchBehaviourID.NORMAL)); //Arnould
		positions.put(MatchRoleID.RIGHT_WINGER, new PlayerBehaviour(386236379, MatchBehaviourID.NORMAL)); //Lafalize
		positions.put(MatchRoleID.RIGHT_INNER_MIDFIELD, new PlayerBehaviour(386236370, MatchBehaviourID.NORMAL)); //Ghaye
		positions.put(MatchRoleID.LEFT_INNER_MIDFIELD, new PlayerBehaviour(386236375, MatchBehaviourID.NORMAL)); //Branswyck
		positions.put(MatchRoleID.LEFT_WINGER, new PlayerBehaviour(386236369, MatchBehaviourID.NORMAL)); //Debaveye
		positions.put(MatchRoleID.RIGHT_FORWARD, new PlayerBehaviour(386236373, MatchBehaviourID.NORMAL)); //Godfroid
		positions.put(MatchRoleID.LEFT_FORWARD, new PlayerBehaviour(386236372, MatchBehaviourID.NORMAL)); //Pauwels
		
		return new RatingPredictor(positions, 386236362, 321576, false);
	}
	
	public RatingPredictor(Map<MatchRoleID,PlayerBehaviour> positions, int trainerId, int teamId, boolean homeMatch) throws IOException, IllegalXMLException
	{
		this(positions,
				new HattrickObjectCreator().getPlayerDetails(trainerId).getPlayer().getTrainerData().getTrainerType(),
				new HattrickObjectCreator().getTraining(teamId),
				homeMatch);
	}
	
	public RatingPredictor(Map<MatchRoleID,PlayerBehaviour> positions, TrainerType trainerType, Training training, boolean homeMatch)
	{
		this.positions = positions;
		
		this.ratingMidField = 0;
		this.ratingRightDef = 0;
		this.ratingMidDef = 0;
		this.ratingLeftDef = 0;
		this.ratingRightAtt = 0;
		this.ratingMidAtt = 0;
		this.ratingLeftAtt = 0;
		
		int nbForward = 0;
		int nbInnerMidfield = 0;
		int nbCentralDefender = 0;
		
		this.trainerType = trainerType;
		
		this.training = training;
		
		this.homeMatch = homeMatch;
		
		for(MatchRoleID role : positions.keySet())
		{
			if(role.equals(MatchRoleID.LEFT_CENTRAL_DEFENDER)
					|| role.equals(MatchRoleID.MIDDLE_CENTRAL_DEFENDER)
					|| role.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER))
				nbCentralDefender++;
			
			if(role.equals(MatchRoleID.LEFT_INNER_MIDFIELD)
					|| role.equals(MatchRoleID.MIDDLE_INNER_MIDFIELD)
					|| role.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
				nbInnerMidfield++;
			
			if(role.equals(MatchRoleID.LEFT_FORWARD)
					|| role.equals(MatchRoleID.MIDDLE_FORWARD)
					|| role.equals(MatchRoleID.RIGHT_FORWARD))
				nbForward++;
		}
		
		this.forwardPenalty =  (nbForward == 1 ) ? 1 : ((nbForward == 2) ? 0.948 : 0.865);
		this.innerMidfieldPenalty = (nbInnerMidfield == 1 ) ? 1 : ((nbInnerMidfield == 2) ? 0.9356 : 0.8268);
		this.centralDefenderPenalty = (nbCentralDefender == 1 ) ? 1 : ((nbCentralDefender == 2) ? 0.9647 : 0.8731);
		
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
		
		this.ratingRightDef += 0.008504*Math.pow(this.ratingRightDef, 2) - 0.000027*Math.pow(this.ratingRightDef, 3);
		this.ratingMidDef += 0.008504*Math.pow(this.ratingMidDef, 2) - 0.000027*Math.pow(this.ratingMidDef, 3);
		this.ratingLeftDef += 0.008504*Math.pow(this.ratingLeftDef, 2) - 0.000027*Math.pow(this.ratingLeftDef, 3);
		
		this.ratingMidField += 0.008504*Math.pow(this.ratingMidField, 2) - 0.000027*Math.pow(this.ratingMidField, 3);
		
		this.ratingRightAtt += 0.008504*Math.pow(this.ratingRightAtt, 2) - 0.000027*Math.pow(this.ratingRightAtt, 3);
		this.ratingMidAtt += 0.008504*Math.pow(this.ratingMidAtt, 2) - 0.000027*Math.pow(this.ratingMidAtt, 3);
		this.ratingLeftAtt += 0.008504*Math.pow(this.ratingLeftAtt, 2) - 0.000027*Math.pow(this.ratingLeftAtt, 3);
		
		
		this.ratingLeftAtt *= this.trainerType.equals(TrainerType.BALANCED) ? 1.05 : ((this.trainerType.equals(TrainerType.OFFENSIVE)) ? 1.133359 : 0.921278);
		this.ratingRightAtt *= this.trainerType.equals(TrainerType.BALANCED) ? 1.05 : ((this.trainerType.equals(TrainerType.OFFENSIVE)) ? 1.133359 : 0.921278);
		this.ratingMidAtt *= this.trainerType.equals(TrainerType.BALANCED) ? 1.05 : ((this.trainerType.equals(TrainerType.OFFENSIVE)) ? 1.135257 : 0.927930);
		this.ratingRightDef *= this.trainerType.equals(TrainerType.BALANCED) ? 1.05 : ((this.trainerType.equals(TrainerType.OFFENSIVE)) ? 0.927577 : 1.197332);
		this.ratingLeftDef *= this.trainerType.equals(TrainerType.BALANCED) ? 1.05 : ((this.trainerType.equals(TrainerType.OFFENSIVE)) ? 0.927577 : 1.197332);
		this.ratingMidDef *= this.trainerType.equals(TrainerType.BALANCED) ? 1.05 : ((this.trainerType.equals(TrainerType.OFFENSIVE)) ? 0.928162 : 1.196307);
	
		this.ratingMidField *= Math.pow(0.147832*((double)this.training.getMorale()), 0.417779);
		if(this.homeMatch)
			this.ratingMidField *= 1.20;
		
		this.ratingRightAtt *= (1.0 + 0.0525 * ((double)(this.training.getConfidence() - 5)));
		this.ratingLeftAtt *= (1.0 + 0.0525 * ((double)(this.training.getConfidence() - 5)));
		this.ratingMidAtt *= (1.0 + 0.0525 * ((double)(this.training.getConfidence() - 5)));
		
		this.ratingRightDef = Math.round(this.ratingRightDef);
		this.ratingMidDef = Math.round(this.ratingMidDef);
		this.ratingLeftDef = Math.round(this.ratingLeftDef);
		this.ratingMidField = Math.round(this.ratingMidField);
		this.ratingRightAtt = Math.round(this.ratingRightAtt);
		this.ratingMidAtt = Math.round(this.ratingMidAtt);
		this.ratingLeftAtt = Math.round(this.ratingLeftAtt);
	}

	private void addSideForwardFactors(MatchRoleID position, Player player,
			MatchBehaviourID behaviour, PlayerSkills playerSkills)
			throws InvalidBehaviourForRoleException {
		switch(behaviour)
		{
		case NORMAL:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.593971677962079*this.forwardPenalty;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.218876084491443*this.forwardPenalty;
//			if(position.equals(MatchRoleID.RIGHT_FORWARD))
//			{
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.194298673558187*this.forwardPenalty;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.105693415258036*this.forwardPenalty;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.158112641377712*this.forwardPenalty;
//			}
//			else
//			{
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.194298673558187*this.forwardPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.105693415258036*this.forwardPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.158112641377712*this.forwardPenalty;
//			}
			break;
		case DEFENSIVE:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.365602172938477*this.forwardPenalty;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.350593216445366*this.forwardPenalty;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.200663555076171*this.forwardPenalty;
//			if(position.equals(MatchRoleID.RIGHT_FORWARD))
//			{
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.100208327839456*this.forwardPenalty;
				if(player.getSpecialty().equals(SpecialtyID.TECHNICAL))
					this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.258314800652819*this.forwardPenalty;
				else	
					this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.196724769916405*this.forwardPenalty;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.108702440947177*this.forwardPenalty;
//			}
//			else
//			{
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.100208327839456*this.forwardPenalty;
				if(player.getSpecialty().equals(SpecialtyID.TECHNICAL))
					this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.258314800652819*this.forwardPenalty;
				else
					this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.196724769916405*this.forwardPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.108702440947177*this.forwardPenalty;
//			}
			break;
		case TOWARDS_WING:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.361066427573112*this.forwardPenalty;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.154848237124548*this.forwardPenalty;
			if(position.equals(MatchRoleID.RIGHT_FORWARD))
			{
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.391339890825664*this.forwardPenalty;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.156219508979194*this.forwardPenalty;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.459551875390065*this.forwardPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.159594947390628*this.forwardPenalty;
			}
			else
			{
				this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.391339890825664*this.forwardPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.156219508979194*this.forwardPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.459551875390065*this.forwardPenalty;
				this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.159594947390628*this.forwardPenalty;
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
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.593971677962079*this.forwardPenalty;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.218876084491443*this.forwardPenalty;
			this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.194298673558187*this.forwardPenalty;//*0.5;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.105693415258036*this.forwardPenalty;//*0.5;
			this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.158112641377712*this.forwardPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.194298673558187*this.forwardPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.105693415258036*this.forwardPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.158112641377712*this.forwardPenalty;//*0.5;
			break;
		case DEFENSIVE:
			this.ratingMidAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.365602172938477*this.forwardPenalty;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.350593216445366*this.forwardPenalty;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.200663555076171*this.forwardPenalty;
			this.ratingRightAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.100208327839456*this.forwardPenalty;//*0.5;
			if(player.getSpecialty().equals(SpecialtyID.TECHNICAL))
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.258314800652819*this.forwardPenalty;//*0.5;
			else	
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.196724769916405*this.forwardPenalty;//*0.5;
			this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.108702440947177*this.forwardPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getScorerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.100208327839456*this.forwardPenalty;//*0.5;
			if(player.getSpecialty().equals(SpecialtyID.TECHNICAL))
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.258314800652819*this.forwardPenalty;//*0.5;
			else	
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.196724769916405*this.forwardPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.108702440947177*this.forwardPenalty;//*0.5;
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
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4682*this.innerMidfieldPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2496*this.innerMidfieldPenalty;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1893*this.innerMidfieldPenalty;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1894*this.innerMidfieldPenalty;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1893*this.innerMidfieldPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1894*this.innerMidfieldPenalty;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1931*this.innerMidfieldPenalty;
			break;
		case DEFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4421*this.innerMidfieldPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.3705*this.innerMidfieldPenalty;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2711*this.innerMidfieldPenalty;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1218*this.innerMidfieldPenalty;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2711*this.innerMidfieldPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1218*this.innerMidfieldPenalty;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1302*this.innerMidfieldPenalty;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4421*this.innerMidfieldPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1346*this.innerMidfieldPenalty;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1025*this.innerMidfieldPenalty;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1877*this.innerMidfieldPenalty;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1025*this.innerMidfieldPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1877*this.innerMidfieldPenalty;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.287*this.innerMidfieldPenalty;
			break;
		case TOWARDS_WING:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4124*this.innerMidfieldPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.217*this.innerMidfieldPenalty;
			if(position.equals(MatchRoleID.RIGHT_INNER_MIDFIELD))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2491*this.innerMidfieldPenalty;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2356*this.innerMidfieldPenalty;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4288*this.innerMidfieldPenalty;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2491*this.innerMidfieldPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2356*this.innerMidfieldPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4288*this.innerMidfieldPenalty;
			}
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1347*this.innerMidfieldPenalty;
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
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4682*this.innerMidfieldPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2496*this.innerMidfieldPenalty;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0946*this.innerMidfieldPenalty;//*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0946*this.innerMidfieldPenalty;//*0.5;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1931*this.innerMidfieldPenalty;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0947*this.innerMidfieldPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0947*this.innerMidfieldPenalty;//*0.5;
			break;
		case DEFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4421*this.innerMidfieldPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.3705*this.innerMidfieldPenalty;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1355*this.innerMidfieldPenalty;//*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1355*this.innerMidfieldPenalty;//*0.5;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1302*this.innerMidfieldPenalty;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0609*this.innerMidfieldPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0609*this.innerMidfieldPenalty;//*0.5;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4421*this.innerMidfieldPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1346*this.innerMidfieldPenalty;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0512*this.innerMidfieldPenalty;//*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0512*this.innerMidfieldPenalty;//*0.5;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.287*this.innerMidfieldPenalty;
			this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0938*this.innerMidfieldPenalty;//*0.5;
			this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0938*this.innerMidfieldPenalty;//*0.5;
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
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.178486894012448;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0504;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0803;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.181157894736842;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2134;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.877;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.181157894736842;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2134;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.877;
			}
			break;
		case TOWARDS_MIDDLE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.268694661172964;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1524;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0876;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2848;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1152;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.494444444444444;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2848;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1152;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.494444444444444;
			}
			break;
		case NORMAL:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.212851757692645;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1254;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0615;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.3499;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1821;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.749222222222222;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.3499;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1821;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.749222222222222;
			}
			break;
		case DEFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.178486894012448;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1568;
			this.ratingMidAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.031;
			if(position.equals(MatchRoleID.RIGHT_WINGER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.487789473684211;
				this.ratingRightAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1501;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.634444444444444;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.487789473684211;
				this.ratingLeftAtt += ((double)playerSkills.getPassingSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1501;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.634444444444444;
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
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0309;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.29868;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*1.0024;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.283666666666667;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*1.0024;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.283666666666667;
			}
			break;
		case NORMAL:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2804;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.9213;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.444111111111111;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.9213;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.444111111111111;
			}
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0782;
			break;
		case OFFENSIVE:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2384;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.6994;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.542;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.6994;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.542;
			}
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.1078;
			break;
		case TOWARDS_MIDDLE:
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.4261;
			if(position.equals(MatchRoleID.RIGHT_BACK))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.6889;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.242535;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.6889;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.242535;
			}
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0782;
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
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.623608017817372*this.centralDefenderPenalty;
			if(position.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER))
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.51732532242192*this.centralDefenderPenalty;
			else
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.51732532242192*this.centralDefenderPenalty;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.114332444120847*this.centralDefenderPenalty;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.154198959584748*this.centralDefenderPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.45216760760346*this.centralDefenderPenalty;
			if(position.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER))
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.378826332418294*this.centralDefenderPenalty;
			else
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.378826332418294*this.centralDefenderPenalty;
			break;
		case TOWARDS_WING:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.0799146551440389*this.centralDefenderPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.485316206557207*this.centralDefenderPenalty;
			if(position.equals(MatchRoleID.RIGHT_CENTRAL_DEFENDER))
			{
				this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.715857079379445*this.centralDefenderPenalty;
				this.ratingRightAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.221266898016264*this.centralDefenderPenalty;
			}
			else
			{
				this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.715857079379445*this.centralDefenderPenalty;
				this.ratingLeftAtt += ((double)playerSkills.getWingerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.221266898016264*this.centralDefenderPenalty;
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
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.623608017817372*this.centralDefenderPenalty;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.25866266121096*this.centralDefenderPenalty;//*0.5;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.25866266121096*this.centralDefenderPenalty;//*0.5;
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.114332444120847*this.centralDefenderPenalty;
			break;
		case OFFENSIVE:
			this.ratingMidField += ((double)playerSkills.getPlaymakerSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.154198959584748*this.centralDefenderPenalty;
			this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.45216760760346*this.centralDefenderPenalty;
			this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.189361371523282*this.centralDefenderPenalty;
			this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.189361371523282*this.centralDefenderPenalty;
			break;
		default:
			throw new InvalidBehaviourForRoleException("Middle central defender must be normal or offensive");
		}
	}

	private void addKeeperFactors(Player player, PlayerSkills playerSkills) {
		this.ratingMidDef += ((double)playerSkills.getKeeperSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.541809523809524;
		this.ratingMidDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2649;
		this.ratingRightDef += ((double)playerSkills.getKeeperSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.599619047619048;
		this.ratingLeftDef += ((double)playerSkills.getKeeperSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.599619047619048;
		this.ratingRightDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2765;
		this.ratingLeftDef += ((double)playerSkills.getDefenderSkill().getValueForPrediction(player))*player.getPerformanceMultiplier()*0.2765;
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
