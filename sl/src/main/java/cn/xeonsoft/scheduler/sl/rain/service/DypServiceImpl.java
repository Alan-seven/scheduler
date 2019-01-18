package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.domain.StPstatR;
import cn.xeonsoft.scheduler.sl.rain.respository.AccpRepository;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component("accpService")
@Transactional
class DypServiceImpl implements DypService {

	@Autowired
	private AccpRepository accpRepository;

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
			if (accpRepository.findCount(stPstatR) > 0) {
				accpRepository.update(stPstatR);
			} else {
				accpRepository.save(stPstatR);
			}
		}
	}

	@Override
	public void saveOrUpdateHour(List<Accp> accp) {
		StPstatR stPstatR = null;
		for (Accp _accp : accp) {
			stPstatR = new StPstatR();
			stPstatR.setStcd(_accp.getStcd());
			stPstatR.setAccp(_accp.getAccp());
			stPstatR.setIdtm(_accp.getTm());
			stPstatR.setSttdrcd(DateInterval.HOUR.getType()+"");
			if (accpRepository.findCount(stPstatR) > 0) {
				accpRepository.update(stPstatR);
			} else {
				accpRepository.save(stPstatR);
			}
		}
	}
}