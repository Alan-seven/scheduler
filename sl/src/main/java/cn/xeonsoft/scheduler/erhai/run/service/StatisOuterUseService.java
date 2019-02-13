package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.OuterUse;

import java.util.Date;
import java.util.List;

public interface StatisOuterUseService {
    List<OuterUse> list(String sttdrcd, Date startDate, Date endDate);
}