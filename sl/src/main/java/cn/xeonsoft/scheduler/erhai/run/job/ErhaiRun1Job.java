package cn.xeonsoft.scheduler.erhai.run.job;

import cn.xeonsoft.scheduler.erhai.run.bo.InW;
import cn.xeonsoft.scheduler.erhai.run.bo.OuterUse;
import cn.xeonsoft.scheduler.erhai.run.bo.Xierhe;
import cn.xeonsoft.scheduler.erhai.run.service.*;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

public class ErhaiRun1Job extends QuartzJobBean {
    public static final String ADDVCD_DALI = "532901000000";

    @Autowired
    private ErhaiRunService erhaiRunService;
    @Autowired
    private ErhaiZService erhaiZService;
    @Autowired
    private InWService inWService;
    @Autowired
    private StatisDistDrService statisDistDrService;
    @Autowired
    private StatisOuterUseService statisOuterUseService;
    @Autowired
    private StatisXierheService statisXierheService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }

    private void saveOrUpdate(String sttdrcd,String tm){
        erhaiRunService.saveDaliRain(sttdrcd,tm,statisDistDrService.get(sttdrcd,tm,ADDVCD_DALI));
        String year = DateUtils.getYear(DateUtils.parseDate(tm));
        String month = DateUtils.getMonth(DateUtils.parseDate(tm));
        Integer yr = Integer.parseInt(year);
        Integer mnth = Integer.parseInt(month);
        InW inw = inWService.get(yr,mnth);
        erhaiRunService.updateCleanInw(sttdrcd,tm,inw.getFlow());
        erhaiRunService.updateNaturalInW(sttdrcd,tm,inw.getW());
        erhaiRunService.updateFirstDayZ(sttdrcd,tm,erhaiZService.findDayZ());
        OuterUse outerUse = statisOuterUseService.get(sttdrcd,tm);
        Float outerUseW = null;
        Float farming = outerUse.getFarming();
        Float industry = outerUse.getIndustry();
        Float life = outerUse.getLife();
        if(null==farming && null!=industry && null != life){
            outerUseW = industry+life;
        }else if(null!=farming && null!=industry && null == life){
            outerUseW = farming+industry;
        }else if(null!=farming && null==industry && null != life){
            outerUseW = farming+life;
        }else if(null!=farming && null!=industry && null!=life){
            outerUseW = farming+industry+life;
        }
        erhaiRunService.updateOutuseW(sttdrcd,tm,outerUseW);
        Xierhe xierhe = statisXierheService.get(sttdrcd,tm);
        Float xierheW = null;
        Float electricityW = xierhe.getElectricityW();
        Float emissionW = xierhe.getEmissionW();
        Float sluiceW = xierhe.getSluiceW();
        if(null==electricityW && null!=emissionW && null != sluiceW){
            xierheW = emissionW+sluiceW;
        }else if(null!=electricityW && null!=emissionW && null == sluiceW){
            xierheW = electricityW+emissionW;
        }else if(null!=electricityW && null==emissionW && null != sluiceW){
            xierheW = electricityW+sluiceW;
        }else if(null!=electricityW && null!=emissionW && null!=sluiceW){
            xierheW = electricityW+emissionW+sluiceW;
        }
        erhaiRunService.updateXierheOutW(sttdrcd,tm,xierheW);

    }
}
