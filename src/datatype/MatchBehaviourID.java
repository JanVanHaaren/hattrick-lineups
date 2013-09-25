package datatype;

import api.util.Utils;

public enum MatchBehaviourID {

	NO_CHANGE(-1),
	NORMAL(0),
	OFFENSIVE(1),
	DEFENSIVE(2),
	TOWARDS_MIDDLE(3),
	TOWARDS_WING(4),
	EXTRA_FORWARD(5),
	EXTRA_INNER_MIDFIELD(6),
	EXTRA_DEFENDER(7);
	
	private final int code;
	
	private MatchBehaviourID(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static MatchBehaviourID getMatchBehaviourID(String code)
	{
		for(MatchBehaviourID type : MatchBehaviourID.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for MatchBehaviourID");
	}
}
