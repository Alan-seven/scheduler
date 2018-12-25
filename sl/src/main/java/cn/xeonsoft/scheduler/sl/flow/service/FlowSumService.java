package cn.xeonsoft.scheduler.sl.flow.service;


import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;

import java.util.List;

public interface FlowSumService {
	Integer findCount(String tm,String type,String sttdrcd);

	void updateSumq(String tm,Float sumq,String type,String sttdrcd);

	void saveSumq(String tm,Float sumq,String type,String sttdrcd);

	void saveSumq(List<FlowSum> flowSums, String type, String sttdrcd);
}