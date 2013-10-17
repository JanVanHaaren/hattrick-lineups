package api.datatype;

public class PlayerAggressiveness extends ScaledDataType {

	public PlayerAggressiveness(String value) {
		super(value);
	}

	@Override
	protected int getMinValue() {
		return 0;
	}

	@Override
	protected int getMaxValue() {
		return 5;
	}

}
