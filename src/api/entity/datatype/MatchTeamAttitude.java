package api.entity.datatype;

import api.util.Utils;

public enum MatchTeamAttitude {

	PLAY_IT_COOL(-1),
	NORMAL(0),
	MATCH_OF_THE_SEASON(1);
	
	private final int code;
	
	private MatchTeamAttitude(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static MatchTeamAttitude getMatchTeamAttitude(String code)
	{
		for(MatchTeamAttitude attitude : MatchTeamAttitude.values())
			if(attitude.code == Utils.getIntFromString(code))
				return attitude;
		throw new IllegalArgumentException("Invalide code for MatchTeamAttitude");
	}
}
