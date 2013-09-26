package datatype;

import api.util.Utils;

public enum MatchRoleID {
	
	KEEPER(100),
	RIGHT_BACK(101),
	RIGHT_CENTRAL_DEFENDER(102),
	MIDDLE_CENTRAL_DEFENDER(103),
	LEFT_CENTRAL_DEFENDER(104),
	LEFT_BACK(105),
	RIGHT_WINGER(106),
	RIGHT_INNER_MIDFIELD(107),
	MIDDLE_INNER_MIDFIELD(108),
	LEFT_INNER_MIDFIELD(109),
	LEFT_WINGER(110),
	RIGHT_FORWARD(111),
	MIDDLE_FORWARD(112),
	LEFT_FORWARD(113),
	SUBSTITUTION_KEEPER(114),
	SUBSTITUTION_DEFENDER(115),
	INNER_MIDFIELD(116),
	SUBSTITUTION_WINGER(117),
	SUBSTITUTION_FORWARD(118),
	SET_PIECES(17),
	CAPTAIN(18),
	REPLACED_PLAYER_1(19),
	REPLACED_PLAYER_2(20),
	REPLACED_PLAYER_3(21),
	PENALTY_TAKER_1(22),
	PENALTY_TAKER_2(23),
	PENALTY_TAKER_3(24),
	PENALTY_TAKER_4(25),
	PENALTY_TAKER_5(26),
	PENALTY_TAKER_6(27),
	PENALTY_TAKER_7(28),
	PENALTY_TAKER_8(29),
	PENALTY_TAKER_9(30),
	PENALTY_TAKER_10(31),
	PENALTY_TAKER_11(32);
	
	
	private final int code;
	
	private MatchRoleID(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static MatchRoleID getMatchRoleID(String code)
	{
		for(MatchRoleID roleID : MatchRoleID.values())
			if(roleID.code == Utils.getIntFromString(code))
				return roleID;
		throw new IllegalArgumentException("Invalide code for MatchRoleID");
	}

}
