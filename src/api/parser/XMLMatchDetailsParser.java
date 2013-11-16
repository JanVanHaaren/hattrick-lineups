package api.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.MatchDetails;
import api.entity.matchdetails.Match;
import api.entity.matchdetails.Team;
import api.exception.DiscardException;
import api.exception.IllegalXMLException;



public class XMLMatchDetailsParser extends XMLParser {
	
	public XMLMatchDetailsParser() {
		// NOP
	}
	
	public static MatchDetails parseMatchDetailsFromString(String string) throws IllegalXMLException, DiscardException {
		return parseMatchDetails(XMLParser.parseString(string));
	}

	private static MatchDetails parseMatchDetails(Document document) throws DiscardException {

		MatchDetails matchDetails = new MatchDetails();

		try {
			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			
//			matchDetails.setUserID(getElementValue(rootElement, "UserID"));
//			matchDetails.setFetchedDate(getElementValue(rootElement, "FetchedDate"));
//			matchDetails.setSourceSystem(getElementValue(rootElement, "SourceSystem"));
//			matchDetails.setUserSupporterTier(getElementValue(rootElement, "UserSupporterTier"));
			
			// <HattrickData/Match>
			Element matchElement = getChildElement(rootElement, "Match");
			
			Match match = new Match();
			match.setMatchID(getElementValue(matchElement, "MatchID"));
			match.setMatchType(getElementValue(matchElement, "MatchType"));
			match.setMatchDate(getElementValue(matchElement, "MatchDate"));
			match.setFinishedDate(getElementValue(matchElement, "FinishedDate"));
//			match.setPossessionFirstHalfHome(getElementValue(matchElement, "PossessionFirstHalfHome"));
//			match.setPossessionFirstHalfAway(getElementValue(matchElement, "PossessionFirstHalfAway"));
//			match.setPossessionSecondHalfHome(getElementValue(matchElement, "PossessionSecondHalfHome"));
//			match.setPossessionSecondHalfAway(getElementValue(matchElement, "PossessionSecondHalfAway"));
			matchDetails.setMatch(match);
			
			// <HattrickData/Match/HomeTeam>
			Element homeTeamElement = getChildElement(matchElement, "HomeTeam");
			
			Team homeTeam = new Team();
			homeTeam.setTeamID(getElementValue(homeTeamElement, "HomeTeamID"));
			homeTeam.setTeamName(getElementValue(homeTeamElement, "HomeTeamName"));
//			homeTeam.setDressURI(getElementValue(homeTeamElement, "DressURI"));
			homeTeam.setFormation(getElementValue(homeTeamElement, "Formation"));
			homeTeam.setGoals(getElementValue(homeTeamElement, "HomeGoals"));
			homeTeam.setTacticType(getElementValue(homeTeamElement, "TacticType"));
			homeTeam.setTacticSkill(getElementValue(homeTeamElement, "TacticSkill"));
			homeTeam.setRatingMidField(getElementValue(homeTeamElement, "RatingMidfield"));
			homeTeam.setRatingRightDef(getElementValue(homeTeamElement, "RatingRightDef"));
			homeTeam.setRatingMidDef(getElementValue(homeTeamElement, "RatingMidDef"));
			homeTeam.setRatingLeftDef(getElementValue(homeTeamElement, "RatingLeftDef"));
			homeTeam.setRatingRightAtt(getElementValue(homeTeamElement, "RatingRightAtt"));
			homeTeam.setRatingMidAtt(getElementValue(homeTeamElement, "RatingMidAtt"));
			homeTeam.setRatingLeftAtt(getElementValue(homeTeamElement, "RatingLeftAtt"));
//			homeTeam.setTeamAttitude(getElementValue(homeTeamElement, "TeamAttitude"));
//			homeTeam.setRatingIndirectSetPiecesDef(getElementValue(homeTeamElement, "RatingIndirectSetPiecesDef"));
//			homeTeam.setRatingIndirectSetPiecesAtt(getElementValue(homeTeamElement, "RatingIndirectSetPiecesAtt"));
			match.setHomeTeam(homeTeam);		
						
			// <HattrickData/Match/AwayTeam>
			Element awayTeamElement = getChildElement(matchElement, "AwayTeam");
			
			Team awayTeam = new Team();
			awayTeam.setTeamID(getElementValue(awayTeamElement, "AwayTeamID"));
			awayTeam.setTeamName(getElementValue(awayTeamElement, "AwayTeamName"));
//			awayTeam.setDressURI(getElementValue(awayTeamElement, "DressURI"));
			String formation = getElementValue(awayTeamElement, "Formation");
			awayTeam.setFormation(formation);
			awayTeam.setGoals(getElementValue(awayTeamElement, "AwayGoals"));
			awayTeam.setTacticType(getElementValue(awayTeamElement, "TacticType"));
			awayTeam.setTacticSkill(getElementValue(awayTeamElement, "TacticSkill"));
			awayTeam.setRatingMidField(getElementValue(awayTeamElement, "RatingMidfield"));
			awayTeam.setRatingRightDef(getElementValue(awayTeamElement, "RatingRightDef"));
			awayTeam.setRatingMidDef(getElementValue(awayTeamElement, "RatingMidDef"));
			awayTeam.setRatingLeftDef(getElementValue(awayTeamElement, "RatingLeftDef"));
			awayTeam.setRatingRightAtt(getElementValue(awayTeamElement, "RatingRightAtt"));
			awayTeam.setRatingMidAtt(getElementValue(awayTeamElement, "RatingMidAtt"));
			awayTeam.setRatingLeftAtt(getElementValue(awayTeamElement, "RatingLeftAtt"));
//			awayTeam.setTeamAttitude(getElementValue(awayTeamElement, "TeamAttitude"));
//			awayTeam.setRatingIndirectSetPiecesDef(getElementValue(awayTeamElement, "RatingIndirectSetPiecesDef"));
//			awayTeam.setRatingIndirectSetPiecesAtt(getElementValue(awayTeamElement, "RatingIndirectSetPiecesAtt"));
			match.setAwayTeam(awayTeam);
			
			// <HattrickData/Match/Arena>
//			Element arenaElement = getChildElement(matchElement, "Arena");
//			
//			Arena arena = new Arena();
//			arena.setArenaID(getElementValue(arenaElement, "ArenaID"));
//			arena.setArenaName(getElementValue(arenaElement, "ArenaName"));
//			arena.setWeatherID(getElementValue(arenaElement, "WeatherID"));
//			arena.setSoldTotal(getElementValue(arenaElement, "SoldTotal"));
//			arena.setSoldTerraces(getElementValue(arenaElement, "SoldTerraces"));
//			arena.setSoldBasic(getElementValue(arenaElement, "SoldBasic"));
//			arena.setSoldRoof(getElementValue(arenaElement, "SoldRoof"));
//			arena.setSoldVIP(getElementValue(arenaElement, "SoldVIP"));
//			match.setArena(arena);
			
			// <HattrickData/Match/MatchOfficials>
//			Element matchOfficialsElement = getChildElement(matchElement, "MatchOfficials");
//			
//			MatchOfficials matchOfficials = new MatchOfficials();
//			match.setMatchOfficials(matchOfficials);
			
			// <HattrickData/Match/MatchOfficials/Referee>
//			Element refereeElement = getChildElement(matchOfficialsElement, "Referee");
//			
//			Referee referee = new Referee();
//			referee.setRefereeId(getElementValue(refereeElement, "RefereeId"));
//			referee.setRefereeName(getElementValue(refereeElement, "RefereeName"));
//			referee.setRefereeCountryId(getElementValue(refereeElement, "RefereeCountryId"));
//			referee.setRefereeCountryName(getElementValue(refereeElement, "RefereeCountryName"));
//			referee.setRefereeTeamId(getElementValue(refereeElement, "RefereeTeamId"));
//			referee.setRefereeTeamName(getElementValue(refereeElement, "RefereeTeamName"));
//			matchOfficials.setReferee(referee);
			
			// <HattrickData/Match/MatchOfficials/RefereeAssistant1>
//			Element refereeAssistant1Element = getChildElement(matchOfficialsElement, "RefereeAssistant1");
//			
//			Referee refereeAssistant1 = new Referee();
//			refereeAssistant1.setRefereeId(getElementValue(refereeAssistant1Element, "RefereeId"));
//			refereeAssistant1.setRefereeName(getElementValue(refereeAssistant1Element, "RefereeName"));
//			refereeAssistant1.setRefereeCountryId(getElementValue(refereeAssistant1Element, "RefereeCountryId"));
//			refereeAssistant1.setRefereeCountryName(getElementValue(refereeAssistant1Element, "RefereeCountryName"));
//			refereeAssistant1.setRefereeTeamId(getElementValue(refereeAssistant1Element, "RefereeTeamId"));
//			refereeAssistant1.setRefereeTeamName(getElementValue(refereeAssistant1Element, "RefereeTeamName"));
//			matchOfficials.setRefereeAssistant1(refereeAssistant1);
			
			// <HattrickData/Match/MatchOfficials/RefereeAssistant2>
//			Element refereeAssistant2Element = getChildElement(matchOfficialsElement, "RefereeAssistant2");
//			
//			Referee refereeAssistant2 = new Referee();
//			refereeAssistant2.setRefereeId(getElementValue(refereeAssistant2Element, "RefereeId"));
//			refereeAssistant2.setRefereeName(getElementValue(refereeAssistant2Element, "RefereeName"));
//			refereeAssistant2.setRefereeCountryId(getElementValue(refereeAssistant2Element, "RefereeCountryId"));
//			refereeAssistant2.setRefereeCountryName(getElementValue(refereeAssistant2Element, "RefereeCountryName"));
//			refereeAssistant2.setRefereeTeamId(getElementValue(refereeAssistant2Element, "RefereeTeamId"));
//			refereeAssistant2.setRefereeTeamName(getElementValue(refereeAssistant2Element, "RefereeTeamname"));
//			matchOfficials.setRefereeAssistant2(refereeAssistant2);
			
			
			// <HattrickData/Match/Scorers>
//			Element scorersElement = getChildElement(matchElement, "Scorers");
//			Collection<Element> goalElements = getChildElementList(scorersElement, "Goal");
//			
//			Collection<Goal> scorers = new ArrayList<Goal>();
//			for(Element goalElement : goalElements)
//			{
//				Goal goal = new Goal();
//				goal.setScorerPlayerID(getElementValue(goalElement, "ScorerPlayerID"));
//				goal.setScorerPlayerName(getElementValue(goalElement, "ScorerPlayerName"));
//				goal.setScorerTeamID(getElementValue(goalElement, "ScorerTeamID"));
//				goal.setScorerHomeGoals(getElementValue(goalElement, "ScorerHomeGoals"));
//				goal.setScorerAwayGoals(getElementValue(goalElement, "ScorerAwayGoals"));
//				goal.setScorerMinute(getElementValue(goalElement, "ScorerMinute"));
//				scorers.add(goal);
//			}
//			match.setScorers(scorers);
			
			// <HattrickData/Match/Bookings>
//			Element bookingsElement = getChildElement(matchElement, "Bookings");
//			Collection<Element> bookingElements = getChildElementList(bookingsElement, "Booking");
//			
//			Collection<Booking> bookings = new ArrayList<Booking>();
//			for(Element bookingElement : bookingElements)
//			{
//				Booking booking = new Booking();
//				booking.setBookingPlayerID(getElementValue(bookingElement, "BookingPlayerID"));
//				booking.setBookingPlayerName(getElementValue(bookingElement, "BookingPlayerName"));
//				booking.setBookingTeamID(getElementValue(bookingElement, "BookingTeamID"));
//				booking.setBookingType(getElementValue(bookingElement, "BookingType"));
//				booking.setBookingMinute(getElementValue(bookingElement, "BookingMinute"));
//				bookings.add(booking);
//			}
//			match.setBookings(bookings);
			
			// <HattrickData/Match/Injuries>
//			Element injuriesElement = getChildElement(matchElement, "Injuries");
//			Collection<Element> injuryElements = getChildElementList(injuriesElement, "Injury");
//			
//			Collection<Injury> injuries = new ArrayList<Injury>();
//			for(Element injuryElement : injuryElements)
//			{
//				Injury injury = new Injury();
//				injury.setInjuryPlayerId(getElementValue(injuryElement, "InjuryPlayerID"));
//				injury.setInjuryPlayerName(getElementValue(injuryElement, "InjuryPlayerName"));
//				injury.setInjuryTeamId(getElementValue(injuryElement, "InjuryTeamID"));
//				injury.setInjuryType(getElementValue(injuryElement, "InjuryType"));
//				injury.setInjuryMinute(getElementValue(injuryElement, "InjuryMinute"));
//				injuries.add(injury);
//			}
//			match.setInjuries(injuries);
						
			// <HattrickData/Match/EventList>
//			Element eventListElement = getChildElement(matchElement, "EventList");
//			Collection<Element> eventElements = getChildElementList(eventListElement, "Event");
//			
//			Collection<Event> eventList = new ArrayList<Event>();
//			for(Element eventElement : eventElements)
//			{
//				Event event = new Event();
//				event.setMinute(getElementValue(eventElement, "Minute"));
//				event.setEventTypeID(getElementValue(eventElement, "EventTypeID"));
//				event.setEventVariation(getElementValue(eventElement, "EventVariation"));
//				event.setEventText(getElementValue(eventElement, "EventText"));
//				event.setSubjectTeamID(getElementValue(eventElement, "SubjectTeamID"));
//				event.setSubjectPlayerID(getElementValue(eventElement, "SubjectPlayerID"));
//				event.setObjectPlayerID(getElementValue(eventElement, "ObjectPlayerID"));
//				eventList.add(event);
//			}
//			match.setEventList(eventList);
			
		}
		catch (DiscardException e) {
			throw e;
		}
		
		return matchDetails;

	}
}