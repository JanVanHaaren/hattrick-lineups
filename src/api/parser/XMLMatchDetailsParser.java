package api.parser;

import java.util.ArrayList;
import java.util.Collection;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.MatchDetails;
import api.entity.matchdetails.Arena;
import api.entity.matchdetails.Booking;
import api.entity.matchdetails.Goal;
import api.entity.matchdetails.Match;
import api.entity.matchdetails.Team;

public class XMLMatchDetailsParser extends XMLParser {
	
	protected XMLMatchDetailsParser() {
		// NOP
	}

	public static MatchDetails parseMatchDetailsFromString(String string) {
		return parseMatchDetails(XMLParser.parseString(string));
	}

	private static MatchDetails parseMatchDetails(Document document) {

		MatchDetails matchDetails = new MatchDetails();

		try {
			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			
			matchDetails.setUserID(getElementValue(rootElement, "UserID"));
			matchDetails.setFetchedDate(getElementValue(rootElement, "FetchedDate"));
			matchDetails.setYouth(getElementValue(rootElement, "IsYouth"));
			matchDetails.setUserIsSupporter(getElementValue(rootElement, "UserIsSupporter"));
			
			// <HattrickData/Match>
			Element matchElement = getChildElement(rootElement, "Match");
			
			Match match = new Match();
			match.setMatchID(getElementValue(matchElement, "MatchID"));
			match.setMatchType(getElementValue(matchElement, "MatchType"));
			match.setMatchDate(getElementValue(matchElement, "MatchDate"));
			match.setFinishedDate(getElementValue(matchElement, "FinishedDate"));
			match.setPossessionFirstHalfHome(getElementValue(matchElement, "PossessionFirstHalfHome"));
			match.setPossessionFirstHalfAway(getElementValue(matchElement, "PossessionFirstHalfAway"));
			match.setPossessionSecondHalfHome(getElementValue(matchElement, "PossessionSecondHalfHome"));
			match.setPossessionSecondHalfAway(getElementValue(matchElement, "PossessionSecondHalfAway"));
			matchDetails.setMatch(match);
			
			// <HattrickData/Match/HomeTeam>
			Element homeTeamElement = getChildElement(matchElement, "HomeTeam");
			
			Team homeTeam = new Team();
			homeTeam.setTeamID(getElementValue(homeTeamElement, "HomeTeamID"));
			homeTeam.setTeamName(getElementValue(homeTeamElement, "HomeTeamName"));
			homeTeam.setDressURI(getElementValue(homeTeamElement, "DressURI"));
			homeTeam.setFormation(getElementValue(homeTeamElement, "Formation"));
			homeTeam.setGoals(getElementValue(homeTeamElement, "HomeGoals"));
			homeTeam.setTacticType(getElementValue(homeTeamElement, "TacticType"));
			homeTeam.setTacticSkill(getElementValue(homeTeamElement, "TacticSkill"));
			homeTeam.setRatingMidField(getElementValue(homeTeamElement, "RatingMidField"));
			homeTeam.setRatingRightDef(getElementValue(homeTeamElement, "RatingRightDef"));
			homeTeam.setRatingMidDef(getElementValue(homeTeamElement, "RatingMidDef"));
			homeTeam.setRatingLeftDef(getElementValue(homeTeamElement, "RatingLeftDef"));
			homeTeam.setRatingRightAtt(getElementValue(homeTeamElement, "RatingRightAtt"));
			homeTeam.setRatingMidAtt(getElementValue(homeTeamElement, "RatingMidAtt"));
			homeTeam.setRatingLeftAtt(getElementValue(homeTeamElement, "RatingLeftAtt"));
			homeTeam.setTeamAttitude(getElementValue(homeTeamElement, "TeamAttitude"));
			homeTeam.setRatingIndirectSetPiecesDef(getElementValue(homeTeamElement, "RatingIndirectSetPiecesDef"));
			homeTeam.setRatingIndirectSetPiecesAtt(getElementValue(homeTeamElement, "RatingIndirectSetPiecesAtt"));
			match.setHomeTeam(homeTeam);		
						
			// <HattrickData/Match/AwayTeam>
			Element awayTeamElement = getChildElement(matchElement, "AwayTeam");
			
			Team awayTeam = new Team();
			awayTeam.setTeamID(getElementValue(awayTeamElement, "AwayTeamID"));
			awayTeam.setTeamName(getElementValue(awayTeamElement, "AwayTeamName"));
			awayTeam.setDressURI(getElementValue(awayTeamElement, "DressURI"));
			awayTeam.setFormation(getElementValue(awayTeamElement, "Formation"));
			awayTeam.setGoals(getElementValue(awayTeamElement, "AwayGoals"));
			awayTeam.setTacticType(getElementValue(awayTeamElement, "TacticType"));
			awayTeam.setTacticSkill(getElementValue(awayTeamElement, "TacticSkill"));
			awayTeam.setRatingMidField(getElementValue(awayTeamElement, "RatingMidField"));
			awayTeam.setRatingRightDef(getElementValue(awayTeamElement, "RatingRightDef"));
			awayTeam.setRatingMidDef(getElementValue(awayTeamElement, "RatingMidDef"));
			awayTeam.setRatingLeftDef(getElementValue(awayTeamElement, "RatingLeftDef"));
			awayTeam.setRatingRightAtt(getElementValue(awayTeamElement, "RatingRightAtt"));
			awayTeam.setRatingMidAtt(getElementValue(awayTeamElement, "RatingMidAtt"));
			awayTeam.setRatingLeftAtt(getElementValue(awayTeamElement, "RatingLeftAtt"));
			awayTeam.setTeamAttitude(getElementValue(awayTeamElement, "TeamAttitude"));
			awayTeam.setRatingIndirectSetPiecesDef(getElementValue(awayTeamElement, "RatingIndirectSetPiecesDef"));
			awayTeam.setRatingIndirectSetPiecesAtt(getElementValue(awayTeamElement, "RatingIndirectSetPiecesAtt"));
			match.setAwayTeam(awayTeam);
			
			// <HattrickData/Match/Arena>
			Element arenaElement = getChildElement(matchElement, "Arena");
			
			Arena arena = new Arena();
			arena.setArenaID(getElementValue(arenaElement, "ArenaID"));
			arena.setArenaName(getElementValue(arenaElement, "ArenaName"));
			arena.setWeatherID(getElementValue(arenaElement, "WeatherID"));
			arena.setSoldTotal(getElementValue(arenaElement, "SoldTotal"));
			arena.setSoldTerraces(getElementValue(arenaElement, "SoldTerraces"));
			arena.setSoldBasic(getElementValue(arenaElement, "SoldBasic"));
			arena.setSoldRoof(getElementValue(arenaElement, "SoldRoof"));
			arena.setSoldVIP(getElementValue(arenaElement, "SoldVIP"));
			match.setArena(arena);
			
			// <HattrickData/Match/Scorers>
			Element scorersElement = getChildElement(matchElement, "Scorers");
			Collection<Element> goalElements = getChildElementList(scorersElement, "Goal");
			
			Collection<Goal> scorers = new ArrayList<Goal>();
			for(Element goalElement : goalElements)
			{
				Goal goal = new Goal();
				goal.setScorerPlayerID(getElementValue(goalElement, "ScorerPlayerID"));
				goal.setScorerPlayerName(getElementValue(goalElement, "ScorerPlayerName"));
				goal.setScorerTeamID(getElementValue(goalElement, "ScorerTeamID"));
				goal.setScorerHomeGoals(getElementValue(goalElement, "ScorerHomeGoals"));
				goal.setScorerAwayGoals(getElementValue(goalElement, "ScorerAwayGoals"));
				goal.setScorerMinute(getElementValue(goalElement, "ScorerMinute"));
				scorers.add(goal);
			}
			match.setScorers(scorers);
			
			// <HattrickData/Match/Bookings>
			Element bookingsElement = getChildElement(matchElement, "Bookings");
			Collection<Element> bookingElements = getChildElementList(bookingsElement, "Booking");
			
			Collection<Booking> bookings = new ArrayList<Booking>();
			for(Element bookingElement : bookingElements)
			{
				Booking booking = new Booking();
				booking.setBookingPlayerID(getElementValue(bookingElement, "BookingPlayerID"));
				booking.setBookingPlayerName(getElementValue(bookingElement, "BookingPlayerName"));
				booking.setBookingTeamID(getElementValue(bookingElement, "BookingTeamID"));
				booking.setBookingType(getElementValue(bookingElement, "BookingType"));
				booking.setBookingMinute(getElementValue(bookingElement, "BookingMinute"));
				bookings.add(booking);
			}
			match.setBookings(bookings);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return matchDetails;

	}
}
