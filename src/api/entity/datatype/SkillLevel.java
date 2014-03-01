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
		value -= 0.5; // -1 in HO, +0.5 to account for unknown subskill
						//TODO: checken welk het beste werkt? maar hoe!
		if(player.hasMotherClubBonus())
			value += 0.5;
		value += 1*(((double)player.getLoyalty().getValue())/20);
		return Math.max(0, value);
	}
}
