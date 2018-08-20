package cn.xeonsoft.scheduler.sl.domain;

public class RiverDay {
	private String stcd;
	private Float z; // 平均水位
	private Float ehz; // 8h水位
	public String getStcd() {
		return stcd;
	}

	public void setStcd(String stcd) {
		this.stcd = stcd;
	}

	public Float getZ() {
		return z;
	}

	public void setZ(Float z) {
		this.z = z;
	}

	public Float getEhz() {
		return ehz;
	}

	public void setEhz(Float ehz) {
		this.ehz = ehz;
	}

}
