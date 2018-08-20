package cn.xeonsoft.scheduler.sl.bo;

import java.util.Date;

public class PptnExtremum {
	private String stcd;
	private Date tm;	//最高发生时间
	private Float maxdrp;	//最大时段雨量

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

	public Float getMaxdrp() {
		return maxdrp;
	}

	public void setMaxdrp(Float maxdrp) {
		this.maxdrp = maxdrp;
	}

}
