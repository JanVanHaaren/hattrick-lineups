package api.entity.playerdetails;

import java.text.ParseException;
import java.util.Calendar;

import api.entity.datatype.PlayerAggressiveness;
import api.entity.datatype.PlayerAgreeability;
import api.entity.datatype.PlayerCategoryID;
import api.entity.datatype.PlayerHonesty;
import api.entity.datatype.SkillLevel;
import api.entity.datatype.SpecialtyID;
import api.util.Utils;

public class Player {

	private int playerID;
	
	private String firstName;
	
	private String nickName;

	private String lastName;
	
	private int playerNumber;
	
	private PlayerCategoryID playerCategoryID;
	
	private String ownerNotes;
	
	private int age;
	
	private int ageDays;
	
	private Calendar nextBirthDay;
	
	private Calendar arrivalDate;
	
	private SkillLevel playerForm;
	
	private int cards;
	
	private int injuryLevel;
	
	private String statement;
	
	private String playerLanguage;
	
	private int playerLanguageID;
	
	private TrainerData trainerData;
	
	private PlayerAgreeability agreeability;
	
	private PlayerAggressiveness aggressiveness;
	
	private PlayerHonesty honesty;
	
	private SkillLevel experience;
	
	private SkillLevel loyalty;
	
	private boolean motherClubBonus;
	
	private SkillLevel leaderShip;
	
	private SpecialtyID specialty;
	
	private int nativeCountryID;
	
	private int nativeLeagueID;
	
	private String nativeLeagueName;
	
	private int tsi;
	
	private Team owningTeam;
	
	private int salary;
	
	private Boolean isAbroad;
	
	private PlayerSkills playerSkills;
	
	private int caps;
	
	private int capsU20;
	
	private int careerGoals;
	
	private int careerHattricks;
	
	private int leagueGoals;
	
	private int cupGoals;
	
	private Integer friendliesGoals;
	
	private Integer nationalTeamId;
	
	private String nationalTeamName;
	
	private boolean transferListed;
	
	private Match lastMatch;

	public int getPlayerID() {
		return playerID;
	}

	public void setPlayerID(String playerID) {
		this.playerID = Utils.getIntFromString(playerID);
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public int getPlayerNumber() {
		return playerNumber;
	}

	public void setPlayerNumber(String playerNumber) {
		this.playerNumber = Utils.getIntFromString(playerNumber);
	}
	
	public PlayerCategoryID getPlayerCategoryID() {
		return playerCategoryID;
	}

	public void setPlayerCategoryID(String playerCategoryID) {
		if(playerCategoryID != null)
			this.playerCategoryID = PlayerCategoryID.getPlayerCategoryID(playerCategoryID);
	}

	public String getOwnerNotes() {
		return ownerNotes;
	}

	public void setOwnerNotes(String ownerNotes) {
		this.ownerNotes = ownerNotes;
	}

	public int getAge() {
		return age;
	}

	public void setAge(String age) {
		this.age = Utils.getIntFromString(age);
	}

	public int getAgeDays() {
		return ageDays;
	}

	public void setAgeDays(String ageDays) {
		this.ageDays = Utils.getIntFromString(ageDays);
	}

	public Calendar getNextBirthDay() {
		return nextBirthDay;
	}

	public void setNextBirthDay(String nextBirthDay) {
		this.nextBirthDay = Calendar.getInstance();

		try {
			this.getNextBirthDay().setTime(Utils.getHattrickDateFormat().parse(nextBirthDay));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}
	
	public Calendar getArrivalDate() {
		return arrivalDate;
	}

	public void setArrivalDate(String arrivalDate) {
		this.arrivalDate = Calendar.getInstance();

		try {
			this.getArrivalDate().setTime(Utils.getHattrickDateFormat().parse(arrivalDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public SkillLevel getPlayerForm() {
		return playerForm;
	}

	public void setPlayerForm(String playerForm) {
		this.playerForm = new SkillLevel(playerForm);
	}

	public int getCards() {
		return cards;
	}

	public void setCards(String cards) {
		this.cards = Utils.getIntFromString(cards);
	}

	public int getInjuryLevel() {
		return injuryLevel;
	}

	public void setInjuryLevel(String injuryLevel) {
		this.injuryLevel = Utils.getIntFromString(injuryLevel);
	}

	public String getStatement() {
		return statement;
	}

	public void setStatement(String statement) {
		this.statement = statement;
	}
	
	public String getPlayerLanguage() {
		return playerLanguage;
	}

	public void setPlayerLanguage(String playerLanguage) {
		this.playerLanguage = playerLanguage;
	}

	public int getPlayerLanguageID() {
		return playerLanguageID;
	}

	public void setPlayerLanguageID(String playerLanguageID) {
		this.playerLanguageID = Utils.getIntFromString(playerLanguageID);
	}

	public TrainerData getTrainerData() {
		return trainerData;
	}

	public void setTrainerData(TrainerData trainerData) {
		this.trainerData = trainerData;
	}

	public PlayerAgreeability getAgreeability() {
		return agreeability;
	}

	public void setAgreeability(String agreeability) {
		this.agreeability = new PlayerAgreeability(agreeability);
	}

	public PlayerAggressiveness getAggressiveness() {
		return aggressiveness;
	}

	public void setAggressiveness(String aggressiveness) {
		this.aggressiveness = new PlayerAggressiveness(aggressiveness);
	}

	public PlayerHonesty getHonesty() {
		return honesty;
	}

	public void setHonesty(String honesty) {
		this.honesty = new PlayerHonesty(honesty);
	}

	public SkillLevel getExperience() {
		return experience;
	}

	public void setExperience(String experience) {
		this.experience = new SkillLevel(experience);
	}
	
	public SkillLevel getLoyalty() {
		return loyalty;
	}

	public void setLoyalty(String loyalty) {
		this.loyalty = new SkillLevel(loyalty);
	}

	public boolean isMotherClubBonus() {
		return motherClubBonus;
	}

	public void setMotherClubBonus(String motherClubBonus) {
		this.motherClubBonus = Utils.getBooleanFromString(motherClubBonus);
	}

	public SkillLevel getLeaderShip() {
		return leaderShip;
	}

	public void setLeaderShip(String leaderShip) {
		this.leaderShip = new SkillLevel(leaderShip);
	}

	public SpecialtyID getSpecialty() {
		return specialty;
	}

	public void setSpecialty(String specialty) {
		if(specialty != null)
			this.specialty = SpecialtyID.getSpecialtyID(specialty);
	}

	public int getNativeCountryID() {
		return nativeCountryID;
	}

	public void setNativeCountryID(String nativeCountryID) {
		this.nativeCountryID = Utils.getIntFromString(nativeCountryID);
	}

	public int getNativeLeagueID() {
		return nativeLeagueID;
	}

	public void setNativeLeagueID(String nativeLeagueID) {
		this.nativeLeagueID = Utils.getIntFromString(nativeLeagueID);
	}

	public String getNativeLeagueName() {
		return nativeLeagueName;
	}

	public void setNativeLeagueName(String nativeLeagueName) {
		this.nativeLeagueName = nativeLeagueName;
	}

	public int getTsi() {
		return tsi;
	}

	public void setTsi(String tsi) {
		this.tsi = Utils.getIntFromString(tsi);
	}

	public Team getOwningTeam() {
		return owningTeam;
	}

	public void setOwningTeam(Team owningTeam) {
		this.owningTeam = owningTeam;
	}

	public int getSalary() {
		return salary;
	}

	public void setSalary(String salary) {
		this.salary = Utils.getIntFromString(salary);
	}

	public Boolean getIsAbroad() {
		return isAbroad;
	}

	public void setIsAbroad(String isAbroad) {
		this.isAbroad = Utils.getBooleanFromString(isAbroad);
	}

	public PlayerSkills getPlayerSkills() {
		return playerSkills;
	}

	public void setPlayerSkills(PlayerSkills playerSkills) {
		this.playerSkills = playerSkills;
	}

	public int getCaps() {
		return caps;
	}

	public void setCaps(String caps) {
		this.caps = Utils.getIntFromString(caps);
	}

	public int getCapsU20() {
		return capsU20;
	}

	public void setCapsU20(String capsU20) {
		this.capsU20 = Utils.getIntFromString(capsU20);
	}

	public int getCareerGoals() {
		return careerGoals;
	}

	public void setCareerGoals(String careerGoals) {
		this.careerGoals = Utils.getIntFromString(careerGoals);
	}

	public int getCareerHattricks() {
		return careerHattricks;
	}

	public void setCareerHattricks(String careerHattricks) {
		this.careerHattricks = Utils.getIntFromString(careerHattricks);
	}

	public int getLeagueGoals() {
		return leagueGoals;
	}

	public void setLeagueGoals(String leagueGoals) {
		this.leagueGoals = Utils.getIntFromString(leagueGoals);
	}

	public int getCupGoals() {
		return cupGoals;
	}

	public void setCupGoals(String cupGoals) {
		this.cupGoals = Utils.getIntFromString(cupGoals);
	}

	public int getFriendliesGoals() {
		return friendliesGoals;
	}

	public void setFriendliesGoals(String friendliesGoals) {
		if(friendliesGoals != null)
			this.friendliesGoals = Utils.getIntFromString(friendliesGoals);
	}

	public Integer getNationalTeamId() {
		return nationalTeamId;
	}

	public void setNationalTeamId(String nationalTeamId) {
		if(nationalTeamId != null)
			this.nationalTeamId = Utils.getIntFromString(nationalTeamId);
	}

	public String getNationalTeamName() {
		return nationalTeamName;
	}

	public void setNationalTeamName(String nationalTeamName) {
		this.nationalTeamName = nationalTeamName;
	}

	public boolean isTransferListed() {
		return transferListed;
	}

	public void setTransferListed(String transferListed) {
		this.transferListed = Utils.getBooleanFromString(transferListed);
	}

	public Match getLastMatch() {
		return lastMatch;
	}

	public void setLastMatch(Match lastMatch) {
		this.lastMatch = lastMatch;
	}
	
	public double getPerformanceMultiplier()
	{
		double multiplier = 1;
		if(this.getInjuryLevel() > 0)
			return 0;
		if(this.getInjuryLevel() == 0)
			multiplier *= 0.95;
		multiplier *= Math.pow((((double)this.getPlayerForm().getValue()-0.5D)/7D),0.45);
		
		return multiplier;
	}
}
