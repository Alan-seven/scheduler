package cn.xeonsoft.scheduler.sl.flow.job;

import cn.xeonsoft.scheduler.sl.flow.FlowConstant;
import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.service.FlowRtService;
import cn.xeonsoft.scheduler.sl.flow.service.FlowSumService;
import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.service.RiverDayService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthAvgService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthExtremeService;
import cn.xeonsoft.scheduler.sl.water.service.RiverRtService;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 
 * @author wantwantxu
 *
 */
public class Flow1HJob extends QuartzJobBean {
	@Autowired
	private FlowRtService flowRtService;
	@Autowired
	private FlowSumService flowSumService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		saveSumq(DateInterval.DAY,DateInterval.MINUTE,FlowConstant.TYPE_INSTATION);
		saveSumq(DateInterval.DAY,DateInterval.HOUR,FlowConstant.TYPE_INSTATION);
		saveSumq(DateInterval.DAY,DateInterval.DAY,FlowConstant.TYPE_INSTATION);
		saveSumq(DateInterval.MONTH,DateInterval.MONTH,FlowConstant.TYPE_INSTATION);
		saveSumq(DateInterval.THREEDAYS,DateInterval.THREEDAYS,FlowConstant.TYPE_INSTATION);
		saveSumq(DateInterval.FIVEDAYS,DateInterval.FIVEDAYS,FlowConstant.TYPE_INSTATION);
		saveSumq(DateInterval.TENDAYS,DateInterval.TENDAYS,FlowConstant.TYPE_INSTATION);
		saveSumq(DateInterval.YEAR,DateInterval.YEAR,FlowConstant.TYPE_INSTATION);
	}

	private void saveSumq(DateInterval dataInterval,DateInterval dataInterval2,String type){
		Date beginDate = DateUtils.getBeginDate(dataInterval);
		Date endDate = DateUtils.getEndDate(dataInterval);
		List<FlowSum> inFlowSums = new ArrayList<>();
		switch (dataInterval2){
			case MINUTE:
				inFlowSums = flowRtService.findMinuteSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				break;
			case HOUR:
				inFlowSums = flowRtService.findHourSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				break;
			case DAY:
				inFlowSums = flowRtService.findDaySum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				break;
			case MONTH:
				inFlowSums = flowRtService.findMonthSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				break;
			case YEAR:
				inFlowSums = flowRtService.findYearSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				break;
		}
		flowSumService.saveSumq(inFlowSums,type,dataInterval2.getType()+"");
	}

}