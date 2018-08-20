package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.bo.RainDay;
import cn.xeonsoft.scheduler.sl.domain.Accp;
import cn.xeonsoft.scheduler.sl.domain.PptnSt;

public interface PptnRtService {
	List<PptnSt> findList();

	List<PptnSt> findListByMonth();

	List<PptnSt> findListByYear();

	/**
	 * 得到指定开始时间、结束时间的原始数据
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<PptnSt> findList(Date startDate, Date endDate);

	List<Accp> findAccp();

	List<Accp> findAccpByMonth(Date tm);

	List<Accp> findAccpByMonth();

	List<Accp> findAccpByYear();


	List<Accp> findAccp(Date tm);
	/**
	 * 得到指定开始时间、结束时间的累计雨量
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Accp> findAccp(Date startDate, Date endDate);

	List<RainDay> findRainDay();

	List<RainDay> findRainDayByMonth(Date tm);

	List<RainDay> findRainDayByMonth();

	List<RainDay> findRainDayByYear();

	/**
	 * 得到指定开始时间、结束时间的累计下雨天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<RainDay> findRainDay(Date startDate, Date endDate);

	List<PptnExtremum> findMaxDrpAndTm();

	List<PptnExtremum> findMaxDrpAndTmByMonth(Date tm);

	List<PptnExtremum> findMaxDrpAndTmByMonth();

	List<PptnExtremum> findMaxDrpAndTmByYear();

	/**
	 * 得到指定开始时间、结束时间的最高雨量
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<PptnExtremum> findMaxDrpAndTm(Date startDate, Date endDate);
}