package cn.xeonsoft.scheduler.sl.water.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;

/**
 * 旬月均值ST_RVDMMYSQ_S
 * 
 * @author wantwantxu
 *
 */
public interface RiverMonthAvgService {
	public void saveOrUpdate(List<Extreme> riverAvg);

	public void saveOrUpdate(Date tm, List<Extreme> riverAvg);

}
