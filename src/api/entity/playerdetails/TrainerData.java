package api.entity.playerdetails;

import api.datatype.SkillLevel;
import api.datatype.TrainerType;

public class TrainerData {
	
	private TrainerType trainerType;
	
	private SkillLevel trainerSkill;

	public TrainerType getTrainerType() {
		return trainerType;
	}

	public void setTrainerType(String trainerType) {
		if(trainerType != null)
			this.trainerType = TrainerType.getTrainerType(trainerType);
	}

	public SkillLevel getTrainerSkill() {
		return trainerSkill;
	}

	public void setTrainerSkill(String trainerSkill) {
		if(trainerSkill != null)
			this.trainerSkill = new SkillLevel(trainerSkill);
	}
	
	
}
