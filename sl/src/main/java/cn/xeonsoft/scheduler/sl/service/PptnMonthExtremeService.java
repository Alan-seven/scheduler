package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.bo.RainDay;

/**
 * 雨量月极值STATIS_DYP
 * 
 * @author wantwantxu
 *
 */
public interface PptnMonthExtremeService {
	
	public void saveOrUpdateMaxdrpAndTm(Date tm,List<PptnExtremum> pptnExtremum);
	
	public void saveOrUpdateMaxdrpAndTm(List<PptnExtremum> pptnExtremum);

	public void updateRainDays(List<RainDay> raindays);

	public void updateRainDays(Date tm,List<RainDay> raindays);
}
