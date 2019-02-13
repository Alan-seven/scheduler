package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.OuterUse;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisOuterUseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component("statisOuterUseService")
@Transactional
public class StatisOuterUseServiceImpl implements StatisOuterUseService {
	@Autowired
	private StatisOuterUseRepository statisOuterUseRepository;

    @Override
    public List<OuterUse> list(String sttdrcd, Date startDate, Date endDate) {
        return statisOuterUseRepository.list(sttdrcd,startDate,endDate);
    }
}
