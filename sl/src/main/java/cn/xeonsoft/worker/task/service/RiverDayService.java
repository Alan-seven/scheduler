package cn.xeonsoft.worker.task.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.worker.task.bo.ZQExtremum;
import cn.xeonsoft.worker.task.domain.RiverDay;

public interface RiverDayService {
	void saveOrUpdateAvz(Date tm, List<ZQExtremum> avzList);

	void saveOrUpdateAvz(List<ZQExtremum> avzList);

	void saveOrUpdateEhz(List<RiverDay> riverDayList);

	void remove();
}