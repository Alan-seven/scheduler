package cn.xeonsoft.scheduler.sl.flow.service;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.respository.FlowSumRepository;
import cn.xeonsoft.scheduler.sl.flow.respository.StationFlowRtRepository;
import cn.xeonsoft.scheduler.sl.flow.respository.StationFlowSumRepository;
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
@Component("stationFlowSumService")
@Transactional
public class StationFlowSumServiceImpl implements StationFlowSumService {
	@Autowired
	private StationFlowSumRepository stationFlowSumRepository;

	@Override
	public Integer findCount(String stcd, Date tm, String sttdrcd) {
		return stationFlowSumRepository.findCount(stcd,tm,sttdrcd);
	}

	private void updateSumq(String stcd,Date tm, Float sumq,String sttdrcd) {
		stationFlowSumRepository.updateSumq(stcd,tm,sumq,sttdrcd);
	}

	@Override
	public void saveSumq(String stcd,Date tm, Float sumq,String sttdrcd) {
		//计算水量
		if(null!=sumq){
			float w = sumq * 15 * 60 / 10000;
			if("90210530".equals(stcd)){//西洱河测站对应的数据是统计水量
				w = sumq/10000;
			}
			if(findCount(stcd,tm,sttdrcd)>0){
				updateSumq(stcd,tm,w,sttdrcd);
			}else{
				stationFlowSumRepository.saveSumq(stcd,tm,w,sttdrcd);
			}
		}

	}

	@Override
	public void saveSumq(List<FlowSum> flowSums,String sttdrcd,String type) {
		for(FlowSum flowsum : flowSums){
			String stcd = flowsum.getStcd();
			String tm = flowsum.getTm();
			Date _tm  = DateUtils.parseDate(tm);
			if(null==_tm){
				continue;
			}
			Float sumq = flowsum.getSumq();
			if(null==sumq){
				continue;
			}
			//所有测站根据流量统计出来的西洱河水量统计数据抛弃掉，需要根据水量自动换算
			if("1".equals(type) && "90210530".equals(stcd)){
				continue;
			}
			saveSumq(stcd, _tm,sumq,sttdrcd);
		}
	}

	public static void main(String[] args){
		float sumq = 0.006f;
		System.out.println(sumq/10000);
	}
}
