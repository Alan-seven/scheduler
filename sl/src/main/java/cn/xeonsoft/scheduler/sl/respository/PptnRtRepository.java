package cn.xeonsoft.scheduler.sl.respository;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import cn.xeonsoft.scheduler.sl.bo.PptnExtremum;
import cn.xeonsoft.scheduler.sl.bo.RainDay;
import cn.xeonsoft.scheduler.sl.domain.Accp;
import cn.xeonsoft.scheduler.sl.domain.PptnSt;

/**
 * 雨量
 * 
 * @author wantwantxu
 *
 */
public interface PptnRtRepository {
	@Select("SELECT stcd,tm,z FROM ST_PPTN_R WHERE tm >= #{startDate} AND tm <= #{endDate}")
	List<PptnSt> findList(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Select("SELECT stcd,SUM(drp) as accp FROM ST_PPTN_R WHERE tm >= #{startDate} AND tm <= #{endDate} GROUP BY stcd")
	List<Accp> findAccp(@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	/**
	 * 从ST_PSTAT_R表的日雨量得出每月有多少天下雨
	 * 
	 * @param startDate
	 * @param endDate
	 * @return
	 */
	@Select("select stcd,count(1) as days from ST_PSTAT_R where sttdrcd = #{sttdrcd} and idtm >= #{startDate} and idtm <= #{endDate} and accp > 0 group by stcd")
	List<RainDay> findRainDay(@Param("sttdrcd") String sttdrcd,@Param("startDate") Date startDate, @Param("endDate") Date endDate);

	@Select("select top 1 stcd,idtm as tm,MAX(accp) as maxdrp from ST_PSTAT_R where "
			+ "STTDRCD = #{sttdrcd} and STCD = #{stcd} and IDTM >= #{startDate} and IDTM <= #{endDate}" + " group by stcd,idtm order by idtm desc")
	PptnExtremum findMaxDrpAndTm(@Param("sttdrcd") String sttdrcd,@Param("stcd") String stcd, @Param("startDate") Date startDate,
			@Param("endDate") Date endDate);
}