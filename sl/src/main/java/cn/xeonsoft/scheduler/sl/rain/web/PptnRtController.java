package cn.xeonsoft.scheduler.sl.rain.web;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.service.DypService;
import cn.xeonsoft.scheduler.sl.rain.service.PptnMonthDrpService;
import cn.xeonsoft.scheduler.sl.rain.service.PptnMonthExtremeService;
import cn.xeonsoft.scheduler.sl.rain.service.PptnRtService;
import cn.xeonsoft.scheduler.utils.DateUtils;

@RestController
@RequestMapping("/api/pptnRt")
public class PptnRtController {
	@Autowired
	private PptnRtService pptnRtService;

	@Autowired
	private DypService sumDrpService;
	@Autowired
	private PptnMonthExtremeService pptnMonthExtremeService;
	@Autowired
	private PptnMonthDrpService pptnMonthDrpService;

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
		Date start = DateUtils.parseDate("2017-01-01");
		Date end = DateUtils.parseDate(DateUtils.getDate());

		double distance = DateUtils.getDistanceOfTwoDate(start, end);
		for (int i = 0; i < distance; i++) {
			Date tm = DateUtils.addDays(start, i);
			this.initDay(tm);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initAllMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initAllMonth() {
		Date start = DateUtils.parseDate("2017-01-01");
		Date end = DateUtils.parseDate(DateUtils.getDate());

		int distance = DateUtils.getDistanceMonthOfTwoDate(end, start);
		for (int i = 0; i < distance; i++) {
			Date tm = DateUtils.addMonths(start, i);
			this.initMonth(tm);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	private void initDay(Date tm) {
		// 日累计雨量
		List<Accp> dayAccpList = pptnRtService.findAccp(tm);
		sumDrpService.saveOrUpdate(tm, Constant.STTDRCD_DAY, dayAccpList);
	}

	private void initMonth(Date tm) {
		// 月累计雨量
		List<Accp> accpList = pptnRtService.findAccpByMonth(tm);
		sumDrpService.saveOrUpdate(tm, Constant.STTDRCD_MONTH, accpList);
		pptnMonthDrpService.saveOrUpdate(tm, accpList);
		// 月最大雨量及发生时间
		List<PptnExtremum> maxzAndTmList = pptnRtService.findMaxDrpAndTmByMonth(tm);
		pptnMonthExtremeService.saveOrUpdateMaxdrpAndTm(tm, maxzAndTmList);
		// 每月的雨日
		List<RainDays> rainDayList = pptnRtService.findRainDaysByMonth(tm);
		pptnMonthExtremeService.updateRainDays(tm, rainDayList);
	}
}