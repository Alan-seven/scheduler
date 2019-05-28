package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;

import java.util.Date;
import java.util.List;

public interface StationTmService {

    List<StationTm> listByRiver(String beginDate,String endDate);

    StationTm getByErhai(String beginDate,String endDate);

    Integer findCount(String stcd, Date tm);

    void update(String stcd,String wqg,Date tm);

    void save(String id,String stcd,Date tm,String wqg);

}
