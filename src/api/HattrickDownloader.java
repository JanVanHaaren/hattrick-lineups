package api;

import java.io.IOException;

/**
 * @author	Jan Van Haaren
 * @date	27 June 2013
 */
public class HattrickDownloader {

	private final static String ARENA_DETAILS_VERSION = "1.5";
	
	private final static String PLAYERS_VERSION = "2.3";

	private final static String PLAYER_DETAILS_VERSION = "2.4";

	private final static String MATCH_LINEUP_VERSION = "1.8";
	
	private final static String MATCH_DETAILS_VERSION = "2.5";
	
	private final static String LEAGUE_FIXTURES_VERSION = "1.2";
	
	private final static String WORLD_DETAILS_VERSION = "1.5";

	String getArenaDetailsString(int arenaId) throws IOException {
		// file and version
		String sURL = "?file=arenadetails&version=" + ARENA_DETAILS_VERSION;

		// parameters
		if (arenaId > 0) {
			sURL += "&arenaID=" + arenaId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}

	String getPlayersString(int teamId) throws IOException {
		// file and version
		String sURL = "?file=players&version=" + PLAYERS_VERSION;

		// parameters
		if (teamId > 0) {
			sURL += "&teamID=" + teamId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}

	String getPlayerDetailsString(int playerId) throws IOException {
		// file and version
		String sURL = "?file=playerdetails&version=" + PLAYER_DETAILS_VERSION;

		// parameters
		if (playerId > 0) {
			sURL += "&playerID=" + playerId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	String getMatchLineupString(int matchId, int teamId) {
		// file and version
		String sURL = "?file=matchlineup&version=" + MATCH_LINEUP_VERSION;

		// parameters
		if (matchId > 0) {
			sURL += "&matchID=" + matchId;
		}
		
		if (teamId > 0) {
			sURL += "&teamID=" + teamId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	String getMatchDetailsString(int matchId) {
		// file and version
		String sURL = "?file=matchdetails&version=" + MATCH_DETAILS_VERSION + "&matchEvents=true";

		// parameters
		if (matchId > 0) {
			sURL += "&matchID=" + matchId;
		}
		
		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	String getLeagueFixturesString(int leagueLevelUnitID) {
		// file and version
		String sURL = "?file=leaguefixtures&version=" + LEAGUE_FIXTURES_VERSION;
		
		// parameters
		if (leagueLevelUnitID > 0) {
			sURL += "&leagueLevelUnitID=" + leagueLevelUnitID;
		}
		
		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	String getWorldDetailsString() {
		// file and version
		String sURL = "?file=worlddetails&version=" + WORLD_DETAILS_VERSION + "&includeRegions=false";
		
		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	String getMatchesArchiveString(int teamID) {
		// file and version
				String sURL = "?file=matchesarchive&version=" + LEAGUE_FIXTURES_VERSION;
				
				// parameters
				if (teamID > 0) {
					sURL += "&teamID=" + teamID;
				}
				
				// retrieve content
				return this.getConnector().getWebContent(sURL);
	}
	
	private HattrickConnector getConnector() {
		return HattrickConnector.getInstance();
	}

}
