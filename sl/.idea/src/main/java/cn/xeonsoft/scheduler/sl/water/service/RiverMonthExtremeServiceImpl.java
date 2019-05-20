package cn.xeonsoft.scheduler.sl.water.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.water.respository.RiverMonthExtremumRepository;

/**
 * 旬月极值ST_RVDMEVSQ_S
 * 
 * @author wantwantxu
 *
 */
@Component("riverMonthExtremeService")
@Transactional
public class RiverMonthExtremeServiceImpl implements RiverMonthExtremeService {
	@Autowired
	private RiverMonthExtremumRepository riverMonthExtremumRepository;

	@Override
	public void saveOrUpdateMaxzAndTm(List<Extreme> zqExtremeList) {
		saveOrUpdateMaxzAndTm(new Date(), zqExtremeList);
	}

	@Override
	public void saveOrUpdateMinzAndTm(List<Extreme> zqExtremeList) {
		saveOrUpdateMinzAndTm(new Date(), zqExtremeList);
	}

	@Override
	public void saveOrUpdateMaxzAndTm(Date tm, List<Extreme> zqExtremeList) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));
		for (Extreme zqExtremum : zqExtremeList) {
			if (null == zqExtremum) {
				continue;
			}
			Integer count = this.riverMonthExtremumRepository.findCount(zqExtremum.getStcd(), yr, mnth,
					Constant.PRDTP_MONTH);
			if (count > 0) {
				riverMonthExtremumRepository.updateHtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getExtremumTm());
			} else {
				riverMonthExtremumRepository.saveHtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getExtremumTm());
			}
		}
	}

	@Override
	public void saveOrUpdateMinzAndTm(Date tm, List<Extreme> zqExtremeList) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));
		for (Extreme zqExtremum : zqExtremeList) {
			if (null == zqExtremum) {
				continue;
			}
			Integer count = this.riverMonthExtremumRepository.findCount(zqExtremum.getStcd(), yr, mnth,
					Constant.PRDTP_MONTH);
			if (count > 0) {
				riverMonthExtremumRepository.updateLtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getExtremumTm());
			} else {
				riverMonthExtremumRepository.saveLtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getExtremumTm());

			}
		}
	}
}
