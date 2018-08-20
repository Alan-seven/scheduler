package cn.xeonsoft.scheduler.sl.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.bo.RainDay;
import cn.xeonsoft.scheduler.sl.domain.Accp;
import cn.xeonsoft.scheduler.sl.domain.PptnSt;
import cn.xeonsoft.scheduler.sl.domain.Station;
import cn.xeonsoft.scheduler.sl.respository.PptnRtRepository;
import cn.xeonsoft.scheduler.sl.utils.DateInterval;
import cn.xeonsoft.scheduler.sl.utils.DateUtils;

@Component("pptnStService")
@Transactional
class PptnRtServiceImpl implements PptnRtService {

	@Autowired
	private StationService stationService;

	private final PptnRtRepository pptnRtRepository;

	public PptnRtServiceImpl(PptnRtRepository pptnRtRepository) {
		this.pptnRtRepository = pptnRtRepository;
	}

	public List<PptnSt> findList() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.get8hEndDate(DateInterval.DAY);
		return this.findList(startDate,endDate);
	}

	public List<PptnSt> findListByMonth() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.get8hEndDate(DateInterval.MONTH);
		return this.findList(startDate,endDate);
	}

	public List<PptnSt> findListByYear() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.get8hEndDate(DateInterval.YEAR);
		return this.findList(startDate,endDate);
	}

	@Override
	public List<PptnSt> findList(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		return this.pptnRtRepository.findList(startDate, endDate);
	}

	public List<Accp> findAccp() {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY);
		return this.findAccp(startDate, endDate);
	}

	public List<Accp> findAccpByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findAccp(startDate, endDate);
	}

	@Override
	public List<Accp> findAccpByMonth(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findAccp(startDate, endDate);
	}

	public List<Accp> findAccpByYear() {
		Date startDate = DateUtils.getBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.getEndDate(DateInterval.YEAR);
		return this.findAccp(startDate, endDate);
	}

	@Override
	public List<Accp> findAccp(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.DAY, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.DAY, tm);
		return this.findAccp(startDate, endDate);
	}

	@Override
	public List<Accp> findAccp(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		return this.pptnRtRepository.findAccp(startDate, endDate);
	}

	public List<RainDay> findRainDay() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.get8hEndDate(DateInterval.DAY);
		return this.findRainDay(startDate, endDate);
	}

	public List<RainDay> findRainDayByMonth() {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH);
		return this.findRainDay(startDate, endDate);
	}

	@Override
	public List<RainDay> findRainDayByMonth(Date tm) {
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH, tm);
		return this.findRainDay(startDate, endDate);
	}

	public List<RainDay> findRainDayByYear() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.get8hEndDate(DateInterval.YEAR);
		return this.findRainDay(startDate, endDate);
	}

	@Override
	public List<RainDay> findRainDay(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		return this.pptnRtRepository.findRainDay(Constant.STTDRCD_DAY, startDate, endDate);
	}

	public List<PptnExtremum> findMaxDrpAndTm() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.DAY);
		Date endDate = DateUtils.get8hEndDate(DateInterval.DAY);
		return this.findMaxDrpAndTm(startDate, endDate);
	}

	public List<PptnExtremum> findMaxDrpAndTmByMonth() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.MONTH);
		Date endDate = DateUtils.get8hEndDate(DateInterval.MONTH);
		return this.findMaxDrpAndTm(startDate, endDate);
	}

	public List<PptnExtremum> findMaxDrpAndTmByYear() {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.YEAR);
		Date endDate = DateUtils.get8hEndDate(DateInterval.YEAR);
		return this.findMaxDrpAndTm(startDate, endDate);
	}

	@Override
	public List<PptnExtremum> findMaxDrpAndTm(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		List<PptnExtremum> pptnExtremumList = new ArrayList<PptnExtremum>();
		List<Station> stationList = stationService.findList();
		for (Station station : stationList) {
			pptnExtremumList.add(this.pptnRtRepository.findMaxDrpAndTm(Constant.STTDRCD_DAY,station.getStcd(), startDate, endDate));
		}
		return pptnExtremumList;
	}

	@Override
	public List<PptnExtremum> findMaxDrpAndTmByMonth(Date tm) {
		Date startDate = DateUtils.get8hBeginDate(DateInterval.MONTH, tm);
		Date endDate = DateUtils.get8hEndDate(DateInterval.MONTH, tm);
		return this.findMaxDrpAndTm(startDate, endDate);
	}
}