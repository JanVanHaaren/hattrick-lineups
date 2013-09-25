package datatype;

import api.util.Utils;

public enum WeatherID {
	
	RAIN(0),
	OVERCAST(1),
	PARTIALLY_CLUDY(2),
	SUNNY(3);
	
	private final int code;
	
	private WeatherID(int code)
	{
		this.code = code;
	}
	
	public int getCode() {
		return code;
	}

	public static WeatherID getWeatherID(String code)
	{
		for(WeatherID type : WeatherID.values())
			if(type.code == Utils.getIntFromString(code))
				return type;
		throw new IllegalArgumentException("Invalide code for WeatherID");
	}

}
