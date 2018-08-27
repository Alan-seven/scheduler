package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.respository.RiverMonthAvgRepository;
import cn.xeonsoft.scheduler.sl.utils.DateUtils;

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
	public void saveOrUpdate(List<ZQExtremum> zqExtremeList) {
		saveOrUpdate(new Date(), zqExtremeList);
	}

	@Override
	public void saveOrUpdate(Date tm, List<ZQExtremum> riverAvg) {
		int yr = Integer.parseInt(DateUtils.getYear(tm));
		int mnth = Integer.parseInt(DateUtils.getMonth(tm));

		for (ZQExtremum zqExtremum : riverAvg) {
			if (riverMonthAvgRepository.findCount(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH) <= 0)
				riverMonthAvgRepository.save(zqExtremum.getStcd(), yr, mnth, Constant.PRDTP_MONTH,
						zqExtremum.getExtremum());
		}
	}

}