package cn.xeonsoft.scheduler.sl.weather.job;

import cn.xeonsoft.scheduler.sl.weather.service.MojiService;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import cn.xeonsoft.scheduler.utils.Md5KeyUtil;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Calendar;
import java.util.Date;

public class Weather1HJob extends QuartzJobBean {

    @Autowired
    private MojiService mojiService;

    @Override
    protected void executeInternal( JobExecutionContext context) throws JobExecutionException {
        //未来两小时降雨
        String nonce = "217";
        String suburl = "/radar/rain/full";
        Calendar cal = Calendar.getInstance();
        cal.clear();
        cal.setTime(new Date());
        //cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        long ts = cal.getTime().getTime();
        String key = Md5KeyUtil.getKey(nonce,suburl,ts);
        String url = "http://hydrometeo.mojicb.com/radar/rain/full/json?token=a8c01907b793b49fc683fe757936a4e6&key="+key+"&timestamp="+ts+"&nonce="+nonce+"&suburl="+suburl+"&cityid=532900";
        Md5KeyUtil md5KeyUtil = new Md5KeyUtil();
        String result = md5KeyUtil.getResult(url);
        cal.set(Calendar.MINUTE, 0);
        Date hourTm = cal.getTime();
        mojiService.save(hourTm,"1",result);

        //未来七天降雨
        String sevenSuburl = "/ec/forcast";
     /*   Calendar sevenCal = Calendar.getInstance();
        sevenCal.clear();
        sevenCal.setTime(new Date());
        sevenCal.set(Calendar.SECOND, 0);
        sevenCal.set(Calendar.MILLISECOND, 0);
        long sevenTs = sevenCal.getTime().getTime();*/
        String sevenKey = Md5KeyUtil.getKey(nonce,sevenSuburl,ts);
        String sevenurl = "http://hydrometeo.mojicb.com/ec/forecast/json?token=a8c01907b793b49fc683fe757936a4e6&key="+sevenKey+"&timestamp="+ts+"&nonce="+nonce+"&suburl="+sevenSuburl+"&cityid=532900&elem=RAIN";
        String sevenresult = md5KeyUtil.getResult(sevenurl);
        mojiService.save(hourTm,"2",sevenresult);
    }
}
