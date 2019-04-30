package cn.xeonsoft.scheduler.sl.rain.domain;

import java.util.Date;

/**
 * 一日、三日、一侯、一旬、一月、一年累计雨量
 */
public class StPstatR {
	private String stcd;
	private Date idtm;
	/**
	 * 一日：1
	 * 三日：2
	 * 一侯：3
	 * 一旬：4
	 * 一月：5
	 * 一年：6
	 */
	private String sttdrcd;
	private Float accp;
	public String getStcd() {
		return stcd;
	}
	public void setStcd(String stcd) {
		this.stcd = stcd;
	}
	public Date getIdtm() {
		return idtm;
	}

	public void setIdtm(Date idtm) {
		this.idtm = idtm;
	}

	public String getSttdrcd() {
		return sttdrcd;
	}
	public void setSttdrcd(String sttdrcd) {
		this.sttdrcd = sttdrcd;
	}
	public Float getAccp() {
		return accp;
	}
	public void setAccp(Float accp) {
		this.accp = accp;
	}
	
	
}
