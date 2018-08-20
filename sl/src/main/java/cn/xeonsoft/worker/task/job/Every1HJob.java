package cn.xeonsoft.worker.task.job;

import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.QuartzJobBean;

import cn.xeonsoft.worker.task.bo.PptnExtremum;
import cn.xeonsoft.worker.task.bo.RainDay;
import cn.xeonsoft.worker.task.bo.ZQExtremum;
import cn.xeonsoft.worker.task.domain.Accp;
import cn.xeonsoft.worker.task.service.PptnMonthDrpService;
import cn.xeonsoft.worker.task.service.PptnMonthExtremeService;
import cn.xeonsoft.worker.task.service.PptnRtService;
import cn.xeonsoft.worker.task.service.RiverDayService;
import cn.xeonsoft.worker.task.service.RiverMonthAvgService;
import cn.xeonsoft.worker.task.service.RiverMonthExtremeService;
import cn.xeonsoft.worker.task.service.RiverRtService;
import cn.xeonsoft.worker.task.service.AccpService;

/**
 * 
 * @author wantwantxu
 *
 */
public class Every1HJob extends QuartzJobBean {

	@Autowired
	private RiverRtService stRiverService;

	@Autowired
	private PptnRtService pptnRtService;

	@Autowired
	private PptnMonthExtremeService pptnMonthExtremeService;
	@Autowired
	private AccpService sumDrpService;
	@Autowired
	private RiverDayService riverDayService;
	@Autowired
	private RiverMonthAvgService riverMonthAvgService;
	@Autowired
	private RiverMonthExtremeService riverMonthExtremeService;
	@Autowired
	private PptnMonthDrpService pptnMonthDrpService;

	@Override
	protected void executeInternal(JobExecutionContext context) throws JobExecutionException {
		// 得到最大值及发生时间
		List<ZQExtremum> riverMaxzAndTm = stRiverService.findMaxzAndTmByMonth();
		// 得到最小值及发生时间
		List<ZQExtremum> riverMinzAndTm = stRiverService.findMinzAndTmByMonth();
		// 最高雨量及发生时间
		List<PptnExtremum> maxDrpAndTm = pptnRtService.findMaxDrpAndTmByMonth();
		// 雨日
		List<RainDay> rainDayOfMonth = pptnRtService.findRainDayByMonth();

		// 1.保存累计雨量(日、月)
		List<Accp> accp = pptnRtService.findAccp();
		List<Accp> accpOfMonth = pptnRtService.findAccpByMonth();
		sumDrpService.saveOrUpdate(accp);
		sumDrpService.saveOrUpdateMonth(accpOfMonth);
		pptnMonthDrpService.saveOrUpdate(accpOfMonth);
		// 2.保存月极值，雨日
		pptnMonthExtremeService.saveOrUpdateMaxdrpAndTm(maxDrpAndTm);
		pptnMonthExtremeService.updateRainDays(rainDayOfMonth);
		// 3. 保存平均水位（日、月）
		List<ZQExtremum> avgzListOfMonth = stRiverService.findAvgzByMonth();
		List<ZQExtremum> avgzList = stRiverService.findAvgz();

		riverDayService.saveOrUpdateAvz(avgzList);
		riverMonthAvgService.saveOrUpdate(avgzListOfMonth);
		// 4. 保存极值水位
		riverMonthExtremeService.saveOrUpdateMaxzAndTm(riverMaxzAndTm);
		riverMonthExtremeService.saveOrUpdateMinzAndTm(riverMinzAndTm);
	}
}