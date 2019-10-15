package cn.xeonsoft.scheduler.sl.flow.web;

import cn.xeonsoft.scheduler.sl.flow.service.StationFlowRtService;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Date;

@Controller
@RequestMapping("/api/flow")
public class FowModifyController {

    @Autowired
    private StationFlowRtService stationFlowRtService;


    /**
     * TODO 修改河道水情表对应的测站流量
     * @param
     * @return
     */
    @RequestMapping(value = "/updateQ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateQ(@Param("stcd") String stcd, @Param("tm") String tm, @Param("q") Float q) {
        Date dt = DateUtils.parseDate(tm);
        stationFlowRtService.updateQ(stcd,dt,q);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }

    /**
     * TODO 修改河道水情表对应的测站水位
     * @param
     * @return
     */
    @RequestMapping(value = "/updateZ", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity updateZ(@Param("stcd") String stcd, @RequestParam("tm") String tm, @Param("z") Float z) {
        Date dt = DateUtils.parseDate(tm);
        stationFlowRtService.updateZ(stcd,dt,z);
        return new ResponseEntity<>("OK", HttpStatus.OK);
    }
}
