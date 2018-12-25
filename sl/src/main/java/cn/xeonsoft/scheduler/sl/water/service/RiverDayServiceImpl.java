package cn.xeonsoft.scheduler.sl.water.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.water.domain.StatisDQ;
import cn.xeonsoft.scheduler.sl.water.domain.StatisDZ;
import cn.xeonsoft.scheduler.sl.water.respository.RiverDayRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 每日8h水位及平均水位,存自建表DATA_RIVER_DAY
 * 
 * @author wantwantxu
 *
 */
@Component("riverDayService")
@Transactional
public class RiverDayServiceImpl implements RiverDayService {
	@Autowired
	private RiverDayRepository riverDayRepository;

	@Override
	public void saveOrUpdateAvgz(Date tm, List<Extreme> avgzList) {
		for (Extreme extreme : avgzList) {
			if(null==extreme){
				continue;
			}
			extreme.setTm(DateUtils.parseDate(DateUtils.formatDate(tm)));
			if (exist(extreme.getStcd(),tm)) {
				this.riverDayRepository.updateAvgz(extreme);
			} else {
				this.riverDayRepository.saveAvgz(extreme);
			}
		}
	}

	@Override
	public void saveOrUpdateEhz(Date tm, List<Extreme> ehzList) {
		StatisDZ statisDZ = null;
		for (Extreme extreme : ehzList) {
			if(null==extreme){
				continue;
			}
			extreme.setTm(DateUtils.parseDate(DateUtils.formatDate(tm)));
			if (exist(extreme.getStcd(),tm)) {
				this.riverDayRepository.updateEhz(extreme);
			} else {
				this.riverDayRepository.saveEhz(extreme);
			}
		}
	}

	@Override
	public void saveOrUpdateHtzAndTm(Date tm, List<Extreme> htzAndTmList) {
		for (Extreme extreme : htzAndTmList) {
			if(null==extreme){
				continue;
			}
			extreme.setTm(DateUtils.parseDate(DateUtils.formatDate(tm)));
			if (exist(extreme.getStcd(),tm)) {
				this.riverDayRepository.updateHtzAndTm(extreme);
			} else {
				this.riverDayRepository.saveHtzAndTm(extreme);
			}
		}
	}

	@Override
	public void saveOrUpdateLtzAndTm(Date tm, List<Extreme> ltzAndTmList) {
		for (Extreme extreme : ltzAndTmList) {
			if(null==extreme){
				continue;
			}
			extreme.setTm(DateUtils.parseDate(DateUtils.formatDate(tm)));
			if (exist(extreme.getStcd(),tm)) {
				this.riverDayRepository.updateLtzAndTm(extreme);
			} else {
				this.riverDayRepository.saveLtzAndTm(extreme);
			}
		}
	}

	private boolean exist(String stcd,Date tm){
		String ymd = DateUtils.formatDate(tm);
		return this.riverDayRepository.findDZCount(stcd, ymd) > 0;
	}
}
