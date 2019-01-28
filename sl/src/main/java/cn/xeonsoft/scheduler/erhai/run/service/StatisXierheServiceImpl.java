package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.Xierhe;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisXierheRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;


@Component("statisXierheService")
@Transactional
public class StatisXierheServiceImpl implements StatisXierheService {
	@Autowired
	private StatisXierheRepository statisXierheRepository;

    @Override
    public Xierhe get(String sttdrcd, String tm) {
        return statisXierheRepository.get(sttdrcd,tm);
    }
}
