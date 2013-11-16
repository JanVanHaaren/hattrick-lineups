package api.entity.datatype;

public class PlayerHonesty extends ScaledDataType {

	public PlayerHonesty(String value) {
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
