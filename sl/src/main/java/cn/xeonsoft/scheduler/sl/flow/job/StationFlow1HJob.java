package cn.xeonsoft.scheduler.sl.flow.job;

import cn.xeonsoft.scheduler.sl.flow.FlowConstant;
import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.respository.DirectionFlowSumRepository;
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
	@Autowired
	private DirectionFlowSumRepository directionFlowSumRepository;

	@Override
	protected void executeInternal(JobExecutionContext context){
		saveSumq(DateInterval.HOUR);
		saveSumq(DateInterval.DAY);
		saveSumq(DateInterval.FIVEDAYS);
		saveSumq(DateInterval.TENDAYS);
		saveSumq(DateInterval.MONTH);
		saveSumq(DateInterval.YEAR);

		//计算西洱河水量
		saveXierheSumq(DateInterval.HOUR);
		saveXierheSumq(DateInterval.DAY);
		saveXierheSumq(DateInterval.FIVEDAYS);
		saveXierheSumq(DateInterval.TENDAYS);
		saveXierheSumq(DateInterval.MONTH);
		saveXierheSumq(DateInterval.YEAR);
	}

	private void saveSumq(DateInterval dataInterval){
		Date beginDate = DateUtils.getBeginDate(dataInterval);
		Date endDate = DateUtils.getEndDate(dataInterval);
		List<FlowSum> flowSums = new ArrayList<>();
		switch (dataInterval){
			case HOUR:
				flowSums = stationFlowRtService.findHourSum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
			case DAY:
				flowSums = stationFlowRtService.findDaySum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
			case FIVEDAYS:
				flowSums = stationFlowRtService.findSum(beginDate,endDate);
				Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
				for(FlowSum flowSum:flowSums){
					stationFlowSumService.saveSumq(flowSum.getStcd(),tm,flowSum.getSumq(),DateInterval.FIVEDAYS.getType()+"");
				}
				break;
			case TENDAYS:
				flowSums = stationFlowRtService.findSum(beginDate,endDate);
				Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
				for(FlowSum flowSum:flowSums){
					stationFlowSumService.saveSumq(flowSum.getStcd(),tm2,flowSum.getSumq(),DateInterval.TENDAYS.getType()+"");
				}
				break;
			case MONTH:
				flowSums = stationFlowRtService.findMonthSum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
			case YEAR:
				flowSums = stationFlowRtService.findYearSum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
		}
	}

	/**
	 * 因为西洱河的水量数据保存在statis_station_sumq表中，流量为瞬时流量，不能用来计算水量
	 * @param dataInterval
	 */
	private void saveXierheSumq(DateInterval dataInterval){
		Date beginDate = DateUtils.getBeginDate(dataInterval);
		Date endDate = DateUtils.getEndDate(dataInterval);
		List<FlowSum> flowSums = new ArrayList<>();
		switch (dataInterval){
			case HOUR:
				flowSums = stationFlowRtService.findXierheHourSum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
			case DAY:
				flowSums = stationFlowRtService.findXierheDaySum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
			case FIVEDAYS:
				flowSums = stationFlowRtService.findXierheSum(beginDate,endDate);
				Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
				for(FlowSum flowSum:flowSums){
					stationFlowSumService.saveSumq(flowSum.getStcd(),tm,flowSum.getSumq(),DateInterval.FIVEDAYS.getType()+"");
				}
				break;
			case TENDAYS:
				flowSums = stationFlowRtService.findXierheSum(beginDate,endDate);
				Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
				for(FlowSum flowSum:flowSums){
					stationFlowSumService.saveSumq(flowSum.getStcd(),tm2,flowSum.getSumq(),DateInterval.TENDAYS.getType()+"");
				}
				break;
			case MONTH:
				flowSums = stationFlowRtService.findXierheMonthSum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
			case YEAR:
				flowSums = stationFlowRtService.findXierheYearSum(beginDate,endDate);
				stationFlowSumService.saveSumq(flowSums,dataInterval.getType()+"");
				break;
		}
	}
}