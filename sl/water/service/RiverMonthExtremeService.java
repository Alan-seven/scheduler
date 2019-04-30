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
public interface RiverMonthExtremeService {

	public void saveOrUpdateMaxzAndTm(Date tm, List<Extreme> riverAvg);

	public void saveOrUpdateMaxzAndTm(List<Extreme> riverAvg);

	public void saveOrUpdateMinzAndTm(List<Extreme> riverAvg);

	public void saveOrUpdateMinzAndTm(Date tm, List<Extreme> riverAvg);

}
