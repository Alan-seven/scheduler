package cn.xeonsoft.scheduler.sl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.xeonsoft.scheduler.sl.bo.ZQExtremum;
import cn.xeonsoft.scheduler.sl.domain.RiverDay;
import cn.xeonsoft.scheduler.sl.domain.RiverSt;
import cn.xeonsoft.scheduler.sl.domain.Station;
import cn.xeonsoft.scheduler.sl.respository.RiverRtRepository;
import cn.xeonsoft.scheduler.sl.utils.DateInterval;
import cn.xeonsoft.scheduler.sl.utils.DateUtils;

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
	public List<ZQExtremum> findMinzAndTm(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		List<Station> stationList = stationService.findList();
		List<ZQExtremum> zqExtremumList = new ArrayList<ZQExtremum>();
		for (Station station : stationList) {
			zqExtremumList.add(this.stRiverRepository.findMinzAndTm(station.getStcd(), startDate, endDate));
		}
		return zqExtremumList;
	}

	@Override
	public List<ZQExtremum> findMaxzAndTm(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		List<Station> stationList = stationService.findList();
		List<ZQExtremum> zqExtremumList = new ArrayList<ZQExtremum>();
		for (Station station : stationList) {
			zqExtremumList.add(this.stRiverRepository.findMaxzAndTm(station.getStcd(), startDate, endDate));
		}
		return zqExtremumList;
	}

	@Override
	public List<ZQExtremum> findMaxzAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findMaxzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinzAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findMinzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxzAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findMaxzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxzAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findMaxzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxzAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findMaxzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxzAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findMaxzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinzAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findMinzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinzAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findMinzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinzAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findMinzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinzAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findMinzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxqAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findMaxqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinqAndTm() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findMinqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxqAndTm(Date startDate, Date endDate) {
		return this.findMaxqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinqAndTm(Date startDate, Date endDate) {
		return this.findMinqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxqAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findMaxqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxqAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findMaxqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinqAndTmByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findMinqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinqAndTmByMonth(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findMinqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxqAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findMaxqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxqAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findMaxqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinqAndTmByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findMinqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinqAndTmByYear(Date tm) {
		Assert.notNull(tm, "date must not be null");
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.findMinqAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findAvgz() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findAvgzByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findAvgzByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findAvgz(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY, tm);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findAvgzByMonth(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findAvgzByYear(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR, tm);
		return this.stRiverRepository.findAvgz(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMaxzAndTm(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY, tm);
		return this.findMaxzAndTm(startDate, endDate);
	}

	@Override
	public List<ZQExtremum> findMinzAndTm(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY, tm);
		return this.findMinzAndTm(startDate, endDate);
	}

	@Override
	public List<RiverDay> find8hList() {
		return this.find8hList(new Date());
	}

	@Override
	public List<RiverDay> find8hList(Date tm) {
		Date startDate = DateUtils.get8hBeginDateTime(tm);
		Date endDate = DateUtils.get8hEndDateTime(tm);
		List<Station> stationList = stationService.findList();
		List<RiverDay> riverDayList = new ArrayList<RiverDay>();
		for (Station station : stationList) {
			riverDayList.addAll(this.stRiverRepository.find8hList(station.getStcd(), startDate, endDate));
		}
		return riverDayList;
	}

}