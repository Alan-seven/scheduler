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
}
