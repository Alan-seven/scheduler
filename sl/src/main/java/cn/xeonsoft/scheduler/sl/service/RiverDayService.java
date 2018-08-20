package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.domain.RiverDay;

public interface RiverDayService {
	void saveOrUpdateAvz(Date tm, List<ZQExtremum> avzList);

	void saveOrUpdateAvz(List<ZQExtremum> avzList);

	void saveOrUpdateEhz(List<RiverDay> riverDayList);

	void remove();
}