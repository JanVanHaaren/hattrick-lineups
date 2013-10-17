package api.datatype;


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
}
