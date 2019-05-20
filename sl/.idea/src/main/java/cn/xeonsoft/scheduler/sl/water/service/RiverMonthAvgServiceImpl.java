package cn.xeonsoft.scheduler.sl.water.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.water.respository.RiverMonthAvgRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

/**
 * 旬月均值ST_RVDMMYSQ_S
 * 
 * @author wantwantxu
 *
 */
@Component("riverMonthAvgService")
@Transactional
public class RiverMonthAvgServiceImpl implements RiverMonthAvgService {
	@Autowired
	private RiverMonthAvgRepository riverMonthAvgRepository;

	@Override
	public void saveOrUpdate(List<Extreme> zqExtremeList) {
		saveOrUpdate(new Date(), zqExtremeList);
	}

	@Override
	public void saveOrUpdate(Date tm, List<Extreme> riverAvg) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));

		for (Extreme zqExtremum : riverAvg) {
			if (riverMonthAvgRepository.findCount(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH) <= 0)
				riverMonthAvgRepository.save(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum());
		}
	}

}
