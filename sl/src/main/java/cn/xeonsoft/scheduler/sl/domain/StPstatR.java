package cn.xeonsoft.scheduler.sl.domain;

import java.util.Date;

public class StPstatR {
	private String stcd;
	private Date idtm;
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
