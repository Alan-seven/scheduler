package cn.xeonsoft.scheduler.reptile.weather.domain;

import java.util.Date;

public class Weather {
	private String id;
	private String cityId;
	private String temp;
	private String tempn;
	private String weather;
	private String wd;
	private String ws;
	private Date tm;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getTemp() {
		return temp;
	}

	public void setTemp(String temp) {
		this.temp = temp;
	}

	public String getTempn() {
		return tempn;
	}

	public void setTempn(String tempn) {
		this.tempn = tempn;
	}

	public String getWeather() {
		return weather;
	}

	public void setWeather(String weather) {
		this.weather = weather;
	}

	public String getWd() {
		return wd;
	}

	public void setWd(String wd) {
		this.wd = wd;
	}

	public String getWs() {
		return ws;
	}

	public void setWs(String ws) {
		this.ws = ws;
	}

	public Date getTm() {
		return tm;
	}

	public void setTm(Date tm) {
		this.tm = tm;
	}
}
