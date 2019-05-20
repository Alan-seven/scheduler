package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.Xierhe;

import java.util.Date;
import java.util.List;

public interface StatisXierheService {
    List<Xierhe> list(String sttdrcd, Date startDate, Date endDate);
}