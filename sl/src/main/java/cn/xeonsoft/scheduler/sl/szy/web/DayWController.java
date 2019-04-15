package cn.xeonsoft.scheduler.sl.szy.web;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.DayWService;
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
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api/dayw")
public class DayWController {

    @Autowired
    private DayWService dayWService;

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

    @RequestMapping(value = "/initYear", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity initYear(@Param("tm") String tm) {
        save(DateInterval.YEAR,tm);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    private void save(DateInterval dateInterval,DateInterval dateInterval2,String tm){
        Date beginDate = DateUtils.getBeginDate(dateInterval,DateUtils.parseDate(tm));
        Date endDate = DateUtils.getEndDate(dateInterval,DateUtils.parseDate(tm));
        List<DayW> dayWList = new ArrayList<>();
        switch(dateInterval){
            case DAY:
                dayWList = dayWService.findDaySum(beginDate,endDate);
                dayWService.batchSave(dayWList,dateInterval2.getType()+"");
                break;
            case FIVEDAYS:
                dayWList = dayWService.findSum(beginDate,endDate);
                for(DayW dayw:dayWList){
                    if(null==dayw){
                        continue;
                    }
                    DateUtils.formatDateTime(beginDate);
                    dayWService.saveRecord(dayw,DateInterval.FIVEDAYS.getType()+"");
                }
                break;
            case TENDAYS:
                dayWList = dayWService.findSum(beginDate,endDate);
                for(DayW dayw:dayWList){
                    if(null==dayw){
                        continue;
                    }
                    DateUtils.formatDateTime(beginDate);
                    dayWService.saveRecord(dayw,DateInterval.TENDAYS.getType()+"");
                }
                break;
            case MONTH:
                dayWList = dayWService.findMonthSum(beginDate,endDate);
                dayWService.batchSave(dayWList,dateInterval2.getType()+"");
                break;
            case YEAR:
                dayWList = dayWService.findYearSum(beginDate,endDate);
                dayWService.batchSave(dayWList,dateInterval2.getType()+"");
                break;
        }

    }

    private void save(DateInterval dateInterval,String tm){
        save(dateInterval,dateInterval,tm);
    }

}
