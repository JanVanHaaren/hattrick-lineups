package api.entity.matchdetails;

import api.util.Utils;

public class Team {
	
	private int teamID;
	
	private String teamName;
	
	private String dressURI;
	
	private String formation;
	
	private int goals;
	
	private int tacticType;
	
	private int tacticSkill;
	
	private int ratingMidField;
	
	private int ratingRightDef;
	
	private int ratingMidDef;
	
	private int ratingLeftDef;
	
	private int ratingRightAtt;
	
	private int ratingMidAtt;
	
	private int ratingLeftAtt;
	
	private int teamAttitude;
	
	private int ratingIndirectSetPiecesDef;
	
	private int ratingIndirectSetPiecesAtt;

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

	public int getTacticType() {
		return tacticType;
	}

	public void setTacticType(String tacticType) {
		this.tacticType = Utils.getIntFromString(tacticType);
	}

	public int getTacticSkill() {
		return tacticSkill;
	}

	public void setTacticSkill(String tacticSkill) {
		this.tacticSkill = Utils.getIntFromString(tacticSkill);
	}

	public int getRatingMidField() {
		return ratingMidField;
	}

	public void setRatingMidField(String ratingMidField) {
		if(ratingMidField != null)
			this.ratingMidField = Utils.getIntFromString(ratingMidField);
	}

	public int getRatingRightDef() {
		return ratingRightDef;
	}

	public void setRatingRightDef(String ratingRightDef) {
		if(ratingRightDef != null)
			this.ratingRightDef = Utils.getIntFromString(ratingRightDef);
	}

	public int getRatingMidDef() {
		return ratingMidDef;
	}

	public void setRatingMidDef(String ratingMidDef) {
		if(ratingMidDef != null)
			this.ratingMidDef = Utils.getIntFromString(ratingMidDef);
	}

	public int getRatingLeftDef() {
		return ratingLeftDef;
	}

	public void setRatingLeftDef(String ratingLeftDef) {
		if(ratingLeftDef != null)	
			this.ratingLeftDef = Utils.getIntFromString(ratingLeftDef);
	}

	public int getRatingRightAtt() {
		return ratingRightAtt;
	}

	public void setRatingRightAtt(String ratingRightAtt) {
		if(ratingRightAtt != null)
			this.ratingRightAtt = Utils.getIntFromString(ratingRightAtt);
	}

	public int getRatingMidAtt() {
		return ratingMidAtt;
	}

	public void setRatingMidAtt(String ratingMidAtt) {
		if(ratingMidAtt != null)
			this.ratingMidAtt = Utils.getIntFromString(ratingMidAtt);
	}

	public int getRatingLeftAtt() {
		return ratingLeftAtt;
	}

	public void setRatingLeftAtt(String ratingLeftAtt) {
		if(ratingLeftAtt != null)
			this.ratingLeftAtt = Utils.getIntFromString(ratingLeftAtt);
	}

	public int getTeamAttitude() {
		return teamAttitude;
	}

	public void setTeamAttitude(String teamAttitude) {
		if(teamAttitude != null)
			this.teamAttitude = Utils.getIntFromString(teamAttitude);
	}

	public int getRatingIndirectSetPiecesDef() {
		return ratingIndirectSetPiecesDef;
	}

	public void setRatingIndirectSetPiecesDef(String ratingIndirectSetPiecesDef) {
		if(ratingIndirectSetPiecesDef != null)
			this.ratingIndirectSetPiecesDef = Utils.getIntFromString(ratingIndirectSetPiecesDef);
	}

	public int getRatingIndirectSetPiecesAtt() {
		return ratingIndirectSetPiecesAtt;
	}

	public void setRatingIndirectSetPiecesAtt(String ratingIndirectSetPiecesAtt) {
		if(ratingIndirectSetPiecesAtt != null)
			this.ratingIndirectSetPiecesAtt = Utils.getIntFromString(ratingIndirectSetPiecesAtt);
	}
	
	
}
