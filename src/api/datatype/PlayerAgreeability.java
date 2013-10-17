package api.datatype;

public class PlayerAgreeability extends ScaledDataType {

	public PlayerAgreeability(String value) {
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
