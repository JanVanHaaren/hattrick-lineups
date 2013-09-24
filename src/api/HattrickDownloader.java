package api;

import java.io.IOException;

import api.entity.ArenaDetails;
import api.entity.MatchDetails;
import api.entity.MatchLineup;
import api.parser.XMLArenaDetailsParser;
import api.parser.XMLMatchDetailsParser;
import api.parser.XMLMatchLineupParser;

/**
 * @author	Jan Van Haaren
 * @date	27 June 2013
 */
public class HattrickDownloader {

	private final static String ARENA_DETAILS_VERSION = "1.5";
	
	private final static String PLAYERS_VERSION = "2.3";

	private final static String PLAYER_DETAILS_VERSION = "2.4";

	private final static String MATCH_LINEUP_VERSION = "1.8";
	
	private final static String MATCH_DETAILS_VERSION = "2.0";

	public static void main(String[] arguments) throws IOException {
		HattrickDownloader downloader = new HattrickDownloader();

		// String arena = downloader.getArena(511721);
		ArenaDetails arenaDetails = downloader.getArenaDetails(2009310);
		
		System.out.println(arenaDetails.getArena().getCurrentCapacity().getTotal());

		MatchLineup matchLineup = downloader.getMatchLineup(9213693, 3000);
		MatchLineup matchLineup2 = downloader.getMatchLineup(9213003, 73803);
		
		System.out.println(matchLineup.getTeam().getLineup().size());
		System.out.println(matchLineup2.getTeam().getSubstitutions());
		
		MatchDetails matchDetails = downloader.getMatchDetails(237985);
		
	}

	public ArenaDetails getArenaDetails(int arenaId) throws IOException {
		return XMLArenaDetailsParser.parseArenaDetailsFromString(this.getArenaDetailsString(arenaId));
	}
	
	public MatchLineup getMatchLineup(int matchId, int teamId) throws IOException {
		return XMLMatchLineupParser.parseMatchLineupFromString(this.getMatchLineupString(matchId, teamId));
	}
	
	public MatchDetails getMatchDetails(int matchId)throws IOException {
		return XMLMatchDetailsParser.parseMatchDetailsFromString(this.getMatchDetailsString(matchId));
	}
	
	private String getArenaDetailsString(int arenaId) throws IOException {

		// file and version
		String sURL = "?file=arenadetails&version=" + ARENA_DETAILS_VERSION;

		// parameters
		if (arenaId > 0) {
			sURL += "&arenaID=" + arenaId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}

	public String getPlayersString(int teamId) throws IOException {

		// file and version
		String sURL = "?file=players&version=" + PLAYERS_VERSION;

		// parameters
		if (teamId > 0) {
			sURL += "&teamID=" + teamId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}

	public String getPlayerDetailsString(int playerId) throws IOException {

		// file and version
		String sURL = "?file=playerdetails&version=" + PLAYER_DETAILS_VERSION;

		// parameters
		if (playerId > 0) {
			sURL += "&playerID=" + playerId;
		}

		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}
	
	private String getMatchLineupString(int matchId, int teamId) {
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
	
	private String getMatchDetailsString(int matchId)
	{
		// file and version
		String sURL = "?file=matchdetails&version=" + MATCH_DETAILS_VERSION;

		// parameters
		if (matchId > 0) {
			sURL += "&matchID=" + matchId;
		}
		
		// retrieve content
		return this.getConnector().getWebContent(sURL);
	}

	private HattrickConnector getConnector() {
		return HattrickConnector.getInstance();
	}

}
