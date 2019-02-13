package cn.xeonsoft.scheduler.erhai.run.service;

import cn.xeonsoft.scheduler.erhai.run.bo.*;
import cn.xeonsoft.scheduler.erhai.run.respository.ErhaiRunRepository;
import cn.xeonsoft.scheduler.sl.water.bo.Extreme;
import cn.xeonsoft.scheduler.sl.water.domain.StatisDZ;
import cn.xeonsoft.scheduler.sl.water.respository.RiverDayRepository;
import cn.xeonsoft.scheduler.utils.DateInterval;
import cn.xeonsoft.scheduler.utils.DateUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;
import java.util.UUID;


@Component("erhaiRunSerivce")
@Transactional
public class ErhaiRunServiceImpl implements ErhaiRunService {
	public static final String ADDVCD_DALI = "532901000000";
	@Autowired
	private ErhaiRunRepository erhaiRunRepository;
	@Autowired
	private ErhaiService erhaiService;
	@Autowired
	private InWService inWService;
	@Autowired
	private StatisDistDrService statisDistDrService;
	@Autowired
	private StatisOuterUseService statisOuterUseService;
	@Autowired
	private StatisXierheService statisXierheService;
	@Autowired
	private StatisYerbService statisYerbService;

	@Override
	public Integer findCount(String sttdrcd, String tm) {
		return erhaiRunRepository.findCount(sttdrcd,tm);
	}

	@Override
	public void saveOrUpdate(DateInterval dateInterval, Date tm) {
		saveOrUpdate(DateInterval.HOUR.getType(),tm);
		saveOrUpdate(DateInterval.FIVEDAYS.getType(),tm);
		saveOrUpdate(DateInterval.TENDAYS.getType(),tm);
		saveOrUpdate(DateInterval.MONTH.getType(),tm);
		saveOrUpdate(DateInterval.YEAR.getType(),tm);
	}


	/**
	 * 缺少入湖 小时 统计数据
	 * @param sttdrcd
	 * @param tm
	 */
	public void saveOrUpdate(int sttdrcd,Date tm){
		Erhai erhai= findErhai(tm);
		List<Dali> daliList = null;
		List<OuterUse> outerUseList = null;
		List<Xierhe> xierheList = null;
		List<Yerb> yerbList = null;
		List<InW> inWList = null;
		DateInterval dimInterval = null;
		DateInterval typeInterval = null;
		if(DateInterval.HOUR.getType() == sttdrcd){
			dimInterval = DateInterval.DAY;
			typeInterval = DateInterval.HOUR;
		}else if(DateInterval.DAY.getType() == sttdrcd){
			dimInterval = DateInterval.MONTH;
			typeInterval = DateInterval.DAY;
		}else if(DateInterval.FIVEDAYS.getType() == sttdrcd){
			dimInterval = DateInterval.MONTH;
			typeInterval = DateInterval.FIVEDAYS;
		}else if(DateInterval.TENDAYS.getType() ==sttdrcd){
			dimInterval = DateInterval.YEAR;
			typeInterval = DateInterval.TENDAYS;
		}else if(DateInterval.MONTH.getType() ==sttdrcd){
			String year = DateUtils.getYear(tm);
			String month = DateUtils.getMonth(tm);
			int yr = Integer.parseInt(year);
			int mnth =  Integer.parseInt(month);
			inWList = inWService.listYearOrMonth(yr,mnth);
			dimInterval = DateInterval.YEAR;
			typeInterval = DateInterval.MONTH;
		}else if(DateInterval.YEAR.getType() ==sttdrcd){
			dimInterval = DateInterval.YEAR;
			typeInterval = DateInterval.YEAR;
		}
		daliList = listDali(dimInterval,typeInterval,tm);
		outerUseList = listOuterUse(dimInterval,typeInterval,tm);
		xierheList = listXierhe(dimInterval,typeInterval,tm);
		yerbList = listYerb(dimInterval,typeInterval,tm);
		//保存
		saveOrUpdateDaliRain(typeInterval,daliList);
		saveOrUpdateOuterUse(typeInterval,outerUseList);
		saveOrUpdateXierhe(typeInterval,xierheList);
		saveOrUpdateYerb(typeInterval,yerbList);
		//saveOrUpdateInW(typeInterval,inWList);
	}

	public void saveOrUpdateDaliRain(DateInterval dateInterval,List<Dali> daliList){
		//大理降雨
		for(Dali dali:daliList){
			String sttdrcd = dateInterval.getType()+"";
			Integer cnt = erhaiRunRepository.findCount(sttdrcd,dali.getTm());
			if(cnt > 0){
				erhaiRunRepository.updateDaliRain(sttdrcd,dali.getTm(),dali.getRain());
			}else{
				erhaiRunRepository.saveDaliRain(UUID.randomUUID().toString(),sttdrcd,dali.getTm(),dali.getRain());
			}
		}
	}

	/**
	 * 河道外用水
	 */
	public void saveOrUpdateOuterUse(DateInterval dateInterval,List<OuterUse> outerUseList){
		for(OuterUse outerUse : outerUseList) {
			if (null != outerUse) {
				Float farming = outerUse.getFarming();
				Float industry = outerUse.getIndustry();
				Float life = outerUse.getLife();
				Float sum = sum(farming,industry,life);
				String sttdrcd = dateInterval.getType()+"";
				Integer cnt = erhaiRunRepository.findCount(sttdrcd,outerUse.getTm());
				if(cnt > 0) {
					erhaiRunRepository.updateOutuseW(dateInterval.getType() + "", outerUse.getTm(), sum);
				}else{
					erhaiRunRepository.saveOutuseW(UUID.randomUUID().toString(),dateInterval.getType() + "", outerUse.getTm(), sum);
				}
			}
		}
	}

	public Float sum(Float f1,Float f2,Float f3){
		Float sum = null;
		if (null == f1 && null != f2 && null != f3) {
			sum = f2 + f3;
		} else if (null != f1 && null != f2 && null == f3) {
			sum = f1 + f2;
		} else if (null != f1 && null == f2 && null != f3) {
			sum = f1 + f3;
		} else if (null != f1 && null != f2 && null != f3) {
			sum = f1 + f2 + f3;
		}
		return sum;
	}

	public void saveOrUpdateXierhe(DateInterval dateInterval,List<Xierhe> xierheList){
		//西洱河总出水量
		for(Xierhe xierhe : xierheList) {
			if (null != xierhe) {
				Float electricityW = xierhe.getElectricityW();
				Float emissionW = xierhe.getEmissionW();
				Float sluiceW = xierhe.getSluiceW();
				Float sum = sum(electricityW,emissionW,sluiceW);
				String sttdrcd = dateInterval.getType()+"";
				Integer cnt = erhaiRunRepository.findCount(sttdrcd,xierhe.getTm());
				if(cnt > 0) {
					erhaiRunRepository.updateOutuseW(dateInterval.getType() + "", xierhe.getTm(), sum);
				}else{
					erhaiRunRepository.saveOutuseW(UUID.randomUUID().toString(),dateInterval.getType() + "", xierhe.getTm(), sum);
				}
			}
		}
	}

	public void saveOrUpdateYerb(DateInterval dateInterval,List<Yerb> yerbList){
		for(Yerb yerb : yerbList){
			String sttdrcd = dateInterval.getType()+"";
			Integer cnt = erhaiRunRepository.findCount(sttdrcd,yerb.getTm());
			if(cnt > 0) {
				erhaiRunRepository.updateOutuseW(dateInterval.getType() + "", yerb.getTm(), yerb.getW());
			}else{
				erhaiRunRepository.saveOutuseW(UUID.randomUUID().toString(),dateInterval.getType() + "",yerb.getTm(), yerb.getW());
			}
		}
	}

	public void saveOrUpdateErhai(DateInterval dateInterval,List<Erhai> erhaiList){
		for(Erhai erhai : erhaiList){
			String sttdrcd = dateInterval.getType()+"";
			Integer cnt = erhaiRunRepository.findCount(sttdrcd,erhai.getTm());
			if(cnt > 0) {
				erhaiRunRepository.saveFirstDayZ(UUID.randomUUID().toString(),dateInterval.getType() + "", erhai.getTm(), erhai.getFirstZ());
				erhaiRunRepository.saveLastDayZ(UUID.randomUUID().toString(),dateInterval.getType() + "", erhai.getTm(), erhai.getLastZ());
			}else{
				erhaiRunRepository.updateFirstDayZ(dateInterval.getType() + "", erhai.getTm(), erhai.getFirstZ());
				erhaiRunRepository.updateLastDayZ(dateInterval.getType() + "", erhai.getTm(), erhai.getLastZ());
			}
		}
	}

	public void saveOrUpdateInW(DateInterval dateInterval,List<InW> inWList){
		for(InW inW : inWList) {
			if (null != inW) {
				String sttdrcd = dateInterval.getType()+"";
				Integer cnt = erhaiRunRepository.findCount(sttdrcd,inW.getTm());
				if(cnt > 0) {
					erhaiRunRepository.saveCleanInw(UUID.randomUUID().toString(),dateInterval.getType()+"",inW.getTm(),inW.getFlow());
					erhaiRunRepository.saveNaturalInW(UUID.randomUUID().toString(),dateInterval.getType()+"",inW.getTm(),inW.getFlow());
				}else{
					erhaiRunRepository.updateCleanInw(dateInterval.getType()+"",inW.getTm(),inW.getFlow());
					erhaiRunRepository.updateNaturalInW(dateInterval.getType()+"",inW.getTm(),inW.getW());
				}
			}
		}
	}
	public List<Yerb> listYerb(DateInterval dateInterval,DateInterval dateInterval2,Date tm){
		Date startDate = DateUtils.getBeginDate(dateInterval,tm);
		Date endDate = DateUtils.getEndDate(dateInterval,tm);
		return statisYerbService.list(dateInterval2.getType()+"",startDate,endDate);
	}
	public List<OuterUse> listOuterUse(DateInterval dateInterval,DateInterval dateInterval2,Date tm){
		Date startDate = DateUtils.getBeginDate(dateInterval,tm);
		Date endDate = DateUtils.getEndDate(dateInterval,tm);
		return statisOuterUseService.list(dateInterval2.getType()+"",startDate,endDate);
	}

	public List<Dali> listDali(DateInterval dateInterval,DateInterval dateInterval2,Date tm){
		Date startDate = DateUtils.getBeginDate(dateInterval,tm);
		Date endDate = DateUtils.getEndDate(dateInterval,tm);
		return statisDistDrService.list(dateInterval2.getType()+"",startDate,endDate,ADDVCD_DALI);
	}

	public List<Xierhe> listXierhe(DateInterval dateInterval,DateInterval dateInterval2,Date tm){
		Date startDate = DateUtils.getBeginDate(dateInterval,tm);
		Date endDate = DateUtils.getEndDate(dateInterval,tm);
		return statisXierheService.list(dateInterval2.getType()+"",startDate,endDate);
	}

	public Erhai findErhai(Date tm){
		Date startDate = DateUtils.getBeginDate(DateInterval.MONTH,tm);
		Date endDate = DateUtils.getEndDate(DateInterval.MONTH,tm);
		Float firstZ = erhaiService.findFirstZ(DateInterval.DAY.getType()+"",startDate,endDate);
		Float lastZ = erhaiService.findLastZ(DateInterval.DAY.getType()+"",startDate,endDate);
		Erhai erhai = new Erhai();
		erhai.setFirstZ(firstZ);
		erhai.setLastZ(lastZ);
		return erhai;
	}



}
