package cn.xeonsoft.worker.task.domain;

import java.io.Serializable;

public class Station implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4595433883034457461L;

	private String stcd;
	private String stnm;

	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public String getStnm() {
		return stnm;
	}

	public void setStnm(String stnm) {
		this.stnm = stnm;
	}

}