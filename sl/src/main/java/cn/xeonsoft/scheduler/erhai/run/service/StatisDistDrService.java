package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.Dali;

import java.util.Date;
import java.util.List;

public interface StatisDistDrService {
	List<Dali> list(String sttdrcd, Date startDate, Date endDate, String addvcd);
}