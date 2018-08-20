package cn.xeonsoft.scheduler.sl.web;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.service.RiverDayService;
import cn.xeonsoft.scheduler.sl.service.RiverMonthAvgService;
import cn.xeonsoft.scheduler.sl.service.RiverMonthExtremeService;
import cn.xeonsoft.scheduler.sl.service.RiverRtService;
import cn.xeonsoft.scheduler.sl.utils.DateUtils;

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
		List<ZQExtremum> avgzList = riverRtService.findAvgz(tm);
		riverDayService.saveOrUpdateAvz(tm, avgzList);
	}

	private void initMonth(Date tm) {
		// 月平均水位
		List<ZQExtremum> avgzOfMonthList = riverRtService.findAvgzByMonth(tm);
		riverMonthAvgService.saveOrUpdate(tm, avgzOfMonthList);
		// 保存最大、最小水位及发生时间
		List<ZQExtremum> maxzAndTmList = riverRtService.findMaxzAndTmByMonth(tm);
		riverMonthExtremeService.saveOrUpdateMaxzAndTm(tm, maxzAndTmList);
		List<ZQExtremum> minzAndTmList = riverRtService.findMinzAndTmByMonth(tm);
		riverMonthExtremeService.saveOrUpdateMinzAndTm(tm, minzAndTmList);
	}
}