package cn.xeonsoft.scheduler.sl.szy.web;

import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.PumpSumService;
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
@RequestMapping("/api/pumpsum")
public class PumpSumController {

    @Autowired
    private PumpSumService pumpSumService;

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
            case HOUR:
                dayWList = pumpSumService.findHourSum(beginDate,endDate);
                pumpSumService.saveSumw(dayWList,dateInterval2.getType()+"");
                break;
            case DAY:
                dayWList = pumpSumService.findDaySum(beginDate,endDate);
                pumpSumService.saveSumw(dayWList,dateInterval2.getType()+"");
                break;
            case FIVEDAYS:
                dayWList = pumpSumService.findSum(beginDate,endDate);
                Date tm1 = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
                for(DayW entity:dayWList){
                    if(null==entity){
                        continue;
                    }
                    pumpSumService.saveSumw(tm1,entity.getDayW(),DateInterval.FIVEDAYS.getType()+"");
                }
                break;
            case TENDAYS:
                dayWList = pumpSumService.findSum(beginDate,endDate);
                for(DayW entity:dayWList){
                    if(null==entity){
                        continue;
                    }
                    Date tm2 = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
                    pumpSumService.saveSumw(tm2,entity.getDayW(),DateInterval.TENDAYS.getType()+"");
                }
                break;
            case MONTH:
                dayWList = pumpSumService.findMonthSum(beginDate,endDate);
                pumpSumService.saveSumw(dayWList,dateInterval2.getType()+"");
                break;
            case YEAR:
                dayWList = pumpSumService.findYearSum(beginDate,endDate);
                pumpSumService.saveSumw(dayWList,dateInterval2.getType()+"");
                break;
        }

    }

    private void save(DateInterval dateInterval,String tm){
        save(dateInterval,dateInterval,tm);
    }


}
