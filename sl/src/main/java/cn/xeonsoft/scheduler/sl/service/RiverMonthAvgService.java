package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;

/**
 * 旬月均值ST_RVDMMYSQ_S
 * 
 * @author wantwantxu
 *
 */
public interface RiverMonthAvgService {
	public void saveOrUpdate(List<ZQExtremum> riverAvg);

	public void saveOrUpdate(Date tm, List<ZQExtremum> riverAvg);

}
