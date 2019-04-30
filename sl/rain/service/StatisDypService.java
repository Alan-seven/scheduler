package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;

/**
 * @author wantwantxu
 *
 */
public interface StatisDypService {
	
	public void saveOrUpdateMaxdrpAndTm(Date tm,String sttdrcd,List<PptnExtremum> pptnExtremum);
	
	public void updateRainDays(Date tm,String sttdrcd,List<RainDays> raindays);
}
