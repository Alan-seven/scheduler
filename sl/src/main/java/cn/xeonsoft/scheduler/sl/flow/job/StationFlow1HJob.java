package cn.xeonsoft.scheduler.sl.flow.job;

import cn.xeonsoft.scheduler.sl.flow.FlowConstant;
import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.service.FlowRtService;
import cn.xeonsoft.scheduler.sl.flow.service.FlowSumService;
import cn.xeonsoft.scheduler.sl.flow.service.StationFlowRtService;
import cn.xeonsoft.scheduler.sl.flow.service.StationFlowSumService;
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
public class StationFlow1HJob extends QuartzJobBean {
	@Autowired
	private StationFlowRtService stationFlowRtService;
	@Autowired
	private StationFlowSumService stationFlowSumService;

	@Override
	protected void executeInternal(JobExecutionContext context){
		saveSumq(DateInterval.DAY);
		saveSumq(DateInterval.MONTH);
		saveSumq(DateInterval.YEAR);
	}

	private void saveSumq(DateInterval dataInterval){
		Date beginDate = DateUtils.getBeginDate(dataInterval);
		Date endDate = DateUtils.getEndDate(dataInterval);
		List<FlowSum> flowSums = new ArrayList<>();
		switch (dataInterval){
			case DAY:
				flowSums = stationFlowRtService.findDaySum(beginDate,endDate);
				break;
			case MONTH:
				flowSums = stationFlowRtService.findMonthSum(beginDate,endDate);
				break;
			case YEAR:
				flowSums = stationFlowRtService.findYearSum(beginDate,endDate);
				break;
		}
		stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
	}

}