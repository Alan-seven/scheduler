package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.Dali;
import cn.xeonsoft.scheduler.erhai.run.bo.InW;
import cn.xeonsoft.scheduler.erhai.run.bo.StatisDistDr;
import cn.xeonsoft.scheduler.erhai.run.respository.InWRepository;
import cn.xeonsoft.scheduler.erhai.run.respository.StatisDistDrRepository;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.domain.StPstatR;
import cn.xeonsoft.scheduler.utils.DateInterval;
import com.alibaba.druid.sql.ast.statement.SQLColumnDefinition;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.mchange.v2.uid.UidUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.server.UID;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component("statisDistDrService")
@Transactional
public class StatisDistDrServiceImpl implements StatisDistDrService {
    //洱海流域行政分区
    public static final String ADDVCD_DALI = "532901000000";

	@Autowired
	private StatisDistDrRepository statisDistDrRepository;

    @Override
    public List<Dali> list(String sttdrcd, Date startDate, Date endDate, String addvcd) {
        return statisDistDrRepository.list(sttdrcd,startDate,endDate,addvcd);
    }

    //生成32位id
    public static String uuid() {
        return UUID.randomUUID().toString().replaceAll("-", "");
    }

    @Override
    public void saveOrUpdateHour(List<Accp> accp,String sttdrcd) {
        StatisDistDr distDr = null;
        for (Accp _accp : accp) {
            if(null == _accp)
                continue;
            distDr = new StatisDistDr();
            distDr.setId(uuid());
            distDr.setDyp(_accp.getAccp());
            distDr.setAddvcd(ADDVCD_DALI);
            distDr.setSttdrcd(sttdrcd);
            distDr.setTm(_accp.getTm());
            if (statisDistDrRepository.findCount(distDr) > 0) {
                statisDistDrRepository.update(distDr);
            } else {
                statisDistDrRepository.save(distDr);
            }
        }
    }

    @Override
    public void saveOrUpdate(List<Accp> accp,String sttdrcd,Date tm) {
        StatisDistDr distDr = null;
        for (Accp _accp : accp) {
            if(null == _accp)
                continue;
            distDr = new StatisDistDr();
            distDr.setId(uuid());
            distDr.setDyp(_accp.getAccp());
            distDr.setAddvcd(ADDVCD_DALI);
            distDr.setSttdrcd(sttdrcd);
            distDr.setTm(tm);
            if (statisDistDrRepository.findCount(distDr) > 0) {
                statisDistDrRepository.update(distDr);
            } else {
                statisDistDrRepository.save(distDr);
            }
        }
    }
}
