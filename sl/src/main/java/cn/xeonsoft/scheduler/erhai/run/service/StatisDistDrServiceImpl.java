package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.Dali;
import cn.xeonsoft.scheduler.erhai.run.bo.InW;
import cn.xeonsoft.scheduler.erhai.run.respository.InWRepository;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisDistDrRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component("statisDistDrService")
@Transactional
public class StatisDistDrServiceImpl implements StatisDistDrService {
	@Autowired
	private StatisDistDrRepository statisDistDrRepository;

    @Override
    public List<Dali> list(String sttdrcd, Date startDate, Date endDate, String addvcd) {
        return statisDistDrRepository.list(sttdrcd,startDate,endDate,addvcd);
    }
}
