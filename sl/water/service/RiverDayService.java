package cn.xeonsoft.scheduler.sl.water.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.water.domain.StatisDQ;
import cn.xeonsoft.scheduler.sl.water.domain.StatisDZ;

public interface RiverDayService {
	void saveOrUpdateAvgz(Date tm, List<Extreme> avgzList);

	void saveOrUpdateEhz(Date tm, List<Extreme> ehzList);

	void saveOrUpdateHtzAndTm(Date tm, List<Extreme> htzAndTmList);

	void saveOrUpdateLtzAndTm(Date tm, List<Extreme> ltzAndTmList);
}