package cn.xeonsoft.worker.task.domain;

import java.io.Serializable;
import java.util.Date;

public class PptnSt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4595433883034457461L;

	private String stcd;
	private Date tm;
	private Float drp;	//最新雨量
	private Float dyp;	//日雨量

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}

	public Float getDrp() {
		return drp;
	}

	public void setDrp(Float drp) {
		this.drp = drp;
	}

	public Float getDyp() {
		return dyp;
	}

	public void setDyp(Float dyp) {
		this.dyp = dyp;
	}
}