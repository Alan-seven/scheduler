package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.respository.ErhaiRunRepository;
import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.domain.StatisDZ;
import cn.xeonsoft.scheduler.sl.water.respository.RiverDayRepository;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;


@Component("riverDayService")
@Transactional
public class ErhaiRunServiceImpl implements ErhaiRunService {
	@Autowired
	private ErhaiRunRepository erhaiRunRepository;

	@Override
	public Integer findCount(String sttdrcd, String tm) {
		return erhaiRunRepository.findCount(sttdrcd,tm);
	}

	@Override
	public void updateDaliRain(String sttdrcd, String tm, Float daliRain) {
		erhaiRunRepository.updateDaliRain(sttdrcd,tm,daliRain);
	}

	@Override
	public void updateNaturalInW(String sttdrcd, String tm, Float naturalInW) {
		erhaiRunRepository.updateNaturalInW(sttdrcd,tm,naturalInW);
	}

	@Override
	public void updateCleanInw(String sttdrcd, String tm, Float cleanInW) {
		erhaiRunRepository.updateCleanInw(sttdrcd,tm,cleanInW);
	}

	@Override
	public void updateFirstDayZ(String sttdrcd, String tm, Float firstDayZ) {
		erhaiRunRepository.updateFirstDayZ(sttdrcd,tm,firstDayZ);
	}

	@Override
	public void updateLastDayZ(String sttdrcd, String tm, Float lastDayZ) {
		erhaiRunRepository.updateLastDayZ(sttdrcd,tm,lastDayZ);
	}

	@Override
	public void updateOutuseW(String sttdrcd, String tm, Float outeruseW) {
		erhaiRunRepository.updateOutuseW(sttdrcd,tm,outeruseW);
	}

	@Override
	public void updateXierheOutW(String sttdrcd, String tm, Float xierheOutW) {
		erhaiRunRepository.updateXierheOutW(sttdrcd,tm,xierheOutW);
	}

	@Override
	public void updateYerbW(String sttdrcd, String tm, Float yerbW) {
		erhaiRunRepository.updateYerbW(sttdrcd,tm,yerbW);
	}

	@Override
	public void saveDaliRain(String sttdrcd, String tm, Float daliRain) {
		erhaiRunRepository.saveDaliRain(sttdrcd,tm,daliRain);
	}
}
