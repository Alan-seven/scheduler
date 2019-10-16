package cn.xeonsoft.scheduler.sl.flow.service;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.respository.FlowRtRepository;
import cn.xeonsoft.scheduler.sl.flow.respository.StationFlowRtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wantwantxu
 *
 */
@Component("stationFlowRtService")
@Transactional
public class StationFlowRtServiceImpl implements StationFlowRtService {
	@Autowired
	private StationFlowRtRepository stationFlowRtRepository;

	@Override
	public List<FlowSum> findSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findSum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findHourSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findHourSum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findDaySum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findDaySum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findMonthSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findMonthSum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findYearSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findYearSum(startDate,endDate);
	}


	@Override
	public List<FlowSum> findXierheSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findXierheSum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findXierheHourSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findXierheHourSum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findXierheDaySum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findXierheDaySum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findXierheMonthSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findXierheMonthSum(startDate,endDate);
	}

	@Override
	public List<FlowSum> findXierheYearSum(Date startDate, Date endDate) {
		return stationFlowRtRepository.findXierheYearSum(startDate,endDate);
	}

	public void updateQ(String stcd,Date tm,Float q){
		stationFlowRtRepository.updateQ(stcd,tm,q);
	}

	public void updateZ(String stcd,Date tm,Float z){
		stationFlowRtRepository.updateZ(stcd,tm,z);
	}

	/**
	 * 查询引洱入宾统计数据
	 * @param stcd
	 * @param sttdrcd
	 * @param tm
	 * @return
	 */
	public List<FlowSum> findYerbSum(String stcd,String sttdrcd,Date tm){
		return stationFlowRtRepository.findYerbSum(stcd,sttdrcd,tm);
	}
}
