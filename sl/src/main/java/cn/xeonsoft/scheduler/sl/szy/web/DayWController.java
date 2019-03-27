package cn.xeonsoft.scheduler.sl.szy.web;

import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.szy.bo.DayW;
import cn.xeonsoft.scheduler.sl.szy.service.DayWService;
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
@RequestMapping("/api/dayw")
public class DayWController {

    @Autowired
    private DayWService dayWService;

    /**
     * 流量转换为水量暂时未实现
     * @param tm
     * @return
     */
    @RequestMapping(value = "/initDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity initDay( @Param("tm") String tm) {
        Date date = DateUtils.parseDate(tm);
        /*List<DayW> dayAccpList = dayWService.(date);
        dayWService.saveOrUpdateHour(dayAccpList);*/
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

}
