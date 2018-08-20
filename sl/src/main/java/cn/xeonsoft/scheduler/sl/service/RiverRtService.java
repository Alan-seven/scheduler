package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.domain.RiverDay;
import cn.xeonsoft.scheduler.sl.domain.RiverSt;

public interface RiverRtService {

	List<RiverDay> find8hList();

	List<RiverDay> find8hList(Date tm);

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
	List<ZQExtremum> findMaxzAndTm(Date tm);

	/**
	 * 得到今天最大水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxzAndTm();

	/**
	 * 得到最小水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinzAndTm(Date tm);

	/**
	 * 得到今天最小水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinzAndTm();

	/**
	 * 得到指定日期平均水位
	 * 
	 * @return
	 */
	List<ZQExtremum> findAvgz(Date tm);

	/**
	 * 得到今天平均水位
	 * 
	 * @return
	 */
	List<ZQExtremum> findAvgz();

	/**
	 * 得到月平均水位
	 * 
	 * @return
	 */
	List<ZQExtremum> findAvgzByMonth(Date tm);

	/**
	 * 得到月平均水位
	 * 
	 * @return
	 */
	List<ZQExtremum> findAvgzByMonth();

	/**
	 * 得到年平均水位
	 * 
	 * @return
	 */
	List<ZQExtremum> findAvgzByYear(Date tm);

	/**
	 * 得到年平均水位
	 * 
	 * @return
	 */
	List<ZQExtremum> findAvgzByYear();

	/**
	 * 得到指定开始时间、结束时间的最大水位及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ZQExtremum> findMaxzAndTm(Date startDate, Date endDate);

	/**
	 * 得到指定开始时间、结束时间的最小水位及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ZQExtremum> findMinzAndTm(Date startDate, Date endDate);

	/**
	 * 得到当月最大水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxzAndTmByMonth();

	/**
	 * 得到指定月份最大水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxzAndTmByMonth(Date tm);

	/**
	 * 得到当月最小水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinzAndTmByMonth();

	/**
	 * 得到指定月份最小水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinzAndTmByMonth(Date tm);

	/**
	 * 得到当年最大水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxzAndTmByYear();

	/**
	 * 得到指定年份最大水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxzAndTmByYear(Date tm);

	/**
	 * 得到当年最小水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinzAndTmByYear();

	/**
	 * 得到指定年份最小水位及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinzAndTmByYear(Date tm);

	/**
	 * 得到今天最大流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxqAndTm();

	/**
	 * 得到今天最小流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinqAndTm();

	/**
	 * 得到指定开始时间、结束时间的最大流量及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ZQExtremum> findMaxqAndTm(Date startDate, Date endDate);

	/**
	 * 得到指定开始时间、结束时间的最小流量及发生时间
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<ZQExtremum> findMinqAndTm(Date startDate, Date endDate);

	/**
	 * 得到当月最大流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxqAndTmByMonth();

	/**
	 * 得到指定月份最大流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxqAndTmByMonth(Date tm);

	/**
	 * 得到当月最小流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinqAndTmByMonth();

	/**
	 * 得到指定月份最小流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinqAndTmByMonth(Date tm);

	/**
	 * 得到当年最大流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxqAndTmByYear();

	/**
	 * 得到指定年份最大流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMaxqAndTmByYear(Date tm);

	/**
	 * 得到当年最小流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinqAndTmByYear();

	/**
	 * 得到指定年份最小流量及发生时间
	 * 
	 * @return
	 */
	List<ZQExtremum> findMinqAndTmByYear(Date tm);
}