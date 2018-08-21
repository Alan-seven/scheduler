package cn.xeonsoft.scheduler.reptile.weather.respository;

import org.apache.ibatis.annotations.Insert;

import cn.xeonsoft.scheduler.reptile.weather.domain.Weather;

public interface WeatherRepository {
	@Insert("INSERT INTO T_WEATHER(ID,CITYID,TEMP,WD,WS,TM,TEMPN,WEATHER) values (#{id},#{cityId},#{temp},#{wd},#{ws},#{tm},#{tempn},#{weather})")
	void save(Weather weather);
}