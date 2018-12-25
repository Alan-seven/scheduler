package cn.xeonsoft.scheduler.sl.flow.service;

import cn.xeonsoft.scheduler.sl.flow.bo.FlowSum;
import cn.xeonsoft.scheduler.sl.flow.respository.FlowRtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @author wantwantxu
 *
 */
@Component("flowRtService")
@Transactional
public class FlowRtServiceImpl implements FlowRtService {
	@Autowired
	private FlowRtRepository flowRtRepository;

	@Override
	public List<FlowSum> findMinuteSum(String type, Date startDate, Date endDate) {
		return flowRtRepository.findMinuteSum(type,startDate,endDate);
	}

	@Override
	public List<FlowSum> findHourSum(String type, Date startDate, Date endDate) {
		return flowRtRepository.findHourSum(type,startDate,endDate);
	}

	@Override
	public List<FlowSum> findDaySum(String type, Date startDate, Date endDate) {
		return flowRtRepository.findDaySum(type,startDate,endDate);
	}

	@Override
	public List<FlowSum> findMonthSum(String type, Date startDate, Date endDate) {
		return flowRtRepository.findMonthSum(type,startDate,endDate);
	}

	@Override
	public List<FlowSum> findYearSum(String type, Date startDate, Date endDate) {
		return flowRtRepository.findYearSum(type,startDate,endDate);
	}
}
