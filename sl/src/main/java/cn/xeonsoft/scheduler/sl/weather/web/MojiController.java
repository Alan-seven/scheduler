package cn.xeonsoft.scheduler.sl.weather.web;

import cn.xeonsoft.scheduler.sl.rain.bo.MoJi;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.weather.service.MojiService;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import cn.xeonsoft.scheduler.utils.Md5KeyUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * 墨迹天气接口
 */
@RestController
@RequestMapping("/api/moji")
public class MojiController {

    @Autowired
    private MojiService mojiService;
    /**
     * 未来两小时的降雨
     * @param tm
     * @return
     */
    @RequestMapping(value = "/initHour", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity initHour(@Param("tm") String tm) {
        MoJi entity = mojiService.find(DateUtils.parseDate(tm),"1");
        if(null!=entity){
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        String nonce = "217";
        String suburl = "/radar/rain/full";
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(DateUtils.parseDate(tm));
        cal.add(Calendar.SECOND,-10);
        long ts = cal.getTime().getTime();
        String key = Md5KeyUtil.getKey(nonce,suburl,ts);
        String url = "http://hydrometeo.mojicb.com/radar/rain/full/json?token=a8c01907b793b49fc683fe757936a4e6&key="+key+"&timestamp="+ts+"&nonce="+nonce+"&suburl="+suburl+"&cityid=532900";
        Md5KeyUtil md5KeyUtil = new Md5KeyUtil();
        String result = md5KeyUtil.getResult(url);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        mojiService.save(cal.getTime(),"1",result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 未来七天预报数据
     * @param tm
     * @return
     */
    @RequestMapping(value = "/initDay", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity initDay(@Param("tm") String tm) {
        MoJi entity = mojiService.find(DateUtils.parseDate(tm),"2");
        if(null!=entity){
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        String nonce = "217";
        String suburl = "/radar/rain/full";
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(DateUtils.parseDate(tm));
        cal.add(Calendar.SECOND,-10);
        long ts = cal.getTime().getTime();
        String key = Md5KeyUtil.getKey(nonce,suburl,ts);
        String url = "http://hydrometeo.mojicb.com/ec/forecast/json?token=a8c01907b793b49fc683fe757936a4e6&key="+key+"&timestamp="+ts+"&nonce="+nonce+"&suburl="+suburl+"&cityid=532900&elem=RAIN";
        Md5KeyUtil md5KeyUtil = new Md5KeyUtil();
        String result = md5KeyUtil.getResult(url);
        cal.set(Calendar.MINUTE, 0);
        mojiService.save(cal.getTime(),"2",result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * 未来七天预报数据
     * @param tm
     * @return
     */
    @RequestMapping(value = "/initCldas", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity initCldas(@Param("tm") String tm) {
        MoJi entity = mojiService.find(DateUtils.parseDate(tm),"3");
        if(null!=entity){
            return new ResponseEntity<>(entity, HttpStatus.OK);
        }
        String nonce = "217";
        String suburl = "/radar/rain/full";
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.SECOND,-10);
        long ts = cal.getTime().getTime();
        String key = Md5KeyUtil.getKey(nonce,suburl,ts);
        String url = "http://hydrometeo.mojicb.com/cldas/real/json?token=a8c01907b793b49fc683fe757936a4e6&key="+key+"&timestamp="+ts+"&nonce="+nonce+"&suburl="+suburl+"&cityid=532900&elem=RAIN";
        Md5KeyUtil md5KeyUtil = new Md5KeyUtil();
        String result = md5KeyUtil.getResult(url);
        mojiService.save(cal.getTime(),"3",result);
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    public static void main(String[] args){
    }

}

