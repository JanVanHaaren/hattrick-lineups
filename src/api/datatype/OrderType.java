package api.datatype;

import api.util.Utils;

public enum OrderType {
	
	NORMAL_SUBSTITUTION_OR_BEHAVIOUR_CHANGE(1),
	PLAYER_SWAP(3);
	
	private final int code;
	
	private OrderType(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static OrderType getOrderType(String code)
	{
		for(OrderType type : OrderType.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for OrderType");
	}

}
