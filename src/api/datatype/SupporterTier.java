package api.datatype;


public enum SupporterTier {
	
	SILVER("silver"),
	GOLD("gold"),
	PLATINUM("platinum");
	
	private final String code;
	
	private SupporterTier(String code)
	{
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public static SupporterTier getSupporterTier(String code)
	{
		for(SupporterTier tier : SupporterTier.values())
			if(tier.code.equalsIgnoreCase(code))
				return tier;
		throw new IllegalArgumentException("Invalide code for SupporterTier");
	}

}
