package api.entity.worlddetails;

import api.util.Utils;

public class Country {

	private int countryID;
	
	private String countryName;
	
	private String currencyName;
	
	private Float currencyRate;
	
	private String dateFormat;
	
	private String timeFormat;

	public int getCountryID() {
		return countryID;
	}

	public void setCountryID(String countryID) {
		this.countryID = Utils.getIntFromString(countryID);
	}

	public String getCountryName() {
		return countryName;
	}

	public void setCountryName(String countryName) {
		this.countryName = countryName;
	}

	public String getCurrencyName() {
		return currencyName;
	}

	public void setCurrencyName(String currencyName) {
		this.currencyName = currencyName;
	}

	public Float getCurrencyRate() {
		return currencyRate;
	}

	public void setCurrencyRate(String currencyRate) {
		this.currencyRate = Utils.getFloatFromString(currencyRate);
	}

	public String getDateFormat() {
		return dateFormat;
	}

	public void setDateFormat(String dateFormat) {
		this.dateFormat = dateFormat;
	}

	public String getTimeFormat() {
		return timeFormat;
	}

	public void setTimeFormat(String timeFormat) {
		this.timeFormat = timeFormat;
	}
	
	
	
}
