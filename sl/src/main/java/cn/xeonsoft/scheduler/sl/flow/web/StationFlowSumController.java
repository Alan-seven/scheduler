package cn.xeonsoft.scheduler.sl.flow.web;

import cn.xeonsoft.scheduler.sl.flow.FlowConstant;
import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.service.FlowRtService;
import cn.xeonsoft.scheduler.sl.flow.service.FlowSumService;
import cn.xeonsoft.scheduler.sl.flow.service.StationFlowRtService;
import cn.xeonsoft.scheduler.sl.flow.service.StationFlowSumService;
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
@RequestMapping("/api/station/flowSum")
public class StationFlowSumController {

	@Autowired
	private StationFlowRtService stationFlowRtService;
	@Autowired
	private StationFlowSumService stationFlowSumService;

	@RequestMapping(value = "/initMinute", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initMinute(@Param("tm") String tm) {
		save(DateInterval.DAY,DateInterval.MINUTE,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initHour", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initHour(@Param("tm") String tm) {
		save(DateInterval.HOUR,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initDay(@Param("tm") String tm) {
		save(DateInterval.DAY,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initFiveDays", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initFiveDays(@Param("tm") String tm) {
		save(DateInterval.FIVEDAYS,tm);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initTenDays", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initTenDays(@Param("tm") String tm) {
		save(DateInterval.TENDAYS,tm);
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
		switch(dateInterval){
			case HOUR:
				inFlowSums = stationFlowRtService.findHourSum(beginDate,endDate);
				outFlowSums = stationFlowRtService.findXierheHourSum(beginDate,endDate);
				stationFlowSumService.saveSumq(inFlowSums,dateInterval2.getType()+"","1");
				stationFlowSumService.saveSumq(outFlowSums,dateInterval2.getType()+"","2");
				break;
			case DAY:
				inFlowSums = stationFlowRtService.findDaySum(beginDate,endDate);
				outFlowSums = stationFlowRtService.findXierheDaySum(beginDate,endDate);
				stationFlowSumService.saveSumq(inFlowSums,dateInterval2.getType()+"","1");
				stationFlowSumService.saveSumq(outFlowSums,dateInterval2.getType()+"","2");
				break;
			case FIVEDAYS:
				inFlowSums = stationFlowRtService.findSum(beginDate,endDate);
				outFlowSums = stationFlowRtService.findXierheDaySum(beginDate,endDate);
				for(FlowSum flowSum:inFlowSums){
					if(null==flowSum||("90210530".equals(flowSum.getStcd()))){
						continue;
					}
					stationFlowSumService.saveSumq(flowSum.getStcd(),beginDate,flowSum.getSumq(),DateInterval.FIVEDAYS.getType()+"");
				}
				for(FlowSum flowSum:outFlowSums){
					if(null==flowSum ){
						continue;
					}
					stationFlowSumService.saveSumq(flowSum.getStcd(),beginDate,flowSum.getSumq(),DateInterval.FIVEDAYS.getType()+"");
				}
				break;
			case TENDAYS:
				inFlowSums = stationFlowRtService.findSum(beginDate,endDate);
				outFlowSums = stationFlowRtService.findXierheDaySum(beginDate,endDate);
				for(FlowSum flowSum:inFlowSums){
					if(null==flowSum||("90210530".equals(flowSum.getStcd()))){
						continue;
					}
					stationFlowSumService.saveSumq(flowSum.getStcd(),beginDate,flowSum.getSumq(),DateInterval.TENDAYS.getType()+"");
				}
				for(FlowSum flowSum:outFlowSums){
					if(null==flowSum){
						continue;
					}
					stationFlowSumService.saveSumq(flowSum.getStcd(),beginDate,flowSum.getSumq(),DateInterval.TENDAYS.getType()+"");
				}
				break;
			case MONTH:
				inFlowSums = stationFlowRtService.findMonthSum(beginDate,endDate);
				outFlowSums = stationFlowRtService.findXierheDaySum(beginDate,endDate);
				stationFlowSumService.saveSumq(inFlowSums,dateInterval2.getType()+"","1");
				stationFlowSumService.saveSumq(outFlowSums,dateInterval2.getType()+"","2");
				break;
			case YEAR:
				inFlowSums = stationFlowRtService.findYearSum(beginDate,endDate);
				outFlowSums = stationFlowRtService.findXierheDaySum(beginDate,endDate);
				stationFlowSumService.saveSumq(inFlowSums,dateInterval2.getType()+"","1");
				stationFlowSumService.saveSumq(outFlowSums,dateInterval2.getType()+"","2");
				break;
		}

	}

	private void save(DateInterval dateInterval,String tm){
		save(dateInterval,dateInterval,tm);
	}


}