package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.rain.respository.PptnMonthDrpRepository;

/**
 * 月总量
 * 
 * @author wantwantxu
 *
 */
@Component("pptnMonthDrpService")
@Transactional
public class PptnMonthDrpServiceImpl implements PptnMonthDrpService {
	@Autowired
	private PptnMonthDrpRepository pptnMonthDrpRepository;

	@Override
	public void saveOrUpdate(List<Accp> accpList) {
		for (Accp accp : accpList) {
			String stcd = accp.getStcd();
			Date tm = accp.getTm();
			Integer yr = Integer.parseInt(DateUtils.getYear(tm));
			Integer mnth = Integer.parseInt(DateUtils.getMonth(tm));
			Integer count = this.pptnMonthDrpRepository.findCount(yr, mnth, Constant.PRDTP_MONTH,stcd);
			if (count > 0) {
				this.pptnMonthDrpRepository.update(accp.getStcd(), yr, mnth, Constant.PRDTP_MONTH, accp.getAccp());
			} else {
				this.pptnMonthDrpRepository.save(accp.getStcd(), yr, mnth, Constant.PRDTP_MONTH, accp.getAccp());
			}

		}
	}
}
