package cn.xeonsoft.scheduler.sl.rain.job;

import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.service.*;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.ArrayList;
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
	private StatisDypService statisDypService;
	@Autowired
	private DypService sumDrpService;
	@Autowired
	private PptnMonthDrpService pptnMonthDrpService;
	@Autowired
	private DirectionAccpService directionAccpService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// 月最高雨量及发生时间
		List<PptnExtremum> maxDrpAndTm = pptnRtService.findMaxDrpAndTmByMonth();
		// 月雨日
		List<RainDays> rainDayOfMonth = pptnRtService.findRainDaysByMonth();

		// 保存累计雨量(日、月)
		// 小时累计雨量
		List<Accp> accpOfHour = pptnRtService.findAccpByHour();
		// 日累计雨量
		List<Accp> accp = pptnRtService.findDayAccp();
		// 五日累计
		List<Accp> accpOfFiveDays = pptnRtService.findFiveDaysAccp();
		// 十日累计
		List<Accp> accpOfTenDays = pptnRtService.findTenDaysAccp();
		// 月累计雨量
		List<Accp> accpOfMonth = pptnRtService.findAccpByMonth();
		// 年累计
		List<Accp> accpOfYear = pptnRtService.findAccpByYear();
		Date dayTm = DateUtils.get8hBeginDate(DateInterval.DAY,new Date());
		Date fiveDaysTm = DateUtils.getBeginDate(DateInterval.FIVEDAYS,new Date());
		Date tenDaysTm = DateUtils.getBeginDate(DateInterval.TENDAYS,new Date());
		Date monthTm = DateUtils.getBeginDate(DateInterval.MONTH,new Date());
		Date yearTm = DateUtils.getBeginDate(DateInterval.YEAR,new Date());
		//
		sumDrpService.saveOrUpdate(fiveDaysTm,DateInterval.FIVEDAYS.getType()+"",accpOfFiveDays);
		sumDrpService.saveOrUpdate(tenDaysTm,DateInterval.TENDAYS.getType()+"",accpOfTenDays);
		sumDrpService.saveOrUpdateHour(accpOfHour);
		sumDrpService.saveOrUpdate(dayTm,accp);
		// 旬月表
		pptnMonthDrpService.saveOrUpdate(accpOfMonth);
		pptnMonthDrpService.saveOrUpdate(accpOfYear);
		// 保存月极值，雨日
		statisDypService.saveOrUpdateMaxdrpAndTm(monthTm,DateInterval.MONTH.getType()+"",maxDrpAndTm);
		statisDypService.updateRainDays(monthTm,DateInterval.MONTH.getType()+"",rainDayOfMonth);
		// 年极值，雨日
		List<PptnExtremum> maxDrpAndTmOfYear = pptnRtService.findMaxDrpAndTmByYear();
		List<RainDays> rainDayOfYear = pptnRtService.findRainDaysByYear();
		statisDypService.saveOrUpdateMaxdrpAndTm(yearTm,DateInterval.YEAR.getType()+"",maxDrpAndTmOfYear);
		statisDypService.updateRainDays(yearTm,DateInterval.YEAR.getType()+"",rainDayOfYear);


		saveRegionSumAccp(DateInterval.DAY,"1");
		saveRegionSumAccp(DateInterval.DAY,"2");
		saveRegionSumAccp(DateInterval.DAY,"3");
		saveRegionSumAccp(DateInterval.DAY,"4");
		saveRegionSumAccp(DateInterval.MONTH,"1");
		saveRegionSumAccp(DateInterval.MONTH,"2");
		saveRegionSumAccp(DateInterval.MONTH,"3");
		saveRegionSumAccp(DateInterval.MONTH,"4");
	}


	private void saveRegionSumAccp(DateInterval dateInterval,String gp){
		Date beginDate = DateUtils.get8hBeginDate(dateInterval);
		Date endDate = DateUtils.get8hEndDate(dateInterval);
		List<Accp> accps = new ArrayList<>();
		switch (dateInterval){
			case HOUR:
				accps = pptnRtService.findHourSumByGP(gp,beginDate,endDate);
				directionAccpService.save(accps,gp,dateInterval.getType()+"");
				break;
			case DAY:
				accps = pptnRtService.findDaySumByGP(gp,beginDate,endDate);
				directionAccpService.save(accps,gp,dateInterval.getType()+"");
				break;
		}
	}
}