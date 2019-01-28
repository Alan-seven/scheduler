package cn.xeonsoft.scheduler.erhai.run.web;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.service.RiverDayService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthAvgService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthExtremeService;
import cn.xeonsoft.scheduler.sl.water.service.RiverRtService;
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
	@RequestMapping(value = "/initDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initDay(@Param("tm") String tm) {
		Date date = DateUtils.parseDate(tm);
		//this.initDay(date);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initMonth", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initMonth(@Param("tm") String tm) {
		Date date = DateUtils.parseDate(tm);
		//this.initMonth(date);
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

	@RequestMapping(value = "/initAllDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity initAllDay() {
		Date start = DateUtils.parseDate("2017-01-01");
		Date end = DateUtils.parseDate(DateUtils.getDate());

		double distance = DateUtils.getDistanceOfTwoDate(start, end);
		for (int i = 0; i < distance; i++) {
			Date tm = DateUtils.addDays(start, i);
			//initDay(tm);
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
			//this.initMonth(tm);
		}
		return new ResponseEntity<>("OK", HttpStatus.OK);
	}

}