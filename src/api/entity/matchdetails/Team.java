package api.entity.matchdetails;

import datatype.MatchRating;
import datatype.MatchTacticType;
import datatype.MatchTeamAttitude;
import datatype.SkillLevel;
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

	public void setFormation(String formation) {
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

	public void setRatingMidField(String ratingMidField) {
		if(ratingMidField != null)
			this.ratingMidField = new MatchRating(ratingMidField);
	}

	public MatchRating getRatingRightDef() {
		return ratingRightDef;
	}

	public void setRatingRightDef(String ratingRightDef) {
		if(ratingRightDef != null)
			this.ratingRightDef = new MatchRating(ratingRightDef);
	}

	public MatchRating getRatingMidDef() {
		return ratingMidDef;
	}

	public void setRatingMidDef(String ratingMidDef) {
		if(ratingMidDef != null)
			this.ratingMidDef = new MatchRating(ratingMidDef);
	}

	public MatchRating getRatingLeftDef() {
		return ratingLeftDef;
	}

	public void setRatingLeftDef(String ratingLeftDef) {
		if(ratingLeftDef != null)	
			this.ratingLeftDef = new MatchRating(ratingLeftDef);
	}

	public MatchRating getRatingRightAtt() {
		return ratingRightAtt;
	}

	public void setRatingRightAtt(String ratingRightAtt) {
		if(ratingRightAtt != null)
			this.ratingRightAtt = new MatchRating(ratingRightAtt);
	}

	public MatchRating getRatingMidAtt() {
		return ratingMidAtt;
	}

	public void setRatingMidAtt(String ratingMidAtt) {
		if(ratingMidAtt != null)
			this.ratingMidAtt = new MatchRating(ratingMidAtt);
	}

	public MatchRating getRatingLeftAtt() {
		return ratingLeftAtt;
	}

	public void setRatingLeftAtt(String ratingLeftAtt) {
		if(ratingLeftAtt != null)
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
	
	
}
