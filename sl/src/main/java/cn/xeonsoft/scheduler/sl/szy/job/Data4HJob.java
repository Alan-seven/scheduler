package cn.xeonsoft.scheduler.sl.szy.job;

import cn.xeonsoft.scheduler.sl.szy.bo.Data;
import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;
import cn.xeonsoft.scheduler.sl.szy.bo.WrStatB;
import cn.xeonsoft.scheduler.sl.szy.service.DataService;
import cn.xeonsoft.scheduler.sl.szy.service.StationTmService;
import cn.xeonsoft.scheduler.sl.szy.service.WrStatBService;
import cn.xeonsoft.scheduler.sl.szy.web.ParseResult;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import cn.xeonsoft.scheduler.utils.DateUtils;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Date;
import java.util.List;
import java.util.UUID;

/**
 * 纳污能力计算  暂定4h计算一次
 */
public class Data4HJob extends QuartzJobBean {

    @Autowired
    private DataService dataService;
    @Autowired
    private StationTmService stationTmService;
    //@Autowired
    //private WrStatBService wrStatBService;

    @Override
    protected void executeInternal( JobExecutionContext context) throws JobExecutionException {
        String beginDate = DateUtils.getBeforeDateTime(new Date(),-4);
        String endDate= DateUtils.formatDateTime(new Date());

        //同步单站的水质数据
        ParseResult parseResult = new ParseResult();
        List<WrStatB> statList = parseResult.getStationList();
        for(int i = 0 ; i < statList.size();i++){
            WrStatB stat = statList.get(i);
            List<Data> dataList =parseResult.getNewData(stat.getStcd());
            System.out.println("------------size---"+dataList.size());
            String tmId = UUID.randomUUID().toString().replace("-","");
            if(dataList.size() > 0 ){
                if(stationTmService.findCount(stat.getStcd(),dataList.get(0).getTm())<=0){
                    stationTmService.save(tmId,stat.getStcd(),dataList.get(0).getTm(),"");
                }
                dataService.save(dataList,tmId);
            }
        }

        //List<WrStatB> statList = wrStatBService.list("WQ");
//        List<StationTm> stationList = stationTmService.listByRiver(beginDate,endDate);
//        //保存31条河流纳污能力计算结果
//        dataService.saveRiverResult(stationList);
//
//        //洱海水质纳污能力计算结果保存
//        StationTm station = stationTmService.getByErhai(beginDate,endDate);
//        if(null!=station){
//            dataService.saveErhaiResult(station);
//        }


    }




}
