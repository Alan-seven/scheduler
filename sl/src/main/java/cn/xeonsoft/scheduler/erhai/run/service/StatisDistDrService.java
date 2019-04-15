package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.Dali;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;

import java.util.Date;
import java.util.List;

public interface StatisDistDrService {
	List<Dali> list(String sttdrcd, Date startDate, Date endDate, String addvcd);

	void saveOrUpdate( List<Accp> accp, String sttdrcd,Date tm);

	void saveOrUpdateHour( List<Accp> accp, String sttdrcd);
}