package cn.xeonsoft.worker.task.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.worker.task.domain.Accp;

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