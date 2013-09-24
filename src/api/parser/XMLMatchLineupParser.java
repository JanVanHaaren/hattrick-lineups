package api.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.MatchLineup;
import api.entity.matchlineup.Arena;
import api.entity.matchlineup.LineupPlayer;
import api.entity.matchlineup.Player;
import api.entity.matchlineup.Substitution;
import api.entity.matchlineup.Team;
import api.entity.matchlineup.TeamIdentifier;

public class XMLMatchLineupParser extends XMLParser {
	
	protected XMLMatchLineupParser() {
		// NOP
	}

	public static MatchLineup parseMatchLineupFromString(String string) {
		return parseMatchLineup(XMLParser.parseString(string));
	}

	private static MatchLineup parseMatchLineup(Document document) {

		MatchLineup matchLineup = new MatchLineup();

		try {

			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			matchLineup.setUserID(getElementValue(rootElement, "UserID"));
			matchLineup.setFetchedDate(getElementValue(rootElement, "FetchedDate"));
			matchLineup.setYouth(getElementValue(rootElement, "IsYouth"));
			matchLineup.setMatchID(getElementValue(rootElement, "MatchID"));
			matchLineup.setMatchType(getElementValue(rootElement, "MatchType"));
			
			// <HattrickData/HomeTeam>
			Element homeTeamElement = getChildElement(rootElement, "HomeTeam");
			
			TeamIdentifier homeTeam = new TeamIdentifier();
			homeTeam.setTeamID(getElementValue(homeTeamElement, "HomeTeamID"));
			homeTeam.setTeamName(getElementValue(homeTeamElement, "HomeTeamName"));
			matchLineup.setHomeTeam(homeTeam);
			
			// <HattrickData/AwayTeam>
			Element awayTeamElement = getChildElement(rootElement, "AwayTeam");
			
			TeamIdentifier awayTeam = new TeamIdentifier();
			awayTeam.setTeamID(getElementValue(awayTeamElement, "AwayTeamID"));
			awayTeam.setTeamName(getElementValue(awayTeamElement, "AwayTeamName"));
			matchLineup.setAwayTeam(awayTeam);

			// <HattrickData/Arena>
			Element arenaElement = getChildElement(rootElement, "Arena");
			
			Arena arena = new Arena();
			arena.setArenaID(getElementValue(arenaElement, "ArenaID"));
			arena.setArenaName(getElementValue(arenaElement, "ArenaName"));
			matchLineup.setArena(arena);
			
			// <HattrickData/Team>
			Element teamElement = getChildElement(rootElement, "Team");
			
			Team team = new Team();
			team.setTeamID(getElementValue(teamElement, "TeamID"));
			team.setTeamName(getElementValue(teamElement, "TeamName"));
			team.setExperienceLevel(getElementValue(teamElement, "ExperienceLevel"));
			matchLineup.setTeam(team);
			
			// <HattrickData/Team/StartingLineup>
			Element startingLineupElement = getChildElement(teamElement, "StartingLineup");
			Collection<Element> startingLineupPlayerElements = getChildElementList(startingLineupElement, "Player");
			
			Collection<Player> startingLineup = new ArrayList<Player>();
			for(Element playerElement : startingLineupPlayerElements)
			{
				Player player = new Player();
				player.setPlayerID(getElementValue(playerElement, "PlayerID"));
				player.setRoleID(getElementValue(playerElement, "RoleID"));
				player.setFirstName(getElementValue(playerElement, "FirstName"));
				player.setLastName(getElementValue(playerElement, "LastName"));
				player.setNickName(getElementValue(playerElement, "NickName"));
				player.setBehaviour(getElementValue(playerElement, "Behaviour"));
				startingLineup.add(player);
			}
			team.setStartingLineup(startingLineup);
			
			// <HattrickData/Team/Substitutions>
			Element substitutionsElement = getChildElement(teamElement, "Substitutions");
			Collection<Element> substitutionElements = getChildElementList(substitutionsElement, "Substitution");
			
			Collection<Substitution> substitutions = new ArrayList<Substitution>();
			for(Element substitutionElement : substitutionElements)
			{
				Substitution substitution = new Substitution();
				substitution.setTeamID(getElementValue(substitutionElement, "TeamID"));
				substitution.setSubjectPlayerID(getElementValue(substitutionElement, "SubjectPlayerID"));
				substitution.setObjectPlayerID(getElementValue(substitutionElement, "ObjectPlayerID"));
				substitution.setOrderType(getElementValue(substitutionElement, "OrderType"));
				substitution.setNewPositionID(getElementValue(substitutionElement, "NewPositionId"));
				substitution.setNewPositionBehaviour(getElementValue(substitutionElement, "NewPositionBehaviour"));
				substitution.setMatchMinute(getElementValue(substitutionElement, "MatchMinute"));
				substitutions.add(substitution);
			}
			team.setSubstitutions(substitutions);
			
			// <HattrickData/Team/Lineup>
			Element lineupElement = getChildElement(teamElement, "Lineup");
			Collection<Element> lineupPlayerElements = getChildElementList(lineupElement, "Player");
			
			Collection<LineupPlayer> lineup = new ArrayList<LineupPlayer>();
			for(Element playerElement : lineupPlayerElements)
			{
				LineupPlayer player = new LineupPlayer();
				player.setPlayerID(getElementValue(playerElement, "PlayerID"));
				player.setRoleID(getElementValue(playerElement, "RoleID"));
				player.setFirstName(getElementValue(playerElement, "FirstName"));
				player.setLastName(getElementValue(playerElement, "LastName"));
				player.setNickName(getElementValue(playerElement, "NickName"));
				player.setRatingStars(getElementValue(playerElement, "RatingStars"));
				player.setRatingStarsEndOfMatch(getElementValue(playerElement, "RatingStarsEndOfMatch"));
				player.setBehaviour(getElementValue(playerElement, "Behaviour"));
				lineup.add(player);
			}
			team.setLineup(lineup);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return matchLineup;

	}

}
