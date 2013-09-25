package datatype;

import api.util.Utils;

public enum SpecialtyID {
	
	NO_SPECIALTY(0),
	TECHNICAL(1),
	QUICK(2),
	POWERFUL(3),
	UNPERDICTABLE(4),
	HEAD_SPECIALIST(5),
	REGAINER(6);
	
	private final int code;
	
	private SpecialtyID(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static SpecialtyID getSpecialtyID(String code)
	{
		for(SpecialtyID type : SpecialtyID.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for SpecialtyID");
	}
}
