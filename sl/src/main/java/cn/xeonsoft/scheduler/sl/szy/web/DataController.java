package cn.xeonsoft.scheduler.sl.szy.web;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.service.DataService;
import cn.xeonsoft.scheduler.sl.szy.service.StationTmService;
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
@RequestMapping("/api/data")
public class DataController {

    @Autowired
    private DataService dataService;

    @Autowired
    private StationTmService stationTmService;

    @RequestMapping(value = "/initHour", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity initHour(@Param("tm") String tm) {
        Date dt = new Date();
        String beginDate = DateUtils.formatDateTime(DateUtils.getBeginDate(DateInterval.YEAR,DateUtils.parseDate(tm)));
        String endDate= DateUtils.formatDateTime(dt);
        List<StationTm> stationList = stationTmService.listByRiver(beginDate,endDate);


        //保存2条河流纳污能力计算结果
        dataService.saveRiverResult(stationList);


        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
