package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.rain.respository.StatisDypRepository;

/**
 * @author wantwantxu
 */
@Component("pptnMonthExtremeService")
@Transactional
public class StatisDypServiceImpl implements StatisDypService {
	@Autowired
	private StatisDypRepository statisDypRepository;

	@Override
	public void saveOrUpdateMaxdrpAndTm(Date tm,String sttdrcd,List<PptnExtremum> pptnExtremum) {
		for (PptnExtremum _pptnExtremum : pptnExtremum) {
			if (null == _pptnExtremum) {
				continue;
			}
			if (statisDypRepository.findCount(_pptnExtremum.getStcd(), tm,sttdrcd) <= 0) {
				statisDypRepository.save(_pptnExtremum.getStcd(), tm,sttdrcd, _pptnExtremum.getMaxdrp(),_pptnExtremum.getTm());
			} else {
				statisDypRepository.updateMxDyp(_pptnExtremum.getMaxdrp(),_pptnExtremum.getTm(),_pptnExtremum.getStcd(),tm,sttdrcd);
			}
		}
	}

	@Override
	public void updateRainDays(Date tm,String sttdrcd,List<RainDays> raindays) {
		for (RainDays rainDay : raindays) {
			statisDypRepository.updateRainDays(rainDay.getDays(),rainDay.getStcd(),tm,sttdrcd);
		}
	}

}
