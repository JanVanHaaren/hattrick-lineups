package api.entity.matchdetails;

import api.entity.datatype.MatchRating;
import api.entity.datatype.MatchTacticType;
import api.entity.datatype.MatchTeamAttitude;
import api.entity.datatype.SkillLevel;
import api.exception.ForfeitException;
import api.exception.IncompleteFormationException;
import api.util.Utils;

public class Team {
	
	private int teamID;
	
	private String teamName;
	
	private String dressURI;
	
	private String formation;
	
	private int goals;
	
	private MatchTacticType tacticType;
	
	private SkillLevel tacticSkill;
	
	private MatchRating ratingMidField;
	
	private MatchRating ratingRightDef;
	
	private MatchRating ratingMidDef;
	
	private MatchRating ratingLeftDef;
	
	private MatchRating ratingRightAtt;
	
	private MatchRating ratingMidAtt;
	
	private MatchRating ratingLeftAtt;
	
	private MatchTeamAttitude teamAttitude;
	
	private MatchRating ratingIndirectSetPiecesDef;
	
	private MatchRating ratingIndirectSetPiecesAtt;
	
	private boolean forfeit;

	public int getTeamID() {
		return teamID;
	}

	public void setTeamID(String teamID) {
		this.teamID = Utils.getIntFromString(teamID);
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getDressURI() {
		return dressURI;
	}

	public void setDressURI(String dressURI) {
		this.dressURI = dressURI;
	}

	public String getFormation() {
		return formation;
	}

	public void setFormation(String formation) throws IncompleteFormationException {
		if(Integer.parseInt(formation.substring(0,1))
			+ Integer.parseInt(formation.substring(2,3))
			+ Integer.parseInt(formation.substring(4,5))
			!= 10)
			throw new IncompleteFormationException("Less than 10 players (without keeper) in team " + this.getTeamID());
		this.formation = formation;
	}

	public int getGoals() {
		return goals;
	}

	public void setGoals(String goals) {
		this.goals = Utils.getIntFromString(goals);
	}

	public MatchTacticType getTacticType() {
		return tacticType;
	}

	public void setTacticType(String tacticType) {
		this.tacticType = MatchTacticType.getMatchTacticType(tacticType);
	}

	public SkillLevel getTacticSkill() {
		return tacticSkill;
	}

	public void setTacticSkill(String tacticSkill) {
		this.tacticSkill = new SkillLevel(tacticSkill);
	}

	public MatchRating getRatingMidField() {
		return ratingMidField;
	}

	public void setRatingMidField(String ratingMidField) throws ForfeitException {
		if(!ratingMidField.equals("0"))
			this.ratingMidField = new MatchRating(ratingMidField);
		else
			throw new ForfeitException("Team " + this.getTeamID() + " forfeited a match.");
	}

	public MatchRating getRatingRightDef() {
		return ratingRightDef;
	}

	public void setRatingRightDef(String ratingRightDef) {
		this.ratingRightDef = new MatchRating(ratingRightDef);
	}

	public MatchRating getRatingMidDef() {
		return ratingMidDef;
	}

	public void setRatingMidDef(String ratingMidDef) {
		this.ratingMidDef = new MatchRating(ratingMidDef);
	}

	public MatchRating getRatingLeftDef() {
		return ratingLeftDef;
	}

	public void setRatingLeftDef(String ratingLeftDef) {
		this.ratingLeftDef = new MatchRating(ratingLeftDef);
	}

	public MatchRating getRatingRightAtt() {
		return ratingRightAtt;
	}

	public void setRatingRightAtt(String ratingRightAtt) {
		this.ratingRightAtt = new MatchRating(ratingRightAtt);
	}

	public MatchRating getRatingMidAtt() {
		return ratingMidAtt;
	}

	public void setRatingMidAtt(String ratingMidAtt) {
		this.ratingMidAtt = new MatchRating(ratingMidAtt);
	}

	public MatchRating getRatingLeftAtt() {
		return ratingLeftAtt;
	}

	public void setRatingLeftAtt(String ratingLeftAtt) {
		this.ratingLeftAtt = new MatchRating(ratingLeftAtt);
	}

	public MatchTeamAttitude getTeamAttitude() {
		return teamAttitude;
	}

	public void setTeamAttitude(String teamAttitude) {
		if(teamAttitude != null)
			this.teamAttitude = MatchTeamAttitude.getMatchTeamAttitude(teamAttitude);
	}

	public MatchRating getRatingIndirectSetPiecesDef() {
		return ratingIndirectSetPiecesDef;
	}

	public void setRatingIndirectSetPiecesDef(String ratingIndirectSetPiecesDef) {
		if(ratingIndirectSetPiecesDef != null)
			this.ratingIndirectSetPiecesDef = new MatchRating(ratingIndirectSetPiecesDef);
	}

	public MatchRating getRatingIndirectSetPiecesAtt() {
		return ratingIndirectSetPiecesAtt;
	}

	public void setRatingIndirectSetPiecesAtt(String ratingIndirectSetPiecesAtt) {
		if(ratingIndirectSetPiecesAtt != null)
			this.ratingIndirectSetPiecesAtt = new MatchRating(ratingIndirectSetPiecesAtt);
	}
	
	public boolean isForfeit() {
		return forfeit;
	}

	public void setForfeit(boolean forfeit) {
		this.forfeit = forfeit;
	}
	
	public int getDefenseCount()
	{
		return Integer.parseInt(this.getFormation().substring(0,1));
	}
	
	public int getMidfieldCount()
	{
		return Integer.parseInt(this.getFormation().substring(2,3));
	}
	
	public int getAttackCount()
	{
		return Integer.parseInt(this.getFormation().substring(4,5));
	}
	
	public int getHatStats()
	{
		return 3*getRatingMidField().getValue()
				+ getRatingLeftAtt().getValue() + getRatingMidAtt().getValue() + getRatingRightAtt().getValue()
				+ getRatingLeftDef().getValue() + getRatingMidDef().getValue() + getRatingRightDef().getValue();
	}
	
	public int getVnukStats()
	{
		return 5*getRatingMidField().getValue()
				+ getRatingLeftAtt().getValue() + getRatingMidAtt().getValue() + getRatingRightAtt().getValue()
				+ getRatingLeftDef().getValue() + getRatingMidDef().getValue() + getRatingRightDef().getValue();
	}
	
	public int getOriginalVnukStats()
	{
		return 6*getRatingMidField().getValue()
				+ getRatingLeftAtt().getValue() + getRatingMidAtt().getValue() + getRatingRightAtt().getValue()
				+ getRatingLeftDef().getValue() + getRatingMidDef().getValue() + getRatingRightDef().getValue();
	}

	
	
	
}
