package api.datatype;

import api.util.Utils;

public enum TrainerType {
	
	DEFENSIVE(0),
	OFFENSIVE(1),
	BALANCED(2);
	
	private final int code;
	
	private TrainerType(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static TrainerType getTrainerType(String code)
	{
		for(TrainerType type : TrainerType.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for TrainerType");
	}
}
