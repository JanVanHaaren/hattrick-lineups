package api.datatype;

import api.util.Utils;

public enum MatchTacticType {
	
	NORMAL(0),
	PRESSING(1),
	COUNTER_ATTACKS(2),
	ATTACK_IN_THE_MIDDLE(3),
	ATTACK_INWINGS(4),
	PLAY_CREATIVELY(7),
	LONG_SHOTS(8);
	
	private final int code;
	
	private MatchTacticType(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static MatchTacticType getMatchTacticType(String code)
	{
		for(MatchTacticType type : MatchTacticType.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for MatchTacticType");
	}

}
