package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.InW;
import cn.xeonsoft.scheduler.erhai.run.respository.InWRepository;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisDistDrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("statisDistDrService")
@Transactional
public class StatisDistDrServiceImpl implements StatisDistDrService {
	@Autowired
	private StatisDistDrRepository statisDistDrRepository;

    @Override
    public Float get(String sttdrcd, String tm, String addvcd) {
        return statisDistDrRepository.get(sttdrcd,tm,addvcd);
    }
}
