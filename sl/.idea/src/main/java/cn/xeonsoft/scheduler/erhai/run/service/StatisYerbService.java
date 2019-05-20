package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.Yerb;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface StatisYerbService {

    int findCount(@Param("sttdrcd") String sttdrcd, @Param("tm") String tm);

    List<Yerb> list(String sttdrcd, Date startDate, Date endDate);
}