package cn.xeonsoft.scheduler.erhai.run.service;


import cn.xeonsoft.scheduler.erhai.run.bo.*;
import cn.xeonsoft.scheduler.utils.DateInterval;

import java.util.Date;
import java.util.List;

public interface ErhaiRunService {
	Integer findCount(String sttdrcd,String tm);

	void saveOrUpdate(DateInterval dateInterval, Date tm);

	/**
	 * 缺少入湖 小时 统计数据
	 * @param sttdrcd
	 * @param tm
	 */
	public void saveOrUpdate(int sttdrcd,Date tm);

	public void saveOrUpdateDaliRain(DateInterval dateInterval, List<Dali> daliList);

	/**
	 * 河道外用水
	 */
	public void saveOrUpdateOuterUse(DateInterval dateInterval,List<OuterUse> outerUseList);

	public void saveOrUpdateXierhe(DateInterval dateInterval,List<Xierhe> xierheList);

	public void saveOrUpdateYerb(DateInterval dateInterval,List<Yerb> yerbList);

	public void saveOrUpdateErhai(DateInterval dateInterval,List<Erhai> erhaiList);

	public void saveOrUpdateInW(DateInterval dateInterval,List<InW> inWList);

	public List<Yerb> listYerb(DateInterval dateInterval,DateInterval dateInterval2,Date tm);

	public List<OuterUse> listOuterUse(DateInterval dateInterval, DateInterval dateInterval2, Date tm);

	public List<Dali> listDali(DateInterval dateInterval,DateInterval dateInterval2,Date tm);

	public List<Xierhe> listXierhe(DateInterval dateInterval,DateInterval dateInterval2,Date tm);

	public Erhai findErhai(Date tm);


}