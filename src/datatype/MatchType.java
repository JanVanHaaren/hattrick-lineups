package datatype;

import api.util.Utils;

public enum MatchType {
	
	LEAGUE(1),
	QUALIFICATION(2),
	CUP(3),
	FRIENDLY_NORMAL_RULES(4),
	FRIENDLY_CUP_RULES(5),
	HATTRICK_MASTERS(7),
	INTERNATIONAL_FRIENDLY_NORMAL_RULES(8),
	INTERNATIONAL_FRIENDLY_CUP_RULES(9),
	NATIONAL_COMPETITION_NORMAL_RULES(10),
	NATIONAL_COMPETITION_CUP_RULES(11),
	NATIONAL_FRIENDLY(12),
	TOURNAMENT_LEAGUE(50),
	TOURNAMENT_PLAYOFF(51),
	YOUTH_LEAGUE(100),
	YOUTH_FRIENDLY(101),
	YOUTH_FRIENDLY_CUP_RULES(103),
	YOUTH_INTERNATIONAL_FRIENDLY(105),
	YOUTH_INTERNATIONAL_FRIENDLY_CUP_RULES(106);
	
	private final int code;
	
	private MatchType(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static MatchType getMatchType(String code)
	{
		for(MatchType type : MatchType.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for MatchType");
	}
}
