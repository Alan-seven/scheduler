package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.rain.respository.PptnMonthExtremumRepository;

/**
 * 旬月极值ST_RVDMEVSQ_S
 * 
 * @author wantwantxu
 *
 */
@Component("pptnMonthExtremeService")
@Transactional
public class PptnMonthExtremeServiceImpl implements PptnMonthExtremeService {
	@Autowired
	private PptnMonthExtremumRepository pptnMonthExtremumRepository;

	public void save(int yr, int mnth, List<PptnExtremum> pptnExtremum) {
		for (PptnExtremum _pptnExtremum : pptnExtremum) {
			if (null == _pptnExtremum) {
				continue;
			}
			if (pptnMonthExtremumRepository.findCount(_pptnExtremum.getStcd(), yr, mnth) <= 0) {
				pptnMonthExtremumRepository.save(_pptnExtremum.getStcd(), yr, mnth, _pptnExtremum.getMaxdrp(),
						_pptnExtremum.getTm());
			} else {
				pptnMonthExtremumRepository.updateMxDyp(_pptnExtremum.getStcd(), yr, mnth, _pptnExtremum.getMaxdrp(),_pptnExtremum.getTm());
			}
		}
	}

	public void updateRainDays(int yr, int mnth, List<RainDays> raindays) {
		for (RainDays rainDay : raindays) {
			pptnMonthExtremumRepository.updateRainDays(rainDay.getStcd(), yr, mnth, rainDay.getDays());
		}
	}

	@Override
	public void saveOrUpdateMaxdrpAndTm(List<PptnExtremum> pptnExtremum) {
		int yr = Integer.parseInt(DateUtils.getYear());
		int mnth = Integer.parseInt(DateUtils.getMonth());
		save(yr, mnth, pptnExtremum);
	}

	@Override
	public void updateRainDays(List<RainDays> raindays) {
		int yr = Integer.parseInt(DateUtils.getYear());
		int mnth = Integer.parseInt(DateUtils.getMonth());
		for (RainDays rainDay : raindays) {
			pptnMonthExtremumRepository.updateRainDays(rainDay.getStcd(), yr, mnth, rainDay.getDays());
		}
	}

	@Override
	public void saveOrUpdateMaxdrpAndTm(Date tm, List<PptnExtremum> pptnExtremum) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));
		save(yr, mnth, pptnExtremum);
	}

	@Override
	public void updateRainDays(Date tm, List<RainDays> raindays) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));
		updateRainDays(yr, mnth, raindays);
	}

}
