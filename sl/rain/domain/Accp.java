package cn.xeonsoft.scheduler.sl.rain.domain;

import java.util.Date;

/**
 * 累计雨量
 */
public class Accp {
	private Date tm;
	private String stcd;
	private Float accp;

	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Float getAccp() {
		return accp;
	}

	public void setAccp(Float accp) {
		this.accp = accp;
	}

}
