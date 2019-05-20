package cn.xeonsoft.scheduler.erhai.run.web;

import cn.xeonsoft.scheduler.erhai.run.service.StatisDistDrService;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.service.PptnRtService;
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

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/erhaiRun")
public class ErhaiRunController {

	@Autowired
	private PptnRtService pptnRtService;

	@Autowired
	private StatisDistDrService statisDistDrService;

	@RequestMapping(value = "/initHour", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initHour(@Param("tm") String tm) {
		Date date = DateUtils.parseDate(tm);
		this.initHour(date);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initDay(@Param("tm") String tm) {
		Date date = DateUtils.parseDate(tm);
		this.initDay(date);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initMonth(@Param("tm") String tm) {
		Date date = DateUtils.parseDate(tm);
		this.initMonth(date);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initAllDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initAllDay() {
		Date start = DateUtils.parseDate("2018-12-01 10:00:00");
		Date end = DateUtils.parseDate(DateUtils.getDate());

		double distance = DateUtils.getDistanceOfTwoDate(start, end);
		for (int i = 0; i < distance; i++) {
			Date tm = DateUtils.addDays(start, i);
			initDay(tm);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initAllMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initAllMonth() {
		Date start = DateUtils.parseDate("2018-12-01 10:00:00");
		Date end = DateUtils.parseDate(DateUtils.getDate());

		int distance = DateUtils.getDistanceMonthOfTwoDate(end, start);
		for (int i = 0; i < distance; i++) {
			Date tm = DateUtils.addMonths(start, i);
			this.initMonth(tm);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	private void initHour(Date tm) {
		// 日累计雨量
		List<Accp> hourAccpList = pptnRtService.findDistDrAccpByHour(DateInterval.DAY,tm);
		statisDistDrService.saveOrUpdateHour(hourAccpList,DateInterval.HOUR.getType()+"");
	}

	private void initDay(Date tm) {
		// 日累计雨量
		List<Accp> dayAccpList = pptnRtService.findPeriodAccp(DateInterval.DAY,tm);
		Date dayTm = DateUtils.get8hBeginDate(DateInterval.DAY,tm);
		statisDistDrService.saveOrUpdate(dayAccpList,DateInterval.DAY.getType()+"",dayTm);
	}

	private void initMonth(Date tm) {
		// 日累计雨量
		List<Accp> monthAccpList = pptnRtService.findPeriodAccp(DateInterval.MONTH,tm);
		Date dayTm = DateUtils.get8hBeginDate(DateInterval.MONTH,tm);
		statisDistDrService.saveOrUpdate(monthAccpList,DateInterval.MONTH.getType()+"",dayTm);
	}
}