package cn.xeonsoft.scheduler.sl.water.service;

import java.util.List;

import cn.xeonsoft.scheduler.sl.water.domain.Station;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import cn.xeonsoft.scheduler.sl.water.respository.StationRepository;

@Component("stationService")
@Transactional
public class StationServiceImpl implements StationService {

	private final StationRepository stationRepository;
	private static List<Station> stationList = null;

	public StationServiceImpl(StationRepository stationRepository) {
		this.stationRepository = stationRepository;
	}

	@Override
	public synchronized List<Station> findList() {
		if (null == stationList || (null != stationList && stationList.size() < 1)) {
			StationServiceImpl.stationList = stationRepository.findList();
		}
		return stationList;
	}

}