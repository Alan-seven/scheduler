package cn.xeonsoft.scheduler.sl.water.job;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;
import cn.xeonsoft.scheduler.sl.water.service.RiverDayService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthAvgService;
import cn.xeonsoft.scheduler.sl.water.service.RiverMonthExtremeService;
import cn.xeonsoft.scheduler.sl.water.service.RiverRtService;

/**
 * 
 * @author wantwantxu
 *
 */
public class Water1HJob extends QuartzJobBean {
	@Autowired
	private RiverRtService stRiverService;
	@Autowired
	private RiverDayService riverDayService;
	@Autowired
	private RiverMonthAvgService riverMonthAvgService;
	@Autowired
	private RiverMonthExtremeService riverMonthExtremeService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		List<Extreme> htzAndTmOfDay = stRiverService.findHtzAndTm();
		List<Extreme> ltzAndTmOfDay = stRiverService.findLtzAndTm();
		List<Extreme> ehzOfDay = stRiverService.findEhz();
		List<Extreme> htzAndTmOfMonth = stRiverService.findHtzAndTmByMonth();
		List<Extreme> ltzAndTmOfMonth = stRiverService.findLtzAndTmByMonth();
		List<Extreme> avgzOfMonth = stRiverService.findAvgzByMonth();
		List<Extreme> avgzOfDay = stRiverService.findAvgz();

		riverDayService.saveOrUpdateAvgz(new Date(),avgzOfDay);
		riverDayService.saveOrUpdateEhz(new Date(),ehzOfDay);
		riverDayService.saveOrUpdateHtzAndTm(new Date(),htzAndTmOfDay);
		riverDayService.saveOrUpdateLtzAndTm(new Date(),ltzAndTmOfDay);

		riverMonthAvgService.saveOrUpdate(avgzOfMonth);
		riverMonthExtremeService.saveOrUpdateMaxzAndTm(htzAndTmOfMonth);
		riverMonthExtremeService.saveOrUpdateMinzAndTm(ltzAndTmOfMonth);
	}
}