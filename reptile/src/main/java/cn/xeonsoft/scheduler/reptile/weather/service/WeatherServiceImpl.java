package cn.xeonsoft.scheduler.reptile.weather.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.reptile.weather.domain.Weather;
import cn.xeonsoft.scheduler.reptile.weather.respository.WeatherRepository;

@Component("weatherService")
@Transactional
class WeatherServiceImpl implements WeatherService {
	@Autowired
	private WeatherRepository weatherRepository;

	@Override
	public void save(Weather weather) {
		weatherRepository.save(weather);
	}
}