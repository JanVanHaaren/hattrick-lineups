package datatype;

import api.util.Utils;

public enum InjuryType {
	
	BRUISE(1),
	INJURY(2);
	
	private final int code;
	
	private InjuryType(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static InjuryType getInjuryType(String code)
	{
		for(InjuryType type : InjuryType.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for InjuryType");
	}

}
