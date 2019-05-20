package cn.xeonsoft.scheduler.sl.water.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.water.domain.RiverSt;

public interface RiverRtService {
	/**
	 * 8时水位
	 * @return
	 */
	List<Extreme> findEhz();

	List<Extreme> findEhz(Date tm);

	/**
	 * 得到指定开始时间、结束时间的原始数据
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<RiverSt> findList(Date startDate, Date endDate);

	/**
	 * 得到最大水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtzAndTm(Date tm);

	/**
	 * 得到今天最大水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtzAndTm();

	/**
	 * 得到最小水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtzAndTm(Date tm);

	/**
	 * 得到今天最小水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtzAndTm();

	/**
	 * 得到指定日期平均水位
	 * 
	 * @return
	 */
	List<Extreme> findAvgz(Date tm);

	/**
	 * 得到今天平均水位
	 * 
	 * @return
	 */
	List<Extreme> findAvgz();

	/**
	 * 得到月平均水位
	 * 
	 * @return
	 */
	List<Extreme> findAvgzByMonth(Date tm);

	/**
	 * 得到月平均水位
	 * 
	 * @return
	 */
	List<Extreme> findAvgzByMonth();

	/**
	 * 得到年平均水位
	 * 
	 * @return
	 */
	List<Extreme> findAvgzByYear(Date tm);

	/**
	 * 得到年平均水位
	 * 
	 * @return
	 */
	List<Extreme> findAvgzByYear();

	/**
	 * 得到指定开始时间、结束时间的最大水位及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Extreme> findHtzAndTm(Date startDate, Date endDate);

	/**
	 * 得到指定开始时间、结束时间的最小水位及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Extreme> findLtzAndTm(Date startDate, Date endDate);

	/**
	 * 得到当月最大水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtzAndTmByMonth();

	/**
	 * 得到指定月份最大水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtzAndTmByMonth(Date tm);

	/**
	 * 得到当月最小水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtzAndTmByMonth();

	/**
	 * 得到指定月份最小水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtzAndTmByMonth(Date tm);

	/**
	 * 得到当年最大水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtzAndTmByYear();

	/**
	 * 得到指定年份最大水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtzAndTmByYear(Date tm);

	/**
	 * 得到当年最小水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtzAndTmByYear();

	/**
	 * 得到指定年份最小水位及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtzAndTmByYear(Date tm);

	/**
	 * 得到今天最大流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtqAndTm();

	/**
	 * 得到今天最小流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtqAndTm();

	/**
	 * 得到指定开始时间、结束时间的最大流量及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Extreme> findHtqAndTm(Date startDate, Date endDate);

	/**
	 * 得到指定开始时间、结束时间的最小流量及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<Extreme> findLtqAndTm(Date startDate, Date endDate);

	/**
	 * 得到当月最大流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtqAndTmByMonth();

	/**
	 * 得到指定月份最大流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtqAndTmByMonth(Date tm);

	/**
	 * 得到当月最小流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtqAndTmByMonth();

	/**
	 * 得到指定月份最小流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtqAndTmByMonth(Date tm);

	/**
	 * 得到当年最大流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtqAndTmByYear();

	/**
	 * 得到指定年份最大流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findHtqAndTmByYear(Date tm);

	/**
	 * 得到当年最小流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtqAndTmByYear();

	/**
	 * 得到指定年份最小流量及发生时间
	 * 
	 * @return
	 */
	List<Extreme> findLtqAndTmByYear(Date tm);
}