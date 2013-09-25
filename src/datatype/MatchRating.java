package datatype;


public class MatchRating extends ScaledDataType{

	public MatchRating(String value) {
		super(value);
	}

	@Override
	protected int getMinValue() {
		return 1;
	}

	@Override
	protected int getMaxValue() {
		return 80;
	}
}
