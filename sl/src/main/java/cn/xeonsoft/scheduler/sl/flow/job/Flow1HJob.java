package cn.xeonsoft.scheduler.sl.flow.job;

import cn.xeonsoft.scheduler.sl.flow.FlowConstant;
import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.service.DirectionFlowSumService;
import cn.xeonsoft.scheduler.sl.flow.service.FlowRtService;
import cn.xeonsoft.scheduler.sl.flow.service.FlowSumService;
import cn.xeonsoft.scheduler.sl.flow.service.StationFlowRtService;
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
	@Autowired
	private DirectionFlowSumService directionFlowSumService;
	@Autowired
	private StationFlowRtService stationFlowRtService;
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

		saveOutSumq(DateInterval.YEAR,DateInterval.YEAR,FlowConstant.TYPE_OUTSTATION);

		saveDirectionSumq(DateInterval.DAY,"1");
		saveDirectionSumq(DateInterval.DAY,"2");
		saveDirectionSumq(DateInterval.DAY,"3");
		saveDirectionSumq(DateInterval.DAY,"4");
		saveDirectionSumq(DateInterval.MONTH,"1");
		saveDirectionSumq(DateInterval.MONTH,"2");
		saveDirectionSumq(DateInterval.MONTH,"3");
		saveDirectionSumq(DateInterval.MONTH,"4");
		saveDirectionSumq(DateInterval.THREEDAYS,"1");
		saveDirectionSumq(DateInterval.THREEDAYS,"2");
		saveDirectionSumq(DateInterval.THREEDAYS,"3");
		saveDirectionSumq(DateInterval.THREEDAYS,"4");
		saveDirectionSumq(DateInterval.FIVEDAYS,"1");
		saveDirectionSumq(DateInterval.FIVEDAYS,"2");
		saveDirectionSumq(DateInterval.FIVEDAYS,"3");
		saveDirectionSumq(DateInterval.FIVEDAYS,"4");
		saveDirectionSumq(DateInterval.TENDAYS,"1");
		saveDirectionSumq(DateInterval.TENDAYS,"2");
		saveDirectionSumq(DateInterval.TENDAYS,"3");
		saveDirectionSumq(DateInterval.TENDAYS,"4");
		saveDirectionSumq(DateInterval.YEAR,"1");
		saveDirectionSumq(DateInterval.YEAR,"2");
		saveDirectionSumq(DateInterval.YEAR,"3");
		saveDirectionSumq(DateInterval.YEAR,"4");
	}

	private void saveSumq(DateInterval dataInterval,DateInterval dataInterval2,String type){
		Date beginDate = DateUtils.getBeginDate(dataInterval);
		Date endDate = DateUtils.getEndDate(dataInterval);
		List<FlowSum> inFlowSums = new ArrayList<>();
		switch (dataInterval2){
			case MINUTE:
				inFlowSums = flowRtService.findMinuteSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				flowSumService.saveSumq(inFlowSums,type,dataInterval2.getType()+"");
				break;
			case HOUR:
				inFlowSums = flowRtService.findHourSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				flowSumService.saveSumq(inFlowSums,type,dataInterval2.getType()+"");
				break;
			case DAY:
				inFlowSums = flowRtService.findDaySum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				flowSumService.saveSumq(inFlowSums,type,dataInterval2.getType()+"");
				break;
			case FIVEDAYS:
				inFlowSums = flowRtService.findSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				for(FlowSum flowSum:inFlowSums){
					if(null==flowSum){
						continue;
					}
					Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
					flowSumService.saveSumq(DateUtils.formatDateTime(tm),flowSum.getSumq(),type,DateInterval.FIVEDAYS.getType()+"");
				}
				break;
			case TENDAYS:
				inFlowSums = flowRtService.findSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				for(FlowSum flowSum:inFlowSums){
					if(null==flowSum){
						continue;
					}
					Date tm = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
					flowSumService.saveSumq(DateUtils.formatDateTime(tm),flowSum.getSumq(),type,DateInterval.TENDAYS.getType()+"");
				}
				break;
			case MONTH:
				inFlowSums = flowRtService.findMonthSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				flowSumService.saveSumq(inFlowSums,type,dataInterval2.getType()+"");
				break;
			case YEAR:
				inFlowSums = flowRtService.findYearSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
				flowSumService.saveSumq(inFlowSums,type,dataInterval2.getType()+"");
				break;
		}
	}

	private void saveOutSumq(DateInterval dataInterval,DateInterval dataInterval2,String type){
		Date beginDate = DateUtils.getBeginDate(dataInterval);
		Date endDate = DateUtils.getEndDate(dataInterval);
		List<FlowSum> inFlowSums = new ArrayList<>();
		List<FlowSum> xierheList = new ArrayList<>();
		List<FlowSum> yerbList = new ArrayList<>();
		float w = 0f;
		FlowSum flow = new FlowSum();
		switch (dataInterval2){
			case YEAR:
				xierheList = stationFlowRtService.findXierheYearSum(beginDate,endDate);
				yerbList = stationFlowRtService.findYerbSum("90210531", dataInterval2.getType()+"",beginDate);
				if(xierheList.size()>0){
					w +=xierheList.get(0).getSumq()/10000;
				}
				if(yerbList.size()>0){
					w +=yerbList.get(0).getSumq();
				}
				flow.setSumq(w);
				flow.setTm(DateUtils.formatDateTime(beginDate));
				//inFlowSums = flowRtService.find(FlowConstant.TYPE_OUTSTATION,beginDate,endDate);
				inFlowSums.add(flow);
				flowSumService.saveSumq(inFlowSums,type,dataInterval2.getType()+"");
				break;
		}
	}

	private void saveDirectionSumq(DateInterval dataInterval,String gp){
		Date beginDate = DateUtils.getBeginDate(dataInterval);
		Date endDate = DateUtils.getEndDate(dataInterval);
		List<FlowSum> flowSums = new ArrayList<>();
		switch (dataInterval){
			case HOUR:
				flowSums = flowRtService.findHourSumByGP(gp,beginDate,endDate);
				directionFlowSumService.saveSumq(flowSums,gp,dataInterval.getType()+"");
				break;
			case DAY:
				flowSums = flowRtService.findDaySumByGP(gp,beginDate,endDate);
				directionFlowSumService.saveSumq(flowSums,gp,dataInterval.getType()+"");
				break;
			case FIVEDAYS:
				flowSums = flowRtService.findSumByGP(gp,beginDate,endDate);
				Date tm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
				for(FlowSum flowSum:flowSums){

					if(null==flowSum){
						continue;
					}
					directionFlowSumService.saveSumq(gp,tm,flowSum.getSumq(),DateInterval.FIVEDAYS.getType()+"");
				}
				break;
			case TENDAYS:
				flowSums = flowRtService.findSumByGP(gp,beginDate,endDate);
				Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
				for(FlowSum flowSum:flowSums){
					if(null==flowSum){
						continue;
					}
					directionFlowSumService.saveSumq(gp,tm2,flowSum.getSumq(),DateInterval.TENDAYS.getType()+"");
				}
				break;
			case MONTH:
				flowSums = flowRtService.findMonthSumByGP(gp,beginDate,endDate);
				directionFlowSumService.saveSumq(flowSums,gp,dataInterval.getType()+"");
				break;
			case YEAR:
				flowSums = flowRtService.findYearSumByGP(gp,beginDate,endDate);
				directionFlowSumService.saveSumq(flowSums,gp,dataInterval.getType()+"");
				break;
		}
	}
}