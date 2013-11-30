package api.entity.datatype;

import api.entity.playerdetails.Player;


public class SkillLevel extends ScaledDataType{

	public SkillLevel(String value) {
		super(value);
	}

	@Override
	protected int getMinValue() {
		return 0;
	}

	@Override
	protected int getMaxValue() {
		return 20;
	}
	
	public double getValueForPrediction(Player player) {
		double value = this.getValue();
		if(value < 8)
			value += 0.5;
		if(player.isMotherClubBonus())
			value += 0.5;
		value += 1*(((double)player.getLoyalty().getValue())/20);
		return value;
	}
}
