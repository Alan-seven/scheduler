package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.domain.PptnSt;

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

	/**
	 * 得到小时累计雨量
	 *
	 * @return
	 */
	List<Accp> findAccpByHour();

	List<Accp> findAccpByHour(Date tm);
	/**
	 * 得到当日日累计雨量
	 *
	 * @return
	 */
	List<Accp> findDayAccp();

	List<Accp> findDayAccp(Date tm);

	List<Accp> findFiveDaysAccp();

	List<Accp> findTenDaysAccp();
	/**
	 * 得到当月月累计雨量
	 * @return
	 */
	List<Accp> findAccpByMonth();

	List<Accp> findAccpByMonth(Date tm);

	/**
	 * 得到当年年累计雨量
	 * @return
	 */
	List<Accp> findAccpByYear();

	/**
	 * 得到指定开始时间、结束时间的累计雨量
	 *
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Accp> findAccp(Date startDate, Date endDate);

	List<RainDays> findRainDays();

	List<RainDays> findRainDaysByMonth(Date tm);

	List<RainDays> findRainDaysByMonth();

	List<RainDays> findRainDaysByYear();

	/**
	 * 得到指定开始时间、结束时间的累计下雨天数
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<RainDays> findRainDays(Date startDate, Date endDate);

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