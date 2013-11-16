package api.entity.datatype;

import api.util.Utils;

public abstract class ScaledDataType implements Comparable<ScaledDataType>{
	
	protected abstract int getMinValue();
	protected abstract int getMaxValue();

	private int value;
	
	public ScaledDataType(String value) {
		setValue(value);
	}

	public int getValue() {
		return value;
	}

	private void setValue(String value) {
		int intValue = Utils.getIntFromString(value);
		if(intValue < getMinValue() || intValue > getMaxValue())
			throw new IllegalArgumentException("Invalid value  " + value + " for ScaledDataType " + this.getClass().getCanonicalName());
		this.value = intValue;
	}

	@Override
	public int compareTo(ScaledDataType other) {
		if(!this.getClass().equals(other.getClass()))
			throw new IllegalArgumentException("ScaledDataType objects not comparable");
		return this.value - other.value;
	}

}
