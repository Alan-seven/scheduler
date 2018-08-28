package cn.xeonsoft.scheduler.sl.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.domain.Accp;
import cn.xeonsoft.scheduler.sl.domain.StPstatR;
import cn.xeonsoft.scheduler.sl.respository.AccpRepository;
import cn.xeonsoft.scheduler.sl.utils.DateUtils;

@Component("accpService")
@Transactional
class AccpServiceImpl implements AccpService {

	@Autowired
	private AccpRepository sumDrpRepository;

	@Override
	public void saveOrUpdate(List<Accp> accp) {
		this.saveOrUpdate(new Date(), Constant.STTDRCD_DAY, accp);
	}

	@Override
	public void saveOrUpdateMonth(List<Accp> accp) {
		this.saveOrUpdate(DateUtils.parseDate(DateUtils.getDate()), Constant.STTDRCD_MONTH, accp);
	}

	@Override
	public void saveOrUpdateYear(List<Accp> accp) {
		this.saveOrUpdate(DateUtils.parseDate(DateUtils.getDate()), Constant.STTDRCD_YEAR, accp);
	}

	@Override
	public void saveOrUpdate(Date idtm, String sttdrcd, List<Accp> accp) {
		idtm = DateUtils.parseDate(DateUtils.formatDate(idtm, "yyyy-MM-dd"));
		StPstatR stPstatR = null;
		for (Accp _accp : accp) {
			stPstatR = new StPstatR();
			stPstatR.setStcd(_accp.getStcd());
			stPstatR.setAccp(_accp.getAccp());
			stPstatR.setIdtm(idtm);
			stPstatR.setSttdrcd(sttdrcd);
			if (sumDrpRepository.findCount(stPstatR) > 0) {
				sumDrpRepository.update(stPstatR);
			} else {
				sumDrpRepository.save(stPstatR);
			}
		}
	}

}