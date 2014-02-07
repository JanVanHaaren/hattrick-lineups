package api.wekafilecreation;

public class LeagueDirectoryWrapper {

	private int leagueID;
	private String location;
	
	public LeagueDirectoryWrapper(int leagueID, String location) {
		this.leagueID = leagueID;
		this.location = location;
	}

	public int getLeagueID() {
		return leagueID;
	}
	
	public String getLocation() {
		return location;
	}
	
	
}
