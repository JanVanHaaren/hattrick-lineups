package api.entity.arenadetails;

import java.text.ParseException;
import java.util.Calendar;

import api.util.Utils;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public class CurrentCapacity extends Capacity {

	private Calendar rebuiltDate;

	public CurrentCapacity() {
		super();
	}

	public CurrentCapacity(String rebuiltDate, String terraces, String basic, String roof, String VIP, String total) {
		super(terraces,basic,roof,VIP,total);
		this.setRebuiltDate(rebuiltDate);
	}

	public Calendar getRebuiltDate() {
		return this.rebuiltDate;
	}

	public void setRebuiltDate(String rebuiltDate) {
		this.rebuiltDate = Calendar.getInstance();

		try {
			this.getRebuiltDate().setTime(Utils.getHattrickDateFormat().parse(rebuiltDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
