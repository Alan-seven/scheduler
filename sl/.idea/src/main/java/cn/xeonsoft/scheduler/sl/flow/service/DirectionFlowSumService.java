package cn.xeonsoft.scheduler.sl.flow.service;


import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;

import java.util.Date;
import java.util.List;

public interface DirectionFlowSumService {
	Integer findCount(String direction, Date tm, String sttdrcd);

	void saveSumq(String direction, Date tm, Float sumq, String sttdrcd);

	void saveSumq(List<FlowSum> flowSums,String direction,String sttdrcd);
}