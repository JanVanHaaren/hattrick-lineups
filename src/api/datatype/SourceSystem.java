package api.datatype;

public enum SourceSystem {
	
	HATTRICK_MAIN_SYSTEM("hattrick"),
	YOUTH_SYSTEM("youth"),
	HATTRICK_INTEGRATED_USED_FOR_EX_TOURNAMENTS("htointegrated");
	
	private final String code;
	
	private SourceSystem(String code)
	{
		this.code = code;
	}
	
	public String getCode() {
		return code;
	}

	public static SourceSystem getSourceSystem(String code)
	{
		for(SourceSystem system : SourceSystem.values())
			if(system.code.equalsIgnoreCase(code))
				return system;
		throw new IllegalArgumentException("Invalide code for SourceSystem");
	}

}
