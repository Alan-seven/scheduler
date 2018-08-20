package cn.xeonsoft.worker.task.service;

import java.util.List;

import cn.xeonsoft.worker.task.domain.Station;

public interface StationService {
	List<Station> findList();
}