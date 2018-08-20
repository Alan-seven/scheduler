package cn.xeonsoft.worker.task.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.worker.task.bo.PptnExtremum;
import cn.xeonsoft.worker.task.bo.RainDay;

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
