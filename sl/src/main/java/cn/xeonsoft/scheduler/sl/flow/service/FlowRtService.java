package cn.xeonsoft.scheduler.sl.flow.service;


import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;

import java.util.Date;
import java.util.List;

public interface FlowRtService {
	/**
	 * 得到分钟级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findMinuteSum(String type,Date startDate,Date endDate);

	/**
	 * 得到小时级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findHourSum(String type,Date startDate,Date endDate);

	/**
	 * 得到日级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findDaySum(String type,Date startDate,Date endDate);

	/**
	 * 得到月级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findMonthSum(String type,Date startDate,Date endDate);

	/**
	 * 得到年级的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findYearSum(String type,Date startDate,Date endDate);
}