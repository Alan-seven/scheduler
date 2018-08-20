package cn.xeonsoft.worker.task.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.worker.task.Constant;
import cn.xeonsoft.worker.task.domain.Accp;
import cn.xeonsoft.worker.task.respository.PptnMonthDrpRepository;
import cn.xeonsoft.worker.task.utils.DateUtils;

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
	public void saveOrUpdate(Date tm, List<Accp> accpList) {
		Integer yr = Integer.parseInt(DateUtils.getYear(tm));
		Integer mnth = Integer.parseInt(DateUtils.getMonth(tm));
		for (Accp accp : accpList) {
			Integer count = this.pptnMonthDrpRepository.findCount(yr, mnth, Constant.PRDTP_MONTH);
			if (count > 0) {
				this.pptnMonthDrpRepository.update(accp.getStcd(), yr, mnth, Constant.PRDTP_MONTH, accp.getAccp());
			} else {
				this.pptnMonthDrpRepository.save(accp.getStcd(), yr, mnth, Constant.PRDTP_MONTH, accp.getAccp());
			}

		}
	}

	@Override
	public void saveOrUpdate(List<Accp> accpList) {
		this.saveOrUpdate(new Date(), accpList);
	}

}
