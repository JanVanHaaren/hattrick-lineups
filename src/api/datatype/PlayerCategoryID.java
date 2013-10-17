package api.datatype;

import api.util.Utils;

public enum PlayerCategoryID {
	
	KEEPER(1),
	WING_BACK(2),
	CENTRAL_DEFENDER(3),
	WINGER(4),
	INNER_MIDFIELD(5),
	FORWARD(6),
	SUBSTITUTE(7),
	RESERVE(8),
	EXTRA_1(9),
	EXTRA_2(10),
	NO_CATEGORY_SET(0);
	
	private final int code;
	
	private PlayerCategoryID(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static PlayerCategoryID getPlayerCategoryID(String code)
	{
		for(PlayerCategoryID categoryID : PlayerCategoryID.values())
			if(categoryID.code == Utils.getIntFromString(code))
				return categoryID;
		throw new IllegalArgumentException("Invalide code for PlayerCategoryID");
	}

}
