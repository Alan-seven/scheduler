package cn.xeonsoft.scheduler.sl.flow.service;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.respository.FlowSumRepository;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wantwantxu
 *
 */
@Component("flowSumService")
@Transactional
public class FlowSumServiceImpl implements FlowSumService {
	@Autowired
	private FlowSumRepository flowSumRepository;

	@Override
	public Integer findCount(String tm, String type, String sttdrcd) {
		return flowSumRepository.findCount(tm,type,sttdrcd);
	}

	private void updateSumq(String tm, Float sumq, String type, String sttdrcd) {
		flowSumRepository.updateSumq(tm,sumq,type,sttdrcd);
	}

	@Override
	public void saveSumq(String tm, Float sumq, String type, String sttdrcd) {
		tm = DateUtils.formatDateTime(DateUtils.parseDate(tm));
		//计算水量
		float w = sumq * 15 * 60 /10000;
		if(findCount(tm,type,sttdrcd)>0){
			updateSumq(tm,w,type,sttdrcd);
		}else{
			flowSumRepository.saveSumq(tm,w,type,sttdrcd);
		}
	}

	@Override
	public void saveSumq(List<FlowSum> flowSums, String type, String sttdrcd) {
		for(FlowSum flowsum : flowSums){
			String tm = flowsum.getTm();
			if(null==tm){
				continue;
			}
			if(null==DateUtils.parseDate(tm)){
				continue;
			}
			tm = DateUtils.formatDateTime(DateUtils.parseDate(tm));
			Float sumq = flowsum.getSumq();
			saveSumq(tm,sumq,type,sttdrcd);
		}
	}
}
