package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.Yerb;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisYerbRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component("statisYerbService")
@Transactional
public class StatisYerbServiceImpl implements StatisYerbService {
	@Autowired
	private StatisYerbRepository statisYerbRepository;

    @Override
    public int findCount(String sttdrcd, String tm) {
        return statisYerbRepository.findCount(sttdrcd,tm);
    }

    @Override
    public List<Yerb> list(String sttdrcd, Date startDate, Date endDate) {
        return statisYerbRepository.list(sttdrcd,startDate,endDate);
    }
}
