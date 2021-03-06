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
	 * 得到任意时间段的合计
	 * @param type
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findSum(String type,Date startDate,Date endDate);

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



	/**
	 * 得到分钟级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findMinuteSumByGP(String gp,Date startDate,Date endDate);

	/**
	 * 得到小时级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findHourSumByGP(String gp,Date startDate,Date endDate);

	/**
	 * 得到日级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findDaySumByGP(String gp,Date startDate,Date endDate);

	/**
	 * 得到任意时间段的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findSumByGP(String gp,Date startDate,Date endDate);

	/**
	 * 得到月级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findMonthSumByGP(String gp,Date startDate,Date endDate);

	/**
	 * 得到年级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	List<FlowSum> findYearSumByGP(String gp,Date startDate,Date endDate);
}