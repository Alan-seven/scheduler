package cn.xeonsoft.scheduler.sl.flow.web;

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
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/flowSum")
public class FlowSumController {

	@Autowired
	private FlowRtService flowRtService;
	@Autowired
	private FlowSumService flowSumService;

	@RequestMapping(value = "/initMinute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initMinute(@Param("tm") String tm) {
		save(DateInterval.DAY,DateInterval.MINUTE,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initHour", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initHour(@Param("tm") String tm) {
		//String startDate = "2018-11-01 00:00:00";
		//Date sd = DateUtils.parseDate(startDate);
		//Date ed = new Date();
		Date startDate = DateUtils.getBeginDate(DateInterval.HOUR,DateUtils.parseDate(tm));
		Date ed = DateUtils.getEndDate(DateInterval.HOUR,DateUtils.parseDate(tm));
		List<FlowSum> inFlowSums = flowRtService.findHourSum(FlowConstant.TYPE_INSTATION,startDate,ed);
		flowSumService.saveSumq(inFlowSums,FlowConstant.TYPE_INSTATION,DateInterval.HOUR.getType()+"");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initDay(@Param("tm") String tm) {
		save(DateInterval.DAY,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initMonth(@Param("tm") String tm) {
		save(DateInterval.MONTH,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	/**
	 * TODO 要改
	 * @param tm
	 * @return
	 */
	@RequestMapping(value = "/init3Days", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity init3Days(@Param("tm") String tm) {
		save(DateInterval.THREEDAYS,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	/**
	 * TODO 要改
	 * @param tm
	 * @return
	 */
	@RequestMapping(value = "/init5Days", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity init5Days(@Param("tm") String tm) {
		save(DateInterval.FIVEDAYS,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	/**
	 * TODO 要改
	 * @param tm
	 * @return
	 */
	@RequestMapping(value = "/init10Days", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity init10Days(@Param("tm") String tm) {
		save(DateInterval.TENDAYS,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initYear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initYear(@Param("tm") String tm) {
		save(DateInterval.YEAR,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}


	private void save(DateInterval dateInterval,DateInterval dateInterval2,String tm){
		Date beginDate = DateUtils.getBeginDate(dateInterval,DateUtils.parseDate(tm));
		Date endDate = DateUtils.getEndDate(dateInterval,DateUtils.parseDate(tm));
		List<FlowSum> inFlowSums = new ArrayList<>();
		List<FlowSum> outFlowSums = new ArrayList<>();
		if(dateInterval2.equals(DateInterval.DAY)){
			inFlowSums = flowRtService.findDaySum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
			outFlowSums = flowRtService.findDaySum(FlowConstant.TYPE_OUTSTATION,beginDate,endDate);
			flowSumService.saveSumq(inFlowSums,FlowConstant.TYPE_INSTATION,dateInterval2.getType()+"");
			flowSumService.saveSumq(outFlowSums,FlowConstant.TYPE_OUTSTATION,dateInterval2.getType()+"");
		}else if(dateInterval2.equals(DateInterval.FIVEDAYS)){
			inFlowSums = flowRtService.findSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
			outFlowSums = flowRtService.findSum(FlowConstant.TYPE_OUTSTATION,beginDate,endDate);
			for(FlowSum flowSum:inFlowSums){
				if(null==flowSum){
					continue;
				}
				flowSumService.saveSumq(DateUtils.formatDateTime(beginDate),flowSum.getSumq(),FlowConstant.TYPE_INSTATION,DateInterval.FIVEDAYS.getType()+"");
			}
			for(FlowSum flowSum:outFlowSums){
				if(null==flowSum){
					continue;
				}
				flowSumService.saveSumq(DateUtils.formatDateTime(beginDate),flowSum.getSumq(),FlowConstant.TYPE_OUTSTATION,DateInterval.FIVEDAYS.getType()+"");
			}
		}else if(dateInterval2.equals(DateInterval.TENDAYS)){
			inFlowSums = flowRtService.findSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
			outFlowSums = flowRtService.findSum(FlowConstant.TYPE_OUTSTATION,beginDate,endDate);
			for(FlowSum flowSum:inFlowSums){
				flowSumService.saveSumq(DateUtils.formatDateTime(beginDate),flowSum.getSumq(),FlowConstant.TYPE_INSTATION,DateInterval.TENDAYS.getType()+"");
			}
			for(FlowSum flowSum:outFlowSums){
				if(null==flowSum){
					continue;
				}
				flowSumService.saveSumq(DateUtils.formatDateTime(beginDate),flowSum.getSumq(),FlowConstant.TYPE_OUTSTATION,DateInterval.TENDAYS.getType()+"");
			}
		}else if(dateInterval2.equals(DateInterval.MONTH)){
			inFlowSums = flowRtService.findMonthSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
			outFlowSums = flowRtService.findMonthSum(FlowConstant.TYPE_OUTSTATION,beginDate,endDate);
			flowSumService.saveSumq(inFlowSums,FlowConstant.TYPE_INSTATION,dateInterval2.getType()+"");
			flowSumService.saveSumq(outFlowSums,FlowConstant.TYPE_OUTSTATION,dateInterval2.getType()+"");
		}else if(dateInterval2.equals(DateInterval.YEAR)){
			inFlowSums = flowRtService.findYearSum(FlowConstant.TYPE_INSTATION,beginDate,endDate);
			outFlowSums = flowRtService.findYearSum(FlowConstant.TYPE_OUTSTATION,beginDate,endDate);
			flowSumService.saveSumq(inFlowSums,FlowConstant.TYPE_INSTATION,dateInterval2.getType()+"");
			flowSumService.saveSumq(outFlowSums,FlowConstant.TYPE_OUTSTATION,dateInterval2.getType()+"");
		}
	}

	private void save(DateInterval dateInterval,String tm){
		save(dateInterval,dateInterval,tm);
	}
}