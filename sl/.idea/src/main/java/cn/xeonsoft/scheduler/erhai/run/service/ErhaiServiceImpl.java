package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.Erhai;
import cn.xeonsoft.scheduler.erhai.run.respository.ErhaiRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component("erhaiService")
@Transactional
public class ErhaiServiceImpl implements ErhaiService {
	@Autowired
	private ErhaiRepository erhaiRepository;

	@Override
	public Float findFirstZ(String sttdrcd,Date startDate, Date endDate) {
		return erhaiRepository.findFirstZ(sttdrcd,startDate,endDate);
	}

	@Override
	public Float findLastZ(String sttdrcd,Date startDate, Date endDate) {
		return erhaiRepository.findLastZ(sttdrcd,startDate,endDate);
	}

	@Override
	public List<Erhai> findBySttdrcd(Date startDate, Date endDate, String sttdrcd) {
		return erhaiRepository.findBySttdrcd(startDate,endDate,sttdrcd);
	}
}
