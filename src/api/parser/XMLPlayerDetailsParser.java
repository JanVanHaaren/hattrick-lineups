package api.parser;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import api.entity.PlayerDetails;
import api.entity.playerdetails.Player;
import api.entity.playerdetails.PlayerSkills;
import api.exception.IllegalXMLException;

public class XMLPlayerDetailsParser extends XMLParser {
	public XMLPlayerDetailsParser() {
		// NOP
	}
	
	public static PlayerDetails parsePlayerDetailsFromString(String string) throws IllegalXMLException {
		return parsePlayerDetails(XMLParser.parseString(string));
	}

	private static PlayerDetails parsePlayerDetails(Document document) {

		PlayerDetails playerDetails = new PlayerDetails();

		try {
			// <HattrickData>
			Element rootElement = document.getDocumentElement();
			
//			playerDetails.setUserID(getElementValue(rootElement, "UserID"));
//			playerDetails.setFetchedDate(getElementValue(rootElement, "FetchedDate"));
//			playerDetails.setUserSupporterTier(getElementValue(rootElement, "UserSupporterTier"));
			
			// <HattrickDate/Player>
			Element playerElement = getChildElement(rootElement, "Player");
			
			Player player = new Player();
			player.setPlayerID(getElementValue(playerElement, "PlayerID"));
//			player.setFirstName(getElementValue(playerElement, "FirstName"));
//			player.setNickName(getElementValue(playerElement, "NickName"));
//			player.setLastName(getElementValue(playerElement, "LastName"));
//			player.setPlayerNumber(getElementValue(playerElement, "PlayerNumber"));
//			player.setPlayerCategoryID(getElementValue(playerElement, "PlayerCategoryID"));
//			player.setOwnerNotes(getElementValue(playerElement, "OwnerNotes"));
//			player.setAge(getElementValue(playerElement, "Age"));
//			player.setAgeDays(getElementValue(playerElement, "AgeDays"));
//			player.setNextBirthDay(getElementValue(playerElement, "NextBirthDay"));
//			player.setArrivalDate(getElementValue(playerElement, "ArrivalDate"));
			player.setPlayerForm(getElementValue(playerElement, "PlayerForm"));
//			player.setCards(getElementValue(playerElement, "Cards"));
//			player.setInjuryLevel(getElementValue(playerElement, "InjuryLevel"));
//			player.setStatement(getElementValue(playerElement, "Statement"));
//			player.setPlayerLanguage(getElementValue(playerElement, "PlayerLanguage"));
//			player.setPlayerLanguageID(getElementValue(playerElement, "PlayerLanguageID"));
//			player.setAgreeability(getElementValue(playerElement, "Agreeability"));
//			player.setAggressiveness(getElementValue(playerElement, "Aggressiveness"));
//			player.setHonesty(getElementValue(playerElement, "Honesty"));
//			player.setExperience(getElementValue(playerElement, "Experience"));
//			player.setLoyalty(getElementValue(playerElement, "Loyalty"));
//			player.setMotherClubBonus(getElementValue(playerElement, "MotherClubBonus"));
//			player.setLeaderShip(getElementValue(playerElement, "Leadership"));
			player.setSpecialty(getElementValue(playerElement, "Specialty"));
//			player.setNativeCountryID(getElementValue(playerElement, "NativeCountryID"));
//			player.setNativeLeagueID(getElementValue(playerElement, "NativeLeagueID"));
//			player.setNativeLeagueName(getElementValue(playerElement, "NativeLeagueName"));
			player.setTsi(getElementValue(playerElement, "TSI"));
//			player.setSalary(getElementValue(playerElement, "Salary"));
//			player.setIsAbroad(getElementValue(playerElement, "IsAbroad"));
//			player.setCaps(getElementValue(playerElement, "Caps"));
//			player.setCapsU20(getElementValue(playerElement, "CapsU20"));
//			player.setCareerGoals(getElementValue(playerElement, "CareerGoals"));
//			player.setCareerHattricks(getElementValue(playerElement, "CareerHattricks"));
//			player.setLeagueGoals(getElementValue(playerElement, "LeagueGoals"));
//			player.setCupGoals(getElementValue(playerElement, "CupGoals"));
//			player.setFriendliesGoals(getElementValue(playerElement, "FriendliesGoals"));
//			player.setNationalTeamId(getElementValue(playerElement, "NationalTeamID"));
//			player.setNationalTeamName(getElementValue(playerElement, "NationalTeamName"));
//			player.setTransferListed(getElementValue(playerElement, "TransferListed"));
			playerDetails.setPlayer(player);
			
			// <HattrickDate/Player/TrainerData>
//			Element trainingDataElement = getChildElement(playerElement, "TrainerData");
//			
//			TrainerData trainerData = new TrainerData();
//			trainerData.setTrainerType(getElementValue(trainingDataElement, "TrainerType"));
//			trainerData.setTrainerSkill(getElementValue(trainingDataElement, "TrainerSkill"));
//			player.setTrainerData(trainerData);
			
			// <HattrickDate/Player/OwningTeam>
//			Element owningTeamElement = getChildElement(playerElement, "OwningTeam");
//			
//			Team owningTeam = new Team();
//			owningTeam.setTeamID(getElementValue(owningTeamElement, "TeamID"));
//			owningTeam.setTeamName(getElementValue(owningTeamElement, "TeamName"));
//			owningTeam.setLeagueID(getElementValue(owningTeamElement, "LeagueID"));
//			player.setOwningTeam(owningTeam);
			
			// <HattrickDate/Player/PlayerSkills>
			Element playerSkillsElement = getChildElement(playerElement, "PlayerSkills");
			
			PlayerSkills playerSkills = new PlayerSkills();
			playerSkills.setStaminaSkill(getElementValue(playerSkillsElement, "StaminaSkill"));
			playerSkills.setKeeperSkill(getElementValue(playerSkillsElement, "KeeperSkill"));
			playerSkills.setPlaymakerSkill(getElementValue(playerSkillsElement, "PlaymakerSkill"));
			playerSkills.setScorerSkill(getElementValue(playerSkillsElement, "ScorerSkill"));
			playerSkills.setPassingSkill(getElementValue(playerSkillsElement, "PassingSkill"));
			playerSkills.setWingerSkill(getElementValue(playerSkillsElement, "WingerSkill"));
			playerSkills.setDefenderSkill(getElementValue(playerSkillsElement, "DefenderSkill"));
			playerSkills.setSetPiecesSkill(getElementValue(playerSkillsElement, "SetPiecesSkill"));
			player.setPlayerSkills(playerSkills);
			
			// <HattrickDate/Player/LastMatch>
//			Element lastMatchElement = getChildElement(playerElement, "LastMatch");
//			
//			if(lastMatchElement != null) {
//				Match lastMatch = new Match();
//				lastMatch.setDate(getElementValue(lastMatchElement, "Date"));
//				lastMatch.setMatchId(getElementValue(lastMatchElement, "MatchId"));
//				lastMatch.setPositionCode(getElementValue(lastMatchElement, "PositionCode"));
//				lastMatch.setPlayedMinutes(getElementValue(lastMatchElement, "PlayedMinutes"));
//				lastMatch.setRating(getElementValue(lastMatchElement, "Rating"));
//				lastMatch.setRatingEndOfGame(getElementValue(lastMatchElement, "RatingEndOfGame"));
//				player.setLastMatch(lastMatch);
//			}
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		
		return playerDetails;
	}

}
