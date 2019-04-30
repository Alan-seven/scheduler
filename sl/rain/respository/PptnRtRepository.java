package cn.xeonsoft.scheduler.sl.rain.respository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.xeonsoft.scheduler.sl.rain.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.rain.bo.RainDays;
import cn.xeonsoft.scheduler.sl.rain.domain.Accp;
import cn.xeonsoft.scheduler.sl.rain.domain.PptnSt;

/**
 * 实时雨量数据操作
 *
 * @author wantwantxu
 *
 */
public interface PptnRtRepository {
	@Select("SELECT stcd,tm,z FROM ST_PPTN_R WHERE tm >= #{startDate} AND tm <= #{endDate}")
	List<PptnSt> findList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	//@Select("SELECT stcd,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} AND tm <= #{endDate} GROUP BY stcd")
	@Select("SELECT stcd,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} AND tm < #{endDate} GROUP BY stcd")
	List<Accp> findAccp(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Select("SELECT stcd,CONVERT(CHAR(13),tm,20)+':00:00' as tm,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} and tm < #{endDate} group by stcd,CONVERT(CHAR(13),tm,20)")
	List<Accp> findAccpByHour(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	//解决2019-01-01 08:00:00 -- 2019-02-01 08:00:00 出现的月份不同统计问题
	//@Select("SELECT stcd,CONVERT(CHAR(7),tm,23)+'-01 00:00:00' as tm,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} and tm < #{endDate} group by stcd,CONVERT(CHAR(7),tm,23)")
	@Select("SELECT stcd,min(tm) as tm ,sum(accp) as accp from ( SELECT stcd,CONVERT(CHAR(7),tm,23)+'-01 00:00:00' as tm,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} and tm < #{endDate} group by stcd,CONVERT(CHAR(7),tm,23) ) a group by stcd ")
	List<Accp> findAccpByMonth(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	//决2019-01-01 08:00:00 -- 2020-01-01 08:00:00 出现的年份不同统计问题
	//@Select("SELECT stcd,CONVERT(CHAR(4),TM,23)+'-01-01 00:00:00' as tm,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} and tm < #{endDate} group by stcd,CONVERT(CHAR(4),TM,23)")
	@Select(" select stcd,min(tm) as tm , sum(accp) as accp from ( SELECT stcd,CONVERT(CHAR(4),TM,23)+'-01-01 00:00:00' as tm,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} and tm < #{endDate} group by stcd,CONVERT(CHAR(4),TM,23) ) a group by stcd ")
	List<Accp> findAccpByYear(@Param("startDate") Date startDate, @Param("endDate") Date endDate);
	/**
	 * 从ST_PSTAT_R表的日雨量得出每月有多少天下雨
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("select stcd,count(1) as days from ST_PSTAT_R where sttdrcd = #{sttdrcd} and idtm >= #{startDate} and idtm < #{endDate} and accp > 0 group by stcd")
	List<RainDays> findRainDay(@Param("sttdrcd") String sttdrcd, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Select("select top 1 stcd,idtm as tm,MAX(accp) as maxdrp from ST_PSTAT_R where "
			+ "STTDRCD = #{sttdrcd} and STCD = #{stcd} and IDTM >= #{startDate} and IDTM < #{endDate}" + " group by stcd,idtm order by maxdrp desc")
	PptnExtremum findMaxDrpAndTm(@Param("sttdrcd") String sttdrcd,@Param("stcd") String stcd, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);




	//------------------------------------------------------------------------东南西北分区统计-------------------------------------------------
	/**
	 * 得到小时级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(13),a.tm,20)+':00:00' as tm,SUM(a.drp) as accp FROM ST_PPTN_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm < #{endDate} group by CONVERT(CHAR(13),a.tm,20)")
	List<Accp> findHourSumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到日级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	//@Select("SELECT CONVERT(CHAR(10),a.TM,23)+' 00:00:00' as tm,SUM(a.drp) as accp FROM ST_PPTN_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm <= #{endDate} group by CONVERT(CHAR(10),a.TM,23)")
	@Select(" SELECT MIN(tm) as tm,sum(accp) as accp from ( SELECT CONVERT(CHAR(10),a.TM,23)+' 00:00:00' as tm,SUM(a.drp) as accp FROM ST_PPTN_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm < #{endDate} group by CONVERT(CHAR(10),a.TM,23) ) a ")
	List<Accp> findDaySumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到月级的合计
	 * @param gp
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(7),tm,23)+'-01 00:00:00' as tm,SUM(a.drp) as accp FROM ST_PPTN_R a,STATION_EXTEND b WHERE a.stcd = b.stcd and b.gp = #{gp} and a.tm >= #{startDate} and a.tm < #{endDate} group by CONVERT(CHAR(7),tm,23)")
	List<Accp> findMonthSumByGP(@Param("gp") String gp, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到小时级的大理降雨合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT CONVERT(CHAR(13),tm,20)+':00:00' as tm,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} and tm < #{endDate} group by CONVERT(CHAR(13),tm,20)")
	List<Accp> findDistDrAccpByHour(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 得到日/月 级的大理降雨合计
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("SELECT SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} AND tm < #{endDate} ")
	List<Accp> findPeriodAccp(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	}