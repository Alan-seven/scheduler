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
public interface RiverMonthExtremeService {

	public void saveOrUpdateMaxzAndTm(Date tm, List<ZQExtremum> riverAvg);

	public void saveOrUpdateMaxzAndTm(List<ZQExtremum> riverAvg);

	public void saveOrUpdateMinzAndTm(List<ZQExtremum> riverAvg);

	public void saveOrUpdateMinzAndTm(Date tm, List<ZQExtremum> riverAvg);

}
