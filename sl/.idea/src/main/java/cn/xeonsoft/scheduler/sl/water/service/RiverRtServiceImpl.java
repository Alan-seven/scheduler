package cn.xeonsoft.scheduler.sl.water.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.water.domain.RiverSt;
import cn.xeonsoft.scheduler.sl.water.domain.Station;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.xeonsoft.scheduler.sl.water.respository.RiverRtRepository;

@Component("riverStService")
@Transactional
class RiverRtServiceImpl implements RiverRtService {

	@Autowired
	private StationService stationService;
	@Autowired
	private RiverRtRepository stRiverRepository;

	@Override
	public List<RiverSt> findList(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		return this.stRiverRepository.findList(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtzAndTm(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		List<Station> stationList = stationService.findList();
		List<Extreme> zqExtremumList = new ArrayList<Extreme>();
		for (Station station : stationList) {
			zqExtremumList.add(this.stRiverRepository.findLtzAndTm(station.getStcd(), startDate, endDate));
		}
		return zqExtremumList;
	}

	@Override
	public List<Extreme> findHtzAndTm(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		List<Station> stationList = stationService.findList();
		List<Extreme> zqExtremumList = new ArrayList<Extreme>();
		for (Station station : stationList) {
			zqExtremumList.add(this.stRiverRepository.findHtzAndTm(station.getStcd(), startDate, endDate));
		}
		return zqExtremumList;
	}

	@Override
	public List<Extreme> findHtzAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findHtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtzAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findHtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtzAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findHtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtzAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findHtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtzAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findHtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtzAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findHtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtzAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findLtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtzAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findLtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtzAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findLtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtzAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findLtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtqAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findHtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtqAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findLtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtqAndTm(Date startDate, Date endDate) {
		return this.findHtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtqAndTm(Date startDate, Date endDate) {
		return this.findLtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtqAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findHtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtqAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findHtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtqAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findHtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtqAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findLtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtqAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findHtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtqAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findHtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtqAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findLtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtqAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findLtqAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findAvgz() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<Extreme> findAvgzByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<Extreme> findAvgzByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<Extreme> findAvgz(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY, tm);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<Extreme> findAvgzByMonth(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<Extreme> findAvgzByYear(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<Extreme> findHtzAndTm(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY, tm);
		return this.findHtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findLtzAndTm(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY, tm);
		return this.findLtzAndTm(startDate, endDate);
	}

	@Override
	public List<Extreme> findEhz() {
		return this.findEhz(new Date());
	}

	@Override
	public List<Extreme> findEhz(Date tm) {
		Date startDate = DateUtils.get8hBeginDateTime(tm);
		Date endDate = DateUtils.get8hEndDateTime(tm);
		List<Station> stationList = stationService.findList();
		List<Extreme> riverDayList = new ArrayList<Extreme>();
		for (Station station : stationList) {
			riverDayList.addAll(this.stRiverRepository.findEhz(station.getStcd(), startDate, endDate));
		}
		return riverDayList;
	}

}