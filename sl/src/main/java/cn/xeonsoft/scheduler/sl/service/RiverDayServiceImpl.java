package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.domain.RiverDay;
import cn.xeonsoft.scheduler.sl.respository.RiverDayRepository;
import cn.xeonsoft.scheduler.sl.utils.DateUtils;

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
	private RiverDayRepository riverMonthAvgRepository;

	@Override
	public void saveOrUpdateAvz(List<ZQExtremum> avzList) {
		Date tm = DateUtils.parseDate(DateUtils.getDate());
		this.saveOrUpdateAvz(tm, avzList);
	}

	@Override
	public void saveOrUpdateEhz(List<RiverDay> riverDayList) {
	}

	@Override
	public void remove() {
		// TODO Auto-generated method stub

	}

	@Override
	public void saveOrUpdateAvz(Date tm, List<ZQExtremum> avzList) {
		for (ZQExtremum zqExtremum : avzList) {
			if ((this.riverMonthAvgRepository.findCount(zqExtremum.getStcd(), tm)) > 0) {
				this.riverMonthAvgRepository.updateAvz(zqExtremum.getStcd(), tm, zqExtremum.getExtremum());
			} else {
				this.riverMonthAvgRepository.saveAvz(zqExtremum.getStcd(), tm, zqExtremum.getExtremum());
			}
		}
	}

}
