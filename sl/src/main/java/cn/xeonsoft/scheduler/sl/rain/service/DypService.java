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
	void saveOrUpdate(List<Accp> accp);

	void saveOrUpdateMonth(List<Accp> accp);

	void saveOrUpdateYear(List<Accp> accp);

	void saveOrUpdate(Date idtm, String sttdrcd, List<Accp> accp);
}