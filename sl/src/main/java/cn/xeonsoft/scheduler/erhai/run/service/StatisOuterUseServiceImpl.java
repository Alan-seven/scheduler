package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.OuterUse;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisDistDrRepository;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisOuterUseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("statisOuterUseService")
@Transactional
public class StatisOuterUseServiceImpl implements StatisOuterUseService {
	@Autowired
	private StatisOuterUseRepository statisOuterUseRepository;

    @Override
    public OuterUse get(String sttdrcd, String tm) {
        return statisOuterUseRepository.get(sttdrcd,tm);
    }
}
