package cn.xeonsoft.scheduler.sl.rain.service;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;

import java.util.Date;
import java.util.List;

public interface DirectionAccpService {
	void save(List<Accp> list, String direction, String sttdrcd);

	void save(Date tm, String direction, String sttdrcd, Float accp);

	Integer findCount(Date tm, String direction, String sttdrcd);

	void update(Date tm, String direction, String sttdrcd);
}