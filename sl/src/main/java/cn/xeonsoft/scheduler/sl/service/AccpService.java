package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.domain.Accp;

/**
 * 累计降水量ST_PSTAT_R
 * 
 * @author wantwantxu
 *
 */
public interface AccpService {
	void saveOrUpdate(List<Accp> accp);

	void saveOrUpdateMonth(List<Accp> accp);

	void saveOrUpdateYear(List<Accp> accp);

	void saveOrUpdate(Date idtm, String sttdrcd, List<Accp> accp);
}