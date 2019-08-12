package cn.xeonsoft.scheduler.sl.flow.service;


import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;

import java.util.Date;
import java.util.List;

public interface StationFlowSumService {
	Integer findCount(String stcd,Date tm, String sttdrcd);

	void saveSumq(String stcd, Date tm, Float sumq, String sttdrcd);

	void saveSumq(List<FlowSum> flowSums,String sttdrcd,String type);

}