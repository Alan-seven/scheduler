package cn.xeonsoft.scheduler.sl.rain.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cn.xeonsoft.scheduler.sl.Constant;
import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.domain.PptnSt;
import cn.xeonsoft.scheduler.sl.water.domain.Station;
import cn.xeonsoft.scheduler.sl.rain.respository.PptnRtRepository;
import cn.xeonsoft.scheduler.sl.water.service.StationService;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

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
		return this.findList(DateInterval.DAY);
	}

	public List<PptnSt> findListByMonth() {
		return this.findList(DateInterval.MONTH);
	}

	public List<PptnSt> findListByYear() {
		return this.findList(DateInterval.YEAR);
	}
	public List<PptnSt> findList(DateInterval dateInterval) {
		return this.findList(dateInterval,new Date());
	}
	public List<PptnSt> findList(DateInterval dateInterval,Date tm) {
		Date startDate = DateUtils.get8hBeginDate(dateInterval,tm);
		Date endDate = DateUtils.get8hEndDate(dateInterval,tm);
		return this.findList(startDate, endDate);
	}
	public List<PptnSt> findList(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		return this.pptnRtRepository.findList(startDate, endDate);
	}

	//-----------------------------------------------------得到累计降雨量
	public List<Accp> findAccp() {
		return this.findAccp(DateInterval.DAY);
	}

	public List<Accp> findAccp(Date tm) {
		return this.findAccp(DateInterval.DAY, tm);
	}

	public List<Accp> findAccpByMonth() {
		return this.findAccpByMonth(new Date());
	}

	public List<Accp> findAccpByMonth(Date tm) {
		return this.findAccp(DateInterval.MONTH,tm);
	}

	public List<Accp> findAccpByYear() {
		return this.findAccp(DateInterval.YEAR);
	}

	public List<Accp> findAccp(DateInterval dateInterval) {
		return this.findAccp(dateInterval,new Date());
	}

	public List<Accp> findAccp(DateInterval dateInterval,Date tm) {
		Date startDate = DateUtils.get8hBeginDate(dateInterval,tm);
		Date endDate = DateUtils.get8hEndDate(dateInterval,tm);
		return this.findAccp(startDate, endDate);
	}
	public List<Accp> findAccp(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		return this.pptnRtRepository.findAccp(startDate, endDate);
	}

	//-----------------------------------------------------得到雨日
	public List<RainDays> findRainDays() {
		return this.findRainDays(DateInterval.DAY);
	}

	public List<RainDays> findRainDaysByMonth() {
		return this.findRainDays(DateInterval.MONTH);
	}

	public List<RainDays> findRainDaysByMonth(Date tm) {
		return this.findRainDays(DateInterval.MONTH,tm);
	}

	public List<RainDays> findRainDaysByYear() {
		return this.findRainDays(DateInterval.YEAR);
	}

	public List<RainDays> findRainDays(DateInterval dateInterval) {
		return this.findRainDays(dateInterval, new Date());
	}
	public List<RainDays> findRainDays(DateInterval dateInterval, Date tm) {
		Date startDate = DateUtils.get8hBeginDate(dateInterval,tm);
		Date endDate = DateUtils.get8hEndDate(dateInterval,tm);
		return this.findRainDays(startDate, endDate);
	}
	public List<RainDays> findRainDays(Date startDate, Date endDate) {
		Assert.notNull(startDate, "startDate must not be null");
		Assert.notNull(endDate, "endDate must not be null");
		return this.pptnRtRepository.findRainDay(Constant.STTDRCD_DAY, startDate, endDate);
	}

	//-----------------------------------------------------得到最大降雨量及对应时间
	public List<PptnExtremum> findMaxDrpAndTm() {
		return this.findMaxDrpAndTm(DateInterval.DAY);
	}

	public List<PptnExtremum> findMaxDrpAndTmByMonth() {
		return this.findMaxDrpAndTm(DateInterval.MONTH);
	}

	public List<PptnExtremum> findMaxDrpAndTmByMonth(Date tm) {
		return this.findMaxDrpAndTm(DateInterval.MONTH,tm);
	}

	public List<PptnExtremum> findMaxDrpAndTmByYear() {
		return this.findMaxDrpAndTm(DateInterval.YEAR);
	}


	public List<PptnExtremum> findMaxDrpAndTm(DateInterval dateInterval,Date tm) {
		Assert.notNull(dateInterval, "dateInterval must not be null");
		Date startDate = DateUtils.get8hBeginDate(dateInterval,tm);
		Date endDate = DateUtils.get8hEndDate(dateInterval,tm);
		return findMaxDrpAndTm(startDate,endDate);
	}

	public List<PptnExtremum> findMaxDrpAndTm(DateInterval dateInterval) {
		Assert.notNull(dateInterval, "dateInterval must not be null");
		Date startDate = DateUtils.get8hBeginDate(dateInterval);
		Date endDate = DateUtils.get8hEndDate(dateInterval);
		return findMaxDrpAndTm(startDate,endDate);
	}

	public List<PptnExtremum> findMaxDrpAndTm(Date startDate,Date endDate){
		List<PptnExtremum> pptnExtremumList = new ArrayList<PptnExtremum>();
		List<Station> stationList = stationService.findList();
		for (Station station : stationList) {
			pptnExtremumList.add(this.pptnRtRepository.findMaxDrpAndTm(Constant.STTDRCD_DAY,station.getStcd(), startDate, endDate));
		}
		return pptnExtremumList;
	}
}