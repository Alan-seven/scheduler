package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.Erhai;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface ErhaiService {
	Float findFirstZ(String sttdrcd,Date startDate,Date endDate);

	Float findLastZ(String sttdrcd,Date startDate,Date endDate);

	List<Erhai> findBySttdrcd(Date startDate,Date endDate,String sttdrcd);
}