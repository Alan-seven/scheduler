package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.respository.ErhaiRunRepository;
import cn.xeonsoft.scheduler.erhai.run.respository.ErhaiZRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("erhaiZService")
@Transactional
public class ErhaiZServiceImpl implements ErhaiZService {
	@Autowired
	private ErhaiZRepository erhaiZRepository;

	@Override
	public Float findDayZ() {
		return this.findDayZ(DateUtils.getDateTime());
	}

	@Override
	public Float findDayZ(String tm) {
		return erhaiZRepository.findDayZ(tm);
	}

	@Override
	public Float findMonthZ(String tm) {
		return erhaiZRepository.findMonthZ(tm);
	}
}
