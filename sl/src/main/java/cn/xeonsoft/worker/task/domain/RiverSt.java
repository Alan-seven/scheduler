package cn.xeonsoft.worker.task.domain;

import java.io.Serializable;
import java.util.Date;

public class RiverSt implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4595433883034457461L;

	private String stcd;
	private Date tm;
	private Float z;

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

	public Float getZ() {
		return z;
	}

	public void setZ(Float z) {
		this.z = z;
	}

}