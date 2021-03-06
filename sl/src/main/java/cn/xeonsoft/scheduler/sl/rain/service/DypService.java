package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;

/**
 * 累计降水量ST_PSTAT_R，日旬月年
 * 
 * @author wantwantxu
 *
 */
public interface DypService {
	void saveOrUpdate(Date tm,List<Accp> accp);

	void saveOrUpdateMonth(Date tm,List<Accp> accp);

	void saveOrUpdateYear(Date tm,List<Accp> accp);

	void saveOrUpdate(Date idtm, String sttdrcd, List<Accp> accp);

	void saveOrUpdateHour(List<Accp> accp);
}