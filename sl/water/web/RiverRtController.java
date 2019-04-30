package cn.xeonsoft.scheduler.sl.water.web;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xeonsoft.scheduler.sl.water.service.RiverDayService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthAvgService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthExtremeService;
import cn.xeonsoft.scheduler.sl.water.service.RiverRtService;
import cn.xeonsoft.scheduler.utils.DateUtils;

@RestController
@RequestMapping("/api/riverRt")
public class RiverRtController {
	@Autowired
	private RiverRtService riverRtService;
	@Autowired
	private RiverDayService riverDayService;
	@Autowired
	private RiverMonthAvgService riverMonthAvgService;
	@Autowired
	private RiverMonthExtremeService riverMonthExtremeService;

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
			initDay(tm);
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
		// 日平均水位
		List<Extreme> avgzList = riverRtService.findAvgz(tm);
		List<Extreme> ehzList = riverRtService.findEhz(tm);
		List<Extreme> maxzAndTmList = riverRtService.findHtzAndTm(tm);
		List<Extreme> minzAndTmList = riverRtService.findLtzAndTm(tm);

		riverDayService.saveOrUpdateLtzAndTm(tm,minzAndTmList);
		riverDayService.saveOrUpdateHtzAndTm(tm,maxzAndTmList);
		riverDayService.saveOrUpdateAvgz(tm,avgzList);
		riverDayService.saveOrUpdateEhz(tm,ehzList);
	}

	private void initMonth(Date tm) {
		// 月平均水位
		List<Extreme> avgzOfMonthList = riverRtService.findAvgzByMonth(tm);
		riverMonthAvgService.saveOrUpdate(tm, avgzOfMonthList);
		// 保存最大、最小水位及发生时间
		List<Extreme> maxzAndTmList = riverRtService.findHtzAndTmByMonth(tm);
		riverMonthExtremeService.saveOrUpdateMaxzAndTm(tm, maxzAndTmList);
		List<Extreme> minzAndTmList = riverRtService.findLtzAndTmByMonth(tm);
		riverMonthExtremeService.saveOrUpdateMinzAndTm(tm, minzAndTmList);
	}
}