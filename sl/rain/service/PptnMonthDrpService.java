package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;

/**
 * 每月雨量的总和，存ST_PDMMYSQ_S
 * 
 * @author wantwantxu
 *
 */
public interface PptnMonthDrpService {

	public void saveOrUpdate(int prdtp,List<Accp> accpList);
}
