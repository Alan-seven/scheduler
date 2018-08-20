package cn.xeonsoft.worker.task.bo;

import java.util.Date;

public class ZQExtremum {
	private String stcd;
	private Date tm;
	private Float extremum;

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

	public Float getExtremum() {
		return extremum;
	}

	public void setExtremum(Float extremum) {
		this.extremum = extremum;
	}

}
