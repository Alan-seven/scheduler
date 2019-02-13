package cn.xeonsoft.scheduler.erhai.run.job;

import cn.xeonsoft.scheduler.erhai.run.bo.*;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisYerbRepository;
import cn.xeonsoft.scheduler.erhai.run.service.*;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

public class ErhaiRun1Job extends QuartzJobBean {

    @Autowired
    private ErhaiRunService erhaiRunService;


    @Override
    protected void executeInternal(JobExecutionContext jobExecutionContext) throws JobExecutionException {

    }


}
