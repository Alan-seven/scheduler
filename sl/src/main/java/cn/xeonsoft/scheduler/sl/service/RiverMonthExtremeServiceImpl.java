package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.respository.RiverMonthExtremumRepository;
import cn.xeonsoft.scheduler.sl.utils.DateUtils;

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
	public void saveOrUpdateMaxzAndTm(List<ZQExtremum> zqExtremeList) {
		saveOrUpdateMaxzAndTm(new Date(), zqExtremeList);
	}

	@Override
	public void saveOrUpdateMinzAndTm(List<ZQExtremum> zqExtremeList) {
		saveOrUpdateMinzAndTm(new Date(), zqExtremeList);
	}

	@Override
	public void saveOrUpdateMaxzAndTm(Date tm, List<ZQExtremum> zqExtremeList) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));
		for (ZQExtremum zqExtremum : zqExtremeList) {
			if (null == zqExtremum) {
				continue;
			}
			Integer count = this.riverMonthExtremumRepository.findCount(zqExtremum.getStcd(), yr, mnth,
					Constant.PRDTP_MONTH);
			if (count > 0) {
				riverMonthExtremumRepository.updateHtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getTm());
			} else {
				riverMonthExtremumRepository.saveHtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getTm());
			}
		}
	}

	@Override
	public void saveOrUpdateMinzAndTm(Date tm, List<ZQExtremum> zqExtremeList) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));
		for (ZQExtremum zqExtremum : zqExtremeList) {
			if (null == zqExtremum) {
				continue;
			}
			Integer count = this.riverMonthExtremumRepository.findCount(zqExtremum.getStcd(), yr, mnth,
					Constant.PRDTP_MONTH);
			if (count > 0) {
				riverMonthExtremumRepository.updateLtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getTm());
			} else {
				riverMonthExtremumRepository.saveLtzAndTm(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum(), zqExtremum.getTm());

			}
		}
	}
}
