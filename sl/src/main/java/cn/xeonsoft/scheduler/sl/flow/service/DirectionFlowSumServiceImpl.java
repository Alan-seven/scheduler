package cn.xeonsoft.scheduler.sl.flow.service;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.respository.DirectionFlowSumRepository;
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
@Component("directionFlowSumService")
@Transactional
public class DirectionFlowSumServiceImpl implements DirectionFlowSumService {
	@Autowired
	private DirectionFlowSumRepository directionFlowSumRepository;

	@Override
	public Integer findCount(String direction, Date tm, String sttdrcd) {
		return directionFlowSumRepository.findCount(tm,direction,sttdrcd);
	}

	private void updateSumq(String direction,Date tm, Float sumq,String sttdrcd) {
		directionFlowSumRepository.updateSumq(tm,sumq,direction,sttdrcd);
	}

	@Override
	public void saveSumq(String direction,Date tm, Float sumq,String sttdrcd) {
		//计算水量
		float w = sumq * 15 * 60 / 10000;
		if(findCount(direction,tm,sttdrcd)>0){
			updateSumq(direction,tm,w,sttdrcd);
		}else{
			directionFlowSumRepository.saveSumq(tm,w,direction,sttdrcd);
		}
	}

	@Override
	public void saveSumq(List<FlowSum> flowSums,String direction,String sttdrcd) {
		for(FlowSum flowsum : flowSums){
			String tm = flowsum.getTm();
			Date _tm  = DateUtils.parseDate(tm);
			if(null==_tm){
				continue;
			}
			Float sumq = flowsum.getSumq();
			saveSumq(direction, _tm,sumq,sttdrcd);
		}
	}
}
