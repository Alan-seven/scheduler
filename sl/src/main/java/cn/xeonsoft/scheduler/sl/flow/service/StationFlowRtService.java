package cn.xeonsoft.scheduler.sl.flow.service;


import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;

import java.util.Date;
import java.util.List;

public interface StationFlowRtService {
	/**
	 * 得到任意时间段级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findSum(Date startDate, Date endDate);
	/**
	 * 得到小时级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findHourSum(Date startDate, Date endDate);
	/**
	 * 得到日级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findDaySum(Date startDate, Date endDate);

	/**
	 * 得到月级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findMonthSum(Date startDate, Date endDate);

	/**
	 * 得到年级的合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findYearSum(Date startDate, Date endDate);
}