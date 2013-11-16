package api.entity.playerdetails;

import api.entity.datatype.SkillLevel;

public class PlayerSkills {
	
	private SkillLevel staminaSkill;

	private SkillLevel keeperSkill;
	
	private SkillLevel playmakerSkill;
	
	private SkillLevel scorerSkill;
	
	private SkillLevel passingSkill;
	
	private SkillLevel wingerSkill;

	private SkillLevel defenderSkill;
	
	private SkillLevel setPiecesSkill;

	public SkillLevel getStaminaSkill() {
		return staminaSkill;
	}

	public void setStaminaSkill(String staminaSkill) {
		if(staminaSkill != null);
			this.staminaSkill = new SkillLevel(staminaSkill);
	}

	public SkillLevel getKeeperSkill() {
		return keeperSkill;
	}

	public void setKeeperSkill(String keeperSkill) {
		if(keeperSkill != null)
			this.keeperSkill = new SkillLevel(keeperSkill);
	}

	public SkillLevel getPlaymakerSkill() {
		return playmakerSkill;
	}

	public void setPlaymakerSkill(String playmakerSkill) {
		if(playmakerSkill != null)
			this.playmakerSkill = new SkillLevel(playmakerSkill);
	}

	public SkillLevel getScorerSkill() {
		return scorerSkill;
	}

	public void setScorerSkill(String scorerSkill) {
		if(scorerSkill != null)
			this.scorerSkill = new SkillLevel(scorerSkill);
	}

	public SkillLevel getPassingSkill() {
		return passingSkill;
	}

	public void setPassingSkill(String passingSkill) {
		if(passingSkill != null)
			this.passingSkill = new SkillLevel(passingSkill);
	}

	public SkillLevel getWingerSkill() {
		return wingerSkill;
	}

	public void setWingerSkill(String wingerSkill) {
		if(wingerSkill != null)
			this.wingerSkill = new SkillLevel(wingerSkill);
	}

	public SkillLevel getDefenderSkill() {
		return defenderSkill;
	}

	public void setDefenderSkill(String defenderSkill) {
		if(defenderSkill != null)
			this.defenderSkill = new SkillLevel(defenderSkill);
	}

	public SkillLevel getSetPiecesSkill() {
		return setPiecesSkill;
	}

	public void setSetPiecesSkill(String setPiecesSkill) {
		if(setPiecesSkill != null)
			this.setPiecesSkill = new SkillLevel(setPiecesSkill);
	}
	
	
}
