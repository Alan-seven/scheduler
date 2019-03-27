package cn.xeonsoft.scheduler.sl.szy.service;

import cn.xeonsoft.scheduler.sl.szy.bo.StationTm;

import java.util.List;

public interface StationTmService {

    List<StationTm> list(String beginDate,String endDate);

}
