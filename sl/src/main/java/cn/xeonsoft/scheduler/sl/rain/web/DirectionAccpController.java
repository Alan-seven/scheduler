package cn.xeonsoft.scheduler.sl.rain.web;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.service.FlowRtService;
import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.service.*;
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

import java.util.Date;
import java.util.List;


@RestController
@RequestMapping("/api/directionAcpp")
public class DirectionAccpController {
	@Autowired
	private PptnRtService pptnRtService;
	@Autowired
	private DirectionAccpService directionAccpService;


	@RequestMapping(value = "/initHour", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initHour(@Param("tm") String tm) {
		Date date = DateUtils.parseDate(tm);
		Date beginDate = DateUtils.get8hBeginDate(DateInterval.DAY,date);
		Date endDate = DateUtils.get8hEndDate(DateInterval.DAY,date);
		List<Accp> list = pptnRtService.findHourSumByGP("1",beginDate,endDate);
		List<Accp> list2 = pptnRtService.findHourSumByGP("2",beginDate,endDate);
		List<Accp> list3 = pptnRtService.findHourSumByGP("3",beginDate,endDate);
		List<Accp> list4 = pptnRtService.findHourSumByGP("4",beginDate,endDate);
		directionAccpService.save(list,"1",DateInterval.HOUR.getType()+"");
		directionAccpService.save(list2,"2",DateInterval.HOUR.getType()+"");
		directionAccpService.save(list3,"3",DateInterval.HOUR.getType()+"");
		directionAccpService.save(list4,"4",DateInterval.HOUR.getType()+"");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initDay(@Param("tm") String tm) {
		Date date = DateUtils.parseDate(tm);
		Date beginDate = DateUtils.get8hBeginDate(DateInterval.MONTH,date);
		Date endDate = DateUtils.get8hEndDate(DateInterval.MONTH,date);
		List<Accp> list = pptnRtService.findMonthSumByGP("1",beginDate,endDate);
		List<Accp> list2 = pptnRtService.findMonthSumByGP("2",beginDate,endDate);
		List<Accp> list3 = pptnRtService.findMonthSumByGP("3",beginDate,endDate);
		List<Accp> list4 = pptnRtService.findMonthSumByGP("4",beginDate,endDate);
		directionAccpService.save(list,"1",DateInterval.DAY.getType()+"");
		directionAccpService.save(list2,"2",DateInterval.DAY.getType()+"");
		directionAccpService.save(list3,"3",DateInterval.DAY.getType()+"");
		directionAccpService.save(list4,"4",DateInterval.DAY.getType()+"");
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}
}