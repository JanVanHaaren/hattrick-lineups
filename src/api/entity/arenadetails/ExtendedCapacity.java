package api.entity.arenadetails;

import java.text.ParseException;
import java.util.Calendar;

import api.util.Utils;

public class ExtendedCapacity extends Capacity {

	private Calendar expansionDate;

	public ExtendedCapacity() {
		super();
	}

	public ExtendedCapacity(String expansionDate, String terraces, String basic, String roof, String VIP, String total) {
		super(terraces, basic, roof, VIP, total);
	}

	public Calendar getExpansionDate() {
		return this.expansionDate;
	}

	public void setExpansionDate(String expansionDate) {
		this.expansionDate = Calendar.getInstance();

		try {
			this.getExpansionDate().setTime(Utils.getHattrickDateFormat().parse(expansionDate));
		}
		catch (ParseException e) {
			e.printStackTrace();
		}
	}

}
