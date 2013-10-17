package api.entity.worlddetails;

import java.text.ParseException;
import java.util.Calendar;

import api.util.Utils;

public class League {
	
	private int leagueID;
	
	private String leagueName;
	
	private int season;
	
	private int seasonOffset;
	
	private int matchRound;
	
	private String shortName;
	
	private String continent;
	
	private String zoneName;
	
	private String englishName;
	
	private Country country;
	
	private Cup cup;
	
	private int nationalTeamId;
	
	private int u20TeamId;
	
	private int activeTeams;
	
	private int activeUsers;
	
	private int waitingUsers;
	
	private Calendar trainingDate;
	
	private Calendar economyDate;
	
	private Calendar cupMatchDate;
	
	private Calendar seriesMatchDate;
	
	private int numberOfLevels;

	public int getLeagueID() {
		return leagueID;
	}

	public void setLeagueID(String leagueID) {
		this.leagueID = Utils.getIntFromString(leagueID);
	}

	public String getLeagueName() {
		return leagueName;
	}

	public void setLeagueName(String leagueName) {
		this.leagueName = leagueName;
	}

	public int getSeason() {
		return season;
	}

	public void setSeason(String season) {
		this.season = Utils.getIntFromString(season);
	}

	public int getSeasonOffset() {
		return seasonOffset;
	}

	public void setSeasonOffset(String seasonOffset) {
		this.seasonOffset = Utils.getIntFromString(seasonOffset);
	}

	public int getMatchRound() {
		return matchRound;
	}

	public void setMatchRound(String matchRound) {
		this.matchRound = Utils.getIntFromString(matchRound);
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getContinent() {
		return continent;
	}

	public void setContinent(String continent) {
		this.continent = continent;
	}

	public String getZoneName() {
		return zoneName;
	}

	public void setZoneName(String zoneName) {
		this.zoneName = zoneName;
	}

	public String getEnglishName() {
		return englishName;
	}

	public void setEnglishName(String englishName) {
		this.englishName = englishName;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Cup getCup() {
		return cup;
	}

	public void setCup(Cup cup) {
		this.cup = cup;
	}

	public int getNationalTeamId() {
		return nationalTeamId;
	}

	public void setNationalTeamId(String nationalTeamId) {
		this.nationalTeamId = Utils.getIntFromString(nationalTeamId);
	}

	public int getU20TeamId() {
		return u20TeamId;
	}

	public void setU20TeamId(String u20TeamId) {
		this.u20TeamId = Utils.getIntFromString(u20TeamId);
	}

	public int getActiveTeams() {
		return activeTeams;
	}

	public void setActiveTeams(String activeTeams) {
		this.activeTeams = Utils.getIntFromString(activeTeams);
	}

	public int getActiveUsers() {
		return activeUsers;
	}

	public void setActiveUsers(String activeUsers) {
		this.activeUsers = Utils.getIntFromString(activeUsers);
	}

	public int getWaitingUsers() {
		return waitingUsers;
	}

	public void setWaitingUsers(String waitingUsers) {
		this.waitingUsers = Utils.getIntFromString(waitingUsers);
	}

	public Calendar getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(String trainingDate) {
		this.trainingDate = Calendar.getInstance();

		try {
			this.getTrainingDate().setTime(Utils.getHattrickDateFormat().parse(trainingDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Calendar getEconomyDate() {
		return economyDate;
	}

	public void setEconomyDate(String economyDate) {
		this.economyDate = Calendar.getInstance();

		try {
			this.getEconomyDate().setTime(Utils.getHattrickDateFormat().parse(economyDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Calendar getCupMatchDate() {
		return cupMatchDate;
	}

	public void setCupMatchDate(String cupMatchDate) {
		this.cupMatchDate = Calendar.getInstance();

		try {
			this.getCupMatchDate().setTime(Utils.getHattrickDateFormat().parse(cupMatchDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public Calendar getSeriesMatchDate() {
		return seriesMatchDate;
	}

	public void setSeriesMatchDate(String seriesMatchDate) {
		this.seriesMatchDate = Calendar.getInstance();

		try {
			this.getSeriesMatchDate().setTime(Utils.getHattrickDateFormat().parse(seriesMatchDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

	public int getNumberOfLevels() {
		return numberOfLevels;
	}

	public void setNumberOfLevels(String numberOfLevels) {
		this.numberOfLevels = Utils.getIntFromString(numberOfLevels);
	}
	
	
	
}
