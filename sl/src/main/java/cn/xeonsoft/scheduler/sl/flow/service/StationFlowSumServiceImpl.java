package cn.xeonsoft.scheduler.sl.flow.service;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.respository.FlowSumRepository;
import cn.xeonsoft.scheduler.sl.flow.respository.StationFlowRtRepository;
import cn.xeonsoft.scheduler.sl.flow.respository.StationFlowSumRepository;
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
@Component("stationFlowSumService")
@Transactional
public class StationFlowSumServiceImpl implements StationFlowSumService {
	@Autowired
	private StationFlowSumRepository stationFlowSumRepository;

	@Override
	public Integer findCount(String stcd, Date tm, String sttdrcd) {
		return stationFlowSumRepository.findCount(stcd,tm,sttdrcd);
	}

	@Override
	public void updateSumq(String stcd,Date tm, Float sumq,String sttdrcd) {
		stationFlowSumRepository.updateSumq(stcd,tm,sumq,sttdrcd);
	}

	@Override
	public void saveSumq(String stcd,Date tm, Float sumq,String sttdrcd) {
		if(findCount(stcd,tm,sttdrcd)>0){
			updateSumq(stcd,tm,sumq,sttdrcd);
		}else{
			stationFlowSumRepository.saveSumq(stcd,tm,sumq,sttdrcd);
		}
	}

	@Override
	public void saveSumq(List<FlowSum> flowSums,String sttdrcd) {
		for(FlowSum flowsum : flowSums){
			String stcd = flowsum.getStcd();
			String tm = flowsum.getTm();
			Date _tm  = DateUtils.parseDate(tm);
			if(null==_tm){
				continue;
			}
			Float sumq = flowsum.getSumq();
			//计算水量
			saveSumq(stcd, _tm,sumq * 15 * 60,sttdrcd);
		}
	}
}
