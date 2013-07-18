package api.entity.arenadetails;

import api.util.Utils;

/**
 * @author	Jan Van Haaren
 * @date	28 June 2013
 */
public abstract class Capacity {

	private int terraces;

	private int basic;

	private int roof;

	private int VIP;

	private int total;

	public Capacity() {
		// NOP
	}

	public Capacity(String terraces, String basic, String roof, String VIP, String total) {
		this.setTerraces(terraces);
		this.setBasic(basic);
		this.setRoof(roof);
		this.setVIP(VIP);
		this.setTotal(total);
	}

	public int getTerraces() {
		return this.terraces;
	}

	public void setTerraces(String terraces) {
		this.terraces = Utils.getIntFromString(terraces);
	}

	public int getBasic() {
		return this.basic;
	}

	public void setBasic(String basic) {
		this.basic = Utils.getIntFromString(basic);
	}

	public int getRoof() {
		return this.roof;
	}

	public void setRoof(String roof) {
		this.roof = Utils.getIntFromString(roof);
	}

	public int getVIP() {
		return this.VIP;
	}

	public void setVIP(String VIP) {
		this.VIP = Utils.getIntFromString(VIP);
	}

	public int getTotal() {
		return this.total;
	}

	public void setTotal(String total) {
		this.total = Utils.getIntFromString(total);
	}

}
