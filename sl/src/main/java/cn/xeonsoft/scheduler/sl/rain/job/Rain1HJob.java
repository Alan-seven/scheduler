package cn.xeonsoft.scheduler.sl.rain.job;

import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.service.DypService;
import cn.xeonsoft.scheduler.sl.rain.service.PptnMonthDrpService;
import cn.xeonsoft.scheduler.sl.rain.service.PptnMonthExtremeService;
import cn.xeonsoft.scheduler.sl.rain.service.PptnRtService;
import cn.xeonsoft.scheduler.utils.DateInterval;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;
import java.util.List;

/**
 * 
 * @author wantwantxu
 *
 */
public class Rain1HJob extends QuartzJobBean {

	@Autowired
	private PptnRtService pptnRtService;

	@Autowired
	private PptnMonthExtremeService pptnMonthExtremeService;
	@Autowired
	private DypService sumDrpService;
	@Autowired
	private PptnMonthDrpService pptnMonthDrpService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// 月最高雨量及发生时间
		List<PptnExtremum> maxDrpAndTm = pptnRtService.findMaxDrpAndTmByMonth();
		// 月雨日
		List<RainDays> rainDayOfMonth = pptnRtService.findRainDaysByMonth();

		// 1.保存累计雨量(日、月)
		//小时累计雨量
		List<Accp> accpOfHour = pptnRtService.findAccpByHour();
		//日累计雨量
		List<Accp> accp = pptnRtService.findDayAccp();
		//五日累计
		List<Accp> accpOfFiveDays = pptnRtService.findFiveDaysAccp();
		//十日累计
		List<Accp> accpOfTenDays = pptnRtService.findTenDaysAccp();
		//月累计雨量
		List<Accp> accpOfMonth = pptnRtService.findAccpByMonth();
		sumDrpService.saveOrUpdate(new Date(),DateInterval.FIVEDAYS.getType()+"",accpOfFiveDays);
		sumDrpService.saveOrUpdate(new Date(),DateInterval.TENDAYS.getType()+"",accpOfTenDays);
		sumDrpService.saveOrUpdateHour(accpOfHour);
		sumDrpService.saveOrUpdate(accp);
		sumDrpService.saveOrUpdateMonth(accpOfMonth);

		pptnMonthDrpService.saveOrUpdate(accpOfMonth);
		// 2.保存月极值，雨日
		pptnMonthExtremeService.saveOrUpdateMaxdrpAndTm(maxDrpAndTm);
		pptnMonthExtremeService.updateRainDays(rainDayOfMonth);
	}
}